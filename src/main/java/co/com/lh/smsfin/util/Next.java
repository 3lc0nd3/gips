package co.com.lh.smsfin.util;

import co.com.lh.smsfin.dao.FacDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * Created by Edward L. Ramirez A.
 * cel 300 554 3367
 * email elramireza@gmail.com
 * User: usuariox
 * Date: Jul 27, 2011
 * Time: 3:35:42 PM
 */
@SuppressWarnings(value = "unchecked")
public class Next extends HttpServlet{

    Logger logger  = Logger.getLogger(FrontController.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("POST = ");
        FacDAO usuarioDAO = (FacDAO) getServletContext().getAttribute("smsManager");
        String login = request.getParameter("login");
//        System.out.println("login = " + login);
        String password = request.getParameter("pass");
//        System.out.println("password = " + password);
        String stats = request.getParameter("stats");
        System.out.println("stats = " + stats);

        String portlet = request.getParameter("portlet");

        HttpSession session = request.getSession(true);

        String nextJSP;
        /*Usuario user = (Usuario) session.getAttribute("user");

        if (user == null || portlet!= null) {
            user = usuarioDAO.getUsuario(login, password);
            if (user!=null) {
                System.out.println("user.getCompleteName() = " + user.getNombreUsuario());
            }
        }*/

        String miIP = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        logger.info("uri = " + uri);
        logger.info("miIP antes   = " + miIP);
        int inicioUrl = 0;
        String httpS = "http://";
        if (miIP.contains(httpS)) {
            inicioUrl = miIP.indexOf(httpS)+httpS.length();
        }
        miIP = miIP.substring(inicioUrl, miIP.indexOf(uri));
        String conPuerto = ":";
        if(miIP.contains(conPuerto)){
            miIP = miIP.substring(0, miIP.indexOf(conPuerto));
        }
        logger.info("miIP despues = " + miIP);
        if(request.getQueryString() != null){
        }


        /*if(user == null){
            if (login != null) {
                request.setAttribute("msg", "Error en el Usuario");
            }
            nextJSP = "/indexOld.jsp";
        } else if(user.getEstadoUsuario() == 0){
            request.setAttribute("msg", "Usuario Inactivo");
            nextJSP = "/indexOld.jsp";
        } else if(user.getMaquinaByIdMaquina() == null){
            request.setAttribute("msg", "Usuario No Tiene M&aacute;quina registrada");
            nextJSP = "/indexOld.jsp";
        } else if(!user.getMaquinaByIdMaquina().getIpMaquina().equals(miIP)){  // NO DE LA MAQUINA
            FacDAO smsDAO = (FacDAO) getServletContext().getAttribute("smsManager");
            Maquina maquina = smsDAO.getMaquinaByIP(miIP);
            String errorMaquina = "Usuario No Pertenece a esta M&aacute;quina";
            if(maquina != null){
                errorMaquina += ": " +maquina.getNombreMaquina();
            } else {
                errorMaquina = "Esta M&aacute;quina con IP: "+ miIP +" no est&aacute; Registrada";
            }
            request.setAttribute("msg", errorMaquina);
            nextJSP = "/indexOld.jsp";
        } else {
            int idRol = user.getRolByIdRole().getIdRol();
            System.out.println("user = " + user + " idRol = " + idRol);
            if (idRol == 1) {
                nextJSP = "/p1_admin.jsp";
            } else if (idRol == 2) {
                nextJSP = "/p2_op.jsp";
            } else if (idRol == 3) {
                nextJSP = "/p3_sup.jsp";
            } else if (idRol == 4) {
                nextJSP = "/p4_ger.jsp";
                if(stats!=null){
                    nextJSP = "/stats.jsp";
                }
            } else if (idRol == 6) {
                nextJSP = "/stats.jsp";
            } else {
                nextJSP = "/indexOld.jsp";
            }
            session.setAttribute("user", user);
        }*/
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
//        dispatcher.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        System.out.println("desde NEXT");
        String salir = request.getParameter("salir");
        String newsC = request.getParameter("newsC");
        String nextJSP;
        nextJSP = "/indexOld.jsp";
        if(salir != null){
            session.invalidate();

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
            System.out.println("adios");
        } else if(newsC != null){ //    CONSULTA PUBLICA
            nextJSP = "/clientePub.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        } else {
//            Usuario user = (Usuario) session.getAttribute("user");
//            System.out.println("user.getCompleteName() = " + user.getCompleteName());

//            NUEVO

            doPost(request, response);

            //HABIA

            /*
            if(user != null){
                doPost(request, response);
//                nextJSP = "/frameset.jsp";
            } else {
                //usuario nulo
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
                dispatcher.forward(request, response);
            }*/
        }

    }
}
