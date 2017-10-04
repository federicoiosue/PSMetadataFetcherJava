package it.feio.psmf.http;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayStoreHttpClientTest {

    final String APP_PACKAGE = "it.feio.android.omninotes";
    final String APP_LANG = "it";

    @Test
    public void buildAppUrl() throws Exception {
        String url = new PlayStoreHttpClient().buildAppUrl(APP_PACKAGE, APP_LANG);
        assertEquals(PlayStoreHttpClient.PLAY_STORE_URL + "?id=" + APP_PACKAGE + "&hl=" + APP_LANG, url);
    }

}