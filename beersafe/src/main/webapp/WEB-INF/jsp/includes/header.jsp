<%@taglib prefix="e" uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
	    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    	<link rel="shortcut icon" href="img/beersafe.ico">
	
		<link rel="stylesheet" href="css/custom.css">
		<link rel="stylesheet" href="https://cdn.beersafe.eu/css/font-awesome.min.css">
		<link rel="stylesheet" href="https://cdn.beersafe.eu/css/bootstrap.min.css">		
	
		<script src="https://cdn.beersafe.eu/js/jquery-3.2.1.min.js"></script>
		<script src="https://cdn.beersafe.eu/js/popper.min.js"></script>
		<script src="https://cdn.beersafe.eu/js/tether.min.js"></script>
		<script src="https://cdn.beersafe.eu/js/bootstrap.min.js"></script>
	
		<title>${title}</title>
	</head>
	<body>
		<nav class="navbar navbar-toggleable-md navbar-inverse">
			<button class="navbar-toggler navbar-toggler-right" 
					type="button" 
					data-toggle="collapse" 
					data-target="#navbarSupportedContent" 
					aria-controls="navbarSupportedContent" 
					aria-expanded="false" 
					aria-label="Toggle navigation">
    			<span class="navbar-toggler-icon"></span>
  			</button>
  
			<span class="navbar-brand">
				<img src="img/logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
				<span class="ml-2">Beer Safe</span>
			</span>

			<div class="collapse navbar-collapse ml-4" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item">
						<a class="nav-link" href="/Home">Home</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/Taste">Tasting Suggestion</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/Beers">List of Beers</a>
					</li>
					
					<c:if test="${requestScope.session.isAuthenticated()}">
	                    <li class="nav-item">
							<a class="nav-link" href="/Notes">My Notes</a>
						</li>
					</c:if>
				</ul>
                
                <c:choose>
    					<c:when test="${requestScope.session.isAuthenticated()}">
    						<a class="nav-item btn btn-primary" href="/Logout">Logout ${requestScope.session.authenticatedUser.name}</a>
    					</c:when>    
    					<c:otherwise>
    						<a class="nav-item btn btn-primary" href="/Login">Login</a>
    					</c:otherwise>
    				</c:choose>
			</div>
		</nav>
	
	
		<div id="maincontainer" class="container-fluid">