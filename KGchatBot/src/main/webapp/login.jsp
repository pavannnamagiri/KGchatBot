<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Login/Sign Up</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Lobster&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="css/login.css">
</head>

<body>
	<div class="container">
		<div class="login-main">

			<div class="login-content">
			<!-- bootstrap pills for logina nd register toggle -->
				<ul class="nav nav-pills">
					<li class="active signup"><a href="#">Sign Up</a></li>
					<li class="login"><a href="#">Login</a></li>
				</ul>
				<form class="form-group" method="POST" action="registerServ">
					<input type="text" class="form-control username" name="username"
						placeholder="Name" required><br> <input type="email"
						class="form-control" name="email" placeholder="Email" required><br>
					<input type="password" class="form-control" name="pwd"
						placeholder="Password" required><br> <input
						type="submit" class="btn btn-success user-process center-block"
						value="Sign Up">
				</form>
			</div>
		</div>

	</div>
	<div class="registration-status">
		<div class="registration-status-container">
			<p></p>
		</div>
	</div>
	<!-- saving registration status  -->
	<%
		if (request.getAttribute("stat") == "N") {
	%>
	<script>
		localStorage.setItem('stat', 'N');
	</script>

	<%
		}
	%>
	<!-- saving login status  -->
	<%
		if (request.getAttribute("stat") == "Y") {
	%>
	<script>
		localStorage.setItem('stat', 'Y');
	</script>

	<%
		}
	%>
	<%
		if (request.getAttribute("loginStat") == "N") {
	%>
	<script>
		localStorage.setItem('loginStat', 'N');
	</script>

	<%
		}
	%>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	<script src="https://apis.google.com/js/api.js"></script>
	<script src="js/login.js"></script>
</body>

</html>