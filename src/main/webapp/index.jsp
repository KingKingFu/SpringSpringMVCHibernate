<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</head>
<body>
<h2>Hello World!</h2>
<%--<a href="${pageContext.request.contextPath }/dept/deptDemo.do">aa</a>--%>

<form action="${pageContext.request.contextPath }/dept/deptInput.do" method="post">
   部门名称 <input type="text" name="dname">
   部门编号 <input type="text" name="deptno">
   部门地址 <input type="text" name="loc">
    <input type="submit" value="添加dept">
</form>

<form action="${pageContext.request.contextPath }/dept/deptFind.do" method="post">
    部门编号 <input type="text" name="deptno">
    <input type="submit">
</form>

<a href="${pageContext.request.contextPath}/dept/find.do">显示dept</a>

</body>
</html>
