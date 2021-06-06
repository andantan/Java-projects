package TermProjectPrototype.Crawler;

import java.util.HashMap;
import java.util.Scanner;

public interface Behavior {
    void initialize(String[] set);

    void behave();

    void print();

    void setTime(long time);

    HashMap<Integer, HashMap<String, String>> getList();

    default void call(String[] set) {
        long beforeTime = System.currentTimeMillis();

        initialize(set);
        behave();

        long afterTime = System.currentTimeMillis();

        setTime(afterTime - beforeTime);
    }
}
