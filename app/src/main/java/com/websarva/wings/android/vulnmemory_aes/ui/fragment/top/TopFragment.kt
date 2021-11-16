package com.websarva.wings.android.vulnmemory_aes.ui.fragment.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.websarva.wings.android.vulnmemory_aes.R
import com.websarva.wings.android.vulnmemory_aes.databinding.FragmentTopBinding

class TopFragment: Fragment() {
    private var _binding: FragmentTopBinding? = null
    private val binding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recyclerViewの第１引数に渡す値の作成
        val items = listOf(getString(R.string.rvTitle1), getString(R.string.rvTitle2))
        // RecyclerViewの作成
        val recyclerViewAdapter = RecyclerViewAdapter(items, this)
        binding.rvTop.adapter = recyclerViewAdapter
        binding.rvTop.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}