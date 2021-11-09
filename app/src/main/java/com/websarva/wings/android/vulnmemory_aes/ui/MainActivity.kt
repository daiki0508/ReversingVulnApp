package com.websarva.wings.android.vulnmemory_aes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.websarva.wings.android.vulnmemory_aes.R
import com.websarva.wings.android.vulnmemory_aes.databinding.ActivityMainBinding
import com.websarva.wings.android.vulnmemory_aes.ui.fragment.AESNativeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        // fragmentの起動
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, AESNativeFragment()).commit()
    }
}