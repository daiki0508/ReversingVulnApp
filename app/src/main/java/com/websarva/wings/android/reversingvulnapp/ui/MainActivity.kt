package com.websarva.wings.android.reversingvulnapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.websarva.wings.android.reversingvulnapp.R
import com.websarva.wings.android.reversingvulnapp.databinding.ActivityMainBinding
import com.websarva.wings.android.reversingvulnapp.ui.fragment.top.TopFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        // fragmentの起動
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, TopFragment()).commit()
    }
}