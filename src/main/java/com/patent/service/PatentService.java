package com.patent.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.patent.dataloader.DataFetcher;
import com.patent.dataloader.DataLoader;
import com.patent.service.impl.PatentDaoImpl;

@Path("api/v1")
public class PatentService {
	private PatentDao patentDao;

	public PatentService() {
		patentDao = new PatentDaoImpl();
	}

	@GET
	@Path("patent/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrievePatent() {
		return patentDao.getAllPatent();
	}

	@GET
	@Path("patent")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPath(@QueryParam("document_id") String docId, @QueryParam("application_number") String appNum,
			@QueryParam("patent_number") String patNum, @QueryParam("searchText") String text,
			@QueryParam("title") String title, @QueryParam("assignee") String assignee,
			@QueryParam("inventor") String inventor, @QueryParam("document_type") String docType) {
		
		//based on the type of query param sent, send the detail to perform sql query
		if (docId != null) {
			return patentDao.getPatent("document_id", docId);
		}

		if (appNum != null) {
			return patentDao.getPatent("application_number", appNum);
		}

		if (patNum != null) {
			return patentDao.getPatent("patent_number", patNum);
		}

		if (text != null) {
			return patentDao.getPatent("searchText", text);
		}	

		if (title != null) {
			return patentDao.getPatent("title", title);
		}

		if (assignee != null) {
			return patentDao.getPatent("assignee", assignee);
		}

		if (inventor != null) {
			return patentDao.getPatent("inventor", inventor);
		}
		
		if (docType != null) {
			return patentDao.getPatent("document_type", docType);
		}

		return null;
	}
	
	//test - to run sql
	@GET
	@Path("patent/sql")
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertData(@QueryParam("searchText") String searchText) {
		DataLoader dataLoader = new DataLoader();
		dataLoader.loadData(searchText);
		return Response.ok().build();
	}

}
