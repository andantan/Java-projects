package TermProjectPrototype.Crawler._11_st;

import TermProjectPrototype.Crawler.Behavior;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class stBehavior implements Behavior {
    private final stParser stParser = new stParser();

    @Override
    public void initialize() {
        sorter.put("판매량순", "#sortCd%%A%%누적%20판매순%%4");
        sorter.put("최신순", "#sortCd%%N%%최신순%%10");
        sorter.put("높은 가격순", "#sortCd%%H%%높은%20가격순%%6");
        sorter.put("낮은 가격순", "#sortCd%%L%%낮은%20가격순%%8");

        initQuery();
    }

    @Override
    public void behave() {
        Document document = stParser.getDocument(set[0], sorter.get(set[1]), "11st");
        ArrayList<Element> searchProductList = stParser.getSearchProduct(document, size[0]);

        for (int i = 0; i < size[0]; i++)
            extractedProductInfo.put(i + 1, stParser.getProductInfo(searchProductList.get(i)));
    }

    @Override
    public void print() {
        System.out.printf("검색어: %s, 옵션: %s, 개수: %d%n", set[0].replace("+", " "), set[1], size[0]);
        System.out.printf("URL: https://search.11st.co.kr/Search.tmall?kwd=%s%s\n", set[0], sorter.get(set[1]));

        for (int i = 1; i < size[0] + 1; i++) {
            System.out.println("(" + i + ")상품명: " + extractedProductInfo.get(i).get("productName"));
            System.out.println("상품 링크: " + extractedProductInfo.get(i).get("productHref"));
            System.out.println("상품 가격: " + extractedProductInfo.get(i).get("productPrice") + "원");
            System.out.println("상품 이미지 링크: " + extractedProductInfo.get(i).get("productImageSrc"));
            System.out.println("=====================================================================================");
        }
    }
}
