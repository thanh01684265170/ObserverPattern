package com.example.observerpattern;

import android.os.Handler;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Repository implements Subject, Observer {
    private String mFullName;
    private int mAge;
    private static Repository INSTANCE = null;

    private ArrayList<ObserverTest> mObservers;

    private Repository() {
        mObservers = new ArrayList<>();
        getNewDataFromRemote();
    }

    // Simulate network
    private void getNewDataFromRemote() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setUserData("Pham Quang thanh", 100);
            }
        }, 3000);
    }

    // Creates a Singleton of the class
    public static Repository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Repository();
        }
        return INSTANCE;
    }

    @Override
    public void registerObserver(ObserverTest observerTest) {
        if (!mObservers.contains(observerTest)) {
            mObservers.add(observerTest);
        }
    }

    @Override
    public void removeObserver(ObserverTest observerTest) {
        if (mObservers.contains(observerTest)) {
            mObservers.remove(observerTest);
        }
    }

    @Override
    public void notifyObservers() {
        for (ObserverTest observer : mObservers) {
            observer.onUserDataChanged(mFullName, mAge);
        }
    }

    public void setUserData(String fullName, int age) {
        mFullName = fullName;
        mAge = age;
        notifyObservers();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
