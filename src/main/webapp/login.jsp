<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/html1/DTD/xhtml1-strict.dtd">
	
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<html xmlns = "http://www.w3.org/1999/xhtml">

<!-- pagina di login  -->
<head>
	<title>Login users page</title>
	<link rel="stylesheet" type="text/css" href="style.css" media="screen"/>
</head>
<body>

	<div >
		<table class="col-6" id="loginBox" >
			<tr>
				<td style="background-color: #d1d646; " >
					<h1 style="color: #616161;"><br/>Login Utente<br/></h1>
				</td>
				
			</tr>
			
			<tr style="background-color: transparent; ">
				<td style="background-color: transparent; ">
					
						<div class="col-2">
							<img title="login" src="images/pizza.png" alt="login image" width="200" height="200"
							style="margin-left: auto; margin-right: auto;"/>
						</div>
						<div class="col-10">
							<br/>
								<form action="LoginUtente" method="post">
									<lable>Username</lable>
									<input type="text" name="username"/>
									<lable>Password</lable>
									<input type="password" name="password"/>
									<input type="submit" value="login"/>	
								</form>
						</div><br/>
					
				</td>
			</tr>
			
			<%
			if(ClassDao.isLoginError()) {%>
				<div><h1>Login errato</h1></div>
			<%} %>
		</table>
	
			
	</div>
</body>
</html>