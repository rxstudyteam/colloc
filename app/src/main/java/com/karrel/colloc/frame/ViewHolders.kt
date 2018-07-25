package com.karrel.colloc.frame

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.karrel.colloc.R
import kotlinx.android.synthetic.main.item_info.view.*

sealed class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var bind : (Frame) -> Unit

    init {
        bind = { it -> }
    }
}



class HorizontalLineViewHolder(iv: View) : BaseViewHolder(iv)

class EmptyViewHolder(iv: View) : BaseViewHolder(iv)


class InfoViewHolder(iv: View) : BaseViewHolder(iv) {
    init {
        bind = {
            if (it is InfoFrame) {

                val text : TextView = iv.findViewById(R.id.text)
                text.text = it.text
                text.gravity = when(it.gravity){
                    GRAVITY.CENTER -> Gravity.CENTER_HORIZONTAL
                    GRAVITY.START ->  Gravity.START
                    GRAVITY.END ->    Gravity.END
                }
            }
        }
    }
}

class LabelViewHolder(iv: View) : BaseViewHolder(iv) {
    init{
        bind = {
            if (it is LabelFrame) {
                val label : TextView? = iv.findViewById(R.id.label)
                label?.text = it.label
            }
        }

    }
}

class DetailItemViewHolder(iv: View) : BaseViewHolder(iv) {
    init{

        bind = {
            if (it is DetailItemFrame) {
                val label : TextView? = iv.findViewById(R.id.label)
                val text : TextView? = iv.findViewById(R.id.text)
                label?.text = it.label
                text?.text = it.text

            }
        }

    }
}

class DailyItemViewHolder(iv: View) : BaseViewHolder(iv) {
    init{

        bind = {
            if (it is DailyItemFrame) {
                val time : TextView? = iv.findViewById(R.id.time)
                val icon : ImageView? = iv.findViewById(R.id.icon)
                val state : TextView? = iv.findViewById(R.id.state)
                time?.text = it.time
                icon?.setImageResource(it.icon)
                state?.text = it.state

            }
        }

    }
}