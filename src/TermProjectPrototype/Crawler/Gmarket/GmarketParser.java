package TermProjectPrototype.Crawler.Gmarket;

import TermProjectPrototype.Crawler.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

public class GmarketParser extends Parser {
    @Override
    protected Document getDocument(String keyword, String sorter, String webPage) {
        return super.getDocument(keyword, sorter, webPage);
    }

    protected ArrayList<Element> getSearchProduct(Document document, int size) {
        Elements elements = document.getElementsByClass("box__component-itemcard");
        ArrayList<Element> products = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            products.add(elements.get(i));
        }

        return products;
    }

    public HashMap<String, String> getProductInfo(Element element) {
        HashMap<String, String> productInfo = new HashMap<>();

        productInfo.put("productName", element.getElementsByClass("text__item").get(0).text());
        productInfo.put("productHref", element.getElementsByClass("link__item").get(0).attr("href"));
        productInfo.put("productPrice", element.getElementsByClass("box__price-seller").get(0).getElementsByClass("text text__value").get(0).text());
        productInfo.put("productImageSrc", element.getElementsByTag("img").get(0).attr("src"));

        return productInfo;
    }
}
