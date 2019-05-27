<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix = "rms" uri = "/WEB-INF/tags/link.tld"%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">

  <title>RMS</title>
  <meta name="description" content="Index">
  <meta name="author" content="Mitrais">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
  <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
  <rms:link type="stylesheet" href="css/styles.css?v=1.0"/>

  <!--[if lt IE 9]>-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.js"></script>
	<script type="text/javascript">
		function validate()
		{
			var user = document.getElementById("username");
			var pass = document.getElementById("userpass");
			var valid = true;
			if(user.value.length<=0)
			{
				alert("Username is empty");
				valid = false;
			}else if(pass.value.length<=0)
			{
				alert("Password is empty");
				valid = false;
			}
			return valid;
		};
	</script>
  <![endif]-->
</head>

<body>
    <div class="mdl-layout mdl-js-layout mdl-color--grey-100">
    	<main class="mdl-layout__content">
    		<div class="mdl-card mdl-shadow--6dp">
    			<div class="mdl-card__title mdl-color--primary mdl-color-text--white">
    				<h2 class="mdl-card__title-text">Create</h2>
    			</div>
    	  	<div class="mdl-card__supporting-text">
    				<form action="${pageContext.request.contextPath}/CreateUserServlet" method="post" onsubmit="return validate();">
    					<div class="mdl-textfield mdl-js-textfield">
    						<input class="mdl-textfield__input" type="text" id="username" name="username" />
    						<label class="mdl-textfield__label" for="username">Username</label>
    					</div>
    					<div class="mdl-textfield mdl-js-textfield">
    						<input class="mdl-textfield__input" type="password" id="userpass" name="password" />
    						<label class="mdl-textfield__label" for="userpass">Password</label>
    					</div>
						<input class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" type="submit" value="Add">
    				</form>
    			</div>
<%--    			<div class="mdl-card__actions mdl-card--border">--%>
<%--    				<button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Save</button>--%>
<%--    			</div>--%>
    		</div>
    	</main>
    </div>
  <script src="js/scripts.js"></script>
</body>
</html>
