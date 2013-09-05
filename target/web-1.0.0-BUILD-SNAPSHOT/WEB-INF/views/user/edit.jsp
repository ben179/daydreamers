<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<t:genericpage>
  <h1>
    <spring:message code="label.user.edit" /> ${user.login} 
  </h1>
  <h2> Version : ${user.version} 
  </h2>
  <sf:form method="post" modelAttribute="user" action="/daydreamers/user/${user.login}">
    <table>
           <sf:hidden path="version" id="user_version"/>
           
<%--       <tr> --%>
<%--         <td><sf:label path="login"> --%>
<%--             <spring:message code="label.user.login" /> --%>
<%--           </sf:label></td> --%>
<%--         <td><sf:input path="login" size="15" id="user_login" /></td> --%>
<%--         <td><sf:errors path="login" delimiter=", " /></td> --%>
<%--       </tr> --%>
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
        <td><input type="submit" value="<spring:message code="label.update" />" /></td>
      </tr>
    </table>
  </sf:form>

<!--   <h1> -->
<%--     <spring:message code="label.image.gallery" /> --%>
<!--   </h1> -->
  
<%--   <sf:form method="POST" modelAttribute="image" action="/daydreamers/user/${user.login}/images" enctype="multipart/form-data"> --%>
<%--     <table> --%>
<%--       <tr> --%>
<%--         <td><sf:label path="name"> --%>
<%--             <spring:message code="label.image.name" /> --%>
<%--           </sf:label></td> --%>
<%--         <td><sf:input path="name" size="15" id="image_name" /></td> --%>
<%--         <td><sf:errors path="name" delimiter=", " /></td> --%>
<%--       </tr> --%>
<%--       <tr> --%>
<%--         <td> --%>
<%--             <spring:message code="label.image.file" /> --%>
<%--         </td> --%>
<%--         <td><input name="image" type="file" /></td> --%>
<%--       </tr> --%>
<%--       <tr> --%>
<%--         <td><input type="submit" value="<spring:message code="label.image.add" />" /></td> --%>
<%--       </tr> --%>
<%--     </table> --%>
<%--   </sf:form> --%>
  
  
<%--     <table border="1" > --%>
<%--     <tr> --%>
<%--       <th width="100"><spring:message code="label.image.name" /> --%>
<%--       </th> --%>
<%--       <th width="100"><spring:message code="label.image.date" /> --%>
<%--       </th> --%>
<%--       <th width="100"><spring:message code="label.image.content" /> --%>
<%--       </th> --%>
<%--       <th width="100"><spring:message code="label.image.delete" /> --%>
<%--       </th>       --%>
<%--     </tr> --%>
<%--     <c:forEach var="image" items="${user.images}"> --%>
<%--       <tr> --%>
<%--         <td><c:out value="${image.name}" /></td> --%>
<%--         <td><c:out value="${image.dateAdded}" /></td> --%>
<%--         <td><a href="/daydreamers/image/${image.id}/content"><img src="<c:out value='/daydreamers/image/${image.id}/thumbnail'/>" width="${image.thumbWidth}" height="${image.thumbHeight}"/></a></td> --%>
<%--         <td><sf:form method="delete" action="/daydreamers/user/${user.login}/images/${image.id}"><input type="submit" value="<spring:message code='label.image.delete' />" /></sf:form></td> --%>
<%--       </tr> --%>
<%--     </c:forEach> --%>
<%--   </table>  --%>
</t:genericpage>