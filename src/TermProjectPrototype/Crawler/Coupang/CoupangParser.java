package TermProjectPrototype.Crawler.Coupang;

import TermProjectPrototype.Crawler.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

public class CoupangParser extends Parser {
    @Override
    protected Document getDocument(String keyword, String sorter, String webPage) {
        return super.getDocument(keyword, sorter, webPage);
    }

    protected ArrayList<Element> getSearchProduct(Document document, int size) {
        Elements elements = document.getElementsByClass("search-product");
        ArrayList<Element> products = new ArrayList<>();

        for (int i = 0; i < size; i++)
            products.add(elements.get(i));

        return products;
    }

    public HashMap<String, String> getProductInfo(Element element, int size) {
        HashMap<String, String> productInfo = new HashMap<>();

        productInfo.put("productName", element.getElementsByClass("name").get(0).text());
        productInfo.put("productHref", element.getElementsByTag("a").get(0).attr("href"));
        productInfo.put("productPrice", element.getElementsByClass("price-value").get(0).text());

        if (size < 8)
            productInfo.put("productImageSrc", element.getElementsByClass("search-product-wrap-img").get(0).attr("src"));
        else
            productInfo.put("productImageSrc", element.getElementsByClass("search-product-wrap-img").get(0).attr("data-img-src"));

        return productInfo;
    }
}
