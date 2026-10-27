package com.example.iscip.concurency;

public interface TaskCallback<T> {
    void onComplete(T result);
    void onError(Fault fault);
}