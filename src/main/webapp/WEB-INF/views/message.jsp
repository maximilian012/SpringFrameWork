<%@page import="mul.cam.a.dto.MemberDto"%>
<%@page import="mul.cam.a.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
String message = (String) request.getAttribute("msg");
if (message != null && !message.equals("")) {
	if (message.equals("MEMBER_YES")) {
%>
<script type="text/javascript">
		alert("성공적으로 가입되었습니다");
		location.href = "login.do";
		</script>
<%
} else {
%>
<script type="text/javascript">
		alert("가입되지 않았습니다 다시 가입해 주십시오");
		location.href = "regi.do";
		</script>
<%
}
}
%>



<%
MemberDto dto = (MemberDto)request.getAttribute("mem");
String loginOK = (String) request.getAttribute("loginOK");
if (loginOK != null && !loginOK.equals("")) {
	if (loginOK.equals("BBS_ADD_OK")) {
%>
<script type="text/javascript">
		alert("환영합니다!!! <%= dto.getName()%>님");
		location.href = "bbslist.do?id=" + <%= dto.getId()%>;
		</script>
<%
} else if(loginOK.equals("BBS_ADD_NO")) {
	%>
<script type="text/javascript">
		alert("로그인 실패");
		location.href = "login.do";
		</script>
<%
}
}
%>

<%
String bbswrite = (String) request.getAttribute("bbswrite");
if (bbswrite != null && !bbswrite.equals("")) {
	if (bbswrite.equals("writeOK")) {
%>
<script type="text/javascript">
		alert("글쓰기 성공");
		location.href = "bbslist.do";
		</script>
<%
} else if(bbswrite.equals("writeNO")) {
	%>
<script type="text/javascript">
		alert("글쓰기 실패");
		location.href = "bbswrite.do";
		</script>
<%
}
}
%>

<%
String update = (String) request.getAttribute("update");
if (update != null && !update.equals("")) {
	if (update.equals("updateOK")) {
%>
<script type="text/javascript">
		alert("수정 성공");
		location.href = "bbslist.do";
		</script>
<%
} else if(update.equals("updateNO")) {
	%>
<script type="text/javascript">
		alert("수정 실패");
		location.href = "bbsupdate.do";
		</script>
<%
}
}
%>
<%
String pdsupdate = (String) request.getAttribute("pdsupdate");
if (pdsupdate != null && !pdsupdate.equals("")) {
	if (pdsupdate.equals("updateOK")) {
%>
<script type="text/javascript">
		alert("수정 성공");
		location.href = "pdslist.do";
		</script>
<%
} else if(pdsupdate.equals("updateNO")) {
	%>
<script type="text/javascript">
		alert("수정 실패");
		location.href = "pdsupdate.do";
		</script>
<%
}
}
%>
<%
String delete = (String) request.getAttribute("delete");
if (delete != null && !delete.equals("")) {
	if (delete.equals("deleteOK")) {
%>
<script type="text/javascript">
		alert("삭제 성공");
		location.href = "bbslist.do";
		</script>
<%
} else if(delete.equals("deleteNO")) {
	%>
<script type="text/javascript">
		alert("삭제 실패");
		location.href = "bbsdetail.do";
		</script>
<%
}
}
%>
<%
String pdsdelete = (String) request.getAttribute("pdsdelete");
if (pdsdelete != null && !pdsdelete.equals("")) {
	if (pdsdelete.equals("deleteOK")) {
%>
<script type="text/javascript">
		alert("삭제 성공");
		location.href = "pdslist.do";
		</script>
<%
} else if(pdsdelete.equals("deleteNO")) {
	%>
<script type="text/javascript">
		alert("삭제 실패");
		location.href = "pdsdetail.do";
		</script>
<%
}
}
%>

<%
String bbsanswer = (String) request.getAttribute("bbsanswer");
if (bbsanswer != null && !bbsanswer.equals("")) {
	if (bbsanswer.equals("bbsanswerOK")) {
%>
<script type="text/javascript">
		alert("답글 성공");
		location.href = "bbslist.do";
		</script>
<%
} else if(bbsanswer.equals("bbsanswerNO")) {
	%>
<script type="text/javascript">
		alert("답글 실패");
		location.href = "bbsanswer.do";
		</script>
<%
}
}
%>




<%
String sessionOut = (String) request.getAttribute("sessionOut");
if (sessionOut != null && !sessionOut.equals("")) {
	
%>
<script type="text/javascript">
		alert("로그인 해주 십시오");
		location.href = "login.do";
		</script>
<%
} 
%>







