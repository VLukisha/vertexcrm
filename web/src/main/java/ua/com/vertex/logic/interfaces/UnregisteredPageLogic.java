package ua.com.vertex.logic.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UnregisteredPageLogic {

    void getCertificateDetails(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}