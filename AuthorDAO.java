package com.wipro.book.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import com.wipro.book.bean.AuthorBean;
import com.wipro.book.util.DBUtil;

public class AuthorDAO {
	
	Connection conn;
	
	public AuthorBean getAuthor(int authorCode) throws SQLException, ClassNotFoundException
	{
		try
		{
		conn = DBUtil.getDBConnection();
		Statement stmt =  conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from Author_tbl where Author_code ="+authorCode);
		AuthorBean ab = new AuthorBean();
		if(rs.next())
		{
			ab.setAuthorCode(rs.getInt(1));
			ab.setAuthorName(rs.getString(2));
			ab.setContactNo(rs.getLong(3));
			return ab;
		}
		else
		{
		return null;
		}
		}
		catch(Exception e)
		{
			return null;
		}
		
	}
	
	
	AuthorBean getAuthor(String authorName) throws SQLException, ClassNotFoundException
	{
		conn = DBUtil.getDBConnection();
		Statement stmt =  conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from Author_tbl where Author_name ='"+authorName+"'");
		AuthorBean ab = new AuthorBean();
		if(rs.next() )
		{
			ab.setAuthorCode(rs.getInt(1));
			ab.setAuthorName(rs.getString(2));
			ab.setContactNo(rs.getLong(3));
			return ab;
		}
		else
		{
		return null;
		}
	}

}
