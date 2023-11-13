/**
 * DSS - Digital Signature Services
 * Copyright (C) 2015 European Commission, provided under the CEF programme
 * 
 * This file is part of the "DSS - Digital Signature Services" project.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package eu.europa.esig.dss.pades.signature.suite;

import eu.europa.esig.dss.diagnostic.CertificateWrapper;
import eu.europa.esig.dss.diagnostic.DiagnosticData;
import eu.europa.esig.dss.diagnostic.RevocationWrapper;
import eu.europa.esig.dss.diagnostic.SignatureWrapper;
import eu.europa.esig.dss.diagnostic.TimestampWrapper;
import eu.europa.esig.dss.enumerations.DigestAlgorithm;
import eu.europa.esig.dss.enumerations.MaskGenerationFunction;
import eu.europa.esig.dss.enumerations.SignatureLevel;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.InMemoryDocument;
import eu.europa.esig.dss.pades.PAdESSignatureParameters;
import eu.europa.esig.dss.pades.PAdESTimestampParameters;
import eu.europa.esig.dss.pades.signature.PAdESService;
import eu.europa.esig.dss.signature.DocumentSignatureService;
import eu.europa.esig.dss.validation.SignedDocumentValidator;
import eu.europa.esig.dss.validation.reports.Reports;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PAdESWithPSSTest extends AbstractPAdESTestSignature {

    private DocumentSignatureService<PAdESSignatureParameters, PAdESTimestampParameters> service;
    private PAdESSignatureParameters signatureParameters;
    private DSSDocument documentToSign;

    @BeforeEach
    public void init() throws Exception {
        documentToSign = new InMemoryDocument(getClass().getResourceAsStream("/sample.pdf"));

        signatureParameters = new PAdESSignatureParameters();
        signatureParameters.setSigningCertificate(getSigningCert());
        signatureParameters.setCertificateChain(getCertificateChain());
        signatureParameters.setSignatureLevel(SignatureLevel.PAdES_BASELINE_LTA);
        signatureParameters.setDigestAlgorithm(DigestAlgorithm.SHA256);
        signatureParameters.setMaskGenerationFunction(MaskGenerationFunction.MGF1);

        service = new PAdESService(getCertificateVerifierWithMGF1());
        service.setTspSource(getPSSGoodTsa());
    }

//    @Override
//    protected CertificateVerifier getCertificateVerifierWithMGF1() {
//        CertificateVerifier certificateVerifier = super.getCertificateVerifierWithMGF1();
//        certificateVerifier.setOcspSource(new );
//        certificateVerifier.setCrlSource(null);
//        return certificateVerifier;
//    }

    @Override
    protected void onDocumentSigned(byte[] byteArray) {
        super.onDocumentSigned(byteArray);

        InMemoryDocument doc = new InMemoryDocument(byteArray);

        SignedDocumentValidator validator = getValidator(doc);

        Reports reports = validator.validateDocument();

        DiagnosticData diagnosticData = reports.getDiagnosticData();
        verifyDiagnosticData(diagnosticData);

        Set<SignatureWrapper> allSignatures = diagnosticData.getAllSignatures();
        for (SignatureWrapper wrapper : allSignatures) {
            assertEquals(MaskGenerationFunction.MGF1, wrapper.getMaskGenerationFunction());
        }

        List<CertificateWrapper> usedCertificates = diagnosticData.getUsedCertificates();
        for (CertificateWrapper wrapper : usedCertificates) {
            assertEquals(MaskGenerationFunction.MGF1, wrapper.getMaskGenerationFunction());
        }

        Set<RevocationWrapper> allRevocationData = diagnosticData.getAllRevocationData();
        for (RevocationWrapper wrapper : allRevocationData) {
            assertEquals(MaskGenerationFunction.MGF1, wrapper.getMaskGenerationFunction());
        }

        List<TimestampWrapper> timestampList = diagnosticData.getTimestampList();
        for (TimestampWrapper wrapper : timestampList) {
            assertEquals(MaskGenerationFunction.MGF1, wrapper.getMaskGenerationFunction());
        }
    }

    @Override
    protected DocumentSignatureService<PAdESSignatureParameters, PAdESTimestampParameters> getService() {
        return service;
    }

    @Override
    protected PAdESSignatureParameters getSignatureParameters() {
        return signatureParameters;
    }

    @Override
    protected DSSDocument getDocumentToSign() {
        return documentToSign;
    }

    @Override
    protected String getSigningAlias() {
        return PSS_GOOD_USER;
    }

}
