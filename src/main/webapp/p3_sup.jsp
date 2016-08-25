<html>
<jsp:include page="c_head.jsp"/>

<%--<script type="text/javascript" src="scripts/tabber-minimized.js"></script>--%>
<%--<link rel="stylesheet" href="scripts/tabber.css" TYPE="text/css" MEDIA="screen">--%>

<div class="tabber" id="tab1">

    <div class="tabbertab">
        <h2>Pendientes</h2>
        <jsp:include page="mensajesPorEnviar.jsp"/>
    </div>

</div>




<jsp:include page="c_footer.jsp"/>
</html>