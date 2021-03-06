package ua.com.vertex.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.vertex.beans.Certificate;
import ua.com.vertex.dao.interfaces.CertificateDaoInf;
import ua.com.vertex.logic.interfaces.CertificateLogic;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateLogicImpl implements CertificateLogic {

    private static final Logger LOGGER = LogManager.getLogger(CertificateLogicImpl.class);
    private static final String LOG_ALLCERT = "Call - certificateDao.getAllCertificateByUserId(%s);";
    private static final String LOG_CERT = "Call - certificateDao.getCertificateById(%s);";



    private CertificateDaoInf certificateDaoInf;

    @Autowired
    public CertificateLogicImpl(CertificateDaoInf certificateDaoInf) {
        this.certificateDaoInf = certificateDaoInf;
    }

    public List<Certificate> getAllCertificatesByUserId(int userId) {
        LOGGER.debug(String.format(LOG_ALLCERT, Integer.toString(userId)));
        return certificateDaoInf.getAllCertificatesByUserId(userId);
    }

    public Optional<Certificate> getCertificateById(int certificateId) {
        LOGGER.debug(String.format(LOG_CERT, Integer.toString(certificateId)));
        return certificateDaoInf.getCertificateById(certificateId);
    }
}
