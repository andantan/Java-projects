package TermProjectPrototype.Crawler._11_st;

import TermProjectPrototype.Crawler.Exception.InvalidFlagValueException;
import TermProjectPrototype.Crawler.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

public final class StreetParser extends Parser {
    @Override
    protected Document getDocument(String keyword, String sorter, String webPage) {
        Document document = null;

        try {
            document = super.getDocument(keyword, sorter, webPage);
        } catch (InvalidFlagValueException ERO) {
            System.out.println("[TermProjectPrototype.Crawler._11_st.StreetParser::getDocument]: " + ERO.getMessage());
            ERO.printStackTrace();
        }

        return document;
    }

    protected ArrayList<Element> getSearchProduct(Document document, int size) {
        Elements elements = document.getElementsByClass("c_listing c_listing_view_type_list").get(0).getElementsByTag("li");
        ArrayList<Element> products = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            products.add(elements.get(i));
        }

        return products;
    }

    public HashMap<String, String> getProductInfo(Element element) {
        HashMap<String, String> productInfo = new HashMap<>();

        productInfo.put("productName", element.getElementsByClass("c_prd_name c_prd_name_row_1").get(0).text());
        productInfo.put("productHref", element.getElementsByClass("c_prd_name c_prd_name_row_1").get(0).getElementsByTag("a").get(0).attr("href"));
        productInfo.put("productPrice", element.getElementsByClass("c_prd_price").get(0).getElementsByClass("value").get(0).text());
        productInfo.put("productImageSrc", element.getElementsByClass("c_prd_thumb").get(0).getElementsByTag("img").attr("src"));

        return productInfo;
    }
}
