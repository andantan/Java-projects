package TermProjectPrototype.Crawler._11_st;

import TermProjectPrototype.Crawler.Behavior;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashMap;

public final class StreetBehavior implements Behavior {
    private final StreetParser streetParser = new StreetParser();
    private final HashMap<Integer, HashMap<String, String>> extractedProductInfo = new HashMap<>();
    private final HashMap<String, String> sorter = new HashMap<>();
    private String searchProduct;
    private String searchOption;
    private int searchNeeds;
    private double time;

    @Override
    public synchronized void setTime(long time) {
        this.time = (double) time / 1000;
    }
    @Override
    public synchronized HashMap<Integer, HashMap<String, String>> getList() {
        return extractedProductInfo;
    }

    @Override
    public synchronized void initialize(String[] searchSet) {
        sorter.put("판매량순", "#sortCd%%A%%누적%20판매순%%4");
        sorter.put("최신순", "#sortCd%%N%%최신순%%10");
        sorter.put("높은 가격순", "#sortCd%%H%%높은%20가격순%%6");
        sorter.put("낮은 가격순", "#sortCd%%L%%낮은%20가격순%%8");

        searchProduct = searchSet[0];
        searchOption = searchSet[1];
        searchNeeds = Integer.parseInt(searchSet[2]);
    }

    @Override
    public synchronized void behave() {
        Document document = streetParser.getDocument(searchProduct, sorter.get(searchOption), "11st");
        ArrayList<Element> searchProductList = streetParser.getSearchProduct(document, searchNeeds);

        for (int i = 0; i < searchNeeds; i++)
            extractedProductInfo.put(i + 1, streetParser.getProductInfo(searchProductList.get(i)));
    }

    @Override
    public void print() {
        System.out.println("(StreetBehavior finished) consumed time: " + time + " second");
        System.out.println("Extracted product infomation list");
        System.out.printf("URL: https://search.11st.co.kr/Search.tmall?kwd=%s%s\n", searchProduct, sorter.get(searchOption));

        for (int i = 1; i < searchNeeds + 1; i++) {
            System.out.println("(" + i + ") Product name: " + extractedProductInfo.get(i).get("productName"));
            System.out.println("Product link: " + extractedProductInfo.get(i).get("productHref"));
            System.out.println("Product price: " + extractedProductInfo.get(i).get("productPrice") + "원");
            System.out.println("Product image source: " + extractedProductInfo.get(i).get("productImageSrc"));
            System.out.print("-------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------");
        }
    }
}
