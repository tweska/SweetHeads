package com.tweska.sweetheads.scraper;

import com.tweska.sweetheads.heads.Head;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public interface Scraper {
    public List<Head> scrape(Logger logger) throws IOException;
}
