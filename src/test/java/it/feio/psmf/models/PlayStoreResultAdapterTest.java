package it.feio.psmf.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import it.feio.psmf.BaseTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.junit.Test;

public class PlayStoreResultAdapterTest extends BaseTest {

    @Test
    public void adapt() throws Exception {
        Document doc = Jsoup.parse(buildHtml(), "", Parser.htmlParser());
        PlayStoreResult playStoreResult = PlayStoreResultAdapter.adapt(doc);
        assertEquals(PLAY_STORE_RESULT_SOFTWARE_VERSION, playStoreResult.getSoftwareVersion());
        assertEquals(PLAY_STORE_RESULT_DATE_PUBLISHED, playStoreResult.getDatePublished());
    }

    @Test
    public void isSoftwareVersionFalse() throws Exception {
        assertFalse(PlayStoreResultAdapter.isSoftwareVersion("abaco"));
    }

    @Test
    public void isSoftwareVersionTrue() throws Exception {
        assertTrue(PlayStoreResultAdapter.isSoftwareVersion("5.2.13"));
    }


}