package com.billingapp.utils

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RxUtils {
    companion object {
        @SuppressLint("CheckResult")
        fun <T> runInBack(process:()->T, onNext: ((T) -> Unit)? = null, complete:()->Unit){
            Observable.fromCallable(process)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ onNext?.invoke(it) }, { it?.printStackTrace() }, { complete() })
        }

        fun then(){

        }

        @SuppressLint("CheckResult")
        fun withDelay(retryMilliSecond:Long, bool:Boolean, onNext: (() -> Unit)? = null, complete:()->Unit){
            Observable.interval(retryMilliSecond, TimeUnit.MILLISECONDS )
                    .retryWhen { Observable.just(!bool) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ onNext?.invoke() }, { it?.printStackTrace() }, { complete() })
        }
    }
}