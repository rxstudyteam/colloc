package com.karrel.colloc.main

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.log.Log
import android.net.Net
import android.util.SDF
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.R
import com.karrel.colloc.databinding.CollocMainFrBinding
import com.karrel.colloc.model.airdata.*
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.colloc_main_fr.*

class CollocMainFr : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    internal interface EXTRA {
        companion object {
            val LOCATION = "LOCATION"
            val POSITION = "POSITION"
            val ITEM_COUNT = "ITEM_COUNT"
        }
    }

    //        제주특별자치도 제주시 아라동 첨단로 242
    private lateinit var bb: CollocMainFrBinding

    private var mLocation: String = ""
    private var mPosition: Int = 0
    private var mItemCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(arguments)
        arguments?.run {
            mLocation = getString(EXTRA.LOCATION, "종로")
            mPosition = getInt(EXTRA.POSITION, -1)
            mItemCount = getInt(EXTRA.ITEM_COUNT, 0)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bb = DataBindingUtil.inflate(inflater, R.layout.colloc_main_fr, container, false)
        bb.vm = ViewModelProviders.of(this).get(CollocMainViewModel::class.java)
        return bb.root
    }

    val disposables: Set<Disposable> = emptySet()
    override fun onDestroy() {
        super.onDestroy()
        for (disposable in disposables) {
            if (!disposable.isDisposed)
                disposable.dispose()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onLoadOnce()
        onLoad()
    }

    private fun onLoadOnce() {
        swipe_layer.setOnRefreshListener(this)
        swipe_layer.setColorSchemeResources(android.R.color.holo_blue_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark)

        bb.indecater.setDrawable(R.drawable.shape_dot_n, R.drawable.shape_dot_c)
        bb.indecater.setCount(mItemCount)
        bb.indecater.setCurrentPosition(mPosition)

        disposables + Net.create(LocalService::class.java)
                .getTM(126.57821604896051, 33.45613394001848)
                .subscribeOn(Schedulers.io())
                .filter { it.documents.isNotEmpty() }
                .flatMap {
                    Net.create(CollocService::class.java)
                            .getAirData(it.documents[0].x, it.documents[0].y)
                            .onErrorResumeNext { observer: Observer<in AirData> -> observer.onNext(dummyAirData()) }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { onUpdateUI(it) }
    }

    private fun onUpdateUI(airData: AirData?) {
        Log.e("여기에 들어옴", airData)
        bb.locationLabel.text = "현재 위치".takeIf { mPosition == 0 }
        bb.locationName.text = airData?.overallValue?.locationName
    }


    private fun onLoad() {
    }

    //-------------------------------------------------------------------------------------
    override fun onRefresh() {
//        Handler().postDelayed({ swipe_layer.isRefreshing = false }, 5000)

//        val long = 127.029475
//        val lan = 37.496690
//        bb.vm!!.getAirData(long, lan).observe(this, Observer {
//            it.toString()
//        })
        swipe_layer.isRefreshing = false
    }
}

