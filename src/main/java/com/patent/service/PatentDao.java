package com.patent.service;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;


@Component
public interface PatentDao {
	//Interface needed - to expose service
	//Differentiate definition and implementation
	public Response getAllPatent();
	public Response getPatent(String reqParam, String queryValue);
	public Response getDataForPie(String searchText, String searchVal, String docType);	
}
