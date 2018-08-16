package android.net

import android.content.Context
import android.log.Log

import java.io.InputStream
import java.security.KeyStore
import java.security.cert.Certificate
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManagerFactory

/**
 * <pre>
 * &lt;uses-permission android:name="android.permission.INTERNET" />
</pre> *
 */
object Trust {
    fun SELF_TRUST_HTTPS_CERTIFICATES(context: Context, resid: Int) {
        try {
            // Load CAs from an InputStream(could be from a resource or ByteArrayInputStream or ...)
            val cf = CertificateFactory.getInstance("X.509")
            // From https://www.washington.edu/itconnect/security/ca/load-der.crt
            //InputStream caInput = new BufferedInputStream(new FileInputStream("load-der.crt"));
            val caInput = context.resources.openRawResource(resid) //R.raw.tft : tft.cer
            val ca = cf.generateCertificate(caInput)
            Log.i("ca=" + (ca as X509Certificate).subjectDN);
            caInput.close()

            // Create a KeyStore containing our trusted CAs
            val keyStoreType = KeyStore.getDefaultType()
            val keyStore = KeyStore.getInstance(keyStoreType)
            keyStore.load(null, null)
            keyStore.setCertificateEntry("ca", ca)

            // Create a TrustManager that trusts the CAs in our KeyStore
            val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
            val tmf = TrustManagerFactory.getInstance(tmfAlgorithm)
            tmf.init(keyStore)

            // Create an SSLContext that uses our TrustManager
            val sc = SSLContext.getInstance("TLS")
            sc.init(null, tmf.trustManagers, null)

            HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
            HttpsURLConnection.setDefaultHostnameVerifier { urlHostName, session -> true }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
