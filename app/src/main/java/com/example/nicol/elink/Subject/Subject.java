package com.example.nicol.elink.Subject;

import com.example.nicol.elink.Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject{

   protected List<Observer> observers;

    public Subject(){
        this.observers  = new ArrayList<Observer>();
    }

    /**
     * Registra un nuevo observador
     * @param o nuevo observador
     */
    public void register(Observer o){
        observers.add(o);
    };

    /**
     * Elimina un observador
     * @param o
     */
    public void unregister(Observer o){
        if(observers.contains(o)){
            observers.remove(observers.indexOf(o));
        }
    }

    /**
     * Notifica a los observadores del estado interno del Subject
     */
    public abstract void notificar();
}
