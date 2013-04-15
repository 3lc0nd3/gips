<form id="modemForm">
    <table>
        <tr>
            <td>Imei:</td>
            <td>
                <%--<input name="idModem" size="2" type="text">--%>
                <input name="idModem" size="2" type="hidden" value="0">
                <input name="imeiModem" size="20" maxlength="30">
            </td>
        </tr>
        <tr>
            <td>Operador:</td>
            <td>
                <select id="idOperadorI"></select>
            </td>
        </tr>
        <tr>
            <td>M&aacute;quina:</td>
            <td>
                <select id="idMaquinaI"></select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input id="bNuevoModem" type="button" value="Nuevo" onclick="limpiarModem();">
                <input id="bGuardarModem" type="button" value="Guardar" onclick="guardarModem();">
                <input id="bCargarModem" type="button" value="Explorar Modems Conectados" onclick="recargarModemsSystema();">
                <input id="bprocesosModem" type="button" value="Revisar Procesos SMS" onclick="revisarProcesosSMS();">
                <img id="cargaModemImg" src="images/spacer.gif" width="1" height="1" alt="Cargando">
            </td>
        </tr>
    </table>
</form>

<table border="0" width="80%">
    <thead>
    <tr>
        <th class="myTh">Id</th>
        <th class="myTh">IMEI</th>
        <th class="myTh">Operador</th>
        <th class="myTh">M&aacute;quina</th>
        <th class="myTh">Id Interno SMS</th>
        <th class="myTh">Enlace</th>
        <th class="myTh">Operando</th>
        <th class="myTh">Proceso</th>
        <th class="myTh">Funciones</th>
    </tr>
    </thead>

    <tbody id="modemsTBody">

    </tbody>
</table>