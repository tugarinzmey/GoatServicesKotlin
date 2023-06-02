package com.kalabekov.frontend.client

import ViewHolder
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater

import android.view.ViewGroup
import com.kalabekov.frontend.databinding.ViewHolderBinding

class ClientRecyclerViewAdapter(private val values: List<Client>, ) : RecyclerView.Adapter<ViewHolder>() {
    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = (position + 1).toString()
        val itemText = StringBuilder()
        itemText.append(item.firstName).append(" ").append(item.lastName)
        holder.contentView.text = itemText.toString()
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, item)
            }
        }
    }
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
    interface OnClickListener {
        fun onClick(position: Int, model: Client)
    }

    override fun getItemCount(): Int = values.size




}