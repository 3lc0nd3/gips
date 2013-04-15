/* Licence:
*   Use this however/wherever you like, just don't blame me if it breaks anything.
*
* Credit:
*   If you're nice, you'll leave this bit:
*
*   Class by Pierre-Alexandre Losson -- http://www.telio.be/blog
*   email : plosson@users.sourceforge.net
*/
function refreshProgress()
{
    UploadMonitor.getUploadInfo(updateProgress);
}


function updateProgress(uploadInfo)
{
    if (uploadInfo.inProgress)
    {
        document.getElementById('uploadbutton').disabled = true;
//        document.getElementById('file1').disabled = true;
//        document.getElementById('file2').disabled = true;
//        document.getElementById('file3').disabled = true;
//        document.getElementById('file4').disabled = true;

        var fileIndex = uploadInfo.fileIndex;

        var progressPercent = Math.ceil((uploadInfo.bytesRead / uploadInfo.totalSize) * 100);

        document.getElementById('progressBarText').innerHTML = 'Progreso: ' + progressPercent + '%';

        document.getElementById('progressBarBoxContent').style.width = parseInt(progressPercent * 1.5) + 'px';

//        window.setTimeout('refreshProgress()', 1000);
        window.setTimeout('refreshProgress()', 1000);
    }
    else
    {
        /**
         * si ya acabo de subir el archivo...
         */
        //enciende el boton de subir
        document.getElementById('uploadbutton').disabled = false;
        // limpia el campo file
        document.getElementById('vinc_arch').value = '';
        // borra la barra de progreso
        $('progressBar').style.display = 'none';
        document.getElementById('progressBarBoxContent').style.width = 0;
//        alert(" = " + $('idNewsd'));
        window.setTimeout('muestraResultado()', 1000);
//        document.getElementById('file1').disabled = false;
//        document.getElementById('file2').disabled = false;
//        document.getElementById('file3').disabled = false;
//        document.getElementById('file4').disabled = false;
    }

    return true;
}

var ic = 1;

function muestraResultado(){
    putMsg("Procesando ... <img alt='Cargando' width='16' height='16' title='Cargando' src='images/loading.gif' />");

    // AHORA SE PROCEDE A SACAR LOS DATOS

    if(!globalEstadoConversion){
        window.setTimeout('muestraResultado()', 1000);
    } else {
        // FILE UPLOAD COMPLETO
        putMsg("archivo subido " + archivoCargado);

        //cargo el menu de Archivos Lote
        getArchivosMenu("archivosMenu");

        //muestro el contenido del archivo
        getContentOfFile(archivoCargado);


//        alert("idNews = " + idNews);
        // carga los archivos relacionados con la noticia
        /*if($('idNews') != null){
//            alert("globalEstadoConversion = " + globalEstadoConversion);
            getFiles(idNews, 1);

        }*/

    }
}

function startProgress(){
    // INICIALIZA EL FILE ULOAD

    globalEstadoConversion =false;
    archivoCargado = 0;

    if (validaAdjunto()) {
        document.getElementById('progressBar').style.display = 'block';
        document.getElementById('progressBarText').innerHTML = 'Progreso: 0%';
        document.getElementById('uploadbutton').disabled = true;

        // wait a little while to make sure the upload has started ..
        //    window.setTimeout("refreshProgress()", 1500);
        window.setTimeout("refreshProgress()", 1500);
        return true;
    } else {
//        alert("false = " + false);
        return false;
    }
}
