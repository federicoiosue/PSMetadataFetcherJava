package it.feio.psmf;

import it.feio.psmf.models.PlayStoreResult;

public class BaseTest {

    protected final String PLAY_STORE_RESULT_SOFTWARE_VERSION = "4.2.42";
    protected final String PLAY_STORE_RESULT_DATE_PUBLISHED = "01 january 1970";
    protected final String PLAY_STORE_RESULT_FILE_SIZE = "6 Mb";
    protected final String PLAY_STORE_RESULT_NUM_DOWNLOADS = "100.000-500.000";
    protected final String PLAY_STORE_OPERATING_SISTEMS = "Varia in base al dispositivo";
    protected final String PLAY_STORE_RESULT_CONTENT_RATING = "PEGI 3";
    protected final String LANG_POSTFIX = "_localized_";

    protected PlayStoreResult buildPlayStoreResult() {
        PlayStoreResult playStoreResult = new PlayStoreResult();
        playStoreResult.setOperatingSystems(PLAY_STORE_OPERATING_SISTEMS);
        playStoreResult.setNumDownloads(PLAY_STORE_RESULT_NUM_DOWNLOADS);
        playStoreResult.setFileSize(PLAY_STORE_RESULT_FILE_SIZE);
        playStoreResult.setDatePublished(PLAY_STORE_RESULT_DATE_PUBLISHED);
        playStoreResult.setContentRating(PLAY_STORE_RESULT_CONTENT_RATING);
        playStoreResult.setSoftwareVersion(PLAY_STORE_RESULT_SOFTWARE_VERSION);
        return playStoreResult;
    }

    protected PlayStoreResult buildPlayStoreResult(String lang) {
        PlayStoreResult res = buildPlayStoreResult();
        res.setOperatingSystems(res.getOperatingSystems() + LANG_POSTFIX + lang);
        return res;
    }

    protected String buildHtml() {
        return "<div class=\"details-section metadata\">"
          + "    <div class=\"details-section-contents\">"
          + "        <div class=\"meta-info\">"
          + "            <div class=\"title\">"
          + "                Aggiornata"
          + "            </div>"
          + "            <div class=\"content\" itemprop=\"datePublished\">"
          + "                " + PLAY_STORE_RESULT_DATE_PUBLISHED
          + "            </div>"
          + "        </div>"
          + "        <div class=\"meta-info\">"
          + "            <div class=\"title\">"
          + "                Dimensioni"
          + "            </div>"
          + "            <div class=\"content\" itemprop=\"fileSize\">"
          + "                " + PLAY_STORE_RESULT_FILE_SIZE
          + "            </div>"
          + "        </div>"
          + "        <div class=\"meta-info\">"
          + "            <div class=\"title\">"
          + "                Installazioni"
          + "            </div>"
          + "            <div class=\"content\" itemprop=\"numDownloads\">"
          + "                " + PLAY_STORE_RESULT_NUM_DOWNLOADS
          + "            </div>"
          + "        </div>"
          + "        <div class=\"meta-info\">"
          + "            <div class=\"title\">"
          + "                Versione corrente"
          + "            </div>"
          + "            <div class=\"content\" itemprop=\"softwareVersion\">"
          + "                " + PLAY_STORE_RESULT_SOFTWARE_VERSION
          + "            </div>"
          + "        </div>"
          + "        <div class=\"meta-info\">"
          + "            <div class=\"title\">"
          + "                È necessario Android"
          + "            </div>"
          + "            <div class=\"content\" itemprop=\"operatingSystems\">"
          + "                " + PLAY_STORE_OPERATING_SISTEMS
          + "            </div>"
          + "        </div>"
          + "        <div class=\"meta-info contains-text-link\">"
          + "            <div class=\"title\">"
          + "                Classificazione contenuti"
          + "            </div>"
          + "            <div class=\"content\" itemprop=\"contentRating\">"
          + "                " + PLAY_STORE_RESULT_CONTENT_RATING
          + "            </div>"
          + "            <a class=\"content\" href=\"https://support.google.com/googleplay?p=appgame_ratings\" target=\"_blank\">Ulteriori informazioni</a>"
          + "        </div>"
          + "    </div>"
          + "</div>";
    }
}
