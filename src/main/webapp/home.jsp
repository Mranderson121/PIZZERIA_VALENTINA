<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/html1/DTD/xhtml1-strict.dtd">
	
<%@ page import="java.util.*" %>
<%@ page import="com.pizzeria.restfulcrud.model.*" %>
<%@ page import="com.pizzeria.restfulcrud.dao.ClassDao" %>

<html xmlns = "http://www.w3.org/1999/xhtml">


<head>
<title>Home page</title>
<link rel="stylesheet" type="text/css" href="style.css" media="screen"/>
</head>

<body>
	<% Utente u = ClassDao.getUtenteAttivo();%>
	<h2>Ciao <%= u.getUsername()%> seleziona la tua pizza!</h2>
	
	
	<div class="col-12"><form action="HomeServlet" method="get">
		<div class="col-5" id="box" style="height:300px;overflow-y: scroll; border:0px solid black;">
			<p>Seleziona un impasto:</p>
			<% List<Impasto> listaImpasti  = (List<Impasto>) request.getAttribute("listaImpasti");
			for(Impasto i: listaImpasti){ %>
				<div class="col-4">
				 <label>
				 <input type="radio" name="selectedImpasto" value="<%=i.getId()%>">
				 <%= i.getNome()%></label>
				</div>
			<%} %>
		</div>
		<div class="col-1"></div>
		<div class="col-6" id="box" style="height:300px;overflow-y: scroll; border:0px solid black;">
			<p>Seleziona gli ingredienti:</p>
			<% 
			List<Ingrediente> listaIngredienti  = (List<Ingrediente>) request.getAttribute("listaIngredienti");
			for(Ingrediente i: listaIngredienti){ %>
				<div class="col-4" style="font: 15px Arial, Helvetica, sans-serif;"> 
				<label>
					<input type="checkbox" name="selectedIngrediente" value="<%=i.getId()%>">
				 <%= i.getNome()%></label>
				</div>
			<%} %>
		</div>
		<lable>Nome pizza: <input type="text" name="nomePizza"/></lable>
		<input type="submit" value="carica pizza" style=" width:200px; font: 20px Arial, Helvetica, sans-serif;"/>	
	</form></div>
	

	<h2><br/>Pizze Utente:<br/></h2>
	<table id="box" style="font: 10px; table-layout:auto;"  >
			
			<tr>
				<td style="background-color: #e8e8e8" > <p>modifica parametri </p> </td>
				<td style="background-color: #e8e8e8" ><p>nome pizza</p></td>
				<td style="background-color: #e8e8e8"><p>impasto</p></td>
				<td style="background-color: #e8e8e8"><p>ingredienti</p></td>
			</tr>
			<% 
				if(u.getPizza() != null){
					for(Pizza p: u.getPizza()){ 
			%>
			<tr>
				<td>
					<form action="Delete" method="post">
						<input type="hidden" name="idPizza" value="<%=p.getId()%>"/>
						<input type="submit" value="cancella pizza" style=" width:100px;" />	
					</form>
					<form action="GoToUpdatePage" method="post">
						<input type="hidden" name="idPizza" value="<%=p.getId()%>"/>
						<input type="submit" value="aggiorna dati" style=" width:100px;"/>	
					</form>
				</td>
				<td><p> <%= p.getNome() %></p></td>
				<td><p> <%= p.getImpasto().getNome() %></p></td>
				<td>
					<ul>
					<% for(Ingrediente i : p.getIngrediente()){ %>
			           <li><%= i.getNome() %></li> 
			        <%} %>
					
					</ul>
					
				</td>
			</tr>
			<%	
					}
				} 
			%>
	</table>
</body>
</html>