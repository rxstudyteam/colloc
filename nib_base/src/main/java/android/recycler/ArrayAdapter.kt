package android.recycler

import android.log.Log
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList
import java.util.Arrays
import java.util.Collections
import java.util.Comparator

abstract class ArrayAdapter<VH : RecyclerView.ViewHolder, VD> : RecyclerView.Adapter<VH> {
    protected var mObjects: MutableList<VD> = ArrayList()
    private var mItemLayer: Int = 0
    protected var mInflater: LayoutInflater? = null

    private val mLock = Any()

    internal var mOnItemClickListener: OnItemClickListener<VD>? = null

    constructor(@LayoutRes itemLayer: Int) {
        mItemLayer = itemLayer
    }

    constructor(@LayoutRes itemLayer: Int, datas: Array<VD>) {
        mItemLayer = itemLayer
        mObjects.addAll(datas)
    }

    constructor(@LayoutRes itemLayer: Int, datas: Collection<VD>) {
        mItemLayer = itemLayer
        mObjects.addAll(datas)
    }

    abstract fun onCreateViewHolder(itemView: View): VH // {return new Holder(view);}

    abstract fun onBindViewHolder(h: VH, d: VD)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView = inflate(mItemLayer, parent)

        if (mOnItemClickListener != null) {
            itemView.setOnClickListener { v ->
                val position = (parent as RecyclerView).getChildLayoutPosition(v)
                mOnItemClickListener!!.onItemClick(parent, itemView, position, getItem(position))
            }
        } else {
            itemView.setOnClickListener(null)
        }

//          RecyclerView.ViewHolder::class.java.getConstructor(View::class.java).newInstance(itemView)
//        return VH(itemView)
        return onCreateViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        onBindViewHolder(holder, mObjects!![position])
    }

    protected fun inflate(@LayoutRes layer: Int, parent: ViewGroup): View {
        if (mInflater == null)
            mInflater = LayoutInflater.from(parent.context)
        return mInflater!!.inflate(layer, parent, false)
    }

    override fun getItemCount(): Int {
        return if (mObjects == null) 0 else mObjects.size
    }

    fun getItem(position: Int): VD {
        return mObjects!![position]
    }

    fun add(obj: VD) {
        synchronized(mLock) {
            mObjects.add(obj)
        }
        notifyItemInserted(mObjects.size)
    }

    fun addAll(collection: Collection<VD>) {
        synchronized(mLock) {
            mObjects.addAll(collection)
        }
        notifyDataSetChanged()
    }

    fun addAll(vararg items: VD) {
        synchronized(mLock) {
            Collections.addAll(mObjects, *items)
        }
        notifyDataSetChanged()
    }

    fun set(collection: Collection<VD>) {
        synchronized(mLock) {
            mObjects.clear()
            if (collection.isNotEmpty())
                mObjects.addAll(collection)
        }
        notifyDataSetChanged()
    }

    fun set(vararg items: VD) {
        synchronized(mLock) {
            mObjects.clear()
            Collections.addAll(mObjects, *items)
        }
        notifyDataSetChanged()
    }

    fun insert(obj: VD, position: Int) {
        synchronized(mLock) {
            mObjects.add(position, obj)
        }
        notifyItemInserted(position)
    }

    fun remove(position: Int) {
        Log.w(position)
        if (!(0 <= position && position < mObjects.size))
            return
        synchronized(mLock) {
            mObjects.removeAt(position)
        }
        notifyItemRemoved(position)
    }

    fun clear() {
        synchronized(mLock) {
            mObjects.clear()
        }
        notifyDataSetChanged()
    }

    fun sort(comparator: Comparator<in VD>) {
        synchronized(mLock) {
            Collections.sort(mObjects, comparator)
        }
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<VD>) {
        mOnItemClickListener = onItemClickListener
    }

    fun get(): List<VD> {
        return mObjects
    }

    interface OnItemClickListener<VD> {
        fun onItemClick(parent: RecyclerView, view: View, position: Int, data: VD)
    }
}

//        private class NothingHolder extends DiffHolder {
//            public NothingHolder(View itemView) { super(itemView); }
//            @Override
//            public void bind(Object d) { }
//        }
