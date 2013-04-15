
<form id="operadorForm">
    <table border="0">
        <tr>
            <td>
                Nombre:
            </td>
            <td>
                <input id="idOperador" value="0" size="2" type="hidden">
                <input id="nombreOperador" maxlength="20">
            </td>
        </tr>
        <tr>
            <td>
                Prefijos (separados por espacio):
            </td>
            <td>
                <input id="prefijoOperador" size="50" maxlength="100">
            </td>
        </tr>
        <tr>
            <td>
                Comentario:
            </td>
            <td>
                <textarea id="comentarioOperador" rows="3" cols="50"></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input id="bNuevoOp" type="button" value="Nuevo" onclick="limpiarOperadores();">
                <input id="bGuardarOp" type="button" value="Guardar" onclick="guardarOperador();">
                <input id="bCargarOp" type="button" value="Cargar" onclick="cargarOperadores();">
            </td>
        </tr>
    </table>
</form>

<table border="0" width="80%">
    <thead>
    <tr>
        <th class="myTh">Id</th>
        <th class="myTh">Operador</th>
        <th class="myTh">Prefijo</th>
        <th class="myTh">Comentario</th>
        <th class="myTh">Acciones</th>
    </tr>
    </thead>

    <tbody id="operadoresTBody">

    </tbody>
</table>