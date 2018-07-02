package it.feio.psmf.models;

import java.util.NoSuchElementException;
import java.util.logging.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PlayStoreResultAdapter {

    static final String SEMANTIC_VERSIONING_REGEXP = "(\\d{1,2}\\.){2}\\d{1,2}";

    private static final Logger LOG = Logger.getLogger(PlayStoreResultAdapter.class.getSimpleName());

    public static PlayStoreResult adapt(Document document) {
        return new PlayStoreResult()
          .setName(getName(document))
          .setDescription(getHtmlValueMeta(document, "description"))
          .setContentRating(getHtmlValueMeta(document, "contentRating"))
          .setDatePublished(getHtmlValue(document, "datePublished"))
          .setFileSize(getHtmlValue(document, "fileSize"))
          .setNumDownloads(getHtmlValue(document, "numDownloads"))
          .setOperatingSystems(getHtmlValue(document, "operatingSystems"))
          .setSoftwareVersion(getSoftwareVersion(document))
          .setScore(getScore(document))
          .setReviewCount(getReviewCount(document))
          .setAuthor(getAuthor(document))
          .setGenre(getGenre(document));
    }

    private static String getName(Document document) {
        return getElementBySelector(document, "h1[itemprop='name'] span").ownText().trim();
    }

    private static String getReviewCount(Document document) {
        return getElementBySelector(document, "meta[itemprop='reviewCount']").attr("content").trim();
    }

    private static String getGenre(Document document) {
        String genre = "";
        try {
            genre = getElementBySelector(document, "a[itemprop='genre']").ownText().trim();
        } catch (NullPointerException e) {
            LOG.warning("Genre not parsable");
        }
        return genre;
    }

    private static String getAuthor(Document document) {
        String author = "";
        try {
            author = getElementBySelector(document, "div[itemprop='author'] span[itemprop='name']").ownText().trim();
        } catch (NullPointerException e) {
            LOG.warning("Author not parsable");
        }
        return author;
    }

    private static String getScore(Document document) {
        String score = "";
        try {
            score = getElementBySelector(document, "meta[itemprop=ratingValue]").attr("content").trim();
        } catch (NullPointerException e) {
            LOG.warning("Score not parsable");
        }
        return score;
    }

    private static String getHtmlValue(Document document, String attribute) {
        Element element = getElementBySelector(document, "div[itemprop='" + attribute + "']");
        return element == null ? "" : element.ownText().trim();
    }

    private static String getHtmlValueMeta(Document document, String attribute) {
        Element element = getElementBySelector(document, "meta[itemprop='" + attribute + "']");
        return element == null ? "" : element.attr("content").trim();
    }

    private static Element getElementBySelector(Document document, String selector) {
        return document.select(selector).first();
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
