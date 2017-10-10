package it.feio.psmf.http;

import it.feio.psmf.models.PlayStoreResult;
import it.feio.psmf.models.PlayStoreResultAdapter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component("playStoreHttpClient")
public class PlayStoreHttpClient {

    final static String PLAY_STORE_URL = "https://play.google.com/store/apps/details";
    final static String CACHE_NAME = "appPackages";

    @Cacheable(value = CACHE_NAME, key="{ #appPackage, #lang }")
    public PlayStoreResult get(String appPackage, String lang) throws IOException {
        Document doc = Jsoup.connect(buildAppUrl(appPackage, lang)).get();
        return PlayStoreResultAdapter.adapt(doc);
    }

    String buildAppUrl(String appPackage, String lang) {
        return PLAY_STORE_URL + "?id=" + appPackage + "&hl=" + lang;
    }
}
