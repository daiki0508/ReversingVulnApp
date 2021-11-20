package com.websarva.wings.android.reversingvulnapp.ui.fragment.rewrite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.websarva.wings.android.reversingvulnapp.BuildConfig
import com.websarva.wings.android.reversingvulnapp.R
import com.websarva.wings.android.reversingvulnapp.databinding.FragmentRewriteBinding
import com.websarva.wings.android.reversingvulnapp.ui.MainActivity
import com.websarva.wings.android.reversingvulnapp.viewmodel.rewrite.RewriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RewriteFragment: Fragment() {
    private var _binding: FragmentRewriteBinding? = null
    private val binding
    get() = _binding!!

    private val viewModel: RewriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener(DialogBundle.RewriteDialog.name){_, _ ->
            Log.d("test", "Success!!")
            // TODO("未実装")
            viewModel.decrypt()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRewriteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 戻るボタンタップ時の処理
        requireActivity().onBackPressedDispatcher.addCallback(this){
            with(Intent(activity, MainActivity::class.java)){
                activity?.finish()
                startActivity(this)
                activity?.overridePendingTransition(R.anim.fragment_pop_enter_anim, R.anim.fragment_pop_exit_anim)
            }
        }

        // dialogの表示
        activity?.let {
            // build状態によってflagが変化
            AlertDialogFragment(flag = BuildConfig.DEBUG).show(it.supportFragmentManager, "RewriteAlertDialogFragment")
        }

        // decryptDataのobserver
        viewModel.decryptBytes.observe(this.viewLifecycleOwner, {
            binding.tvResult.text = "daiki0508{${String(it)}}"
        })
    }
}