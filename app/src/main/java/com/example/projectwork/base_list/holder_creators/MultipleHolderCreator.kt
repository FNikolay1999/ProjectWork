package com.example.projectwork.base_list.holder_creators

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.projectwork.base_list.holder_creators.BindingCreator
import com.example.projectwork.base_list.holder_creators.Creator

open class MultipleHolderCreator(
    private vararg val creators: BindingCreator<ViewDataBinding, RecyclerView.ViewHolder>
) : Creator<RecyclerView.ViewHolder> {
    override fun from(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return creators[viewType].from(parent)
    }
}
