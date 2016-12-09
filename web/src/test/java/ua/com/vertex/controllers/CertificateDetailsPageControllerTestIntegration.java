package ua.com.vertex.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.vertex.beans.ImageStorage;
import ua.com.vertex.context.TestMainContext;
import ua.com.vertex.logic.interfaces.CertDetailsPageLogic;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestMainContext.class)
@ActiveProfiles("test")
public class CertificateDetailsPageControllerTestIntegration {

    @Autowired
    private CertDetailsPageLogic logic;

    @Autowired
    private ImageStorage storage;

    @Test
    public void logicShouldNotBeNull() {
        assertNotNull(logic);
    }

    @Test
    public void imageStorageShouldNotBeNull() {
        assertNotNull(storage);
    }
}