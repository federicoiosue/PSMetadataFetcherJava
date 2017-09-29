package it.feio.psmf.models;

public class PlayStoreResult {

    private String datePublished;
    private String fileSize;
    private String numDownloads;
    private String softwareVersion;
    private String operatingSystems;
    private String contentRating;

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getNumDownloads() {
        return numDownloads;
    }

    public void setNumDownloads(String numDownloads) {
        this.numDownloads = numDownloads;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getOperatingSystems() {
        return operatingSystems;
    }

    public void setOperatingSystems(String operatingSystems) {
        this.operatingSystems = operatingSystems;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }
    
}
