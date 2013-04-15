
<form action="upload.jsp" enctype="multipart/form-data" method="post" onsubmit="startProgress();" target="frameRechazado">
    <table width="500" border="0">
        <tr bgcolor="#F8F8F8">
            <td colspan="2">Seleccione el archivo CSV con los datos de Celular y Mensaje</td>
        </tr>
        <tr>
            <td colspan="2">
                <input id="vinc_arch" name="vinc_arch" type="file"  size="30" />
                <input type="submit" name="button" id="uploadbutton" value="Enviar" />
                <br>
                <span id="msg"></span>
                <div id="progressBar" style="display: none;">

                    <div id="theMeter">
                        <div id="progressBarText"></div>

                        <div id="progressBarBox">
                            <div id="progressBarBoxContent"></div>
                        </div>
                    </div>
                </div>
                <br>
                <br>    
                <%--<input type="submit" value="Enviar"/></td><td><input type="button" value="Cancelar" name="cancelar"/>--%>

            </td>
        </tr>
            <td width="30%">
                Revisar archivos
            </td>
            <td>
                <%--<input type="button" onclick="getContentOfFile();">--%>
                <select id="archivosMenu" onchange="getContentOfFile();"></select>
            </td>
    </table>
</form>

                    <table border="0" width="80%">
                        <thead>
                        <tr>
                            <td colspan="5" class="myth" align="center">
                                <span id="archivoContenidoTitle" style="font-size:14px;">

                                </span>
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
                                Estado
                            </th>
                            <th class="myTh">
                                Funciones
                            </th>
                        </tr>
                        </thead>

                        <tbody id="archivoContenidoTbody">

                        </tbody>
                    </table>

<script type="text/javascript">
    getArchivosMenu("archivosMenu");
</script>

<iframe name="frameRechazado" id="frameRechazado" style="width:100%; height:0px; border:0px solid white;"></iframe>