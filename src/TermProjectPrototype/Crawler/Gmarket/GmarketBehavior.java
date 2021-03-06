package TermProjectPrototype.Crawler.Gmarket;

import TermProjectPrototype.Crawler.Behavior;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashMap;

public final class GmarketBehavior implements Behavior {
    private final GmarketParser gmarketParser = new GmarketParser();
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
        sorter.put("판매량순", "&s=8");
        sorter.put("최신순", "&s=3");
        sorter.put("높은 가격순", "&s=2");
        sorter.put("낮은 가격순", "&s=1");

        searchProduct = searchSet[0];
        searchOption = searchSet[1];
        searchNeeds = Integer.parseInt(searchSet[2]);
    }

    @Override
    public synchronized void behave() {
        Document document = gmarketParser.getDocument(searchProduct, sorter.get(searchOption), "Gmarket");
        ArrayList<Element> searchProductList = gmarketParser.getSearchProduct(document, searchNeeds);

        for (int i = 0; i < searchNeeds; i++)
            extractedProductInfo.put(i + 1, gmarketParser.getProductInfo(searchProductList.get(i)));
    }

    @Override
    public void print() {
        System.out.println("(GmarketBehavior finished) consumed time: " + time + " second");
        System.out.println("Extracted product infomation list");
        System.out.printf("URL: https://browse.gmarket.co.kr/search?keyword=%s%s\n", searchProduct, sorter.get(searchOption));

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
