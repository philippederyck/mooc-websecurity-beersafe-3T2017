<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<c:set var="title" scope="request" value="BeerSafe"/>
<%@include file="../includes/header.jsp"%>

<!-- start content -->

<div class="container">
	<div class="row justify-content-center mt-4">
		<h2 class="col-12 text-center">BeerSafe</h2>
		<h5 class="col-12 text-center">never lose track of your beer tasting notes again!</h5>
	</div>
	
	<div class="row justify-content-center align-items-center mt-5">
		<div class="col-10 col-md-6 text-center">
			<img src="/img/beer_home.png" class="img-fluid img-blur">
			<!-- Image source: https://unsplash.com/photos/JKMnm3CIncw -->
		</div>
		<div class="col-10 col-md-6 p-2 pl-4">
			<p>Don't you hate having the perfect beer on a hot summer night, only to forget the name of the beer that took you to heaven? No more!</p>
			
			<p><b>BeerSafe</b> allows you to keep track of which beers you drink, rate them and add detailed tasting notes. Adding more beers will help you build up your personal taste profile.</p>
			
			<p>Beer is even better when enjoyed in the right company. Share your tasting notes with your friends, or find likeminded souls!</p>
		</div>
	</div>
	
	<div class="row justify-content-center mt-5 mb-5">
		<h4 class="col-12 text-center">BeerSafe is currently an invitation-only application for the most exquisite connoisseurs.</h4>
		<div class="col-12 text-center">
			<p>Lucky enough to have an account? <a class="text-accent" href="/Login">Login here</a>.</p>
		</div>
	</div>
</div>

<!-- end content -->

<%@include file="../includes/footer.jsp"%>