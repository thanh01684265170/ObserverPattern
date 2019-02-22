package com.example.observerpattern;

public interface Subject {
    void registerObserver(ObserverTest observerTest);

    void removeObserver(ObserverTest observerTest);

    void notifyObservers();
}