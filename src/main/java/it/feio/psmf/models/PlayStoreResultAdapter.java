package it.feio.psmf.models;

import java.util.NoSuchElementException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PlayStoreResultAdapter {

    static final String SEMANTIC_VERSIONING_REGEXP = "(\\d{1,2}\\.){2}\\d{1,2}";

    public static PlayStoreResult adapt(Document document) {
        PlayStoreResult playStoreResult = new PlayStoreResult();
        playStoreResult.setContentRating(getHtmlValue(document, "contentRating"));
        playStoreResult.setDatePublished(getHtmlValue(document, "datePublished"));
        playStoreResult.setFileSize(getHtmlValue(document, "fileSize"));
        playStoreResult.setNumDownloads(getHtmlValue(document, "numDownloads"));
        playStoreResult.setOperatingSystems(getHtmlValue(document, "operatingSystems"));
        playStoreResult.setSoftwareVersion(getSoftwareVersion(document));
        return playStoreResult;
    }

    private static String getHtmlValue(Document document, String attribute) {
        Element element = document.select("div[itemprop='" + attribute + "']").first();
        return element == null ? "" : element.ownText().trim();
    }

    private static String getSoftwareVersion(Document document) {
        String softwareVersion = getHtmlValue(document, "softwareVersion");
        if (!isSoftwareVersion(softwareVersion)) {
            softwareVersion = getSoftwareVersionByRecentChanges(document);
        }
        return softwareVersion;
    }

    private static String getSoftwareVersionByRecentChanges(Document document) {
        String softwareVersion = "";
        try {
            softwareVersion = document.select("div.recent-change").stream()
              .map(element -> element.ownText().trim())
              .filter(PlayStoreResultAdapter::isSoftwareVersion)
              .findFirst()
              .get();
        } catch (NoSuchElementException e) {
            // Nothing do to here, just the element is not been used by PS app's developer
        }
        return softwareVersion;
    }

    static boolean isSoftwareVersion(String test) {
        return test.matches(SEMANTIC_VERSIONING_REGEXP);
    }
}
