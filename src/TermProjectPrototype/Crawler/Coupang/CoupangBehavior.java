package TermProjectPrototype.Crawler.Coupang;

import TermProjectPrototype.Crawler.Behavior;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class CoupangBehavior implements Behavior {
    private final CoupangParser coupangParser = new CoupangParser();

    @Override
    public void initialize() {
        sorter.put("판매량순", "&sorter=saleCountDesc");
        sorter.put("최신순", "&sorter=latestAsc");
        sorter.put("높은 가격순", "&sorter=salePriceDesc");
        sorter.put("낮은 가격순", "&sorter=salePriceAsc");

        initQuery();
    }

    @Override
    public void behave() {
        Document document = coupangParser.getDocument(set[0], sorter.get(set[1]), "Coupang");
        ArrayList<Element> searchProductList = coupangParser.getSearchProduct(document, size[0]);

        for (int i = 0; i < size[0]; i++)
            extractedProductInfo.put(i + 1, coupangParser.getProductInfo(searchProductList.get(i), i));
    }

    @Override
    public void print() {
        System.out.printf("검색어: %s, 옵션: %s, 개수: %d%n", set[0].replace("+", " "), set[1], size[0]);
        System.out.printf("URL: https://www.coupang.com/np/search?component=&q=%s&channel=user%s%n", set[0], sorter.get(set[1]));

        for (int i = 1; i < size[0] + 1; i++) {
            System.out.println("(" + i + ")상품명: " + extractedProductInfo.get(i).get("productName"));
            System.out.println("상품 링크: https://www.coupang.com" + extractedProductInfo.get(i).get("productHref"));
            System.out.println("상품 가격: " + extractedProductInfo.get(i).get("productPrice") + "원");
            System.out.println("상품 이미지 링크: " + extractedProductInfo.get(i).get("productImageSrc"));
            System.out.println("=====================================================================================");
        }
    }
}
