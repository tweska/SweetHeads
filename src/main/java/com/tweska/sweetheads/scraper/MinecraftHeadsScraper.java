package com.tweska.sweetheads.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public abstract class MinecraftHeadsScraper implements Scraper {

    private static final String base = "https://minecraft-heads.com";

    private static final String entrypoint = "https://minecraft-heads.com/custom-heads";

    public static ArrayList<String> doSomething(Logger logger) {
        logger.info(String.format("Start scraping %s", base));

        ArrayList<String> uuids = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(entrypoint).get();

            Elements heads = doc.select(".itemList a");

            for (Element head : heads) {
                Document headPage = Jsoup.connect(String.format("%s%s", base, head.attr("href"))).get();
                Element uuidField = headPage.selectFirst("#UUID-Value");
                uuids.add(uuidField.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info(String.format("Done scraping %s", base));

        return uuids;
    }


}
