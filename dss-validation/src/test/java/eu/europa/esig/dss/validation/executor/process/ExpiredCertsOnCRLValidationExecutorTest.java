package eu.europa.esig.dss.validation.executor.process;

import eu.europa.esig.dss.detailedreport.DetailedReport;
import eu.europa.esig.dss.detailedreport.jaxb.XmlBasicBuildingBlocks;
import eu.europa.esig.dss.detailedreport.jaxb.XmlCRS;
import eu.europa.esig.dss.detailedreport.jaxb.XmlConstraint;
import eu.europa.esig.dss.detailedreport.jaxb.XmlRAC;
import eu.europa.esig.dss.detailedreport.jaxb.XmlStatus;
import eu.europa.esig.dss.detailedreport.jaxb.XmlSubXCV;
import eu.europa.esig.dss.detailedreport.jaxb.XmlXCV;
import eu.europa.esig.dss.diagnostic.CertificateRevocationWrapper;
import eu.europa.esig.dss.diagnostic.CertificateWrapper;
import eu.europa.esig.dss.diagnostic.DiagnosticData;
import eu.europa.esig.dss.diagnostic.DiagnosticDataFacade;
import eu.europa.esig.dss.diagnostic.SignatureWrapper;
import eu.europa.esig.dss.diagnostic.jaxb.XmlDiagnosticData;
import eu.europa.esig.dss.enumerations.Indication;
import eu.europa.esig.dss.enumerations.SubIndication;
import eu.europa.esig.dss.i18n.MessageTag;
import eu.europa.esig.dss.simplereport.SimpleReport;
import eu.europa.esig.dss.validation.executor.signature.DefaultSignatureProcessExecutor;
import eu.europa.esig.dss.validation.process.ValidationProcessUtils;
import eu.europa.esig.dss.validation.reports.Reports;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExpiredCertsOnCRLValidationExecutorTest extends AbstractProcessExecutorTest {

    @Test
    void expiredCertsRevocationInfoTest() throws Exception {
        XmlDiagnosticData xmlDiagnosticData = DiagnosticDataFacade.newFacade().unmarshall(
                new File("src/test/resources/diag-data/expired-certs-revocation-info.xml"));
        assertNotNull(xmlDiagnosticData);

        DefaultSignatureProcessExecutor executor = new DefaultSignatureProcessExecutor();
        executor.setDiagnosticData(xmlDiagnosticData);
        executor.setValidationPolicy(loadDefaultPolicy());
        executor.setCurrentTime(xmlDiagnosticData.getValidationDate());

        Reports reports = executor.execute();
        SimpleReport simpleReport = reports.getSimpleReport();
        assertEquals(Indication.TOTAL_PASSED, simpleReport.getIndication(simpleReport.getFirstSignatureId()));

        DiagnosticData diagnosticData = reports.getDiagnosticData();
        SignatureWrapper signature = diagnosticData.getSignatureById(diagnosticData.getFirstSignatureId());
        CertificateWrapper signingCertificate = signature.getSigningCertificate();
        assertNotNull(signingCertificate);
        List<CertificateRevocationWrapper> certificateRevocationData = signingCertificate.getCertificateRevocationData();
        assertEquals(1, certificateRevocationData.size());
        CertificateRevocationWrapper certificateRevocation = certificateRevocationData.get(0);

        DetailedReport detailedReport = reports.getDetailedReport();
        XmlBasicBuildingBlocks signatureBBB = detailedReport.getBasicBuildingBlockById(detailedReport.getFirstSignatureId());
        assertNotNull(signatureBBB);

        XmlXCV xcv = signatureBBB.getXCV();
        assertNotNull(xcv);

        List<XmlSubXCV> subXCV = xcv.getSubXCV();
        assertEquals(2, subXCV.size());

        XmlSubXCV xmlSubXCV = subXCV.get(0);
        XmlCRS crs = xmlSubXCV.getCRS();
        assertNotNull(crs);

        List<XmlRAC> racs = crs.getRAC();
        assertEquals(1, racs.size());

        XmlRAC xmlRAC = racs.get(0);
        boolean consistencyCheckFound = false;
        for (XmlConstraint constraint : xmlRAC.getConstraint()) {
            if (MessageTag.BBB_XCV_IRDC.getId().equals(constraint.getName().getKey())) {
                assertEquals(i18nProvider.getMessage(MessageTag.REVOCATION_CONSISTENT_TL,
                        ValidationProcessUtils.getFormattedDate(certificateRevocation.getThisUpdate()),
                        ValidationProcessUtils.getFormattedDate(certificateRevocation.getSigningCertificate().getCertificateTSPServiceExpiredCertsRevocationInfo()),
                        ValidationProcessUtils.getFormattedDate(signingCertificate.getNotBefore()),
                        ValidationProcessUtils.getFormattedDate(signingCertificate.getNotAfter())), constraint.getAdditionalInfo());
                consistencyCheckFound = true;
            }
        }
        assertTrue(consistencyCheckFound);
    }

    @Test
    void expiredCertsOnCRLExtension() throws Exception {
        XmlDiagnosticData xmlDiagnosticData = DiagnosticDataFacade.newFacade().unmarshall(
                new File("src/test/resources/diag-data/expired-certs-on-crl-extension.xml"));
        assertNotNull(xmlDiagnosticData);

        DefaultSignatureProcessExecutor executor = new DefaultSignatureProcessExecutor();
        executor.setDiagnosticData(xmlDiagnosticData);
        executor.setValidationPolicy(loadDefaultPolicy());
        executor.setCurrentTime(xmlDiagnosticData.getValidationDate());

        Reports reports = executor.execute();
        SimpleReport simpleReport = reports.getSimpleReport();
        assertEquals(Indication.TOTAL_PASSED, simpleReport.getIndication(simpleReport.getFirstSignatureId()));

        DiagnosticData diagnosticData = reports.getDiagnosticData();
        SignatureWrapper signature = diagnosticData.getSignatureById(diagnosticData.getFirstSignatureId());
        CertificateWrapper signingCertificate = signature.getSigningCertificate();
        assertNotNull(signingCertificate);
        List<CertificateRevocationWrapper> certificateRevocationData = signingCertificate.getCertificateRevocationData();
        assertEquals(1, certificateRevocationData.size());
        CertificateRevocationWrapper certificateRevocation = certificateRevocationData.get(0);

        DetailedReport detailedReport = reports.getDetailedReport();
        XmlBasicBuildingBlocks signatureBBB = detailedReport.getBasicBuildingBlockById(detailedReport.getFirstSignatureId());
        assertNotNull(signatureBBB);

        XmlXCV xcv = signatureBBB.getXCV();
        assertNotNull(xcv);

        List<XmlSubXCV> subXCV = xcv.getSubXCV();
        assertEquals(2, subXCV.size());

        XmlSubXCV xmlSubXCV = subXCV.get(0);
        XmlCRS crs = xmlSubXCV.getCRS();
        assertNotNull(crs);

        List<XmlRAC> racs = crs.getRAC();
        assertEquals(1, racs.size());

        XmlRAC xmlRAC = racs.get(0);
        boolean consistencyCheckFound = false;
        for (XmlConstraint constraint : xmlRAC.getConstraint()) {
            if (MessageTag.BBB_XCV_IRDC.getId().equals(constraint.getName().getKey())) {
                assertEquals(i18nProvider.getMessage(MessageTag.REVOCATION_CONSISTENT_CRL,
                        ValidationProcessUtils.getFormattedDate(certificateRevocation.getThisUpdate()),
                        ValidationProcessUtils.getFormattedDate(certificateRevocation.getExpiredCertsOnCRL()),
                        ValidationProcessUtils.getFormattedDate(signingCertificate.getNotBefore()),
                        ValidationProcessUtils.getFormattedDate(signingCertificate.getNotAfter())), constraint.getAdditionalInfo());
                consistencyCheckFound = true;
            }
        }
        assertTrue(consistencyCheckFound);
    }

    @Test
    void expiredCertsRevocationInfoAndExpiredCertsOnCRLTest() throws Exception {
        XmlDiagnosticData xmlDiagnosticData = DiagnosticDataFacade.newFacade().unmarshall(
                new File("src/test/resources/diag-data/expired-certs-revocation-info-with-expired-certs-on-crl.xml"));
        assertNotNull(xmlDiagnosticData);

        DefaultSignatureProcessExecutor executor = new DefaultSignatureProcessExecutor();
        executor.setDiagnosticData(xmlDiagnosticData);
        executor.setValidationPolicy(loadDefaultPolicy());
        executor.setCurrentTime(xmlDiagnosticData.getValidationDate());

        Reports reports = executor.execute();
        SimpleReport simpleReport = reports.getSimpleReport();
        assertEquals(Indication.INDETERMINATE, simpleReport.getIndication(simpleReport.getFirstSignatureId()));
        assertEquals(SubIndication.CERTIFICATE_CHAIN_GENERAL_FAILURE, simpleReport.getSubIndication(simpleReport.getFirstSignatureId()));

        DiagnosticData diagnosticData = reports.getDiagnosticData();
        SignatureWrapper signature = diagnosticData.getSignatureById(diagnosticData.getFirstSignatureId());
        CertificateWrapper signingCertificate = signature.getSigningCertificate();
        assertNotNull(signingCertificate);
        List<CertificateRevocationWrapper> certificateRevocationData = signingCertificate.getCertificateRevocationData();
        assertEquals(1, certificateRevocationData.size());
        CertificateRevocationWrapper certificateRevocation = certificateRevocationData.get(0);

        DetailedReport detailedReport = reports.getDetailedReport();
        XmlBasicBuildingBlocks signatureBBB = detailedReport.getBasicBuildingBlockById(detailedReport.getFirstSignatureId());
        assertNotNull(signatureBBB);

        XmlXCV xcv = signatureBBB.getXCV();
        assertNotNull(xcv);

        List<XmlSubXCV> subXCV = xcv.getSubXCV();
        assertEquals(2, subXCV.size());

        XmlSubXCV xmlSubXCV = subXCV.get(0);
        XmlCRS crs = xmlSubXCV.getCRS();
        assertNotNull(crs);

        List<XmlRAC> racs = crs.getRAC();
        assertEquals(1, racs.size());

        XmlRAC xmlRAC = racs.get(0);
        boolean consistencyCheckFound = false;
        for (XmlConstraint constraint : xmlRAC.getConstraint()) {
            if (MessageTag.BBB_XCV_IRDC.getId().equals(constraint.getName().getKey())) {
                assertEquals(XmlStatus.NOT_OK, constraint.getStatus());
                assertEquals(i18nProvider.getMessage(MessageTag.REVOCATION_NOT_AFTER_AFTER,
                        ValidationProcessUtils.getFormattedDate(certificateRevocation.getExpiredCertsOnCRL()),
                        ValidationProcessUtils.getFormattedDate(signingCertificate.getNotBefore()),
                        ValidationProcessUtils.getFormattedDate(signingCertificate.getNotAfter())), constraint.getAdditionalInfo());
                consistencyCheckFound = true;
            }
        }
        assertTrue(consistencyCheckFound);
    }

}