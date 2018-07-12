package com.common.baseframe.rx;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxTransformer {



    public static <T> Observable.Transformer<T, T> applySchedulers(){

        return new Observable.Transformer<T,T>(){

            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };

    }


}
