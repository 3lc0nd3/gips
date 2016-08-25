package co.com.lh.smsfin.filter;

import org.directwebremoting.AjaxFilter;
import org.directwebremoting.AjaxFilterChain;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.extend.LoginRequiredException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


/**
 * Created by Edward L. Ramirez A.
 * cel 300 554 3367
 * email elramireza@gmail.com
 * User: usuariox
 * Date: Jul 27, 2011
 * Time: 5:28:34 PM
 */
public class DwrFilter implements AjaxFilter {

    Logger logger = Logger.getLogger(DwrFilter.class);

    public Object doFilter(Object obj,
                           Method method,
                           Object[] params,
                           AjaxFilterChain chain) throws Exception {
//        logger.debug("");
//        logger.debug("");
//        logger.debug("/////////////////////////////***********************//////////////////////////////////////");
//        logger.debug("/////////////////////////////****   DWR Filter 1 ***//////////////////////////////////////");
//        logger.debug("/////////////////////////////***********************//////////////////////////////////////");


        /**
         * Con esto se obtiene el Objeto que hace la llamada
         */
        /*
        NewsDAO newsDAO = (NewsDAO) obj;
        logger.debug("newsDAO.getMedia(1).getName() = " + newsDAO.getMedia(1).getName());
        */

        /**
         * Tambien se tiene acceso al metodo invocado
         */
        /*
        logger.debug("method.getName() = " + method.getName());
        */

        WebContext webContext = WebContextFactory.get();
        /*
        Container c = webContext.getContainer();
        Collection<String> g = c.getBeanNames();
        for (String s : g) {
            logger.debug("s = " + s);
        }
*/
        /*HttpServletRequest req = webContext.getHttpServletRequest();
        Usuario user = (Usuario) req.getSession().getAttribute("user");
        if (user == null) {
            logger.debug("Usuario Nulo - no hay usuario en session");
            throw new LoginRequiredException("La sesión a terminado. Vuelva a ingresar al sistema.") ;
        } *//*else {
            logger.debug("user.getCompleteName() = " + user.getCompleteName());
        }*//*
*/
        return chain.doFilter(obj, method, params);
    }
}
