package TermProjectPrototype;

import TermProjectPrototype.Crawler.Pool;

public class Main {
    public static void main(String[] args) {
        Pool.getInstance().execute();
    }
}
