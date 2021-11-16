package com.websarva.wings.android.vulnmemory_aes.ui.fragment.vulnalg

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.websarva.wings.android.vulnmemory_aes.R
import com.websarva.wings.android.vulnmemory_aes.databinding.FragmentAesalgBinding
import com.websarva.wings.android.vulnmemory_aes.ui.MainActivity
import com.websarva.wings.android.vulnmemory_aes.viewmodel.vulnalg.VulnAlgViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AESAlgFragment: Fragment() {
    private var _binding: FragmentAesalgBinding? = null
    private val binding
    get() = _binding!!

    private val viewModel: VulnAlgViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAesalgBinding.inflate(inflater, container, false)

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

        // buttonタップ時の処理
        binding.button.setOnClickListener {
            viewModel.decrypt()
        }

        // decryptBytesのobserver
        viewModel.decryptBytes.observe(this.viewLifecycleOwner, { decryptSrc ->
            activity?.let {
                AlertDialogFragment(binding.edText.text.toString() == String(decryptSrc)).show(it.supportFragmentManager, "AlertDialogFragment")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}