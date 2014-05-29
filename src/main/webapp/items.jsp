<%@ page import="co.com.lh.smsfin.model.PhpposItemsEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="co.com.lh.smsfin.model.PhpposAppConfigEntity" %>
<%@ page import="co.com.lh.smsfin.model.PhpposLogEntrada" %>
<%@ page import="java.text.Format" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.hibernate.classic.Session" %>
<%--
  Created.
  User: edward
  Date: 22/06/2012
  Time: 09:29:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="posManager" class="co.com.lh.smsfin.dao.FacDAO" scope="application" />
<%
    Format f = new SimpleDateFormat("yyyy MMMMMM dd HH:mm:ss");


    int idPos = posManager.getIdPos();

    String nombrePos = posManager.getAppConfig("address").getValue();

    Session session1 = posManager.getSessionFactory().openSession();
    List<PhpposLogEntrada> logEntradas = session1.createQuery(
            " from PhpposLogEntrada where tipo = 'U' order by id desc ").setMaxResults(1).list();
    session1.close();

    String fecha = "";
    if (logEntradas.size()>0) {
        fecha = f.format(logEntradas.get(0).getFechaCreacion());
    }

    List<PhpposItemsEntity> items = posManager.getHibernateTemplate().find(
            "from PhpposItemsEntity where quantity > 0 order by name ");
%>
<html>
<head><title>Pos: <%=idPos%>, <%=nombrePos%> Items </title></head>
<body>
<h1>Items en Pos: <%=idPos%>, <%=nombrePos%> </h1>
<h2> fecha de la &uacute;ltima actualizaci&oacute;n:  <%=fecha%></h2>
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