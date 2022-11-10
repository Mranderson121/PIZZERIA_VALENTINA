<?xml version="1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/html1/DTD/xhtml1-strict.dtd">
	
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
<%@ page import="dao.ClassDao" %>

<html xmlns = "http://www.w3.org/1999/xhtml">

<head>
<title>Update pizza data</title>
<link rel="stylesheet" type="text/css" href="style.css" media="screen"/>
</head>


<body>
	<% Pizza p = (Pizza) request.getAttribute("pizza");%>
	<div class="col-12">
	
	<form action="Update" method="get">
		<div class="col-5" id="box" style="height:500px;overflow-y: scroll; border:0px solid black;">
			<p>Seleziona un impasto:</p>
			<% List<Impasto> listaImpasti  = (List<Impasto>) request.getAttribute("listaImpasti");
			for(Impasto i: listaImpasti){ %>
				<div class="col-4">
				 <label>
				 <% if(p.getImpasto().getId() == i.getId()){%>
				 	<input type="radio" name="selectedImpasto" value="<%=i.getId()%>" checked/>
				 <% } else { %>
				 	<input type="radio" name="selectedImpasto" value="<%=i.getId()%>" />
				 <% }%>
				 <%= i.getNome()%></label>
				</div>
			<%} %>
		</div>
		<div class="col-1"></div>
		<div class="col-6" id="box" style="height:500px;overflow-y: scroll; border:0px solid black;">
			<p>Seleziona gli ingredienti:</p>
			<% 
			List<Ingrediente> listaIngredienti  = (List<Ingrediente>) request.getAttribute("listaIngredienti");
			for(Ingrediente i: listaIngredienti){ %>
				<div class="col-4" style="font: 15px Arial, Helvetica, sans-serif;"> 
				<label>
					<% if(p.getIngrediente().contains(i)){ %>
						<input type="checkbox" name="selectedIngrediente" value=" <%= i.getId()%>" checked/>
					<%}else{ %>
						<input type="checkbox" name="selectedIngrediente" value=" <%= i.getId()%>"/>
					<%} %>
				 <%= i.getNome()%></label>
				</div>
			<%} %>
		</div>
		<lable>Nome pizza: <input type="text" name="nomePizza" value="<%= p.getNome() %>"/></lable>
		<input type="hidden"  name="idPizza" value="<%= p.getId() %>"/>
		<input type="submit" value="aggiorna pizza" style=" width:200px; font: 20px Arial, Helvetica, sans-serif;"/>	
	</form></div>
	
</body>
</html>