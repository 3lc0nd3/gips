<%@ page import="co.com.lh.smsfin.model.Operador" %>
<%@ page import="java.util.List" %>
<%@ page import="co.com.lh.smsfin.dao.FacDAO" %>


<%--<h3>Mensajes por Lotes</h3>--%>


        <table width="80%" border="0">
            <tr>
                <td width="30%">
                    Archivos con mensajes pendientes
                </td>
                <td>
                    <%--<input type="button" onclick="getContentOfFile();">--%>
                    <select id="archivosMenu2" onchange="getContentOfFile2();"></select>
                </td>
                <td>
                    <input type="button" style="background-color:#ffcccc;" value="Borrar Archivo" onclick="borrarArchivoCompleto();" >
                </td>
                <td>
                    <%--<input type="button" value="Recargar Archivo" onclick="recargarArchivoSmsEnProceso();" >--%>
                </td>
            </tr>
        </table>
        
        <br>
        <form id="loteForm">
            <div class="esquinasRedondas" style="width:99%">
                <table width="220" border="0" >
                    <tr>
                        <td>Modem</td>
                        <td>Cantidad</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>
                            <input id="idLote" type="hidden">
                            <select id="idModemLote"></select>
                        </td>
                        <td>
                            <input id="cantidadLote" maxlength="5" size="3">
                        </td>
                        <td>
                            <input type="button" value="Agregar Cantidad" onclick="guardarLote();">
                        </td>
                    </tr>
                </table>
                <br>
                <table border="0" align="center" width="95%">
                    <tr>
                        <th class="myTh">Id Lote</th>
                        <th class="myTh">Archivo</th>
                        <th class="myTh">Modem</th>
                        <th class="myTh">Estado</th>
                        <th class="myTh">Cantidad</th>
                        <th class="myTh">Enviados</th>
                        <th class="myTh">En Proceso</th>
                        <th class="myTh">Funciones</th>
                    </tr>
                    <tbody id="lotesTBody">

                    </tbody>
                    <tfoot>
                    <tr>
                        <td align="right">&nbsp;</td>
                        <td align="center" class="myTh">Total en Lotes</td>
                        <td align="right" class="tablaZebra1"><span id="sumaLote">0</span></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="right">&nbsp;</td>
                        <td align="center" class="myTh">Faltan</td>
                        <td align="right" class="tablaZebra1"><span id="faltanLote">0</span></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="right">&nbsp;</td>
                        <td align="center">&nbsp;</td>
                        <td align="right">
                            <input type="button" value="Enviar SMS" onclick="enviarSMSPendientes();">
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    </tfoot>
                </table>

                <br>
            </div>
        </form>

<%
    FacDAO facDAO = (FacDAO) application.getAttribute("smsManager");
    List<Operador> operadores = facDAO.getOperadores();

%>

        <table border="0" width="90%" >
            <form id="archivoContenido2Form">
            <thead>
            <tr>
                <td colspan="7" class="myth" align="center">
                    <span id="archivoContenidoTitle2" style="font-size:14px;">Seleccione Archivo</span>
                </td>
            </tr>
<%
    for (Operador operador : operadores) {
%>
            <tr>
                <td class="tablaZebra2" colspan="4">
                    &nbsp;
                </td>
                <td class="myth" colspan="2">
                    <%=operador.getNombreOperador()%>
                </td>
                <td class="myth">
                    <input size="7" value="0" id="totalOp<%=operador.getIdOperador()%>" readonly/>
                </td>
            </tr>
<%
    }
%>
            <tr>
                <td class="tablaZebra2" colspan="4">
                    &nbsp;
                </td>
                <td class="myth" colspan="2">
                    Total Para Enviar
                </td>
                <td class="myth">
                    <input size="7" value="0" id="totalSMS" readonly/>
                </td>
            </tr>
            <tr>
                <th class="myTh">
                    Id
                </th>
                <th class="myTh">
                    N&uacute;mero
                </th>
                <th class="myTh">
                    Mensaje
                </th>
                <th class="myTh">
                    Operador
                </th>
                <th class="myTh">
                    Lote
                </th>
                <th class="myTh">
                    Estado
                </th>
                <th class="myTh">
                    Funciones
                </th>
            </tr>
            </thead>
            </form>
            <tbody id="archivoContenidoTbody2">

            </tbody>
        </table>




<script type="text/javascript">
    getArchivosMenuParaEnviar("archivosMenu2");
    getMenuModems("idModemLote");
</script>