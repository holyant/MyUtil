<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript"  src="/js/jquery-1.4.3.js"></script>
	
	<script type="text/javascript">
		function test(){
			$.getJSON("a/json?"+new Date(), function(json){
			  alert("JSON Data: " + json.student.name);
			});
		}
	</script>
  </head>
  
  <body>
    <button onclick="javascript:test()">里好</button><br>
  </body>
</html>
