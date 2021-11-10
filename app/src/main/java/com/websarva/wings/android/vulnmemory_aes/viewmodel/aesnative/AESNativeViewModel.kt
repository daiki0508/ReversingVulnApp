package com.websarva.wings.android.vulnmemory_aes.viewmodel.aesnative

import android.app.Application
import android.text.Editable
import android.util.Base64
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.websarva.wings.android.vulnmemory_aes.databinding.FragmentAesnativeBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.FileOutputStream
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject
import javax.security.auth.Destroyable

@HiltViewModel
class AESNativeViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    companion object{
        init {
            System.loadLibrary("main")
        }
    }

    private var _encryptData: String? = null
    val encryptData
    get() = _encryptData!!
    private var _iv: String? = null
    val iv
    get() = _iv!!
    private val _flag = MutableLiveData<Boolean>()
    val flag: LiveData<Boolean> = Transformations.map(_flag){
        it
    }

    fun encrypt(binding: FragmentAesnativeBinding){
        Log.d("encrypt", "called")

        var secretKey: SecretKey? = null
        var editable: Editable? = null
        try {
            secretKey = SecretKeySpec(getAESData().toByteArray(), "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            editable = Editable.Factory.getInstance().newEditable(binding.edText.text)
            _encryptData = Base64.encodeToString(cipher.doFinal(editable.toString().toByteArray()), Base64.DEFAULT)
            _iv = Base64.encodeToString(cipher.iv, Base64.DEFAULT)
        }finally {
            editable?.clear()
            _flag.value = true
        }
    }

    private external fun getAESData(): String
}