package android.net

import android.log.Log
import android.os.Environment

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter

class Pojo(private val json: String, private val name: String = Exception().stackTrace[1].className) {
    private lateinit var pojo: String

    init {
        gen()
    }

    fun gen(): Pojo {
        val sb = StringBuilder()
        try {
            val jo = JSONObject(json)
            sb.append("public Data data;")
            generateClass(sb, "Data", jo)
            sb.insert("public Data data;public ".length, " static ")
        } catch (ee: JSONException) {
            ee.printStackTrace()
        }

        this.pojo = sb.toString()
        return this
    }

    fun toLog(): Pojo {
        Log.e("generate pojo class for " + name, "START>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        println(pojo)
        Log.e("generate pojo class for " + name, "END  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
        return this
    }

    fun toFile(): Pojo {
        try {
            val dirPath = File(Environment.getExternalStorageDirectory(), "/_flog")
            if (!dirPath.exists())
                dirPath.mkdirs()
            val pojo = File(dirPath, "$name.java")
            if (!pojo.exists()) {
                try {
                    pojo.createNewFile()
                } catch (e: IOException) {
                }

            }
            val writer = OutputStreamWriter(FileOutputStream(pojo))
            writer.write(this.pojo)
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return this
    }

    override fun toString(): String {
        return pojo
    }

    private fun generateClass(sb: StringBuilder, name: String, value: Any) {
        sb.append("public class $name{")
        val jo = value as JSONObject
        val it = jo.keys()
        while (it.hasNext()) {
            val key = it.next() as String
            val o = jo.opt(key)
            if (o is String)
                sb.append("public String $key;")
            if (o is Int)
                sb.append("public int $key;")
            if (o is Long)
                sb.append("public long $key;")
            if (o is Double)
                sb.append("public double $key;")
            if (o is JSONArray)
                generateArray(sb, key, o)
            if (o is JSONObject) {
                sb.append("public $key $key;")
                generateClass(sb, key, o)
            }
        }
        sb.append("}")
    }

    private fun generateArray(sb: StringBuilder, name: String, value: Any) {
        val ja = value as JSONArray
        val o = ja.opt(0)
        if (o is String)
            sb.append("public List<String> $name;")
        if (o is Int)
            sb.append("public List<Integer> $name;")
        if (o is Long)
            sb.append("public List<Long> $name;")
        if (o is Double)
            sb.append("public List<Double> $name;")
        if (o is JSONArray)
            generateArray(sb, name, o)
        if (o is JSONObject) {
            sb.append("public List<$name> $name;")
            generateClass(sb, name, o)
        }
    }
}