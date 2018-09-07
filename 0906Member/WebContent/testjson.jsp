<%@ page language="java" contentType="text/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.json.*"%>

<%

	JSONObject obj = new JSONObject();
	obj.put("name", "이우미");
	
	
	JSONArray ar = new JSONArray();
	ar.put("아이린");
	ar.put("태연");
	ar.put("박보검");
	ar.put("강동원");
	ar.put("박서준");
%>

<%=obj%>

<%=ar%>