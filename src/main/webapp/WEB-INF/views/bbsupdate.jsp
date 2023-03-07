<%@page import="mul.cam.a.dto.BbsDto"%>
<%@page import="mul.cam.a.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
String seq = (String)request.getAttribute("seq");
BbsDto dto = (BbsDto)request.getAttribute("dto");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<style type="text/css">
th {
	background-color: #007bff;
	color:white;
}
pre{
	white-space: pre-wrap;
	word-break:break-all;
	overflow:auto;
}

</style>


</head>
<body>
	<form action="bbsupdateAf.do">
	
	<div id="add" class="container">
		<table border="1" width="500" class="table table-striped">
			<tr>
				<th>작성자</th>
				<td style="font-size: 22px;font-weight: bold;"><%=dto.getId()%><input type="hidden" name="id">
				<input type="hidden" name="seq" value="<%=dto.getSeq()%>">
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td style="font-size: 22px;font-weight: bold;"><input type="text" value='<%=dto.getTitle()%>' name="title"></td>
			</tr>
			<tr>
				<th>작성일</th>
				<td style="font-size: 22px;font-weight: bold;"><%=dto.getWdate()%> <input type="hidden" name="wdate"></td>
				
			</tr>
			<tr>
				<th>조회수</th>
				<td style="font-size: 22px;font-weight: bold;"><%=dto.getReadcount()%></td>
			</tr>
			<tr>
				<th>답글정보</th>
				<td style="font-size: 22px;font-weight: bold;"><%=dto.getRef()%>-<%=dto.getStep()%>-<%=dto.getDepth()%></td>
			</tr>
			<tr style="width: 500px; height: 300px;">
				<th>내용</th>
				<td style="font-size: 22px;font-weight: bold;"><textarea style="width: 100%; height: 100%" name="content" id="content"><%=dto.getContent()%></textarea></td>
			</tr>


		</table>
	</div>
	<br>
	<br>

	<div align="center">


		<%
		MemberDto login = (MemberDto)session.getAttribute("login");
		if (dto.getId().equals(login.getId())) {
		%>
		<input type="submit" class="btn btn-primary" value="수정하기">
		<% 
		}
		%>

	</div>
</form>
	<button type="button" onclick="history.back()" class="btn btn-primary">목록보기</button>

<script type="text/javascript">





</script>


</body>
</html>