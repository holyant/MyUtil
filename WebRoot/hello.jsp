<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'hello.jsp' starting page</title>
    
	<script type="text/javascript" src="js/jquery-1.4.3.js"></script>
	
	<script type="text/javascript">
		$(function(){
			//location.href = "json";
			//$.getJSON("json?data="+new Date(),function(json){
			//	alert("holyant:"+json.test);
			//})
		});
		//window.onload = function(){
		//	alert(document.getElementById("test"));
		//};
			
		
	</script>
  </head>
  
  <body> 
    </br>application.getRealPath("gridTest.jsp"):<%=application.getRealPath("hello.jsp") %>
    </br>new java.io.File(application.getRealPath(request.getRequestURI())).getParent():<%=new java.io.File(application.getRealPath(request.getRequestURI())).getParent()%>
    </br>request.getSession().getServletContext().getRealPath("hello.jsp"):<%=request.getSession().getServletContext().getRealPath("")%>
    </br>${db.properties }
    </br><input type="text" value="lihao" id="test"><br>
    
    <s:debug></s:debug>
  </body>
</html>
