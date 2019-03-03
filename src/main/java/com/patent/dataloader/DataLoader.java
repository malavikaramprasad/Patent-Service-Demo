package com.patent.dataloader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.stereotype.Component;

import com.patent.model.Patent;
import com.patent.service.ConnectionFactory;

@Component
public class DataLoader {
		
	public ResponseBuilder loadData(String searchText) {
		DataFetcher patentData = new DataFetcher();
		Connection connection = ConnectionFactory.getConnection();
		
		try {
			List<Patent> patentList = patentData.getData(searchText);
			System.out.println(patentList.toString());
			if(patentList.size() > 0) {
				deleteData(connection);
				insertData(patentList, connection);
			}			
			return Response.ok("Success");
		}
		catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteData(Connection connection) {
		//Delete data before insertion
		PreparedStatement deletion;
		try {
			
			deletion = connection.prepareStatement("delete from patentdata");
			
			deletion.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void insertData(List<Patent> patentList, Connection connection) {
		//Insert Data
		
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("insert into patentdata (version, application_date, application_number,"
					+ " application_type,archiveurl, document_id, document_date, document_type, patent_number , pdfpath, "
					+ " production_date, publication_date, title, year, inventor, applicant, assignee) " +
			        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			for(Patent patentObj: patentList) {
				if(patentObj.getVersion() == null)
					ps.setNull(1, Types.BIGINT);
				else 
					ps.setLong(1, patentObj.getVersion());
				ps.setString(2, patentObj.getApplicationDate());
				ps.setString(3, patentObj.getApplicationNumber());
				ps.setString(4, patentObj.getApplicationType());
				ps.setString(5, patentObj.getArchiveUrl());
				ps.setString(6, patentObj.getDocumentId());
				ps.setString(7, patentObj.getDocumentDate());
				ps.setString(8, patentObj.getDocumentType());
				ps.setString(9, patentObj.getPatentNumber());
				ps.setString(10, patentObj.getPdfPath());
				ps.setString(11, patentObj.getProductionDate());
				ps.setString(12, patentObj.getPublicationDate());
				ps.setString(13, patentObj.getTitle());
				ps.setString(14, patentObj.getYear());
				if(patentObj.getInventor() == null)
					ps.setNull(15, Types.ARRAY);
				else
					ps.setArray(15, connection.createArrayOf("text", patentObj.getInventor().toArray()));
				if(patentObj.getApplicant() == null)
					ps.setNull(16, Types.ARRAY);
				else
					ps.setArray(16, connection.createArrayOf("text", patentObj.getApplicant().toArray()));
				if(patentObj.getAssignee() == null)
					ps.setNull(17, Types.ARRAY);
				else
					ps.setArray(17, connection.createArrayOf("text", patentObj.getAssignee().toArray()));
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
