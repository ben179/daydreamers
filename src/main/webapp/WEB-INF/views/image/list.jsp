<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:genericpage>
  <h1>
    <spring:message code="label.image.all" />
  </h1>
    <table border="1" >
    <c:forEach var="image" items="${images}">
      <tr>
        <td>
          <spring:message code="label.image.name" />:<c:out value="${image.name}" /><br/>
       	  <spring:message code="label.image.date" />:<c:out value="${image.dateAdded}" /><br/>
          <spring:message code="label.image.owner" />:<c:out value="${image.user.login}" /><br/>
        </td>
        <td>
          <c:forEach var="tag" items="${image.tags}">
            <sf:form method="delete" action="/daydreamers/image/${image.id}/tag/${tag.text}">
              <input type="submit" value="${tag.text}" />
            </sf:form>
          </c:forEach>
        </td>
        <td>
          <sf:form method="post" modelAttribute="tag" action="/daydreamers/image/${image.id}/tag">
            <sf:input path="text" size="15" id="user_login" />
            <input type="submit" value="<spring:message code='label.image.tags.add' />" />
          </sf:form>
        </td>
        <td>
          <a href="/daydreamers/image/${image.id}/content">
            <img src="<c:out value='/daydreamers/image/${image.id}/thumbnail'/>" width="${image.thumbWidth}" height="${image.thumbHeight}"/>
          </a>
        </td>
        <td><sf:form method="delete" action="/daydreamers/image/${image.id}"><input type="submit" value="<spring:message code='label.image.delete' />" /></sf:form></td>
      </tr>
    </c:forEach>
  </table>
</t:genericpage>