package ru.pflb.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static  final Model instance = new Model();

    private final Map<Integer, Transaction> model;

    public  Model(){
        model = new HashMap<Integer, Transaction>();
    }

    public static Model getInstance(){
        return instance;
    }

    public void add(int id, Transaction transaction){
        model.put(id, transaction);
    }

    public Transaction getFromList(int id){
        return model.get(id);
    }

    public Map<Integer, Transaction> getList(){
        return model;
    }
}
