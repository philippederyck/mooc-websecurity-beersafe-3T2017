<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<c:set var="title" scope="request" value="Your Notes"/>
<%@include file="../includes/header.jsp"%>

<!-- start content -->

<div class="container">
	<div class="row justify-content-center mt-4">
		<h2>${title}</h2>
	</div>
	
	<div class="row justify-content-center mt-5">   
	    <div class="col-10 col-md-8 col-lg-6">
	    		<c:if test="${notes.size() == 0}">
				<p class="text-center">No notes found.</p>
			</c:if>
	        <div class="d-flex flex-column">
	            <c:forEach var="note" items="${notes}">
		            <div class="row justify-content-center note">
					    <div class="col-12">
					        <h5>${note.title} <a href="/EditNote?id=${note.id}"><i class="fa fa-pencil text-accent ml-2"></i></a></h5>
					        <p>${note.content}</p>
					        <p class="small"><i><fmt:formatDate value="${note.created}" pattern="yyyy-MM-dd HH:mm:ss" /> - ${note.getBeer().name}</i></p>
					    </div>
					</div>
				</c:forEach>
	        </div>
	    </div>
	</div>
	
</div>

<!-- end content -->

<%@include file="../includes/footer.jsp"%>