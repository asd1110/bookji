package com.wipro.book.service;

import java.sql.SQLException;
import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.BookDAO;

public class Administrator {
	
	
	String addBook(BookBean bookBean) throws SQLException, ClassNotFoundException
	{

		BookDAO bd = new BookDAO();
		if(bookBean == null || bookBean.getBookName().isEmpty() || (bookBean.getIsbn().length() == 0 ||  bookBean.getIsbn()==null ) || bookBean.getCost() == 0 ||  bookBean.getAuthor().getAuthorName().isEmpty() || ((bookBean.getBookType() == ' ')||bookBean.getBookType() != 'G' && bookBean.getBookType() !='T' ))
		{
		
			return "INVALID";
		
		}
		
		else
		{
		
		int res = bd.createBook(bookBean);
		
		if(res == 1)
			return "SUCCESS";
		else
			return "FAILURE";
		
		}
	}

	BookBean viewBook(String isbn) throws SQLException, ClassNotFoundException
	{
		if(isbn == null || isbn.length() == 0)
		{
			return null;
		}
		else
		{
		BookDAO bd = new BookDAO();
		
		BookBean bb ;
		
		bb =bd.fetchBook(isbn);
	
		
			return bb;
		}
	}
	
}
