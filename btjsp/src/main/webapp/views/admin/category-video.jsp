<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp" %>

<a href="${pageContext.request.contextPath }/admin/category/add">Add Category</a>
<table border="1" width="100%" >
	<tr>
		<th>STT</th>
		<th>Images</th>
		<th>VideoID</th>
		<th>Title</th>
		<th>active</th>
		<th>description</th>
		<th>views</th>
		<th>Action</th>
		
	</tr>


	<c:forEach items="${listvideo }" var="video" varStatus="STT">
		<tr>
			<td>${STT.index+1 }</td>
			<td>
					
				<c:if test="${video.poster.substring(0.5) != 'https' }">
					<c:url value="/image?fname=${video.poster } " var="imgUrl"></c:url>
				</c:if>
				
				<c:if test="${video.poster.substring(0.5) == 'https' }">
					<c:url value="${video.poster }" var="imgUrl"></c:url>
				</c:if>
				
				<img height="150" width="200" src="${imgUrl }" />
			</td>
			<td>${video.videoId}</td>
			<td>${video.title }</td>
			<td>
				<c:if test="${video.active == 1}">
					<span>Hoạt Động</span>
				</c:if>
				<c:if test="${video.active != 1}">
					<span>Khoá</span>
				</c:if>
			</td>
			<td>${video.description }</td>
			<td>${video.views }</td>
			<td>
				<a href="<c:url value='/admin/video/edit?id=${video.videoId}&categoryid=${video.category.categoryid}'/>">Sửa</a> 
				<a href="<c:url value='/admin/video/delete?id=${video.videoId}&categoryid=${video.category.categoryid}' />">Xóa</a>
			</td>
			
		</tr>
	</c:forEach>


</table>