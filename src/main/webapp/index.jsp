<%@page import="com.ipartek.formacion.controller.listener.InitListener"%>
<%@ include file="includes/head.jsp" %>

	<!--  Etiquetas para que se visualice a los usuarios -->
	<h1>ongi etorri web app</h1>
	
	<h2>USARIOS CONECTADOS:<%=InitListener.contadorUsuarios %></h2>
	
	<% 
	for(int i=1; i < 7 ; i++ ){
		out.print("<h"+i+">Cabecera"+i+"</h"+i+">");		
	} 
	%>
	
	<a href="candidato/list.jsp">enlace para listar Candidatos</a>

<%@ include file="includes/footer.jsp" %>

