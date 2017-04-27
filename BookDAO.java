package com.wipro.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.wipro.book.bean.AuthorBean;
import com.wipro.book.bean.BookBean;
import com.wipro.book.util.DBUtil;                                                                                                                                                                                    

public class BookDAO {
	Connection conn;
	public BookBean fetchBook(String isbn) throws SQLException, ClassNotFoundException
	{
		AuthorDAO ad = new AuthorDAO();
		conn = DBUtil.getDBConnection();
		Statement stmt =  conn.createStatement();
		BookBean bb = new BookBean();
		AuthorBean ab = new AuthorBean();
		
		int Author_code;
		
		
		ResultSet rs = stmt.executeQuery("select * from Book_tbl where ISBN ='"+isbn+"'");
		
		if(rs.next())
		{

			bb.setIsbn(rs.getString(1));
			bb.setBookName(rs.getString(2));
			
			bb.setBookType(rs.getString(3).charAt(0));
			Author_code = rs.getInt(4);	
			
			ab = ad.getAuthor(Author_code);
			bb.setAuthor(ab);
			
			bb.setCost(rs.getFloat(5));
			return bb;
		}
		else
		{
		return null;
		}
		
		
			
	
		
	}

 public int createBook(BookBean bookBean) throws SQLException, ClassNotFoundException
	{

	 try
	 {
		AuthorBean ab = new AuthorBean();
		ab = bookBean.getAuthor();
		
		char charvalue = bookBean.getBookType();
		conn = DBUtil.getDBConnection();
		PreparedStatement ps = conn.prepareStatement("insert into Book_Tbl values(?,?,?,?,?)");
		ps.setString(1, bookBean.getIsbn());
		ps.setString(2, bookBean.getBookName());
		ps.setString(3,String.valueOf(charvalue) );
		ps.setInt(4, ab.getAuthorCode());
		ps.setFloat(5,bookBean.getCost() );
		
		int res = ps.executeUpdate();
		
		if(res == 1)
			return 1;
		else
			return 0;
	 }
	 catch(SQLException e)
	 {
		 return 0;
	 }
		
	}
	
	
	
}
