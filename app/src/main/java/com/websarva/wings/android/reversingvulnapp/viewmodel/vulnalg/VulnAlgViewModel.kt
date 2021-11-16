package com.websarva.wings.android.reversingvulnapp.viewmodel.vulnalg

import android.app.Application
import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.FileOutputStream
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.inject.Inject
import javax.security.auth.Destroyable
import kotlin.experimental.xor

@HiltViewModel
class VulnAlgViewModel @Inject constructor(application: Application): AndroidViewModel(application) {
    private val _decryptBytes = MutableLiveData<ByteArray>()
    val decryptBytes: LiveData<ByteArray> = Transformations.map(_decryptBytes){
        it
    }

    private val somePublicString = "E/AndroidRuntime: FATAL EXCEPTION: main Process: " +
            "com.websarva.wings.android.androidkeystoresample_kotlin, PID: 6147 " +
            "java.lang.RuntimeException"
    private val nonSecret: ByteArray = somePublicString.toByteArray(Charsets.ISO_8859_1)

    companion object{
        init {
            System.loadLibrary("main")
        }
    }

    fun decrypt(){
        Log.d("decrypt", "called")

        var secretKey: SecretKey? = null
        var encryptData: ByteArray? = null

        try {
            // 複合化処理開始
            secretKey = SecureSecretKey(xorDecode(getAESData(0)).toByteArray(), "AES")
            val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding")
            cipher.init(Cipher.DECRYPT_MODE, secretKey)
            encryptData = cipher.doFinal(Base64.decode(getAESData(1), Base64.DEFAULT))
            _decryptBytes.value = encryptData!!
        }finally {
            // メモリ内から機密情報を削除
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                secretKey?.destroy()
            }else{
                for (i in secretKey!!.encoded.indices){
                    secretKey.encoded[i] = nonSecret[i % nonSecret.size]
                }
                val out = FileOutputStream("dev/null")
                out.write(secretKey.encoded)
                out.flush()
                out.close()
            }

            if (encryptData != null){
                for (i in encryptData.indices){
                    encryptData[i] = nonSecret[i % nonSecret.size]
                }
            }
        }
    }

    private external fun getAESData(flag: Int): String
}

private fun xorDecode(keyPass: String): String{
    val xorDataByte = String(Base64.decode(getXorData(), Base64.DEFAULT)).toByteArray(Charsets.US_ASCII)
    var retValue = ""

    for (i in xorDataByte.indices){
        retValue += String(byteArrayOf((String(Base64.decode(keyPass, Base64.DEFAULT)).toByteArray(Charsets.US_ASCII)[i].xor(xorDataByte[i]))), Charsets.US_ASCII)
    }

    return retValue
}

private external fun getXorData(): String

private class SecureSecretKey(key2: ByteArray, algorithm: String): SecretKey, Destroyable {
    private var key: ByteArray?
    private val algorithm: String

    override fun getAlgorithm(): String {
        return algorithm
    }

    override fun getFormat(): String {
        return "RAW"
    }

    override fun getEncoded(): ByteArray {
        if (key == null){
            throw NullPointerException()
        }
        return key!!.clone()
    }

    override fun destroy() {
        if (isDestroyed){
            return
        }
        val dummy = "E/AndroidRuntime: FATAL EXCEPTION: main Process: " +
                "com.websarva.wings.android.androidkeystoresample_kotlin, PID: 6147 " +
                "java.lang.RuntimeException"
        val nonSecret: ByteArray = dummy.toByteArray(Charsets.ISO_8859_1)

        for (i in key!!.indices){
            key!![i] = nonSecret[i % nonSecret.size]
        }
        val out= FileOutputStream("/dev/null")
        out.write(key)
        out.flush()
        out.close()
        key = null
        System.gc()
    }

    override fun isDestroyed(): Boolean {
        return key == null
    }

    init {
        this.key = key2.clone()
        this.algorithm = algorithm
        Arrays.fill(key2, 0)
    }
}