package geek.example.rainicorn.models;

import rx.Observable;
import rx.subjects.PublishSubject;

public class RxEventBus {
    private static RxEventBus instance;

    private PublishSubject<Object> subject = PublishSubject.create();

    public static RxEventBus instanceOf(){
        if(instance == null){
            instance = new RxEventBus();
        }
        return instance;
    }
    public void send(Object object) {
        subject.onNext(object);
    }

    public Observable<Object> getEvents() {
        return subject;
    }


}
