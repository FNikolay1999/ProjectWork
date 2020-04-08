package com.example.projectwork.base_list.holder_creators

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


abstract class SimpleCreator<out ViewHolder : RecyclerView.ViewHolder>(val creator: (View) -> ViewHolder) :
    Creator<ViewHolder> {
    abstract val holderLayout: Int
    override fun from(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(holderLayout, null)
        return creator(view)
    }
}
