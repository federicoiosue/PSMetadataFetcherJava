package it.feio.psmf.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.junit.Test;

public class PlayStoreResultAdapterTest {

    @Test
    public void adapt() throws Exception {
        Document doc = Jsoup.parse(buildHtml(), "", Parser.htmlParser());
        PlayStoreResult playStoreResult = PlayStoreResultAdapter.adapt(doc);
        assertEquals("42.42.42", playStoreResult.getSoftwareVersion());
        assertEquals("01 january 1970", playStoreResult.getDatePublished());
    }

    @Test
    public void isSoftwareVersionFalse() throws Exception {
        assertFalse(PlayStoreResultAdapter.isSoftwareVersion("abaco"));
    }

    @Test
    public void isSoftwareVersionTrue() throws Exception {
        assertTrue(PlayStoreResultAdapter.isSoftwareVersion("5.2.13"));
    }

    private String buildHtml() {
        return "<div class=\"details-section metadata\">\n"
          + "    <div class=\"details-section-contents\">\n"
          + "        <div class=\"meta-info\">\n"
          + "            <div class=\"title\">\n"
          + "                Aggiornata\n"
          + "            </div>\n"
          + "            <div class=\"content\" itemprop=\"datePublished\">\n"
          + "                01 january 1970\n"
          + "            </div>\n"
          + "        </div>\n"
          + "        <div class=\"meta-info\">\n"
          + "            <div class=\"title\">\n"
          + "                Dimensioni\n"
          + "            </div>\n"
          + "            <div class=\"content\" itemprop=\"fileSize\">\n"
          + "                Varia in base al dispositivo\n"
          + "            </div>\n"
          + "        </div>\n"
          + "        <div class=\"meta-info\">\n"
          + "            <div class=\"title\">\n"
          + "                Installazioni\n"
          + "            </div>\n"
          + "            <div class=\"content\" itemprop=\"numDownloads\">\n"
          + "                100.000-500.000\n"
          + "            </div>\n"
          + "        </div>\n"
          + "        <div class=\"meta-info\">\n"
          + "            <div class=\"title\">\n"
          + "                Versione corrente\n"
          + "            </div>\n"
          + "            <div class=\"content\" itemprop=\"softwareVersion\">\n"
          + "                42.42.42\n"
          + "            </div>\n"
          + "        </div>\n"
          + "        <div class=\"meta-info\">\n"
          + "            <div class=\"title\">\n"
          + "                È necessario Android\n"
          + "            </div>\n"
          + "            <div class=\"content\" itemprop=\"operatingSystems\">\n"
          + "                Varia in base al dispositivo\n"
          + "            </div>\n"
          + "        </div>\n"
          + "        <div class=\"meta-info contains-text-link\">\n"
          + "            <div class=\"title\">\n"
          + "                Classificazione contenuti\n"
          + "            </div>\n"
          + "            <div class=\"content\" itemprop=\"contentRating\">\n"
          + "                PEGI 3\n"
          + "            </div>\n"
          + "            <a class=\"content\" href=\"https://support.google.com/googleplay?p=appgame_ratings\" target=\"_blank\">Ulteriori informazioni</a>\n"
          + "        </div>\n"
          + "    </div>\n"
          + "</div>";
    }

}