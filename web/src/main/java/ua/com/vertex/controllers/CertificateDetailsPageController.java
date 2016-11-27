package ua.com.vertex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.vertex.logic.interfaces.UnregisteredPageLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CertificateDetailsPageController {
    private UnregisteredPageLogic logic;

    @Autowired
    private void setLogic(UnregisteredPageLogic logic) {
        this.logic = logic;
    }

    @RequestMapping(value = "/certificateDetails")
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logic.getCertificateDetails(req, resp);
    }
}