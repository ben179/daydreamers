<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:genericpage>
  <h1>
    <spring:message code="label.users" />
  </h1>

  <table border="1" >
    <tr>
      <th width="100"><spring:message code="label.user.login" />
      </th>
      <th width="100"><spring:message code="label.user.firstname" />
      </th>
      <th width="100"><spring:message code="label.user.lastname" />
      </th>
      <th width="100"><spring:message code="label.user.email" />
      </th>
      <th width="100"><spring:message code="label.user.password" />
      </th>
      <th width="100"><spring:message code="label.user.image" />
      </th>
      <th width="100"><spring:message code="label.user.delete" />
      </th>
    </tr>
    <c:forEach var="user" items="${users}">
      <tr>
        <td><a href="<c:out value='/daydreamers/user/${user.login}'/>"><c:out value="${user.login}" /> </a></td>
        <td><c:out value="${user.firstName}" /></td>
        <td><c:out value="${user.lastName}" /></td>
        <td><c:out value="${user.email}" /></td>
        <td><c:out value="${user.password}" /></td>
        <td><img src="<c:out value='/daydreamers/user/${user.login}/image' />"/></td>
        <td><sf:form method="delete" action="/daydreamers/user/${user.login}"><input type="submit" value="<spring:message code='label.user.delete' />" /></sf:form></td>
      </tr>
    </c:forEach>
  </table>
  <a href="/daydreamers/user/new">Create a New User</a>
</t:genericpage>