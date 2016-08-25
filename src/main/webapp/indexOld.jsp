<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevent caching at the proxy server

    String msg = (String) request.getAttribute("msg");
    if(msg == null){
        msg = "";
    }
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Inicio SENDER</title>
    <link href="css/estilos.css" rel="stylesheet" type="text/css" />
    <link href="css/menuPrincipal.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
        function formReset()
        {
            document.getElementById("frmInicio").reset();
        }






    </script>
</head>

<body>

<div id="contenedor">
    <div id="header" align="center" style="text-align:left">
        <table width="800" border="0" cellspacing="0" cellpadding="0" class="logos_header_cent">
            <tr>
                <td width="768">
                    <img src="img/logos_header.jpg" width="525" height="75">
                </td>
                <td width="22" valign="top">
                    <%--<a href="auth.do?salir=1"><img src="img/log_out.jpg" width="22" height="22" border="0" title="Salir del Sistema"/></a>--%>
                </td>
            </tr>
        </table>
    </div><!--fin header-->
    <div id="mainContent" >


        <h2> Acceso al Sender</h2>
        <form name="frmInicio" id="frmInicio" action="auth.do" method="post">
            <input type="hidden" name="flag" id="flag" value="1"/>
            <fieldset>
                <legend>Ingrese sus Datos</legend>
                <p>&nbsp;</p>
                <table width="200" border="0">
                    <tr>
                        <td colspan="2">Usuario
                            <input type="text" name="login" id="login"
                            <%--value="usuario financreditos" onFocus="if(this.value=='usuario financreditos')this.value='';"--%>
                            <%--onblur="if(this.value=='')this.value='usuario financreditos';"--%>
                                    /></td>
                    </tr>
                    <tr>
                        <td colspan="2">Contraseña
                            <input type="password" name="pass" id="pass" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="enviar" id="enviar" value="Aceptar" /></td>
                        <td><input type="button" name="button" id="button" value="Cancelar" onClick="formReset()"/></td>
                    </tr>
                </table>
                <span style="color:red;">
                    <%=msg%>
                </span>

                <p>&nbsp;</p>
            </fieldset>
        </form>

        <p>

        </p>
        <p>&nbsp; </p>

        <jsp:include page="c_footer.jsp"/>
    </div>
</div>

<!-- end #container -->

</body>
</html>