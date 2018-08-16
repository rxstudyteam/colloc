package android.net

import android.log.Log
import android.log.Log.e
import android.log.Log.i
import android.log.Log.p
import android.log.Log.w
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.http.HttpHeaders
import okio.Buffer
import okio.GzipSource
import java.io.EOFException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

class Logger : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (!Net.LOG) {
            return chain.proceed(request)
        }

        var req = StringBuilder()
        if (Net.LOG && (Net._OUT_1 || Net._OUT_2))
            req.append("--> " + request.method() + ":" + request.url())

        if (Net.LOG && Net._OUT_H) {
            val headers = request.headers()
            for (i in 0..(headers.size() - 1)) {
                if (req.isNotEmpty()) req.append("\n")
                req.append(headers.name(i) + ": " + headers.value(i))
            }
        }

        val body = request.body()
        if (Net.LOG && Net._OUT_2 && body != null && !bodyHasUnknownEncoding(request.headers())) {
            val buffer = Buffer()
            body.writeTo(buffer)
            val contentType = body.contentType()
            var charset: Charset? = UTF8
            if (contentType != null)
                charset = contentType.charset(UTF8)


            if (isPlaintext(buffer)) {
                if (req.isNotEmpty()) req.append("\n")
                req.append(buffer.readString(charset!!))
                if (req.isNotEmpty()) req.append("\n")
                req.append("--> END " + request.method() + " (" + body.contentLength())
            } else {
                if (req.isNotEmpty()) req.append("\n")
                req.append("--> END " + request.method() + " (binary " + body.contentLength())
            }
        }
        e(req)

        val startNs = System.nanoTime()
        val response: Response
        try {
            response = chain.proceed(request)
        } catch (e: Exception) {
            w("<-- HTTP FAILED: $e")
            throw e
        }


        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)

        val responseBody = response.body()
        val contentLength = responseBody!!.contentLength()

        var res = StringBuilder()
        if (Net._IN_1)
            res.append("<-- " + response.code() + (if (response.message().isEmpty()) "" else ' ' + response.message()) + ' ' + response.request().url() + " (" + tookMs + "ms)")

        if (Net._IN_H) {
            if (res.isNotEmpty()) res.append("\n")
            val headers = response.headers()
            for (i in 0..(headers.size() - 1))
                res.append(headers.name(i) + ": " + headers.value(i))
        }

        if (Net._IN_2 && HttpHeaders.hasBody(response) && !bodyHasUnknownEncoding(response.headers())) {
            val source = responseBody.source()
            source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
            var buffer = source.buffer()

            var gzippedLength: Long? = null
            val headers = response.headers()
            if ("gzip".equals(headers.get("Content-Encoding"), ignoreCase = true)) {
                gzippedLength = buffer.size()
                var gzippedResponseBody: GzipSource? = null
                try {
                    gzippedResponseBody = GzipSource(buffer.clone())
                    buffer = Buffer()
                    buffer.writeAll(gzippedResponseBody)
                } finally {
                    gzippedResponseBody?.close()
                }
            }

            var charset: Charset? = UTF8
            val contentType = responseBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(UTF8)
            }

            if (!isPlaintext(buffer)) {
                if (res.isNotEmpty()) res.append("\n")
                res.append("<-- END HTTP (binary " + buffer.size() + "-byte body omitted)")
            }

            if (contentLength != 0L) {
                if (res.isNotEmpty()) res.append("\n")
                res.append(buffer.clone().readString(charset!!))
            }

            if (gzippedLength != null) {
                if (res.isNotEmpty()) res.append("\n")
                res.append("<-- END HTTP (" + buffer.size() + "-byte, " + gzippedLength + "-gzipped-byte body)")
            } else {
                if (res.isNotEmpty()) res.append("\n")
                res.append("<-- END HTTP (" + buffer.size() + "-byte body)")
            }
        }

        p(Log.INFO.takeIf { response.isSuccessful } ?: Log.WARN, res)

        return response
    }

    private fun bodyHasUnknownEncoding(headers: Headers): Boolean {
        val contentEncoding = headers.get("Content-Encoding")
        return (contentEncoding != null
                && !contentEncoding.equals("identity", ignoreCase = true)
                && !contentEncoding.equals("gzip", ignoreCase = true))
    }

    companion object {
        private val UTF8 = Charset.forName("UTF-8")

        /**
         * Returns true if the body in question probably contains human readable text. Uses a small sample
         * of code points to detect unicode control characters commonly used in binary file signatures.
         */
        internal fun isPlaintext(buffer: Buffer): Boolean {
            try {
                val prefix = Buffer()
                val byteCount = if (buffer.size() < 64) buffer.size() else 64
                buffer.copyTo(prefix, 0, byteCount)
                for (i in 0..15) {
                    if (prefix.exhausted()) {
                        break
                    }
                    val codePoint = prefix.readUtf8CodePoint()
                    if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                        return false
                    }
                }
                return true
            } catch (e: EOFException) {
                return false // Truncated UTF-8 sequence.
            }
        }
    }
}
