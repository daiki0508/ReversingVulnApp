package com.websarva.wings.android.vulnmemory_aes.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.websarva.wings.android.vulnmemory_aes.databinding.FragmentAesnativeBinding
import com.websarva.wings.android.vulnmemory_aes.viewmodel.aesnative.AESNativeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AESNativeFragment: Fragment() {
    private var _binding: FragmentAesnativeBinding? = null
    private val binding
    get() = _binding!!

    private val viewModel: AESNativeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAesnativeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ボタンタップ時の処理
        binding.button.setOnClickListener {
            if (!binding.edText.text.isNullOrBlank())
                viewModel.encrypt(binding)
        }

        // ivのobserver
        with(viewModel){
            flag.observe(this@AESNativeFragment.viewLifecycleOwner, {
                binding.tvResult.text = "encrypted: $encryptData\niv: $iv"
            })
        }
    }
}