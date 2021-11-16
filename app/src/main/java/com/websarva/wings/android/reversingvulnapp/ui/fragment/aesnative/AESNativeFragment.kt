package com.websarva.wings.android.reversingvulnapp.ui.fragment.aesnative

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.websarva.wings.android.reversingvulnapp.R
import com.websarva.wings.android.reversingvulnapp.databinding.FragmentAesnativeBinding
import com.websarva.wings.android.reversingvulnapp.ui.MainActivity
import com.websarva.wings.android.reversingvulnapp.viewmodel.aesnative.AESNativeViewModel
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

        // backButtonタップ時の処理
        requireActivity().onBackPressedDispatcher.addCallback(this){
            with(Intent(activity, MainActivity::class.java)){
                activity?.finish()
                startActivity(this)
                activity?.overridePendingTransition(R.anim.fragment_pop_enter_anim, R.anim.fragment_pop_exit_anim)
            }
        }

        // ボタンタップ時の処理
        binding.button.setOnClickListener {
            if (!binding.edText.text.isNullOrBlank())
                viewModel.encrypt(binding)
        }

        // flagのobserver
        with(viewModel){
            flag.observe(this@AESNativeFragment.viewLifecycleOwner, {
                binding.tvResult.text = "encrypted: $encryptData\niv: $iv"
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}