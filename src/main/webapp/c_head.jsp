<%@ page import="co.com.lh.smsfin.model.Usuario" %>

<head>
    <link href="css/estilos.css" type="text/css" rel="stylesheet">
    <%----%>
    <link href="style/cssCalendar/jscal2.css" type="text/css" rel="stylesheet">
    <link href="style/cssCalendar/border-radius.css" type="text/css" rel="stylesheet">
    <%----%>
    <link href="style/cssCalendar/steel/steel.css" type="text/css" rel="stylesheet">
    <%----%>
    <script type="text/javascript" src="scripts/jsCalendar/jscal2.js"></script>
    <script type="text/javascript" src="scripts/jsCalendar/lang/es.js"></script>
    <%----%>
    <script type="text/javascript" src="scripts/prototype.js"></script>


    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/util.js'></script>
    <script type="text/javascript" src="scripts/script.js"></script>

    <script type="text/javascript" src="scripts/validation.js"></script>
    <link href="style/validation.css" rel="stylesheet" type="text/css" />

    <script type='text/javascript' src='dwr/interface/smsRemoto.js'></script>
    <script type='text/javascript' src='dwr/interface/UploadMonitor.js'> </script>

    <%--FILE UPLOAD--%>
    <script type='text/javascript' src='scripts/upload.js'> </script>

    <script type="text/javascript">

        /* Optional: Temporarily hide the "tabber" class so it does not "flash"
         on the page as plain HTML. After tabber runs, the class is changed
         to "tabberlive" and it will appear. */

        document.write('<style type="text/css">.tabber{display:none;}<\/style>');

        var tabberOptions = {
            /* Optional: instead of letting tabber run during the onload event,
             we'll start it up manually. This can be useful because the onload
             even runs after all the images have finished loading, and we can
             run tabber at the bottom of our page to start it up faster. See the
             bottom of this page for more info. Note: this variable must be set
             BEFORE you include tabber.js.
             */
            'manualStartup':true,

            /* Optional: code to run after each tabber object has initialized */

            'onLoad': function(argsObj) {
                /* Display an alert only after tab2 */
               /* if (argsObj.tabber.id == 'tab1') {
                    alert('Finished loading tab1!');
                }*/
            },

            /* Optional: code to run when the user clicks a tab. If this
             function returns boolean false then the tab will not be changed
             (the click is canceled). If you do not return a value or return
             something that is not boolean false, */

            'onClick': function(argsObj){
                onClickTab(argsObj);
            },

            /* Optional: set an ID for each tab navigation link */
            'addLinkId': true

        };
    </script>

    <script type="text/javascript" src="scripts/tabber.js"></script>
    <link rel="stylesheet" href="scripts/tabber.css" TYPE="text/css" MEDIA="screen">

    <%--<script type="text/javascript" src="scripts/alertBox.js"></script>--%>
    <%--<script type="text/javascript" src="scripts/dialog.js"></script>--%>
    <%----%>
    <%--<script type="text/javascript" src="scripts/effects.js"></script>--%>
    <%--<script type="text/javascript" src="scripts/validation.js"></script>--%>
    <%----%>
    <%----%>

    <%----%>
    <%
        Usuario user = (Usuario) session.getAttribute("user");
        String barraS = "";
        if(user!=null){
            barraS = user.getRolByIdRole().getNombreRol() + " - " + user.getNombreUsuario();

        }
    %>

    <title>
        SENDER Financreditos <%=barraS%>
    </title>


    <script type="text/javascript">
        window.history.forward();
        function noBack(){ window.history.forward(); }


        // UPLOAD MIENTRAS CONVIERTE
        var globalEstadoConversion = false;
        var archivoCargado = 0;

    </script>
</head>
<%--<body>--%>
<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">

    <%
    response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
%>


<div id="contenedor">
    <div id="header" align="center" style="text-align:left">
        <table width="800" border="0" cellspacing="0" cellpadding="0" class="logos_header_cent">
            <tr>
                <td width="768">
                         <img src="img/logos_header.jpg" width="525" height="75"></td>
                <td width="22" >
                    <a href="auth.do?salir=1">
                        <img src="img/log_out.jpg" width="22" height="22" border="0" title="Salir del Sistema"/></a>
                </td>
            </tr>
        </table>
    </div><!--fin header-->
    <div id = "mainContent">
        <h2>
            <%=barraS%>
        </h2>