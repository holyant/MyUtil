<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>excel文件导入测试</title>
    <script type="text/javascript"  src="/util/js/jquery-1.4.3.js"></script>
	

	<!--检查文件是否存在 -->
	<!--检查扩展名 -->
	<script type="text/javascript">
		$(document).ready(function(){
			var columnString = '[{"name":"svcNum","type":"DECIMAL","size":"64","nullable":"false"},'
			+'{"name":"prodId","type":"DECIMAL","size":"10","nullable":"false"},'
			+'{"name":"acceptTime","type":"TIMESTAMP","pattern":"yyyy/MM/dd HH:mm:ss","size":"","nullable":"false"},'
			+'{"name":"chnlType","type":"VARCHAR","size":"64","nullable":"true"},'
			+'{"name":"agent1","type":"VARCHAR","size":"64","nullable":"true"},'
			+'{"name":"agent2","type":"VARCHAR","size":"64","nullable":"true"},'
			+'{"name":"dataSrc","type":"DECIMAL","size":"1","nullable":"false"}]';
 			$("input[name=columnString]").val(columnString);
			alert(columnString);
		});
	</script>
</head>
<body>
	<form action="/util/test/excel!upload" name="excelFile" method="post" enctype="multipart/form-data">
		<input type="file" name="file"></input>
		<input type="submit" value="提交"/>
		<input type="hidden" name ="columnString"/>
	</form>
</body>
</html>