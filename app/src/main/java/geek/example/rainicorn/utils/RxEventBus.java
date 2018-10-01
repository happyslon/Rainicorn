package geek.example.rainicorn.utils;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxEventBus {
    private static RxEventBus instance;

    private PublishSubject<String> subject = PublishSubject.create();

    public static RxEventBus instanceOf(){
        if(instance == null){
            instance = new RxEventBus();
        }
        return instance;
    }
    public void send(String newText) {
        subject.onNext(newText);
    }

    public Observable<String> getEvents() {
        return subject;
    }
}
