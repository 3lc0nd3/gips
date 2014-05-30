<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.Format" %>
<%@ page import="co.com.lh.smsfin.model.PhpposLogEntrada" %>
<%@ page import="java.util.List" %>
<%@ page import="org.hibernate.classic.Session" %>
<%@ page import="java.util.Date" %>
<%--
  Created.
  User: edward
  Date: 29/05/2014
  Time: 10:49:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="posManager" class="co.com.lh.smsfin.dao.FacDAO" scope="application" />

<%
    Format f = new SimpleDateFormat("yyyy MMMMMM dd HH:mm:ss");

    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);

    Date date = calendar.getTime();

    System.out.println("date = " + date);

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
%>
<html>
<script type='text/javascript' src='/giposSync/dwr/interface/posRemoto.js'></script>
<script type='text/javascript' src='/giposSync/dwr/engine.js'></script>
<script type='text/javascript' src='/giposSync/dwr/util.js'></script>
<head><title>Sincronizar Pos: <%=idPos%>, <%=nombrePos%> Sincronizar </title></head>
<body>
<h1>Sincronizar Manual<br>Pos: <%=idPos%>, <%=nombrePos%> </h1>
<br>
<input onclick="syncManual();" id="bSync" value="Sync Manual" type="button" >
<br>
<h2> fecha de la &uacute;ltima actualizaci&oacute;n:  <%=fecha%></h2>

<script type="text/javascript">



    function syncManual(){
        botonEnProceso("bSync");
        posRemoto.sincronizaTodo(function(data){
//            alert(data);
            botonOperativo();
            if(data==1){
                alert("Sincronizo Bien");
            } else {
                alert("Problema en la Sincronizacion");
            }
        });
    }


    function botonEnProceso(idBoton){
        var bTmp = dwr.util.byId(idBoton);
        window["botonEnProcesoId"] = idBoton;
        window["botonEnProcesoValueOld"] = bTmp.value;
        bTmp.value = "Procesando";
        bTmp.disabled = true;
    }

    function botonOperativo(){
        var bTmp = dwr.util.byId(window["botonEnProcesoId"]);
//    alrt("bTmp = " + bTmp);
        bTmp.value = window["botonEnProcesoValueOld"];
        bTmp.disabled = false;
    }
</script>
</body></html>