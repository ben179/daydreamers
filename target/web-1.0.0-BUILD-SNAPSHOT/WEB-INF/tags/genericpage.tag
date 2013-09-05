<%@tag description="Page Template" pageEncoding="UTF-8" %>
<html>
  <head>
    <title>daydreamers</title>
  </head>
  <body>
    <div id="pageheader">
      <a href="/daydreamers/spring_security_login">Login</a> |
      <a href="/daydreamers/user/">All users</a> |
      <a href="/daydreamers/image/">All images</a> | 
      <a href="/daydreamers/tag/">All tags</a> | 
      <a href="/daydreamers/j_spring_security_logout">Logout</a>
    </div>
    <div id="pagebody">
      <jsp:doBody />
    </div>
  </body>
</html>