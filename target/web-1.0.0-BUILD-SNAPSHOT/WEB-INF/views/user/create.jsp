<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:genericpage>
  <h1>
    <spring:message code="label.user" />
  </h1>
  <sf:form method="POST" modelAttribute="user" action="/daydreamers/user" enctype="multipart/form-data">
    <table>
      <tr>
        <td><sf:label path="login">
            <spring:message code="label.user.login" />
          </sf:label></td>
        <td><sf:input path="login" size="15" id="user_login" /></td>
        <td><sf:errors path="login" delimiter=", " /></td>
      </tr>
      <tr>
        <td><sf:label path="firstName">
            <spring:message code="label.user.firstname" />
          </sf:label></td>
        <td><sf:input path="firstName" size="15" id="user_firstname" /></td>
        <td><sf:errors path="firstName" delimiter=", " /></td>
      </tr>
      <tr>
        <td><sf:label path="lastName">
            <spring:message code="label.user.lastname" />
          </sf:label></td>
        <td><sf:input path="lastName" size="15" id="user_lastname" /></td>
        <td><sf:errors path="lastName" delimiter=", " /></td>
      </tr>
      <tr>
        <td><sf:label path="email">
            <spring:message code="label.user.email" />
          </sf:label></td>
        <td><sf:input path="email" size="15" id="user_email" /></td>
        <td><sf:errors path="email" delimiter=", " /></td>
      </tr>
      <tr>
        <td><sf:label path="password">
            <spring:message code="label.user.password" />
          </sf:label></td>
        <td><sf:password path="password" size="15" id="user_password" /></td>
        <td><sf:errors path="password" delimiter=", " /></td>
      </tr>
      <tr>
        <td><input type="submit" value="<spring:message code="label.save" />" /></td>
      </tr>
    </table>
  </sf:form>
</t:genericpage>