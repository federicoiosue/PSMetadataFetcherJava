package it.feio.psmf.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PlayStoreResult {
    private String name;
    private String description;
    private String datePublished;
    private String fileSize;
    private String numDownloads;
    private String softwareVersion;
    private String operatingSystems;
    private String contentRating;
    private String reviewCount;
    private String score;
    private String author;
    private String genre;
}
