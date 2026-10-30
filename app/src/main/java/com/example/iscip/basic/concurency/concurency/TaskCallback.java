package com.example.iscip.basic.concurency.concurency;

public interface TaskCallback<T> {
    void onComplete(T result);
    void onError(Fault fault);
}