package com.patent.model;

public class Patent1 {
	private String documentId;
	private String applicationType;
	private String applicationNumber;
	private String documentType;
	private String patentNumber;
	private String publicationDate;
	private String[] applicants;
	private String[] inventors;
	private String[] assignees;
	private String documentDate;
	private String productionDate;
	private String applicationDate;
	private String title;
	private String archiveUrl;
	private String pdfPath;
	private String year;
	private long version;

	public String getDocumentId() {
		return documentId;
	}
	public String[] getApplicants() {
		return applicants;
	}
	public void setApplicants(String[] arr) {
		this.applicants = arr;
	}
	public String[] getInventors() {
		return inventors;
	}
	public void setInventors(String[] inventors) {
		this.inventors = inventors;
	}
	public String[] getAssignees() {
		return assignees;
	}
	public void setAssignees(String[] assignees) {
		this.assignees = assignees;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
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
	public String getPatentNumber() {
		return patentNumber;
	}
	public void setPatentNumber(String patentNumber) {
		this.patentNumber = patentNumber;
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
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	
}
