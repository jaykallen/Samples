package com.jaykallen.samples.rxjava

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import java.util.concurrent.Callable

class Shooter {

    companion object {

        // The create method is the most common and can emit several objects over time.
        fun getCreate(): Observable<String> {
            return Observable.create(object : ObservableOnSubscribe<String> {
                override fun subscribe(emitter: ObservableEmitter<String>) {
                    for (i in 0..4) {
                        try {
                            Thread.sleep(500)
                        } catch (e: Exception) { }
                        emitter.onNext("Create Results $i")
                    }
                    emitter.onComplete()
                }
            })
        }

        // The callable method only emits a single item.  Good for single API calls and has a lower
        // overhead than Create.
        fun getCallable(): Observable<String> {
            return Observable.fromCallable(Callable {
                try {
                    Thread.sleep(500)
                } catch (e: Exception) {
                }

                "Callable Results"
            })
        }

        // The Just method emits a single or multiple objects at the same time.
        fun getJust(input: String): Observable<String> {
            try {
                Thread.sleep(500)
            } catch (e: Exception) {
            }

            return Observable.just("Just Results $input")
        }

        // This is usage of the Just method with multiple items
        fun getMap(): Observable<String> {
            try {
                Thread.sleep(500)
            } catch (e: Exception) {
            }

            return Observable.just("Pizza", "Tacos", "Salad", "Burgers")
        }


    }
}