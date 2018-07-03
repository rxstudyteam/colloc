package com.karrel.colloc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class MainActivity : AppCompatActivity() {

    private val subject = makeDefaultBehaviorSubject()
    private val compositeDisposable = CompositeDisposable()

    private val airApiService by lazy {
        RestfulAdpater.getRestfulApi()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        finishWIthBackPress()
        beginSearch("TEST")
    }

    private fun beginSearch(search: String) {
        airApiService.getSampleAll(search)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { Log.d("SAMPLE", "UI Update") }
                .let {
                    compositeDisposable.add(it)
                }
    }

    private fun makeDefaultBehaviorSubject() = BehaviorSubject.createDefault(0L)

    private fun finishWIthBackPress() {
        subject.observeOn(AndroidSchedulers.mainThread())
                .buffer(2, 1)
                .map{ times -> times.get(0) to times.get(1) }
                .subscribe {
                    if(it.second - it.first < 2000 ) finish()
                    else Toast.makeText(this, "finish activity if click one more time", Toast.LENGTH_SHORT).show();
                }.let {
            compositeDisposable.add(it)
        }
    }

    override fun onBackPressed() {
        subject.onNext(System.currentTimeMillis())
    }

    override fun onDestroy() {
        super.onDestroy()
        with(compositeDisposable) {
            Log.d("SAMPLE", "Dispose all")
            compositeDisposable.dispose()
        }
    }
}
