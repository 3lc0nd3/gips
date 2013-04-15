package co.com.lh.smsfin.util;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import javax.servlet.ServletException;
import java.io.IOException;
import static java.lang.String.format;

/**
 * Created by Edward L. Ramirez A.
 * cel 300 554 3367
 * email elramireza@gmail.com
 * User: usuariox
 * Date: Jul 27, 2011
 * Time: 3:27:57 PM
 */
public class FrontController {

    public final int VIDEO    =1;
    public final int AUDIO    =2;
    public final int IMAGEN   =3;
    public final int OTRO     =4;

    Logger logger  = Logger.getLogger(FrontController.class);

    public String getInclude(String page) throws ServletException, IOException {
        WebContext wctx = WebContextFactory.get();
        String url = format("/%s.jsp", page);
        return wctx.forwardToString(url);
    }

    public String getIncludeNews(int idNews,
                                 String page,
                                 int role)
            throws ServletException, IOException {
        WebContext wctx = WebContextFactory.get();
        wctx.getHttpServletRequest().setAttribute("idNews", idNews);
        String url = format("/%s.jsp?role="+role, page);
        return wctx.forwardToString(url);
    }

    public String getPlayer(int idFile, int tipoSource, String filename) throws IOException, ServletException {
        WebContext wctx = WebContextFactory.get();
        String page;
        switch (tipoSource){
            case VIDEO:
                page = "Video";
                break;
            case AUDIO:
                page = "Audio";
                break;
            case IMAGEN:
                page = "Imagen";
                break;
            default:
                page = "Otro";
        }//fin switch

        String url = format("/player/player%s.jsp?idFile=%d&tipoSource=%d&fileName=%s", page, idFile, tipoSource, filename);
        logger.debug("url = " + url);
        return wctx.forwardToString(url);
    }
}
