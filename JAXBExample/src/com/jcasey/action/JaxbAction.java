package com.jcasey.action;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.jcasey.controller.Manager;
import com.jcasey.jaxb.Books;
import com.jcasey.jaxb.ObjectFactory;
import com.opensymphony.xwork2.Action;

public class JaxbAction {
	//Hibernate Controller
	private Manager linkController;

	private File bookFile;

	private String bookFileName;
	private String bookFileContentType;

	public JaxbAction(){
		linkController = new Manager();
	}

	/*
	 * Adds a bookList to the database from a book.xml file using JAXB
	 */
	public String addBookList(){

		try {
			JAXBContext jaxb = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller unmarshaller = jaxb.createUnmarshaller();
			
			Books xmlBooks =  (Books)unmarshaller.unmarshal(bookFile);
			
			// copy data from XML to Hibernate database object
			
			for(Books.Book xmlBook:  xmlBooks.getBook())												
			{
				com.jcasey.model.Book book = new com.jcasey.model.Book();
				
				book.setAuthor(xmlBook.getAuthor());
				book.setTitle(xmlBook.getTitle());
				book.setIsbn(xmlBook.getIsbn());
				
				//TODO link with genre etc
				
				linkController.addBook(book);
			}
			
			return Action.SUCCESS;
			
		} catch (JAXBException e) {
			e.printStackTrace();
			return Action.ERROR;
		}

	}
	public String getBookFileName() {
		return bookFileName;
	}

	public String getBookFileContentType() {
		return bookFileContentType;
	}

	public void setBookFileName(String bookFileName) {
		this.bookFileName = bookFileName;
	}

	public void setBookFileContentType(String bookFileContentType) {
		this.bookFileContentType = bookFileContentType;
	}


}
