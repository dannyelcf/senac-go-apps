<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body style="margin:0 auto; text-align:center;">
	<br/>	
	<h1>SENAC GO - Alunos</h1>	
	<hr/>
	
	<h2>Listagem de Alunos</h2> 
	
	<table border="1" style="margin:0 auto; text-align:center;">
		<thead>
			<tr>
				<td>Nome</td>
				<td>E-mail</td>
				<td>Criado em</td>
				<td>Acao</td>
			</tr>
		</thead>
		<c:forEach var="aluno" items="${alunoList}" >
			<tr>
				<td>${aluno.name}</td>
				<td>${aluno.email}</td>
				<td>${aluno.createdAt}</td>
				<td><a href="/update?email=${aluno.email}">Atualizar</a> | <a href="/delete?email=${aluno.email}">Excluir</a></td>
			</tr>
		</c:forEach>		
	</table>
	<br/>
	<a href="/create">Novo Aluno</a>
</body>
</html>