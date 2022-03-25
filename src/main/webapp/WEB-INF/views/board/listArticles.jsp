<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<c:set var="now" value="<%=new java.util.Date()%>"	/>
<fmt:formatDate  var="nowday" value="${now}" type="DATE" pattern="yyyy-MM-dd"/>
<%
  request.setCharacterEncoding("UTF-8");
%>  
<!DOCTYPE html>
<html>
<head>
 <style>
   .cls1 {text-decoration:none;}
   .cls2{text-align:center; font-size:30px;}
   .no-uline{text-decoration:none}
  </style>
  <meta charset="UTF-8">
  <title>글목록창</title>
</head>
<script>
	function fn_articleForm(isLogOn,articleForm,loginForm){
	  if(isLogOn != '' && isLogOn != 'false'){
	    location.href=articleForm;
	  }else{
	    alert("로그인 후 글쓰기가 가능합니다.")
	    location.href=loginForm+'?action=/board/articleForm.do';
	  }
	}
</script>
<body>
<table align="center" border="1"  width="80%"  >
  <tr height="10" align="center"  bgcolor="#8f789f">
     <td >글번호</td>
     <td >작성자</td>              
     <td >제목</td>
     <td >작성일</td>
     <td>조회수</td> <!-- 조회수 컬럼 추가 -->
  </tr>
<c:choose>
  <c:when test="${articlesMap.articlesList == null }" >
    <tr  height="10">
      <td colspan="4">
         <p align="center">
            <b><span style="font-size:9pt;">등록된 글이 없습니다.</span></b>
        </p>
      </td>  
    </tr>
  </c:when>
  <c:when test="${articlesMap.articlesList !=null }" >
    <c:forEach  var="article" items="${articlesMap.articlesList }" varStatus="articleNum" >
     <tr align="center" style = "<c:if test= '${ article.notice == 1}'>background-color : #cccccc</c:if>">	<!-- 공지사항 글이면 색추가 -->
	<td width="5%" >${articleNum.count}</td>
	<td width="10%" >${article.id }</td>
	<td align='left'  width="35%">
	  <span style="padding-right:30px"></span>
	   <c:choose>
	      <c:when test='${article.lvl > 1 }'>  
	         <c:forEach begin="1" end="${article.lvl }" step="1">
	              <span style="padding-left:20px"></span>    
	         </c:forEach>
	         <span style="font-size:12px; border-radius: 30%; color:red">[답변]</span>
                   <a class='cls1' href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
	          </c:when>
	          <c:otherwise>
	          <c:if test= '${ article.notice == 1}'> <span style="font-size:12px; border-radius: 30%; color:#7bcabf" >[공지사항]</span></c:if> <!-- 공지사항글이면 [공지사항] 말머리 추가 -->
	            <a class='cls1' href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title }</a>
	           <c:if test= '${ nowday == article.writeDate}'> <span style="font-size:12px; border-radius: 30%; color:red" ><img src="../resources/image/new.png" width="15" height="15"></span></c:if>
	          </c:otherwise>
	        </c:choose>
	  </td>
	  <td  width="10%" >${article.writeDate}</td> 
	  <td width="5%" >${article.readCount}</td>	<!-- 조회수 컬럼 -->
	</tr>
    </c:forEach>
     </c:when>
    </c:choose>
</table>
<div style="font-size:15px"> <!-- 페이지 기능 -->
 <c:if test="${articlesMap.total != null }" >
      <c:choose>
        <c:when test="${articlesMap.total >100 }">  <!-- 글 개수가 100 초과인경우 -->
	      <c:forEach   var="page" begin="1" end="10" step="1" >
	         <c:if test="${articlesMap.section >1 && page==1 }">
	          <a class="no-uline" href="${contextPath }/board/listArticles.do?section=${articlesMap.section-1}&pageNum=${(articlesMap.section-1)*10 +1 }">&nbsp; pre </a>
	         </c:if>
	          <a class="no-uline" href="${contextPath }/board/listArticles.do?section=${articlesMap.section}&pageNum=${page}">${(articlesMap.section-1)*10 +page } </a>
	         <c:if test="${page ==10 }">
	          <a class="no-uline" href="${contextPath }/board/listArticles.do?section=${articlesMap.section+1}&pageNum=${articlesMap.section*10+1}">&nbsp; next</a>
	         </c:if>
	      </c:forEach>
        </c:when>
        <c:when test="${articlesMap.total ==100 }" >  <!--등록된 글 개수가 100개인경우  -->
	      <c:forEach   var="page" begin="1" end="10" step="1" >
	        <a class="no-uline"  href="${contextPath }/board/listArticles.do?section=${articlesMap.section}&pageNum=${page}">${page } </a>
	      </c:forEach>
        </c:when>
        
        <c:when test="${articlesMap.total< 100 }" >   <!--등록된 글 개수가 100개 미만인 경우  -->
	      <c:forEach   var="page" begin="1" end="${articlesMap.total/10 +1}" step="1" >
	         <c:choose>
	           <c:when test="${page==pageNum }">
	            <a class="sel-page"  href="${contextPath }/board/listArticles.do?section=${articlesMap.section}&pageNum=${page}">${page } </a>
	          </c:when>
	          <c:otherwise>
	            <a class="no-uline"  href="${contextPath }/board/listArticles.do?section=${articlesMap.section}&pageNum=${page}">${page } </a>
	          </c:otherwise>
	        </c:choose>
	      </c:forEach>
        </c:when>
      </c:choose>
    </c:if>
</div>    
<br>
<!-- <a  class="cls1"  href="#"><p class="cls2">글쓰기</p></a> -->
<a  class="cls1"  href="javascript:fn_articleForm('${isLogOn}','${contextPath}/board/articleForm.do', 
                                                    '${contextPath}/member/loginForm.do')"><p class="cls2">글쓰기</p></a>
</body>
</html>