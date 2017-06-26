<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'gridTest.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="/ext/resources/css/ext-all.css">
	<script type="text/javascript" src="/ext/ext-all.js"></script>
	<script type="text/javascript" src="/ext/ext-lang-zh_CN.js"></script>
	<script type="text/javascript">
		Ext.onReady(function(){
			//创建表格数据
			var datas = [[100,'张三',24],[100,'张三',24],[100,'张三',24]];
			Ext.create('Ext.grid.Panel',{
				title:'简单Grid表格示例',
				renderTo:Ext.getBody(),
				width:200,
				height:130,
				frame:true,
				viewConfig:{
					forceFit:true,
					stripeRows:true
				},
				store:{
					fields:['id','name','age'],
					proxy:{
						type:'memory',
						data:datas,
						render:'array'
					},
					autoLoad:true
				},
				columns:[
				{header:"id",width:30,dataIndex:'id',sortable:true},
				{header:"id",width:30,dataIndex:'name',sortable:true},
				{header:"id",width:30,dataIndex:'age',sortable:true}
				]
			})
		})
	</script>
  </head>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>

