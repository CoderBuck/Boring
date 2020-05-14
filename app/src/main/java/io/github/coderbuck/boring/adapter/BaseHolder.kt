package io.github.coderbuck.boring.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class BaseHolder(
    parent: ViewGroup,
    id: Int
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(id, parent, false))