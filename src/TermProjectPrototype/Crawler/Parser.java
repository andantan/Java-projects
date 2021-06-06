package TermProjectPrototype.Crawler;

import TermProjectPrototype.Crawler.Exception.InvalidFlagValueException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

public abstract class Parser {
    String url;

    protected Document getDocument(String keyword, String sorter, String flag) throws InvalidFlagValueException {
        Document document;

        switch (flag) {
            case "Coupang" -> {
                url = String.format("https://www.coupang.com/np/search?component=&q=%s&channel=user%s", keyword, sorter);
                document = getPageSourceByJsoup();
            }
            case "Gmarket" -> {
                url = String.format("https://browse.gmarket.co.kr/search?keyword=%s%s", keyword, sorter);
                document = getPageSourceBySelenium();
            }
            case "11st" -> {
                 url = String.format("https://search.11st.co.kr/Search.tmall?kwd=%s%s", keyword, sorter);
                document = getPageSourceBySelenium();
            }
            default -> throw new InvalidFlagValueException("An unknown flag value was passed as a parameter.");
        }

        return document;
     }

    private Document getPageSourceByJsoup() {
        Document document = null;

        try {
            document = Jsoup.connect(url).get();
        } catch (IOException ERO) {
            System.out.println("[TermProjectPrototype.Crawler.Parser::getPageSourceByJsoup] " + ERO.getMessage());
        }

        return document;
    }

    private Document getPageSourceBySelenium() {
        final String WEB_DRIVE_ID = "webdriver.chrome.driver";
        final String WEB_DRIVER_PATH = "C:/Dev/chromedriver.exe";

        System.setProperty(WEB_DRIVE_ID, WEB_DRIVER_PATH);

        // final ChromeOptions chromeOptions = new ChromeOptions();
        // chromeOptions.addArguments("--headless");
        // chromeOptions.addArguments("--no-sandbox");
        // WebDriver driver = new ChromeDriver(chromeOptions);

        WebDriver driver = new ChromeDriver();

        driver.get(url);

        for (int i = 1; i < 30; i++) {
            ((JavascriptExecutor) driver).executeScript(String.format("window.scrollTo(0, %d)", i * 100));

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Document document = Jsoup.parse(driver.getPageSource());

        driver.close();

        return document;
    }
}
