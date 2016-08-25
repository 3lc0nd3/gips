<html>
<jsp:include page="c_head.jsp"/>

<%--<script type="text/javascript" src="scripts/tabber-minimized.js"></script>--%>
<%--<link rel="stylesheet" href="scripts/tabber.css" TYPE="text/css" MEDIA="screen">--%>

<div class="tabber" id="tab1">

    <div class="tabbertab">
        <h2>Simple</h2>
        <jsp:include page="mensajeSimple.jsp"/>
    </div>


    <div class="tabbertab">
        <h2>Lote</h2>
        <jsp:include page="mensajeLote.jsp"/>
    </div>

</div>



<jsp:include page="c_footer.jsp"/>
</html>