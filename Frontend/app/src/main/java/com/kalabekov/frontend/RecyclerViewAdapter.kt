package com.kalabekov.frontend

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater

import android.view.ViewGroup

import android.widget.TextView

import com.kalabekov.frontend.databinding.ClientHolderBinding
import com.kalabekov.frontend.models.Client

class RecyclerViewAdapter(private val values: List<Client>, ) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ClientHolderBinding.inflate(
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

    inner class ViewHolder(binding: ClientHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }


}