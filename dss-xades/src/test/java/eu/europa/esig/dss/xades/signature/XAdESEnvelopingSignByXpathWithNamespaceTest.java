package eu.europa.esig.dss.xades.signature;

import eu.europa.esig.dss.diagnostic.DiagnosticData;
import eu.europa.esig.dss.enumerations.DigestAlgorithm;
import eu.europa.esig.dss.enumerations.SignatureLevel;
import eu.europa.esig.dss.enumerations.SignaturePackaging;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.FileDocument;
import eu.europa.esig.dss.signature.DocumentSignatureService;
import eu.europa.esig.dss.validation.SignedDocumentValidator;
import eu.europa.esig.dss.xades.XAdESSignatureParameters;
import eu.europa.esig.dss.xades.XAdESTimestampParameters;
import eu.europa.esig.dss.xades.reference.CanonicalizationTransform;
import eu.europa.esig.dss.xades.reference.DSSReference;
import eu.europa.esig.dss.xades.reference.DSSTransform;
import eu.europa.esig.dss.xades.reference.XPathTransform;
import eu.europa.esig.dss.xml.common.definition.DSSNamespace;
import eu.europa.esig.dss.xml.utils.DomUtils;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// See DSS-3321
public class XAdESEnvelopingSignByXpathWithNamespaceTest extends AbstractXAdESTestSignature {

    private DocumentSignatureService<XAdESSignatureParameters, XAdESTimestampParameters> service;
    private XAdESSignatureParameters signatureParameters;
    private DSSDocument documentToSign;

    @BeforeEach
    public void init() throws Exception {
        DomUtils.registerNamespace(new DSSNamespace("urn:czech-ba:instant-payments:v1:instantPayment", "czip"));

        documentToSign = new FileDocument("src/test/resources/ns-prefixes-sample.xml");

        signatureParameters = new XAdESSignatureParameters();
        signatureParameters.bLevel().setSigningDate(new Date());
        signatureParameters.setSigningCertificate(getSigningCert());
        signatureParameters.setCertificateChain(getCertificateChain());
        signatureParameters.setSignaturePackaging(SignaturePackaging.ENVELOPED);
        signatureParameters.setSignatureLevel(SignatureLevel.XAdES_BASELINE_B);

        DSSReference reference = new DSSReference();
        reference.setId("REF-ID1");
        reference.setDigestMethodAlgorithm(DigestAlgorithm.SHA256);
        reference.setUri("");

        List<DSSTransform> transforms = new ArrayList<>();
        transforms.add(new XPathTransform("not(ancestor-or-self::czip:timestamps)"));
        transforms.add(new XPathTransform("not(ancestor-or-self::ds:Signature)"));
        transforms.add(new CanonicalizationTransform("http://www.w3.org/2006/12/xml-c14n11"));
        reference.setTransforms(transforms);

        reference.setContents(documentToSign);

        signatureParameters.setReferences(Collections.singletonList(reference));

        service = new XAdESService(getOfflineCertificateVerifier());
    }

    @Override
    protected void verifyOriginalDocuments(SignedDocumentValidator validator, DiagnosticData diagnosticData) {
        // skip
    }

    @Override
    protected DocumentSignatureService<XAdESSignatureParameters, XAdESTimestampParameters> getService() {
        return service;
    }

    @Override
    protected XAdESSignatureParameters getSignatureParameters() {
        return signatureParameters;
    }

    @Override
    protected DSSDocument getDocumentToSign() {
        return documentToSign;
    }

    @Override
    protected String getSigningAlias() {
        return GOOD_USER;
    }

}