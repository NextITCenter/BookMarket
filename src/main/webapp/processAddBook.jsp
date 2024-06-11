<%@page import="book.BookRepository"%>
<%@page import="java.time.LocalDate"%>
<%@page import="book.BookVO"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 	Map<String, String[]> map = request.getParameterMap();
// 	Set<String> keys = map.keySet();
// 	for (String key: keys) {
// 		String[] values = map.get(key);
// 		if (values.length > 0) {
// 			for (String str: values) {
// 				out.println(key + ": " + str + "<br>");
// 			}
// 		}
// 	}
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String title = request.getParameter("title");
	int price = request.getParameter("price").isEmpty() ?
			0 : Integer.parseInt(request.getParameter("price"));
	String author = request.getParameter("author");
	String description = request.getParameter("description");
	String publisher = request.getParameter("publisher");
	String category = request.getParameter("category");
	long quantity = request.getParameter("quantity").isEmpty() ?
			0 : Integer.parseInt(request.getParameter("quantity"));
	String releaseDate = request.getParameter("releaseDate");
	String condition = request.getParameter("condition");

	BookVO book = new BookVO(id, title, price, author, description, publisher, category, quantity, LocalDate.parse(releaseDate), condition);

	BookRepository repository = BookRepository.getInstance();
	repository.addBook(book);
	
	response.sendRedirect("books.jsp");
	
%>







