package TermProjectPrototype.Crawler.Gmarket;

import TermProjectPrototype.Crawler.Behavior;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class GmarketBehavior implements Behavior {
    private final GmarketParser gmarketParser = new GmarketParser();

    @Override
    public void initialize() {
        sorter.put("판매량순", "&s=8");
        sorter.put("최신순", "&s=3");
        sorter.put("높은 가격순", "&s=2");
        sorter.put("낮은 가격순", "&s=1");

        initQuery();
    }

    @Override
    public void behave() {
        Document document = gmarketParser.getDocument(set[0], sorter.get(set[1]), "Gmarket");
        ArrayList<Element> searchProductList = gmarketParser.getSearchProduct(document, size[0]);

        for (int i = 0; i < size[0]; i++)
            extractedProductInfo.put(i + 1, gmarketParser.getProductInfo(searchProductList.get(i)));
    }

    @Override
    public void print() {
        System.out.printf("검색어: %s, 옵션: %s, 개수: %d%n", set[0].replace("+", " "), set[1], size[0]);
        System.out.printf("URL: https://browse.gmarket.co.kr/search?keyword=%s%s\n", set[0], sorter.get(set[1]));

        for (int i = 1; i < size[0] + 1; i++) {
            System.out.println("(" + i + ")상품명: " + extractedProductInfo.get(i).get("productName"));
            System.out.println("상품 링크: " + extractedProductInfo.get(i).get("productHref"));
            System.out.println("상품 가격:" + extractedProductInfo.get(i).get("productPrice") + "원");
            System.out.println("상품 이미지 링크: " + extractedProductInfo.get(i).get("productImageSrc"));
            System.out.println("=====================================================================================");
        }
    }
}
