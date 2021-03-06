package ua.com.vertex.controllers;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.InternalResourceView;
import ua.com.vertex.beans.Certificate;
import ua.com.vertex.beans.User;
import ua.com.vertex.logic.interfaces.CertDetailsPageLogic;
import ua.com.vertex.utils.Storage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class CertificateDetailsPageControllerTest {

    @Mock
    private CertDetailsPageLogic logic;

    @Mock
    private Storage storage;

    @Mock
    private Model model;

    @Mock
    private BindingResult result;

    @Mock
    private Certificate certificate;

    private CertificateDetailsPageController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new CertificateDetailsPageController(logic, storage);
    }

    @Test
    public void certificateDetailsWebMvcShouldReturnPageView() throws Exception {
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("certificateDetails"))
                .build();
        mockMvc.perform(get("/certificateDetails"))
                .andExpect(view().name("certificateDetails"));
    }

    @Test
    public void ProcessFormWebMvcShouldReturnPageView() throws Exception {
        Optional<Certificate> optionalC = Optional.of(new Certificate());
        Optional<User> optionalU = Optional.of(new User());

        when(result.hasErrors()).thenReturn(false);
        when(certificate.getCertificationId()).thenReturn(222);
        when(logic.getCertificateDetails(222)).thenReturn(optionalC);
        when(certificate.getUserId()).thenReturn(22);
        when(logic.getUserDetails(22)).thenReturn(optionalU);

        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("certificateDetails"))
                .build();
        mockMvc.perform(get("/processCertificateDetails"))
                .andExpect(view().name("certificateDetails"));
    }

    @Test
    public void addErrorAttributeAfterRequestingInvalidId() {
        when(result.hasErrors()).thenReturn(true);

        controller.processCertificateDetails(certificate, result, model);
        verify(model).addAttribute("error", "Entered value must be a positive integer!");
    }

    @Test
    public void addCertificateAttributeAfterRetrievingNotEmptyOptional() {
        Certificate certificate = new Certificate.Builder()
                .setCertificationId(222)
                .setUserId(22)
                .setCertificationDate(LocalDate.now())
                .setCourseName("Java Professional")
                .setLanguage("Java")
                .getInstance();
        User user = new User.Builder()
                .setUserId(22)
                .getInstance();
        Optional<Certificate> optionalC = Optional.of(certificate);
        Optional<User> optionalU = Optional.of(user);

        when(result.hasErrors()).thenReturn(false);
        when(logic.getCertificateDetails(222)).thenReturn(optionalC);
        when(logic.getUserDetails(22)).thenReturn(optionalU);

        controller.processCertificateDetails(certificate, result, model);
        verify(model).addAttribute("certificate", certificate);
    }

    @Test
    public void addUserAttributeAfterRetrievingNotEmptyOptional() {
        Certificate certificate = new Certificate.Builder()
                .setCertificationId(222)
                .setUserId(22)
                .setCertificationDate(LocalDate.now())
                .setCourseName("Java Professional")
                .setLanguage("Java")
                .getInstance();
        User user = new User.Builder()
                .setUserId(22)
                .getInstance();
        Optional<Certificate> optionalC = Optional.of(certificate);
        Optional<User> optionalU = Optional.of(user);

        when(result.hasErrors()).thenReturn(false);
        when(logic.getCertificateDetails(222)).thenReturn(optionalC);
        when(logic.getUserDetails(22)).thenReturn(optionalU);

        controller.processCertificateDetails(certificate, result, model);
        verify(model).addAttribute("user", user);
    }

    @Test
    public void addErrorAttributeAfterRetrievingEmptyCertificateOptional() {
        Certificate certificate = new Certificate.Builder()
                .setCertificationId(55555)
                .getInstance();
        Optional<Certificate> optionalC = Optional.empty();

        when(result.hasErrors()).thenReturn(false);
        when(logic.getCertificateDetails(55555)).thenReturn(optionalC);

        controller.processCertificateDetails(certificate, result, model);
        verify(model).addAttribute("error", "No certificate with this ID!");
    }

    @Test
    public void setUserPhotoAfterRetrievingNotEmptyOptional() {
        byte[] photo = {(byte) 1};
        Certificate certificate = new Certificate.Builder()
                .setCertificationId(222)
                .setUserId(22)
                .setCertificationDate(LocalDate.now())
                .setCourseName("Java Professional")
                .setLanguage("Java")
                .getInstance();
        User user = new User.Builder()
                .setUserId(22)
                .setPhoto(photo)
                .getInstance();
        Optional<Certificate> optionalC = Optional.of(certificate);
        Optional<User> optionalU = Optional.of(user);

        when(result.hasErrors()).thenReturn(false);
        when(logic.getCertificateDetails(222)).thenReturn(optionalC);
        when(logic.getUserDetails(22)).thenReturn(optionalU);

        controller.processCertificateDetails(certificate, result, model);
        verify(storage).setPhoto(photo);
    }

    @Test
    public void photoWebMvcShouldReturnCorrectView() throws Exception {
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("certificateHolderPhoto"))
                .build();
        mockMvc.perform(get("/certificateHolderPhoto"))
                .andExpect(view().name("certificateHolderPhoto"));
    }

    @Test
    public void photoWebMvcShouldReturnErrorViewAfterUncheckedException() throws Exception {
        when(storage.getPhoto()).thenThrow(new RuntimeException());
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("certificateHolderPhoto"))
                .build();
        mockMvc.perform(get("/certificateHolderPhoto"))
                .andExpect(view().name("error"));
    }

    @Test
    public void addModelAttributeAfterRetrievingPhoto() throws IOException {
        byte[] data = {(byte) 1};

        when(storage.getPhoto()).thenReturn(data);
        String encodedImage = Base64.encode(data);

        controller.showUserPhoto(model);
        verify(model).addAttribute("image", encodedImage);
    }
}
