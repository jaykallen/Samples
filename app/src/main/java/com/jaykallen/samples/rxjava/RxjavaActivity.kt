package com.jaykallen.samples.rxjava

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.jaykallen.samples.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import timber.log.Timber

// Jay Kallen 8/2/2018: Completely replaced the old code which wasn't doing anything.

class RxjavaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava)
        Timber.d("**************** RxJava Activity Started *****************")
        listenCreate()
        listenCallable()
        listenJust()
        listenJust2()
        listenMap()
    }

    // The subscriber to the create will run the onNext everytime something is seen.
    private fun listenCreate() {
        Shooter.getCreate().subscribe(object : DisposableObserver<String>() {
            override fun onNext(s: String) {
                Timber.d("Listen Create: $s")
            }
            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        })
    }

    private fun listenCallable() {
        // This pulls down data from the Shooter only once.
        // The Disposable is created just to kill the subscriber when its done.
        val compositeDisposable = CompositeDisposable()
        val disposable = Shooter.getCallable().subscribe({ i -> Timber.d("Listen Callable: $i") })
        compositeDisposable.add(disposable)
        compositeDisposable.dispose()
    }

    private fun listenJust() {
        val compositeDisposable = CompositeDisposable()
        val disposable = Shooter.getJust("Jay").subscribe({ i -> Timber.d("Listen Just $i") })
        compositeDisposable.add(disposable)
        compositeDisposable.dispose()
    }

    private fun listenJust2() {
        // This is done with a disposable Observer which kills itself when finished.
        Shooter.getJust("Jay")
                .subscribe(object : DisposableObserver<String>() {
                    override fun onNext(s: String) {
                        Timber.d("Listen Just2 $s")
                    }
                    override fun onError(e: Throwable) {}
                    override fun onComplete() {}
                })
    }

    private fun listenMap() {
        // This is done with a disposable Observer which kills itself when finished.
        Shooter.getMap()
                .map({ s -> "Jay Likes $s" })
                .subscribe(object : DisposableObserver<String>() {
                    override fun onNext(s: String) {
                        Timber.d("Listen Map: $s")
                    }
                    override fun onError(e: Throwable) {}
                    override fun onComplete() {}
                })
    }

    private fun onExitClick(view: View) {
        finish()
    }
}
