package com.example.projectwork.base_list.holder_creators

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BindingCreator<in Binding : ViewDataBinding, out ViewHolder : RecyclerView.ViewHolder>(
    val creator: (Binding) -> ViewHolder
) : Creator<ViewHolder> {
    abstract val holderLayout: Int
    override fun from(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<Binding>(inflater, holderLayout, parent, false)
        return creator(binding)
    }
}