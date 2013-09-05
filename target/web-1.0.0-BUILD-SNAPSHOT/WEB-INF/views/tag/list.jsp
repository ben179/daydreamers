<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>

<t:genericpage>
  <h1>
    <spring:message code="label.tag.all" />
  </h1>
  <c:forEach var="tag" items="${tags}">
    <table border="1">
      <tr>
      <td>
       <h2>${tag.text}</h2>
       <sf:form method="delete" action="/daydreamers/tag/${tag.id}">
          <input type="submit" value="<spring:message code='label.tag.delete' />" />
        </sf:form>        
        <sf:form method="delete" action="/daydreamers/tag/${tag.id}/images">
          <input type="submit" value="<spring:message code='label.tag.removeAll' />" />
        </sf:form>
       </td>
        <c:forEach var="image" items="${tag.taggedImages}">
          <td>
            <a href="/daydreamers/image/${image.id}/content"> 
                <img src="<c:out value='/daydreamers/image/${image.id}/thumbnail'/>" width="${image.thumbWidth}" height="${image.thumbHeight}" /> 
            </a>
         </td>
        </c:forEach>
      </tr>
    </table>
  </c:forEach>
</t:genericpage>