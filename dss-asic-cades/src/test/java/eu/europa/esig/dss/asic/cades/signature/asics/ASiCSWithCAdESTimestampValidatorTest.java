package eu.europa.esig.dss.asic.cades.signature.asics;

import eu.europa.esig.dss.asic.cades.ASiCWithCAdESContainerExtractor;
import eu.europa.esig.dss.asic.cades.ASiCWithCAdESTimestampParameters;
import eu.europa.esig.dss.asic.cades.signature.ASiCWithCAdESService;
import eu.europa.esig.dss.asic.cades.validation.ASiCWithCAdESTimestampValidator;
import eu.europa.esig.dss.asic.cades.validation.AbstractASiCWithCAdESTestValidation;
import eu.europa.esig.dss.asic.common.ASiCExtractResult;
import eu.europa.esig.dss.detailedreport.DetailedReport;
import eu.europa.esig.dss.detailedreport.jaxb.XmlBasicBuildingBlocks;
import eu.europa.esig.dss.detailedreport.jaxb.XmlConclusion;
import eu.europa.esig.dss.detailedreport.jaxb.XmlTimestamp;
import eu.europa.esig.dss.diagnostic.DiagnosticData;
import eu.europa.esig.dss.enumerations.ASiCContainerType;
import eu.europa.esig.dss.enumerations.Indication;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.InMemoryDocument;
import eu.europa.esig.dss.model.MimeType;
import eu.europa.esig.dss.simplereport.SimpleReport;
import eu.europa.esig.dss.utils.Utils;
import eu.europa.esig.dss.validation.AdvancedSignature;
import eu.europa.esig.dss.validation.CertificateVerifier;
import eu.europa.esig.dss.validation.SignedDocumentValidator;
import eu.europa.esig.validationreport.jaxb.SignatureValidationReportType;
import eu.europa.esig.validationreport.jaxb.ValidationReportType;
import eu.europa.esig.validationreport.jaxb.ValidationStatusType;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ASiCSWithCAdESTimestampValidatorTest extends AbstractASiCWithCAdESTestValidation {

    private static List<DSSDocument> documentsToSign;

    @BeforeEach
    public void init() {
        documentsToSign = new ArrayList<>();
        documentsToSign.add(new InMemoryDocument("Hello World !".getBytes(), "test.text", MimeType.TEXT));
        documentsToSign.add(new InMemoryDocument("Bye World !".getBytes(), "test2.text", MimeType.TEXT));
    }

    @Override
    protected DSSDocument getSignedDocument() {
        ASiCWithCAdESTimestampParameters timestampParameters = new ASiCWithCAdESTimestampParameters();
        timestampParameters.aSiC().setContainerType(ASiCContainerType.ASiC_S);

        ASiCWithCAdESService service = new ASiCWithCAdESService(getCompleteCertificateVerifier());
        service.setTspSource(getGoodTsa());

        return service.timestamp(documentsToSign, timestampParameters);
    }

    @Override
    protected SignedDocumentValidator getValidator(DSSDocument signedDocument) {
        ASiCWithCAdESContainerExtractor containerExtractor = new ASiCWithCAdESContainerExtractor(signedDocument);
        ASiCExtractResult asicExtractResult = containerExtractor.extract();
        List<DSSDocument> timestampDocuments = asicExtractResult.getTimestampDocuments();
        assertEquals(1, timestampDocuments.size());
        DSSDocument archiveTimestamp = timestampDocuments.get(0);

        List<DSSDocument> signedDocuments = asicExtractResult.getSignedDocuments();
        assertEquals(1, signedDocuments.size());
        assertEquals("package.zip", signedDocuments.get(0).getName());

        List<DSSDocument> containerDocuments = asicExtractResult.getContainerDocuments();
        assertEquals(2, containerDocuments.size());

        CertificateVerifier certificateVerifier = getCompleteCertificateVerifier();

        ASiCWithCAdESTimestampValidator asicsWithCAdESTimestampValidator = new ASiCWithCAdESTimestampValidator(
                archiveTimestamp);
        asicsWithCAdESTimestampValidator.setTimestampedData(signedDocuments.get(0));
        asicsWithCAdESTimestampValidator.setArchiveDocuments(containerDocuments);
        asicsWithCAdESTimestampValidator.setCertificateVerifier(certificateVerifier);
        return asicsWithCAdESTimestampValidator;
    }

    @Override
    protected void verifySimpleReport(SimpleReport simpleReport) {
        super.verifySimpleReport(simpleReport);

        assertTrue(Utils.isCollectionNotEmpty(simpleReport.getTimestampIdList()));
        assertNotNull(simpleReport.getFirstTimestampId());
        String timestampId = simpleReport.getFirstTimestampId();
        assertNotNull(simpleReport.getCertificateChain(timestampId));
        assertTrue(Utils.isCollectionNotEmpty(simpleReport.getCertificateChain(timestampId).getCertificate()));
        assertNotNull(simpleReport.getProducedBy(timestampId));
        assertNotNull(simpleReport.getProductionTime(timestampId));
        assertNotNull(simpleReport.getValidationTime());
        assertFalse(Utils.isCollectionEmpty(simpleReport.getQualificationErrors(timestampId))); // qualification error message
    }

    @Override
    protected void verifyDetailedReport(DetailedReport detailedReport) {
        super.verifyDetailedReport(detailedReport);

        List<String> timestampIds = detailedReport.getTimestampIds();
        assertEquals(1, timestampIds.size());
        String timestampId = timestampIds.get(0);
        assertEquals(Indication.PASSED, detailedReport.getTimestampValidationIndication(timestampId));

        XmlBasicBuildingBlocks timestampBBB = detailedReport.getBasicBuildingBlockById(timestampId);
        assertNotNull(timestampBBB.getCertificateChain());
        assertEquals(2, timestampBBB.getCertificateChain().getChainItem().size());
        assertEquals(Indication.PASSED, timestampBBB.getConclusion().getIndication());

        assertTrue(Utils.isCollectionEmpty(timestampBBB.getConclusion().getErrors()));
        assertTrue(Utils.isCollectionEmpty(timestampBBB.getConclusion().getWarnings()));
        assertTrue(Utils.isCollectionEmpty(timestampBBB.getConclusion().getInfos()));

        XmlTimestamp xmlTimestamp = detailedReport.getXmlTimestampById(timestampId);
        XmlConclusion qualConslusion = xmlTimestamp.getValidationTimestampQualification().getConclusion();
        assertEquals(Indication.FAILED, qualConslusion.getIndication());
        assertFalse(Utils.isCollectionEmpty(qualConslusion.getErrors()));
    }

    @Override
    protected void verifyETSIValidationReport(ValidationReportType etsiValidationReportJaxb) {
        super.verifyETSIValidationReport(etsiValidationReportJaxb);

        List<SignatureValidationReportType> signatureValidationReports = etsiValidationReportJaxb.getSignatureValidationReport();
        assertNotNull(signatureValidationReports);
        assertEquals(1, signatureValidationReports.size());
    }

    @Override
    protected void checkAdvancedSignatures(List<AdvancedSignature> signatures) {
        assertTrue(Utils.isCollectionEmpty(signatures));
    }

    @Override
    protected void checkNumberOfSignatures(DiagnosticData diagnosticData) {
        assertTrue(Utils.isCollectionEmpty(diagnosticData.getSignatures()));
    }

    @Override
    protected void checkContainerInfo(DiagnosticData diagnosticData) {
        assertNull(diagnosticData.getContainerInfo());
    }

    @Override
    protected void validateValidationStatus(ValidationStatusType signatureValidationStatus) {
        assertEquals(Indication.NO_SIGNATURE_FOUND, signatureValidationStatus.getMainIndication());
    }

    @Override
    protected String getSigningAlias() {
        return GOOD_USER;
    }

}