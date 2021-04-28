<%-- 
    Document   : strona
    Created on : 2021-04-28, 13:30:46
    Author     : rober
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, javax.sql.*, java.io.*, javax.naming.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Prezentacja komunikatu StatusPage.io CABP</h1>
        <jsp:useBean id="applicationScopeBean" class="mybean.UsingBeanScopeApplication" scope="request" />
    <%
        InitialContext ctx;
        DataSource ds;
        Connection conn;
        Statement stmt;
        ResultSet rs;
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/DefaultDataSource");
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM incidents");

            while(rs.next()) {
%>
<h3>Nazwa incydentu: <%= rs.getString("name") %></h3><br>
Status: <%= rs.getString("status") %></h3><br>
Data utworzenia: <%= rs.getString("created_at") %></h3>
<%  
    }
  }
  catch (SQLException se) {
%>
    <%= se.getMessage() %>
<%    
  }
  catch (NamingException ne) {
%>
    <%= ne.getMessage() %>
<%
  }
%>
</body>
</html>
