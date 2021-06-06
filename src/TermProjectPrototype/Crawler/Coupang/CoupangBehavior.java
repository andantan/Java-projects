package TermProjectPrototype.Crawler.Coupang;

import TermProjectPrototype.Crawler.Behavior;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.HashMap;

public final class CoupangBehavior implements Behavior {
    private final CoupangParser coupangParser = new CoupangParser();
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
        sorter.put("판매량순", "&sorter=saleCountDesc");
        sorter.put("최신순", "&sorter=latestAsc");
        sorter.put("높은 가격순", "&sorter=salePriceDesc");
        sorter.put("낮은 가격순", "&sorter=salePriceAsc");

        searchProduct = searchSet[0];
        searchOption = searchSet[1];
        searchNeeds = Integer.parseInt(searchSet[2]);
    }

    @Override
    public synchronized void behave() {
        Document document = coupangParser.getDocument(searchProduct, sorter.get(searchOption), "Coupang");
        ArrayList<Element> searchProductList = coupangParser.getSearchProduct(document, searchNeeds);

        for (int i = 0; i < searchNeeds; i++)
            extractedProductInfo.put(i + 1, coupangParser.getProductInfo(searchProductList.get(i), i));
    }

    @Override
    public void print() {
        System.out.println("(CoupangBehavior finished) consumed time: " + time + " second");
        System.out.println("Extracted product infomation list");
        System.out.printf("URL: https://www.coupang.com/np/search?component=&q=%s&channel=user%s%n", searchProduct, sorter.get(searchOption));

        for (int i = 1; i < searchNeeds + 1; i++) {
            System.out.println("(" + i + ") Product name: " + extractedProductInfo.get(i).get("productName"));
            System.out.println("Product link: https://www.coupang.com" + extractedProductInfo.get(i).get("productHref"));
            System.out.println("Product price: " + extractedProductInfo.get(i).get("productPrice") + "원");
            System.out.println("Product image source: " + extractedProductInfo.get(i).get("productImageSrc"));
            System.out.print("-------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------");
        }
    }
}
