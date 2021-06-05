package TermProjectPrototype;

import TermProjectPrototype.Crawler.Behavior;
import TermProjectPrototype.Crawler.Coupang.CoupangBehavior;
import TermProjectPrototype.Crawler.Gmarket.GmarketBehavior;
import TermProjectPrototype.Crawler._11_st.stBehavior;

public class Main {
    public static void main(String[] args) {
        Behavior coupang = new CoupangBehavior();
        Behavior gmarket = new GmarketBehavior();
        Behavior street = new stBehavior();

        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

        street.initialize();
        street.behave();
        street.print();

        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

        coupang.initialize();
        coupang.behave();
        coupang.print();

        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

        gmarket.initialize();
        gmarket.behave();
        gmarket.print();
    }
}
