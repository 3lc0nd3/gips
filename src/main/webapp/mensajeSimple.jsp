

<form id="form1" name="form1" method="post" action="">
    <table width="500" border="0">
        <tr bgcolor="#F8F8F8">
            <td colspan="2">
                Texto del Mensaje &nbsp;&nbsp;&nbsp;&nbsp; <span id="largo"></span>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <textarea onkeyup="cuentaLetras();" name="mensaje" id="mensaje" cols="35" rows="4"></textarea>
            </td>
        </tr>
        <tr>
            <td width="50%">Celular: <input id="celular" type="text" size="10" maxlength="10" /></td>
            <td><input id="sendSimple" type="button" value="Enviar" onclick="guardaSimple();"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <span id="msg"></span>
            </td>
        </tr>
    </table>
</form>