package ca.nick.boilerplate.utils

import android.view.View
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun Completable.applySchedulers(): Completable =
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.applySchedulers(): Single<T> =
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


fun View.visibleOrGone(shouldBeVisible: Boolean) {
    if (shouldBeVisible) {
        if (visibility != View.VISIBLE) {
            visibility = View.VISIBLE
        }
    } else {
        if (visibility != View.GONE) {
            visibility = View.GONE
        }
    }
}