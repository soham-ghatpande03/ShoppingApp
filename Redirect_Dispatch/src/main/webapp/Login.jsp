<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Form Simple</title>
</head>
<body>
<table>

<form action="http://localhost:8080/Redirect_Dispatch/LoginCheckError" method="post">
<tr>
<td> Enter UID: </td> 
<td> <input type="text" name="u1"/></td>
</tr>

<tr>
<td> Enter Password: </td> 
<td>  <input type="password" name="p1"/></td>
</tr>


<tr>
<td colspan =1>  <input type="submit" value="LOGIN" /></td>
</tr>
<br/>	

<tr>
<td><h2>${cookie.msg1.value}</h2></td> 
</tr>



<tr>
<td> <h5><a href = "InsertForm.html"> New User? Register here Now!! </a></h5></td> 
</tr>

<tr>
<td><h2><a href = "GooglePage">Search For Products</a></h2></td> 
</tr>
</form>
</table>

</body> 
</html>	