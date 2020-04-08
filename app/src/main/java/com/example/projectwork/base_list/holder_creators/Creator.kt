package com.example.projectwork.base_list.holder_creators

import android.view.ViewGroup

interface Creator<out ViewHolder> {
    fun from(parent: ViewGroup, viewType: Int = 0): ViewHolder
}