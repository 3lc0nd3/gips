<%@ page import="co.com.lh.smsfin.model.PhpposItemsEntity" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 22/06/2012
  Time: 09:29:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="posManager" class="co.com.lh.smsfin.dao.FacDAO" scope="application" />
<%
    List<PhpposItemsEntity> items = posManager.getHibernateTemplate().find(
            "from PhpposItemsEntity where quantity > 0 order by name ");
%>
<html>
<head><title>Simple jsp page</title></head>
<body>
<table border="1">
    <tr>
        <th>id</th>
        <th>item</th>
        <th>cantidad</th>
    </tr>
    <%
        for (PhpposItemsEntity item : items){
    %>
    <tr>
        <td align="right"><%=item.getItemId()%></td>
        <td><%=item.getName()%></td>
        <td align="right"><%=item.getQuantity()%></td>
    </tr>
    <%
        } //END FOR ITEMs
    %>
</table>


</body>
</html>