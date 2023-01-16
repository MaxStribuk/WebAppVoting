package web.util;

import javax.servlet.http.HttpServletRequest;

public class RequestParamHandler {

    public static final String GENRE_PARAM_NAME = "genre";
    public static final String ARTIST_PARAM_NAME = "artist";
    public static final String ID_PARAM_NAME = "id";


    public static String getRequestParam(HttpServletRequest req, String name) {
        String[] param = req.getParameterValues(name);
        if (param == null || param.length == 0) {
            throw new IllegalArgumentException("The parameter " + name +
                    " was passed incorrectly");
        }
        if (param.length > 1) {
            throw new IllegalArgumentException("More than one parameter " +
                    name + " has been provided");
        }
        if (param[0] == null || param[0].isBlank()) {
            throw new IllegalArgumentException("Invalid " + name +
                    " parameter provided");
        }

        return param[0];
    }

    public static int getID(String id) {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid " + id +
                    " parameter provided");
        }
    }
}