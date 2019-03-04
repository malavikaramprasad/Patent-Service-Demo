package com.patent.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Patent {
	    @Expose
	    private String applicationType;
	    @Expose
	    private String documentId;
	    @Expose
	    private String applicationNumber;
	    @Expose
	    private String documentType;
	    @Expose
	    private String publicationDate;
	    @Expose
	    private String documentDate;
	    @Expose
	    private String productionDate;
	    @Expose
	    private String applicationDate;
	    @Expose
	    private List<String> applicant = null;
	    @Expose
	    private List<String> inventor = null;
	    @Expose
	    private String title;
	    @Expose
	    private String archiveUrl;
	    @Expose
	    private String pdfPath;
	    @Expose
	    private String year;
	    @Expose
	    private Long version;
	    @Expose
	    private List<String> assignee = null;
	    @Expose
	    private String patentNumber;

	    public String getApplicationType() {
	        return applicationType;
	    }

	    public void setApplicationType(String applicationType) {
	        this.applicationType = applicationType;
	    }

	    public String getDocumentId() {
	        return documentId;
	    }

	    public void setDocumentId(String documentId) {
	        this.documentId = documentId;
	    }

	    public String getApplicationNumber() {
	        return applicationNumber;
	    }

	    public void setApplicationNumber(String applicationNumber) {
	        this.applicationNumber = applicationNumber;
	    }

	    public String getDocumentType() {
	        return documentType;
	    }

	    public void setDocumentType(String documentType) {
	        this.documentType = documentType;
	    }

	    public String getPublicationDate() {
	        return publicationDate;
	    }

	    public void setPublicationDate(String publicationDate) {
	        this.publicationDate = publicationDate;
	    }

	    public String getDocumentDate() {
	        return documentDate;
	    }

	    public void setDocumentDate(String documentDate) {
	        this.documentDate = documentDate;
	    }

	    public String getProductionDate() {
	        return productionDate;
	    }

	    public void setProductionDate(String productionDate) {
	        this.productionDate = productionDate;
	    }

	    public String getApplicationDate() {
	        return applicationDate;
	    }

	    public void setApplicationDate(String applicationDate) {
	        this.applicationDate = applicationDate;
	    }

	    public List<String> getApplicant() {
	        return applicant;
	    }

	    public void setApplicant(List<String> applicant) {
	        this.applicant = applicant;
	    }

	    public List<String> getInventor() {
	        return inventor;
	    }

	    public void setInventor(List<String> inventor) {
	        this.inventor = inventor;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getArchiveUrl() {
	        return archiveUrl;
	    }

	    public void setArchiveUrl(String archiveUrl) {
	        this.archiveUrl = archiveUrl;
	    }

	    public String getPdfPath() {
	        return pdfPath;
	    }

	    public void setPdfPath(String pdfPath) {
	        this.pdfPath = pdfPath;
	    }

	    public String getYear() {
	        return year;
	    }

	    public void setYear(String year) {
	        this.year = year;
	    }

	    public Long getVersion() {
	        return version;
	    }

	    public void setVersion(Long version) {
	        this.version = version;
	    }

	    public List<String> getAssignee() {
	        return assignee;
	    }

	    public void setAssignee(List<String> assignee) {
	        this.assignee = assignee;
	    }

	    public String getPatentNumber() {
	        return patentNumber;
	    }

	    public void setPatentNumber(String patentNumber) {
	        this.patentNumber = patentNumber;
	    }
}
