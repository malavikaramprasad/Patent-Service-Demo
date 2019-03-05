package com.patent.service.impl;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;

import com.patent.model.Patent;
import com.patent.service.ConnectionFactory;
import com.patent.service.PatentDao;

public class PatentDaoImpl implements PatentDao {

	public Response getAllPatent() {
		String sqlQuery = "Select * from patentdata";
		return patentDetails(sqlQuery);
	}
	
	public Response getDataForPie(String searchType, String searchVal, String docType) {
		String sqlQuery = "";
		if (searchType.equals("assignee") || searchType.equals("inventor") || searchType.equals("applicant"))
			sqlQuery = "Select * from patentdata, unnest (" + searchType + ") ap WHERE  ap ILIKE '%" + searchVal + "%' "
					+ " and document_type ILIKE '" + docType +"'";
		else 
		sqlQuery = "Select * from patentdata where document_type ILIKE '" + docType 
				+ "' and "+ searchType + " ILIKE '%" + searchVal + "%'";
		System.out.println(sqlQuery);
		return patentDetails(sqlQuery);
	
	}

	public Response getPatent(String req, String val) {
		String sqlQuery = "";
//		String  sqlQuery1 = "";
		//Search across all data
		if (req.equals("searchText")) {
//			sqlQuery1 = "Select distinct version, application_date, application_number, application_type, archiveurl, document_id, document_date, document_type, patent_number , pdfpath, production_date, publication_date, title, year, inventor, applicant, assignee"
//					+ " from patentdata, unnest(assignee) ass, unnest(applicant) appl, unnest(inventor) inv "
//					+ " where document_id ILIKE '%" + val + "%'" + " or application_number ILIKE '%" + val + "%'"
//					+ " or version::text ILIKE '%" + val + "%'" + " or application_date ILIKE '%" + val + "%'"
//					+ " or application_type ILIKE '%" + val + "%'" + " or archiveurl ILIKE '%" + val + "%'"
//					+ " or patent_number ILIKE '%" + val + "%'" + " or document_date ILIKE '%" + val + "%'"
//					+ " or document_type ILIKE '%" + val + "%'" + " or pdfpath ILIKE '%" + val + "%'"
//					+ " or production_date ILIKE '%" + val + "%'" + " or publication_date ILIKE '%" + val + "%'"
//					+ " or title ILIKE '%" + val + "%'" + " or year ILIKE '%" + val + "%'" + " or  ass ILIKE '%" + val
//					+ "%'" + " or inv ILIKE '%" + val + "%'" + " or appl ILIKE '%" + val + "%'";
			
			sqlQuery = "Select distinct version, application_date, application_number, application_type, archiveurl, document_id, document_date, document_type, patent_number , pdfpath, production_date, publication_date, title, year, inventor, applicant, assignee, ass, inv, appl"
					+ " from patentdata "
					+ " left join lateral unnest(inventor) inv on true"
					+ " left join lateral unnest(applicant) appl on true"
					+ " left join lateral unnest(assignee) ass on true"
					+ " where document_id ILIKE '%" + val + "%'" + " or application_number ILIKE '%" + val + "%'"
					+ " or version::text ILIKE '%" + val + "%'" + " or application_date ILIKE '%" + val + "%'"
					+ " or application_type ILIKE '%" + val + "%'" + " or archiveurl ILIKE '%" + val + "%'"
					+ " or patent_number ILIKE '%" + val + "%'" + " or document_date ILIKE '%" + val + "%'"
					+ " or document_type ILIKE '%" + val + "%'" + " or pdfpath ILIKE '%" + val + "%'"
					+ " or production_date ILIKE '%" + val + "%'" + " or publication_date ILIKE '%" + val + "%'"
					+ " or title ILIKE '%" + val + "%'" + " or year ILIKE '%" + val + "%'" + " or  ass ILIKE '%" + val
					+ "%'" + " or inv ILIKE '%" + val + "%'" + " or appl ILIKE '%" + val + "%'";
		} 
		//postgreSQL array search - using unnest
		else if (req.equals("assignee") || req.equals("inventor") || req.equals("applicant"))
			sqlQuery = "Select * from patentdata, unnest (" + req + ") ap WHERE  ap ILIKE '%" + val + "%'";
		else
			sqlQuery = "Select * from patentdata where " + req + " ILIKE '%" + val + "%'";
		System.out.println(sqlQuery);
		return patentDetails(sqlQuery);
	}

	private Response patentDetails(String sqlQuery) {
		Connection connection = ConnectionFactory.getConnection();
		Statement statement;
		if (connection != null) {
			try {
				//create statement and execute query
				statement = connection.createStatement();
				System.out.println(sqlQuery);
				ResultSet result = statement.executeQuery(sqlQuery);
				ArrayList<Patent> patentArray = new ArrayList<Patent>();
				//create array of result
				while (result.next()) {
					patentArray.add(extractPatentFromResultSet(result));
				}
				return Response.ok().entity(patentArray).build();
			} catch (Exception e) {
				System.out.println("Failed at" + e.getMessage());
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private Patent extractPatentFromResultSet(ResultSet result) {
		Patent patent = new Patent();
		try {
			patent.setVersion(result.getLong("version"));
			patent.setApplicationDate(result.getString("application_date"));
			patent.setApplicationNumber(result.getString("application_number"));
			patent.setApplicationType(result.getString("application_type"));
			patent.setArchiveUrl(result.getString("archiveurl"));
			patent.setDocumentDate(result.getString("document_date"));
			patent.setDocumentId(result.getString("document_id"));
			patent.setDocumentType(result.getString("document_type"));
			patent.setPatentNumber(result.getString("patent_number"));
			patent.setPdfPath(result.getString("pdfpath"));
			patent.setProductionDate(result.getString("production_date"));
			patent.setPublicationDate(result.getString("publication_date"));
			patent.setTitle(result.getString("title"));
			patent.setYear(result.getString("year"));
			// Applicant array
			Array applicantArray = result.getArray("applicant");
			if(applicantArray == null)
				patent.setApplicant(new ArrayList<String>());
			else {
				String[] applicantStringArray = (String[]) applicantArray.getArray();
				List<String> applicantList = Arrays.asList(applicantStringArray);
				patent.setApplicant(applicantList);	
			}
			// Inventor
			Array inventorArray = result.getArray("inventor");
			if(inventorArray == null)
				patent.setApplicant(new ArrayList<String>());
			else {
				String[] inventorStringArray = (String[]) inventorArray.getArray();
				List<String> inventorList = Arrays.asList(inventorStringArray);
				patent.setInventor(inventorList);	
			}
			// Assignee
			Array assigneeArray = result.getArray("assignee");
			if(assigneeArray == null)
				patent.setApplicant(new ArrayList<String>());
			else {
				String[] assigneeStringArray = (String[]) assigneeArray.getArray();
				List<String> assigneeList= Arrays.asList(assigneeStringArray);
				patent.setAssignee(assigneeList);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
		}

		return patent;
	}
}
