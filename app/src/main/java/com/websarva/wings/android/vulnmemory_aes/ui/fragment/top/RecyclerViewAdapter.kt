package com.websarva.wings.android.vulnmemory_aes.ui.fragment.top

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.websarva.wings.android.vulnmemory_aes.R
import com.websarva.wings.android.vulnmemory_aes.ui.fragment.aesnative.AESNativeFragment

class RecyclerViewAdapter(
    private val items: List<String>,
    private val fragment: TopFragment
): RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row_top, parent, false)

        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.title.text = items[position]

        // titleタップ時の処理
        holder.view.setOnClickListener {
            when(position){
                0 -> {
                    fragment.activity?.let {
                        val transaction = it.supportFragmentManager.beginTransaction()
                        transaction.setCustomAnimations(R.anim.fragment_up_enter_anim, R.anim.fragment_up_exit_anim)
                        transaction.replace(R.id.fragment_container, AESNativeFragment()).commit()
                    }
                }
                1 -> {
                    fragment.activity?.let {
                        TODO("未実装")
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}