package com.example.projectwork.base_list


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectwork.base_list.holder_creators.Creator
import com.example.projectwork.base_list.holder_creators.SimpleCreator

@Suppress("UNCHECKED_CAST")
open class ListAdapter<Item>() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    protected var items: List<Item> = emptyList()
    open val creator: Creator<RecyclerView.ViewHolder> =
        SimpleViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return creator.from(parent)
    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? Bindable<Item>)?.bind(items[position])
    }

    open fun setList(it: List<Item>) {
            items = it
            notifyDataSetChanged()
    }


    class SimpleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object : SimpleCreator<SimpleViewHolder>(::SimpleViewHolder) {
            override val holderLayout: Int
                get() = 0
        }
    }
}

interface Bindable<in Item> {
    fun bind(item: Item)
}



