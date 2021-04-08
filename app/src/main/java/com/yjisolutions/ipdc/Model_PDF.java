package com.yjisolutions.ipdc;

public class Model_PDF {
    private String PDFName;
    private String PDFUrl;

    public Model_PDF() {
    }

    public Model_PDF(String PDFName, String PDFUrl) {
        this.PDFName = PDFName;
        this.PDFUrl = PDFUrl;
    }

    public String getPDFName() {
        return PDFName;
    }

    public void setPDFName(String PDFName) {
        this.PDFName = PDFName;
    }

    public String getPDFUrl() {
        return PDFUrl;
    }

    public void setPDFUrl(String PDFUrl) {
        this.PDFUrl = PDFUrl;
    }
}
