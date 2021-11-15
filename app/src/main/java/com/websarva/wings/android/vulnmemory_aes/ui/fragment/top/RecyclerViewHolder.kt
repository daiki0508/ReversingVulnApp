package com.websarva.wings.android.vulnmemory_aes.ui.fragment.top

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.websarva.wings.android.vulnmemory_aes.R

class RecyclerViewHolder(var view: View): RecyclerView.ViewHolder(view) {
    val title: TextView = view.findViewById(R.id.tvTitle)
}