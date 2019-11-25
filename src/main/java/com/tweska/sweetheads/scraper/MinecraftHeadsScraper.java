package com.tweska.sweetheads.scraper;

import com.tweska.sweetheads.heads.Head;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class MinecraftHeadsScraper implements Scraper {
    private static final String source = "Minecraft-Heads.com";

    public MinecraftHeadsScraper() {}


    @Override
    public List<Head> scrape(Logger logger) throws IOException {
        List<Head> heads = new ArrayList<>();

        boolean foundHeads;
        int startIndex = 0;

        logger.info("Scraping Minecraft-Heads.com started!");

        do {
            foundHeads = false;

            String overviewURL = String.format("https://minecraft-heads.com/custom-heads?limitstart=%d", startIndex);
            Document overviewPage = Jsoup.connect(overviewURL).get();

            Elements headLinks = overviewPage.select(".itemList a");

            for (Element headLink: headLinks) {
                foundHeads = true;

                String detailURL = String.format("https://minecraft-heads.com%s", headLink.attr("href"));
                Connection connection = Jsoup.connect(detailURL);
                Connection.Response response = connection.ignoreHttpErrors(true).execute();

                if (response.statusCode() != 200) {
                    logger.warning(String.format("%d", response.statusCode()));
                    logger.warning(response.statusMessage());

                    logger.info(detailURL);
                    continue;
                }

                Document detailPage = connection.get();

                String name = detailPage.title();
                String uuid = detailPage.selectFirst("#UUID-Value").text();
                String category = detailPage.selectFirst("span:contains(Category:)").nextElementSibling().text();

                heads.add(new Head(name, uuid, UUID.randomUUID().toString(), category, new ArrayList<String>(), source, detailURL));
            }

            logger.info(String.format("Found %d heads so far...", heads.size()));

            startIndex += 80;
        } while (foundHeads && startIndex < 80);

        logger.info("Scraping Minecraft-Heads.com done!");

        return heads;
    }
}
