package com.kalabekov.frontend.payment

import ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kalabekov.frontend.databinding.ViewHolderBinding

class PaymentRecyclerViewAdapter(private val values: List<Payment>, ) : RecyclerView.Adapter<ViewHolder>()    {
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

    override fun getItemCount(): Int = values.size

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
    interface OnClickListener {
        fun onClick(position: Int, model: Payment)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = (position + 1).toString()
        val itemText = StringBuilder()
        itemText.append(StaticPaymentManager.getCLient(item.clientId).firstName).append(" купил ").append(StaticPaymentManager.getService(item.serviceId).name)
        holder.contentView.text = itemText
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, item)
            }
        }
    }
}