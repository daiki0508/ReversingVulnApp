package com.websarva.wings.android.vulnmemory_aes.ui.fragment.vulnalg

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.websarva.wings.android.vulnmemory_aes.R
import com.websarva.wings.android.vulnmemory_aes.databinding.FragmentAesalgBinding
import com.websarva.wings.android.vulnmemory_aes.ui.MainActivity

class AESAlgFragment: Fragment() {
    private var _binding: FragmentAesalgBinding? = null
    private val binding
    get() = _binding!!

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
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}