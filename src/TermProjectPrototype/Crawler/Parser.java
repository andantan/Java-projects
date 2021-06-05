package TermProjectPrototype.Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class Parser {
    final ChromeOptions chromeOptions = new ChromeOptions();

    WebDriver driver;
    Document document;
    String url;

    protected Document getDocument(String keyword, String sorter, String flag) {
        final String WEB_DRIVE_ID = "webdriver.chrome.driver";
        final String WEB_DRIVER_PATH = "C:/Dev/chromedriver.exe";

        System.setProperty(WEB_DRIVE_ID, WEB_DRIVER_PATH);

//        chromeOptions.addArguments("--headless");
//        chromeOptions.addArguments("--no-sandbox");

        switch (flag) {
            case "Coupang" -> url = String.format("https://www.coupang.com/np/search?component=&q=%s&channel=user%s", keyword, sorter);
            case "Gmarket" -> url = String.format("https://browse.gmarket.co.kr/search?keyword=%s%s", keyword, sorter);
            case "11st" -> url = String.format("https://search.11st.co.kr/Search.tmall?kwd=%s%s", keyword, sorter);
        }

        driver = new ChromeDriver(chromeOptions);
        document = Jsoup.parse(seleniumExecutor());

        driver.close();

        return document;
     }

    private String seleniumExecutor() {
        driver.get(url);

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 1000);");

        return driver.getPageSource();
    }
}
