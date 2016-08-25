<%@ page import="org.apache.commons.fileupload.FileUploadException" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="co.com.lh.smsfin.dao.FacDAO" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Vector" %>
<%@ page import="static java.lang.String.format" %>
<%@ page import="co.com.lh.smsfin.upload.UploadListener" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.fileupload.FileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="co.com.lh.smsfin.upload.MonitoredDiskFileItemFactory" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="co.com.lh.smsfin.model.Archivo" %>
<%@ page import="co.com.lh.smsfin.model.Usuario" %>
<html>
<body>

<%

    Logger logger  = Logger.getLogger(FacDAO.class);

    Hashtable parameters = new Hashtable();

    FacDAO facDAO = (FacDAO) application.getAttribute("smsManager");
    logger.debug("facDAO 88888888888888888888888888888888888888 = " + facDAO);

    UploadListener listener = new UploadListener(request, 1);

    // Create a factory for disk-based file items
    FileItemFactory factory = new MonitoredDiskFileItemFactory(listener);

    // Create a new file upload handler
    ServletFileUpload upload = new ServletFileUpload(factory);

    Archivo archivo = null;

    try {
        /**
	     * Parse the request
	     */
        List items = upload.parseRequest(request);


        for (Object item1 : items) {
            FileItem item = (FileItem) item1;
            /*
             * Handle Form Fields.
             */
            if (item.isFormField()) {
                String name = item.getFieldName();
                logger.debug("name = " + name);
                String value = item.getString();
                Vector existingValues = (Vector) parameters.get(name);

                if (existingValues == null) {
                    existingValues = new Vector();
                    parameters.put(name, existingValues);
                }
                existingValues.addElement(value);

//                System.out.println("Field Name = " + name + ", Value = " + item.getString());
            } else {
                //Handle Uploaded files.
//                System.out.println("Field Name = " + item.getFieldName() +
//                        ", File Name = " + item.getName() +
//                        ", Content type = " + item.getContentType() +
//                        ", File Size = " + item.getSize());
                /*
                 * Write fileSRC to the ultimate location.
                 */

/*

                News news = facDAO.getNews(Integer.parseInt(idNews));
                Newsfile newsfile = new Newsfile();
                newsfile.setNewsByNewsid(news);
*/

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

                String fileName = item.getName();
                String extencion;
                extencion = FacDAO.getExtFromFile(fileName);

                String cuerpo = FacDAO.getNameFromFile(fileName);


                fileName = format("%s-%s.%s", cuerpo, df.format(new Date()), extencion);
                String path = application.getRealPath("/uploads");

                File fileSRC = new File(format("%s/%s", path, fileName));
                logger.debug("fileSRC = " + fileSRC);
                /**
                 * solo si viene un archivo
                 */
                if (!item.getName().equals("")) {
                    logger.debug("Si es archivo");
                    item.write(fileSRC);

                    Usuario  usuario = new Usuario();
                    usuario.setIdUsuario(1);

                    archivo = facDAO.leeCsvFile(fileSRC,usuario);

                    logger.debug("************* INICIA");
                    logger.debug("************* fin");
//                    out.write("Guardado: " + fileName);
                } else {
                    logger.debug("No es ARCHIVO");
                    //System.out.println("no");
                }

//                Thread.sleep(10000);

            }
        }
    }catch(FileUploadException ex) {
        //System.out.println("Error encountered while parsing the request " + ex.getMessage());
    } catch(Exception ex) {
        //System.out.println("Error encountered while uploading file " + ex.getMessage());
    }



%>

<script type="text/javascript">
    parent.globalEstadoConversion = true;
<%
    if(archivo!= null){

%>
    parent.archivoCargado = <%=archivo.getIdArchivo()%>;
<%

    } else {
%>
    parent.archivoCargado = 0;
<%
    }
%>
    var myGlobal = parent.globalEstadoConversion;
//    alert("parent.globalEstadoConversion = " + parent.globalEstadoConversion);
//    alert("myGlobal = " + myGlobal);

/*

    var ff = myGlobal.getElementById("idNews");
    alert("ff = " + ff.value);
    document = myGlobal;*/
/*
    var jj = DWRUtil.getValue("idNews");
    alert("jj = " + jj);*/



//    myGlobal.test();


//    var ff = myGlobal.

</script>


</body>
</html>