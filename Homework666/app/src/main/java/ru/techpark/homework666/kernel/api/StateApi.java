package ru.techpark.homework666.kernel.api;

import java.util.ArrayList;

import ru.techpark.homework666.kernel.entities.Callback;
import ru.techpark.homework666.kernel.entities.StateObject;

interface StateApiV1{
    public void addState();
    public ArrayList<StateObject> getStates();
    public void addCallback(Callback callback);
}

public class StateApi implements StateApiV1 {
    private static StateApi state;
    private ArrayList<StateObject> items = new ArrayList<>();
    private ArrayList<Callback> callbacks = new ArrayList<>();

    private StateApi() {
        for (int i = 1; i <= 100; i++) {
            items.add(new StateObject(i));
        }
    }

    public static StateApi getInstance(){
        if(state == null){
            state = new StateApi();
        }
        return state;
    }

    @Override
    public void addState() {
        items.add(new StateObject(items.size() + 1));
        for (int i = 0; i < callbacks.size(); i++) {
            callbacks.get(i).callback();
        }
    }

    @Override
    public ArrayList<StateObject> getStates() {
        return items;
    }

    @Override
    public void addCallback(Callback callback) {
        callbacks.add(callback);
    }
}
