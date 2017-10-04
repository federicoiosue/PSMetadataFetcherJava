package it.feio.psmf.http;

import it.feio.psmf.models.PlayStoreResult;
import it.feio.psmf.models.PlayStoreResultAdapter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component("playStoreHttpClient")
public class PlayStoreHttpClient {

    static String PLAY_STORE_URL = "https://play.google.com/store/apps/details";

    public PlayStoreResult get(String appPackage, String lang) throws IOException {
        Document doc = Jsoup.connect(buildAppUrl(appPackage, lang)).get();
        return PlayStoreResultAdapter.adapt(doc);
    }

    String buildAppUrl(String appPackage, String lang) {
        return PLAY_STORE_URL + "?id=" + appPackage + "&hl=" + lang;
    }
}
