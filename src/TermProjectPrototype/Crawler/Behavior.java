package TermProjectPrototype.Crawler;

import java.util.HashMap;
import java.util.Scanner;

public interface Behavior {
    HashMap<Integer, HashMap<String, String>> extractedProductInfo = new HashMap<>();
    HashMap<String, String> sorter = new HashMap<>();
    Scanner scanner = new Scanner(System.in);
    String[] set = new String[2];
    int[] size = new int[1];
    
    void initialize();

    void behave();

    void print();

    default HashMap<Integer, HashMap<String, String>> getList() {
        return extractedProductInfo;
    }

    default void initQuery() {
        System.out.print("검색어: ");
        set[0] = String.join("+", scanner.nextLine().strip().split(" "));

        System.out.print("옵션(판매량순, 최신순, 높은 가격순, 낮은 가격순): ");
        set[1] = scanner.nextLine().strip();

        System.out.print("검색 생품 개수: ");
        size[0] = scanner.nextInt();

        scanner.nextLine();
    }
}
