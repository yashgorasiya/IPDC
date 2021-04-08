package com.yjisolutions.ipdc;

public class ListData {
    private String chapterDescription;
    private String chapterName;
    private String videoThumbnail;
    private String videoUrl;
    private String videoSize;

    public ListData(String chapterName, String chapterDescription, String videoThumbnail, String videoUrl,String videoSize) {
        this.chapterName = chapterName;
        this.chapterDescription = chapterDescription;
        this.videoThumbnail = videoThumbnail;
        this.videoUrl = videoUrl;
        this.videoSize = videoSize;
    }

    public ListData() {
    }
    public String getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(String videoSize) {
        this.videoSize = videoSize;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterDescription() {
        return chapterDescription;
    }

    public void setChapterDescription(String chapterDescription) {
        this.chapterDescription = chapterDescription;
    }

    public String getVideoThumbnail() {
        return videoThumbnail;
    }

    public void setVideoThumbnail(String videoThumbnail) {
        this.videoThumbnail = videoThumbnail;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}