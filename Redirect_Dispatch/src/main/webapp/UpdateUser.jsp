<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="http://localhost:8080/Redirect_Dispatch/UpdateUserDetails" method="post">
UserID: <input type="text" name="u1"  value = "${newloggeduser.uid}" disabled ="disabled"/><br/><br/>
Password: <input type="password" name="p1" disabled ="disabled" value = "${newloggeduser.pass}"/><br/><br/>
First Name: <input type="text" name="f1" disabled ="disabled" value = "${newloggeduser.fname}"/><br/><br/>
Middle Name : <input type="text" name="m1" disabled ="disabled" value = "${newloggeduser.mname}"/><br/><br/>
Last Name: <input type="text" name="l1" disabled ="disabled" value = "${newloggeduser.lname}"/><br/><br/>
Email ID: <input type="text" name="e1" /><br/><br/>
Phone Number:<input type="number" name="n1" /><br/><br/>

<input type="submit" Value="UPDATE DETAILS"/>

</form>

</body>
</html>