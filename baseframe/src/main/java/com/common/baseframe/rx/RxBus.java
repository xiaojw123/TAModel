package com.common.baseframe.rx;


import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by xiaojw on 2017/6/13.
 */

public class RxBus {
    private static volatile RxBus mInstance;
    private final Subject bus;


    private RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getInstance() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    /**
     * 发送消息
     *
     * @param obj
     */
    public void post(Object obj) {
        bus.onNext(obj);
    }

    /**
     * 接收消息
     *
     * @param eventType
     * @param <T>
     * @return
     */
    public <T> Observable<T> toObserverable(Class<T> eventType) {
        return bus.ofType(eventType);
    }


}
