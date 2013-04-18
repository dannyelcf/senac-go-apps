<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body style="margin:0 auto; text-align:center;">
	<br/>	
	<h1>SENAC GO - Alunos</h1>	
	<hr/>

	<h2>Atualizar Aluno</h2>
	
	<form method="post" action="update" >
		<table style="margin:0 auto; text-align:center;">
			<tr>
				<td>
					Nome:
				</td>
				<td>
					<input type="text" style="width: 185px;" maxlength="30" name="name" id="name" 
						value="${aluno.name}" />
				</td>
			</tr>
			<tr>
				<td>
					E-mail:
				</td>
				<td>
					<input readonly="readonly" type="text" style="width: 185px;" maxlength="30" name="email" id="email" 
						value="${aluno.email}" />
				</td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align:right;">
					<!--<button><a href="/list">Cancelar</a></button>-->
					<a href="/list"><input type="button" name="cancelar" value="Cancelar" /></a>					
					<input type="submit" class="atualizar" title="Atualizar" value="Atualizar" />					
				</td>				
			</tr>
		</table>
	</form>
	
</body>
</html>