<%@page import="java.util.Locale"%>
<%@page import="com.dev.model.beans.Person"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%-- <%
Person p=(Person)request.getAttribute("person");
out.print(p);
%> --%>
<%-- <%
int var=90;
System.out.println(var);
%>
<%
 int var2=90;
System.out.println(var+this.var2);
%> --%>
<jsp:useBean id="person"
 class="com.dev.model.beans.Person" 
 scope="request">
  <jsp:setProperty property="id" value="0" name="person"/> 
 <jsp:setProperty property="name" value="unk" name="person"/>
 <jsp:setProperty property="email" value="unk" name="person"/>
 <jsp:setProperty property="address" value="unk" name="person"/>
 </jsp:useBean>
 <%=person %>
<h2>name is:<jsp:getProperty property="name" name="person"/></h2>
<%@include file="my.html" %>
<br>
name:${person.name}
<br>
name=<% person=(Person)request.getAttribute("person"); %><%=person.getName()%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<br>
<c:out value="${'tag what the fuck'}"></c:out>
${param.language(0)}
<br>

</body>
</html>