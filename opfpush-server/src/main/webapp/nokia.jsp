<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>

  <head>
	  <link rel='icon' href='favicon.png'/>
  </head>

  <body>
  <form action="index.jsp">
		  <input type="submit" value="Back">
	  </form>

	Nokia message:
  	<% if (request.getParameter("message") != null) { %>
		<%= request.getParameter("message")%>
	<% } %>
	<form action="/nokia" method="post">
		<div><textarea name="txtInput" rows="3" cols="60"></textarea></div>
		<div><input type="submit" value="Submit" /></div>   
  	</form>
  </body>
</html>
