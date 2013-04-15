function onClickTab(argsObj) {
    //                alert("d");
    var t = argsObj.tabber; /* Tabber object */
    var id = t.id; /* ID of the main tabber DIV */
    var i = argsObj.index; /* Which tab was clicked (0 is the first tab) */
    var e = argsObj.event; /* Event object */

//    if (id == 'tab1') {
//    alert("i = " + i);
//        alert(t.tabs[i].headingText + ' i: ' + i);
    switch (t.tabs[i].headingText){
        case 'Lote':
//            alert('Lote w');
            resetContenidoFile();
            getArchivosMenu("archivosMenu");    
            break;
        case 'Modems':
//            alert('Modems w');
            revisarProcesosSMS();
            getMenuOperadores("idOperadorI");
            getMenuMaquinas("idMaquinaI");
            break;
        case 'Operadores':
//            alert('Operadores w');
            cargarOperadores();
            break;
        case 'Pendientes':
//            alert('Pendientes w');
            resetPendientes();
            getMenuModems("idModemLote");
            getArchivosMenuParaEnviar("archivosMenu2");
            break;
        case 'Reportes':
//            alert('Pendientes w');
            resetPendientes();
            getArchivosMenuParaEnviar("archivosMenu3");
            break;
        case 'Usuarios':
//            alert('Usuarios w');
//            alert("Atenci\xf3n Per\xfa!!!");
            getMenuMaquinas("idMaquinaUserI");
            getMenuRoles("idRolUser");
            cargarUsuarios();
            break;
    }

//    }
    return true;
}

function guardaSimple(){
    var mess = DWRUtil.getValue('mensaje');
    var celS = DWRUtil.getValue('celular');
    if (mess.length > 140) {
        alert("Mensaje mayor a 140 caracteres " + mess.length);
    } else if(celS.length != 10){
        alert("El celular debe tener 10 digitos");
    }else {
        smsRemoto.saveMsgSimple(
                celS,
                mess,
                function(data){
                    if(data!=null){ // lo guardo
                        putMsg('se guardo el Mensaje con id: ' + data);
                    } else { // no lo guardo
                        putMsg('no se ha podido guardar el mensaje');
                    }
                });
    }
}

function cuentaLetras(){
    var mess = DWRUtil.getValue('mensaje');
    DWRUtil.setValue("largo", mess.length);
    if(mess.length>140){
        $('largo').style.color = 'red';
        $('sendSimple').disabled = true;
    } else {
        $('largo').style.color = 'black';
        $('sendSimple').disabled = false;
    }
}


function validaAdjunto(){

    var vinc_arch = DWRUtil.getValue("vinc_arch");
    if(vinc_arch == ''){
        alert("Por favor seleccione un archivo primero");
        return false;
    } else {
        return true;
    }

}

function reporteExcel(){
    var idArchivo3 = DWRUtil.getValue("archivosMenu3");
    var myurl = "toExcel.jsp?t="+new Date().getTime()+
                '&idArchivo=' + idArchivo3;
//            alert("myurl = " + myurl);
    if (idArchivo3!=0) {
        window.open(myurl);
    } else {
        alert("Seleccione un Archivo primero !!!");
    }
}

function reporteExcel2(){
    var fecha1 = DWRUtil.getValue("fecha1");
    var fecha2 = DWRUtil.getValue("fecha2");

    var myurl = "toExcel.jsp?t="+new Date().getTime()+
                '&fecha1=' + fecha1 +
                '&fecha2=' + fecha2;

    if (fecha1!='' && fecha2!='') {
        window.open(myurl);
    } else {
        alert("Seleccione un Fechas primero !!!");
    }
}

/**
 * Obtiene el reporte por archivo, si no hay archivo,
 * muestra el total de los archivos.
 */
function reporteArchivo(){
    var imgC = $("imageC");
    imgC.src = "/images/loading.gif";
    var idArchivo3 = DWRUtil.getValue("archivosMenu3");
    var enviados = DWRUtil.getValue("enviados");
    //    alert("enviados = " + enviados);
    var start = new Date().getTime();
    var srcS = 'chartOperadores.jsp?t=' + start +
               '&idArchivo=' + idArchivo3;
    if(enviados){
        srcS += "&enviados="+1;
    }
    imgC.src = srcS;
}

/**
 * Borra el archivo completo
 * @param idArchivo
 */
function borrarArchivoCompleto(){
    var idArchivo = DWRUtil.getValue("archivosMenu2");
    if (idArchivo!=0) {
        if (confirm("[ALERTA] Desea eliminar Completamente el Archivo: " + idArchivo)) {
            if (confirm("[ALERTA] Esta SEGURO de ELIMINAR Completamente el Archivo: " + idArchivo)) {
                smsRemoto.deleteArchivo(idArchivo, function(data2) {
//                alert("data2 = " + data2);
                    resetPendientes();
                    getArchivosMenuParaEnviar("archivosMenu2");
                    switch (data2) {
                        case 0 : alert('Error en el Borrado'); break;
                        case 1 : alert('Archivo con SMS Enviados... Se borro los pendientes'); break;
                        case 2 : alert('Archivo Borrado Completamente'); break;
                    }
                });
            }
        }
    } else {
        alert("Seleccione un Archivo primero !!!");
    }
}

function getArchivosMenu(idMenu) {
    DWRUtil.removeAllOptions(idMenu);
    smsRemoto.getArchivos(function(data) {
        DWRUtil.addOptions(idMenu, data, "idArchivo", "nombreArchivo");
    });
}

function getArchivosMenuParaEnviar(idMenu) {
    DWRUtil.removeAllOptions(idMenu);
//    smsRemoto.getArchivosParaEnviar(function(data) {
    // TODO trae TODOS LOS ARCHIVOS
    smsRemoto.getArchivos(function(data) {
        DWRUtil.addOptions(idMenu, data, "idArchivo", "nombreArchivo");
    });
}

/**
 * resetea la pantalla de carga de archivos
 */
function resetContenidoFile(){
    DWRUtil.removeAllRows("archivoContenidoTbody");
    DWRUtil.setValue("archivoContenidoTitle", "");
}

function getContentOfFile(idFile){
    if(idFile == undefined){
        idFile = DWRUtil.getValue('archivosMenu');
//        alert("no idFile");
//        return false;
        if(idFile == 0){ // no selecciono ninguno
            resetContenidoFile();
            return false;
        }
    }
    var cellFuncs = [
        function(data) { return data.id;   },
        function(data) { return data.number;   },
        function(data) { return data.text; },
        function(data) {
            if (data.operadorByIdOperador != null) {
                return data.operadorByIdOperador.nombreOperador;
            } else {
                return 'ninguno';
            }
        },
        function(data) {
            switch (data.processed) {
                case 3 : return 'Pendiente'; break;
                case 0 : return '* En Proceso'; break;
				case 1 : return '-> Enviado'; break;
            }
        },
        function(data){
            if(data.processed != 0){
                return "<a onclick='borrarMsg(" + data.id + ")'><img class='iconHand' alt='Eliminar "+data.id+"' title='Eliminar "+data.id+"' src='images/drop.png'/></a>";
            }
        }
    ];

    DWRUtil.removeAllRows("archivoContenidoTbody");


    //APAGO LA PRESENTACION DE TOTAL DE SMS POR ARCHIVO

    /*
    smsRemoto.getArchivoCompleto(idFile, function(data){
        DWRUtil.addRows("archivoContenidoTbody", data.outboxes, cellFuncs, {
            rowCreator:function(options) {
                var row = document.createElement("tr");
                if (options.rowIndex % 2) {
                    row.className = 'tablaZebra1';
//                    row.style.backgroundColor = "#c0ff99";
//                    row.style.color = "rgb(255,255,255)";
                }   else {
                    row.className = 'tablaZebra2';
                }
                return row;
            },
            cellCreator:function(options){
                var cell = document.createElement("td");
                cell.className = 'cellBorder';
                return cell;
            },
            escapeHtml:false } );
        DWRUtil.setValue("archivoContenidoTitle", data.nombreArchivo);
        putMsg("");
    });

    */

}

/**
 *  limpia pantalla de Pendientes
 */
function resetPendientes(){
    // borro lotes
    DWRUtil.removeAllRows("lotesTBody");
    DWRUtil.setValue('sumaLote', 0);
    DWRUtil.setValue('faltanLote', 0);

    DWRUtil.removeAllRows("archivoContenidoTbody2");
    DWRUtil.setValue("archivoContenidoTitle2", "");
    DWRUtil.setValue("totalSMS", "");

    $('archivoContenido2Form').reset();
}

/**
 * funcion que repetira la carga de lotes y revisa si se caen los hilos
 */

var timeOutLotes = 0;

function recargarArchivoSmsEnProceso(){
    cargarLotes();
    timeOutLotes = setTimeout("recargarArchivoSmsEnProceso()", 5000);
}

/**
 * sms para enviar segun archivo
 */
function getContentOfFile2(){
    resetPendientes();
    idFile = DWRUtil.getValue('archivosMenu2');
//        alert("no idFile");
//        return false;
    if(idFile == 0){  // no selecciono ninguno, borrar form
        resetPendientes();
        return false;
    }
    var cellFuncs = [
        function(data) { return data.id;   },
        function(data) { return data.number;   },
        function(data) { return data.text; },
        function(data) {
            if (data.operadorByIdOperador != null) {
                return data.operadorByIdOperador.nombreOperador;
            } else {
                return 'ninguno';
            }
        },
        function(data) {
            if (data.idLote != null) {
                return data.idLote;
            } else {
                return 'ninguno';
            }
        },
        function(data) {
//            alert("data.processed = " + data.processed);
            switch (data.processed) {
                case 3 : return 'Pendiente'; break;
                case 0 : return '* En Proceso'; break;
				case 1 : return '-> Enviado'; break;
            }
        },
        function(data){
            switch (data.processed) { //FUNCIONES
                case 3 :
                    return "<a onclick='borrarMsg2(" + data.id + ")'><img class='iconHand' alt='Eliminar "+data.id+"' title='Eliminar "+data.id+"' src='images/drop.png'/></a>";
                    break;
                case 0 :
                    return '&nbsp;'; 
                    break;
				case 1 :
                    return '&nbsp;';
                    break;
            }


            if(data.processed != 0){

            } else {
                return "&nbsp;"
            }
        }
    ];

    DWRUtil.removeAllRows("archivoContenidoTbody2");

    // CARGA LAS CANTIDADES DE SMS PENDIENTES POR OPERADOR
    cargaCantidadDeMensajesPendientesPorArchivo(idFile);
    // TODO PUSE ESTO SOLO PARA SABER LA CANTIDAD PARA ENVAR
    smsRemoto.getArchivoCompletoParaEnviar(idFile, function(data){
        DWRUtil.setValue("totalSMS", data   );
    });

    clearTimeout(timeOutLotes);
    recargarArchivoSmsEnProceso();
    
    //APAGO LA PRESENTACION DE TOTAL DE SMS POR ARCHIVO

    /*smsRemoto.getArchivoCompleto(idFile, function(data){

        // Carga la Info de los LOTES
//        cargarLotes();

        DWRUtil.addRows("archivoContenidoTbody2", data.outboxes, cellFuncs, {
            rowCreator:function(options) {
                var row = document.createElement("tr");
                if (options.rowIndex % 2) {
                    row.className = 'tablaZebra1';
//                    row.style.backgroundColor = "#c0ff99";
//                    row.style.color = "rgb(255,255,255)";
                }   else {
                    row.className = 'tablaZebra2';
                }
                return row;
            },
            cellCreator:function(options){
                var cell = document.createElement("td");
                cell.className = 'cellBorder';
                return cell;
            },
            escapeHtml:false } );
        DWRUtil.setValue("archivoContenidoTitle2", data.nombreArchivo);
        // TODO CAMBIE ESTO PARA ARRIBA
//        DWRUtil.setValue("totalSMS", data.outboxes.length);
    });
    */


}

function cargaCantidadDeMensajesPendientesPorArchivo(idFile){
//    alert("idFile = " + idFile);
    smsRemoto.getAmountAvailableForOp(idFile, function(dataCount){
//        alert("dataCount = " + dataCount + " dataCount = " + dataCount[2]);
        for(var row=0; row<dataCount.length; row++){
            var opField = "totalOp"+dataCount[row][0];
//            alert("opField = " + opField + " - " + opL[1]);
//            alert("opL = " + opL);
            DWRUtil.setValue(opField, dataCount[row][1]);
        }
    });
}

function borrarOperador(id){
    if (confirm("[ALERTA] Desea eliminar el Operador " + id )) {
        smsRemoto.deleteOperador(id, function(data){
            if(data!=0){
                cargarOperadores();
                alert("Se borro el Operador " + id);
            } else {
                alert("No se pudo borrar el operador " + data);
            }
        });
    }
}

function cargarOperador(id){
    smsRemoto.getOperador(id, function(data){
        if(data.idOperador == id){
            DWRUtil.setValues(data);
        } else {
            alert("No se pudo cargar el operador " + data);
        }
    });
}

function borrarMsg(id){
    if (confirm("[ALERTA] Desea eliminar el Mensaje " + id )) {
        smsRemoto.deleteOutBox(id, function(data){
            if (data != 0) {
                getContentOfFile();
                alert("Se borro el mensaje " + data);
            } else {
                alert("No se pudo borrar el mensaje " + data);
            }
        });
    } else {
    }
}

function borrarMsg2(id){
    if (confirm("[ALERTA] Desea eliminar el Mensaje " + id )) {
        smsRemoto.deleteOutBox(id, function(data){
            if (data != 0) {
                getContentOfFile2();
                alert("Se borro el mensaje " + data);
            } else {
                alert("No se pudo borrar el mensaje " + data);
            }
        });
    } else {
    }
}

function limpiarOperadores(){
    $("operadorForm").reset();
}

function guardarOperador(){
    var operador = {
        idOperador:null,
        nombreOperador:null,
        prefijoOperador: null,
        comentarioOperador:null
    };

    DWRUtil.getValues(operador);

    smsRemoto.saveOperador(operador, function(data){
        if(data!=0){
            limpiarOperadores();
            cargarOperadores();
            alert("Se guardo el Operador " + data);
        } else {
            alert("No se pudo guardar el operador " + data);
        }
    });
}

function getMenuOperadores(idMenu){
    smsRemoto.getOperadores(function(data){
        DWRUtil.removeAllOptions(idMenu);
        DWRUtil.addOptions(idMenu, data, "idOperador", "nombreOperador");
    });
}

function getMenuMaquinas(idMenu){
    smsRemoto.getMenuMaquinas(function(data){
        DWRUtil.removeAllOptions(idMenu);
        DWRUtil.addOptions(idMenu, data, "idMaquina", "nombreMaquina");
    });
}

function getMenuRoles(idMenu){
    smsRemoto.getRoles(function(data){
        DWRUtil.removeAllOptions(idMenu);
        DWRUtil.addOptions(idMenu, data, "idRol", "nombreRol");
    });
}

function getMenuModems(idMenu){
    smsRemoto.getModemsActivos(function(data){
        DWRUtil.removeAllOptions(idMenu);
        DWRUtil.addOptions(idMenu, data, "idModem", "nombreCompletoModem");
    });
}

function cargarOperadores(){
//    alert('cargando');
    var cellFuncs = [
        function(data) { return data.idOperador;   },
        function(data) { return data.nombreOperador;   },
        function(data) { return data.prefijoOperador; },
        function(data) { return data.comentarioOperador + "&nbsp;"; },
        function(data){
            return "<a onclick='cargarOperador(" + data.idOperador + ")'><img class='iconHand' alt='Cargar "+data.idOperador+"' title='Cargar "+data.idOperador+"' src='images/edit.png'/></a>"+
                   "&nbsp;" +
                   "&nbsp;" +
                   "<a onclick='borrarOperador(" + data.idOperador + ")'><img class='iconHand' alt='Eliminar "+data.idOperador+"' title='Eliminar "+data.idOperador+"' src='images/drop.png'/></a>";
        }
    ];

    DWRUtil.removeAllRows("operadoresTBody");

    smsRemoto.getOperadores(function(data){
//        alert("data.length = " + data.length);

        DWRUtil.addRows("operadoresTBody", data, cellFuncs, {
            rowCreator:function(options) {
                var row = document.createElement("tr");
                if (options.rowIndex % 2) {
                    row.className = 'tablaZebra1';
//                    row.style.backgroundColor = "#c0ff99";
//                    row.style.color = "rgb(255,255,255)";
                }   else {
                    row.className = 'tablaZebra2';
                }
                return row;
            },
            cellCreator:function(options){
                var cell = document.createElement("td");
                cell.className = 'cellBorder';
                return cell;
            },
            escapeHtml:false } );

    }); 
}

function limpiarModem(){
    $("modemForm").reset();
    DWRUtil.setValue('idModem',0);
//    alert("DWRUtil.getValue('idModem') = " + DWRUtil.getValue('idModem'));
}

function limpiarUsuario(){
    $("usuariosForm").reset();
    DWRUtil.setValue('idUsuario',0);
//    alert("DWRUtil.getValue('idModem') = " + DWRUtil.getValue('idModem'));
}

function guardarModem(){
    var modem = {
        idModem: null,
        imeiModem: null,
        idMaquinaI: null,
        idOperadorI: null
    };

    DWRUtil.getValues(modem);

    smsRemoto.saveModem(modem, function(data){
        if(data!=0){
            limpiarModem();
            cargarModems();
            alert("Se guardo el Operador " + data);
        } else {
            alert("No se pudo guardar el operador " + data);
        }
    });
}

function guardarUsuario() {
    var usuario = {
        idUsuario: null,
        nombreUsuario: null,
        mailUsuario: null,
        ctrsnUsuario1: null,
        ctrsnUsuario2: null,
        idRolUser: null,
        estadoUsuario: null,
        idMaquinaUserI: null
    };

    DWRUtil.getValues(usuario);

    var valid = new Validation('usuariosForm', {immediate:true, onSubmit:false});
    var result;
    result = valid.validate();

    if (result) {
        smsRemoto.saveUsuario(usuario, function(data) {
            if (data != 0) {
                limpiarUsuario();
                cargarUsuarios();
                alert("Se guardo el Usuario " + data);
            } else {
                alert("No se pudo guardar el Usuario " + data);
            }
        });
    }
}

function enviarSMSPendientes(){
    var lote = {
        archivosMenu2: null
    };

    DWRUtil.getValues(lote);
    var faltanSMS = DWRUtil.getValue("faltanLote");

    if (lote.archivosMenu2 == 0) {
        alert("Seleccione un Archivo primero !!!");
    } else if( false /*0 < eval(faltanSMS)*/){
        alert("Tiene " + faltanSMS + " SMS Pendientes");
    } else {
        if (confirm("[ALERTA] Desea Enviar estos Mensajes")) {
            smsRemoto.sendLoteSMS(lote.archivosMenu2, function(data) {
                getContentOfFile2();
            });
        }
    }

}

function cargandoImg(imagen){
    $(imagen).src = 'images/loading.gif';
    $(imagen).width = '30';
    $(imagen).height = '30';
}

function cargandoDisableImg(imagen){
    $(imagen).src = 'images/spacer.gif';
    $(imagen).width = '0';
    $(imagen).height = '0';
}

function revisarProcesosSMS(){
    $("bprocesosModem").disabled = true;
    cargandoImg("cargaModemImg");

    smsRemoto.revisarProcesosModems(function(data){
        cargarModems();
        cargandoDisableImg("cargaModemImg");
    });
}

function recargarModemsSystema(){
    $("bCargarModem").disabled = true;
    cargandoImg("cargaModemImg");
    DWRUtil.removeAllRows("modemsTBody");
    smsRemoto.exploreModemsInSystem(function(data){
        cargarModems();

        cargandoDisableImg("cargaModemImg");
    });
}

function cargarUsuarios(){
     var cellFuncs = [
        function(data) { return data.idUsuario;   },
        function(data) { return data.nombreUsuario; },
        function(data) { return data.mailUsuario;   },
        function(data) { return data.rolByIdRole.nombreRol;   },
        function(data) {
            if (data.estadoUsuario == 1) {
                return 'Activo';
            } else {
                return 'Inactivo';
            }
        }    ,
        function(data) {
            if(data.maquinaByIdMaquina != null){
                return data.maquinaByIdMaquina.nombreMaquina;
            } else {
                return '&nbsp;';
            }
        },
        function(data) {
            return "<a onclick='cargaUnUsuario(" + data.idUsuario + ")'><img class='iconHand' alt='Cargar "+data.idUsuario+"' title='Cargar "+data.idUsuario+"' src='images/edit.png'/></a>"+
                   "&nbsp;" +
                   "&nbsp;" +
                   "<a onclick='borrarOperador(" + data.idUsuario + ")'><img class='iconHand' alt='Eliminar "+data.idUsuario+"' title='Eliminar "+data.idUsuario+"' src='images/drop.png'/></a>";
        }
     ];

    DWRUtil.removeAllRows("usuariosTBody");

    smsRemoto.getUsuarios(function(data){
        DWRUtil.addRows("usuariosTBody", data, cellFuncs, {
            rowCreator:function(options) {
                var row = document.createElement("tr");
                if (options.rowIndex % 2) {
                    row.className = 'tablaZebra1';
//                    row.style.backgroundColor = "#c0ff99";
//                    row.style.color = "rgb(255,255,255)";
                }   else {
                    row.className = 'tablaZebra2';
                }
                return row;
            },
            cellCreator:function(options){
                var cell = document.createElement("td");
                cell.className = 'cellBorder';
                return cell;
            },
            escapeHtml:false } );
    });
}

function cargaUnUsuario(idUsuario){
    smsRemoto.getUsuario(idUsuario, function(data){
        if(data.idUsuario == idUsuario){
            DWRUtil.setValues(data);
        } else {
            alert("No se pudo cargar el Usuario " + data);
        }
    });
}

function cargarModems(){
    var cellFuncs = [
        function(data) { return data.idModem;   },
        function(data) { return data.imeiModem; },
        function(data) { return data.operadorByIdOperador.nombreOperador;   },
        function(data) {
            if (data.maquinaByIdMaquina != null) {
                return data.maquinaByIdMaquina.nombreMaquina + '&nbsp;';
            } else {
                return '&nbsp;';
            }
        },
        function(data) {
            if (data.phoneModem != null) {
                return data.phoneModem + '&nbsp;';
            } else {
                return '&nbsp;';
            }
        },
        function(data) {
            switch (data.activoModem) {
                case 1 : return 'Conectado';
                case 0 : return 'Sin enlace';
            }
        },
        function(data) {
            switch (data.corriendoModem) {
                case 1 : return 'Operando';
                case 0 : return 'Detenido';
            }
        },
        function(data) { return data.procesoModem;},
        function(data){
            var varAux =
                    "<a onclick='cargarModem(" + data.idModem + ")'><img class='iconHand' alt='Cargar "+data.idModem+"' title='Cargar "+data.idModem+"' src='images/edit.png'/></a>"+
                    "&nbsp;" +
                    "&nbsp;" +
                    "<a onclick='borrarModem(" + data.idModem + ")'><img class='iconHand' alt='Eliminar "+data.idModem+"' title='Eliminar "+data.idModem+"' src='images/drop.png'/></a>" +
                    "&nbsp;" +
                    "&nbsp;";
            if (data.activoModem == 1) {
                if (data.corriendoModem == 0) {
                    varAux += "<a onclick='operarModem(" + data.idModem + ")'><img class='iconHand' alt='Operar " + data.idModem + "' title='Operar " + data.idModem + "' src='images/play.png' height='16' width='16'/></a>";
                } else {
                    varAux += "<a onclick='detenerrModem(" + data.idModem + ")'><img class='iconHand' alt='Detener " + data.idModem + "' title='Detener " + data.idModem + "' src='images/stop.png' height='16' width='16'/></a>";
                }
            }
            return varAux;
        }
    ];

    DWRUtil.removeAllRows("modemsTBody");

    smsRemoto.getModems(function(data){
//        alert("data.length = " + data.length);

        DWRUtil.addRows("modemsTBody", data, cellFuncs, {
            rowCreator:function(options) {
                var row = document.createElement("tr");
                if (options.rowIndex % 2) {
                    row.className = 'tablaZebra1';
//                    row.style.backgroundColor = "#c0ff99";
//                    row.style.color = "rgb(255,255,255)";
                }   else {
                    row.className = 'tablaZebra2';
                }
                return row;
            },
            cellCreator:function(options){
                var cell = document.createElement("td");
                cell.className = 'cellBorder';
                return cell;
            },
            escapeHtml:false } );

        /**
         * Habilito del Boton de Recarga de Modems en caso que este deshabilitado/ 
         */
        $("bCargarModem").disabled = false;
        $("bprocesosModem").disabled = false;
    });
}

function cargarModem(id){
    smsRemoto.getModem(id, function(data){
        if(data.idModem == id){
            DWRUtil.setValues(data);
        } else {
            alert("No se pudo cargar el operador " + data);
        }
    });
}

function borrarModem(id){
    if (confirm("[ALERTA] Desea eliminar el Modem " + id )) {
        smsRemoto.deleteModem(id, function(data){
            if(data!=0){
                cargarModems();
                alert("Se borro el Modem " + id);
            } else {
                alert("No se pudo borrar el Modem " + data);
            }
        });
    }
}

function operarModem(id){
    if (confirm("[ALERTA] Desea Operar con el Modem " + id )) {
        smsRemoto.operarModem(id, function(data){
            if(data!=0){
                cargarModems();
                alert("Operando con el Modem " + id);
            } else {
                alert("Error con el Modem " + id);
            }
        });
    }
}

function detenerrModem(id){
    if (confirm("[ALERTA] Desea Detener el Modem " + id )) {
        smsRemoto.detenerModem(id, function(data){
            if(data!=0){
                cargarModems();
                alert("Se detuvo al Modem " + id);
            } else {
                alert("Error con el Modem " + data);
            }
        });
    }
}

function limpiaLote(){
    $("loteForm").reset();
}

function guardarLote() {
    var lote = {
        idLote: null,
        archivosMenu2: null,
        idModemLote: null,
        cantidadLote: null
    };
    DWRUtil.getValues(lote);

    var totalModemText = $('idModemLote').options[$('idModemLote').selectedIndex].text;
    totalModemText = totalModemText.substring(
            totalModemText.indexOf('(') + 1,
            totalModemText.indexOf(')')
            );
    var totalModemF = DWRUtil.getValue("totalOp" + totalModemText);
//    alert("totalModemF = " + totalModemF);
    var totalModem;
    if (totalModemF == '') {
        totalModem = 0;
    } else {
        totalModem = eval(totalModemF);
    }
//    alert("totalModem = " + totalModem);


    if (lote.archivosMenu2 == 0) {
        alert("Seleccione un Archivo primero !!!");
    } else if (
            lote.cantidadLote == '' ||
            lote.cantidadLote <= 0
            ) {
        alert("Error en la cantidad");
    } else if (eval(lote.cantidadLote) > totalModem) {
            alert("La cantidad no puede ser superior al los SMS Pendientes");
        } else {
            smsRemoto.saveLote(lote, function(data) {
                if (data != 0) {
                    limpiaLote();
//                    cargarLotes();
                    getContentOfFile2();
                    alert("Se guardo el Lote " + data);
                } else {
                    alert("No se pudo guardar el Lote " + data);
                }
            });
        }
}

function cargarLotes(){
    var cellFuncs = [
        function(data) { return data.idLote;   },
        function(data) { return data.archivoByIdArchivo.nombreArchivo;   },
        function(data) { return data.modemByIdModem.nombreCompletoModem; },
        function(data) {
            switch (data.enviadoLote){
                case 0 : return 'Pendiente'; break;
                case 2 : return '* En Proceso'; break;
				case 1 : return '-> Enviado'; break;
            }
        },
        function(data) { return data.cantidadLote;   },
        function(data) { return data.smSenviadosLote; },    
        function(data) { return data.pendientesLote; },
        function(data){ // FUNCIONES
            switch (data.enviadoLote){
                case 0 :
                    return "&nbsp;" +
                           "&nbsp;" +
                           "<a onclick='borrarLote(" + data.idLote + ")'><img class='iconHand' alt='Eliminar Lote: " + data.idLote + "' title='Eliminar Lote: " + data.idLote + "' src='images/drop.png'/></a>";
                    break;
                case 2 :
                    return "&nbsp;" +
                           "&nbsp;" +
                           "<a onclick='detenerCerrarLote(" + data.idLote + ")'><img class='iconHand' alt='Detener y Cerrar Lote: "+data.idLote +"' title='Detener y Cerrar Lote: "+data.idLote+"' src='images/stop.png' width='16' height='16'/></a>";
                    break;
				case 1 :
                    return '&nbsp;';
                    break;
            }
        }
    ];

    var idArchivo = DWRUtil.getValue("archivosMenu2");
//    alert("idArchivo = " + idArchivo);
    if(idArchivo != 0){
//        alert('cargara');
        DWRUtil.removeAllRows("lotesTBody");
//        smsRemoto.getLotesSinEnviar(idArchivo, function(data){
        // PUSE ESTO ACA PARA MOSTRAR TODOS LOS LOTES Y SUS ESTADOS
        smsRemoto.getLotes(idArchivo, function(data){
            DWRUtil.addRows("lotesTBody", data, cellFuncs, {
                rowCreator:function(options) {
                    var row = document.createElement("tr");
                    if (options.rowIndex % 2) {
                        row.className = 'tablaZebra1';
//                    row.style.backgroundColor = "#c0ff99";
//                    row.style.color = "rgb(255,255,255)";
                    }   else {
                        row.className = 'tablaZebra2';
                    }
                    return row;
                },
                cellCreator:function(options){
                    var cell = document.createElement("td");
                    cell.className = 'cellBorder';
                    if(options.cellNum == 2){
//                        cell.style.color = "#FF0000";
                        cell.style.textAlign = "right";
                    }
                    return cell;
                },
                escapeHtml:false } );

            smsRemoto.sumaCantidadLoteByArchivo(idArchivo, function(data1){
                DWRUtil.setValue('sumaLote', data1);

                DWRUtil.setValue('faltanLote', eval(DWRUtil.getValue("totalSMS"))-data1);
            });
        });
    }

}

function cargaLote(id){
    smsRemoto.getLote(id, function(data){
        if(data.idLote == id){
            DWRUtil.setValues(data);
        } else {
            alert("No se pudo cargar el Lote " + data);
        }
    });
}

function detenerCerrarLote(id){
    if (confirm("[ALERTA] Desea Detener y Cerrar el Lote " + id )) {
        smsRemoto.detieneCierraLote(id, function(data){
            if(data!=0){
                getContentOfFile2();
//                cargarLotes();
                alert("Se detuvo el Lote " + id);
            } else {
                alert("No se pudo detener el Lote " + data);
            }
        });
    }
}

function borrarLote(id){
    if (confirm("[ALERTA] Desea eliminar el Lote " + id )) {
        smsRemoto.deleteLote(id, function(data){
            if(data!=0){
                getContentOfFile2();
//                cargarLotes();
                alert("Se borro el Lote " + id);
            } else {
                alert("No se pudo borrar el Lote " + data);
            }
        });
    }
}


function clearRangeStart() {
    document.getElementById("f_rangeStart").value = "";
    LEFT_CAL.args.min = null;
    LEFT_CAL.redraw();
};

function putMsg(msg){
    $("msg").innerHTML = msg;
}

function puterror(msg){
    $("error").innerHTML = msg;
}

function cleanerror(){
    $("error").innerHTML = "";
}

function validateObject(Obj){
    for (var i in citas){
        alert(citas[i]);
    }
}

/**
 * style none
 *
 * @param id
 */
function esconde(id){
    $(id).style.display = "none";
}

/**
 * style block
 * @param id
 */
function muestra(id){
    $(id).style.display = "block";
}


setValues2 = function(data, options) {

    var prefix = "";
    if (options && options.prefix) prefix = options.prefix;
    if (options && options.idPrefix) prefix = options.idPrefix;

    setValuesRecursive2(data, prefix);
};

setValuesRecursive2 = function(data) {
    var cita = 'cita';
    var tmp;
    var aux;
    var i = 1;
    for(i=1; i<=30; i++){
        tmp = cita+i;
        aux = data[tmp];
        //alert(tmp+i +"," + aux);
        if(aux!=undefined){
            //            alert("si");
            dwr.util.setValue(tmp, aux, { escapeHtml:false });
        } else {
            //            alert("no");
            dwr.util.setValue(tmp,"");
        }
    }
};


function ponerCitasC(){
    var params = {
        idCentro:null,
        idProyecto:null
    };
    citasRemoto.ponerCitasDia2(params, function(data){
        setValuesRecursive2(data);
    });
}

function rotar(){
    ponerCitasC();
    setTimeout("rotar()", 1500);
}


function posicionarCapaSegunClic(capa, y_axis){


    var arriba = document.viewport.getScrollOffsets()[1];
    var visibleCentro = document.viewport.getDimensions().height;
    //    alert(visibleCentro);
    var altoCapa = capa.offsetHeight;
    var tope = y_axis;

    if(arriba+visibleCentro < y_axis+altoCapa){

        tope = arriba+visibleCentro - altoCapa - 10;
    } else {

        tope -= 20;
    }

    capa.style.top = tope;

    //flechas
    if(arriba+visibleCentro < y_axis+ altoCapa-143){
        //abajo
        esconde("flechaArriba");
        muestra("flechaAbajo");
        $("flechaAbajo").style.top = y_axis - 82;
    } else {
        // arriba
        muestra("flechaArriba");
        esconde("flechaAbajo");
        $("flechaArriba").style.top = y_axis - 20;
    }
}








//Glogo de ayuda


<!--



function a_popup(titulo, resumen, categoria, visto, destacado)
{

    if (destacado>0) {
        color="#80ff40";
        tabla="tbl_destacado";
    }
    else {
        color="#cbffd0";
        tabla="tbl_naranja1";
    }

    content="<table bgcolor='#ffffff' cellpadding=0 cellspacing=0 class=" + tabla + " width=400>";
    if (destacado>0) content+="<tr><td bgcolor=red class=textop align=center><small><font color=white>ANUNCIO DESTACADO&nbsp;&nbsp;&nbsp; ANUNCIO DESTACADO&nbsp&nbsp;&nbsp;ANUNCIO DESTACADO</td></tr>";
    content+="<tr><td>" +
             "<table border=0 cellspacing=0 cellpadding=2 width=100%><tr><td bgcolor='#2c912c' class=textom align=center><b>" + titulo + "</b></td></tr>" +
             "<tr><td bgcolor='" + color + "'><table border=0 cellpadding=6><tr><td class=textom>" + resumen + "</td></tr></table></td></tr>" +
             "<tr><td bgcolor='2c912c'> " +
             "<table border=0 cellpadding=0 cellspacing=0 width=100%><tr><td class=textom><b>" + categoria + "</b></td>" +
             "<td align=right class=textom>" + visto + "</td></tr></table> "+
             "</td></tr>" +
             "</table>"+

             "</td></tr></table>";

    yyy=Yoffset;
    if(ns4){skn.document.write(content);skn.document.close();skn.visibility="visible";}
    if(ns6){document.getElementById("ptbl").innerHTML=content;skn.display='';}
    if(ie4){document.all("ptbl").innerHTML=content;skn.display='';}
    var pbtlByID = $('ptbl');

    lyAltura = pbtlByID.offsetHeight + 30;
}


function get_mouse(e)
{
    var x=(ns4||ns6)?e.pageX:event.x+document.body.scrollLeft;
    skn.left=x+Xoffset;
    var y=(ns4||ns6)?e.pageY:event.y+document.body.scrollTop;
    if(y > (document.body.offsetHeight / 2))
        skn.top=y+yyy-lyAltura;
    else
        skn.top=y+yyy;
}

function kill()
{
    yyy=-1000;
    if(ns4)
        skn.visibility="hidden";
    else if (ns6||ie4)
        skn.display="none";
}
//-->


function test1(){
    alert("test1")
}



function myShow(){
    Dialog.show();
}
