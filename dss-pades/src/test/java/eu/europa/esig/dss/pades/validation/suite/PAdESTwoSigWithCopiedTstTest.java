package eu.europa.esig.dss.pades.validation.suite;

import eu.europa.esig.dss.diagnostic.DiagnosticData;
import eu.europa.esig.dss.diagnostic.SignatureWrapper;
import eu.europa.esig.dss.diagnostic.TimestampWrapper;
import eu.europa.esig.dss.enumerations.SignatureLevel;
import eu.europa.esig.dss.enumerations.TimestampType;
import eu.europa.esig.dss.model.DSSDocument;
import eu.europa.esig.dss.model.InMemoryDocument;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PAdESTwoSigWithCopiedTstTest extends AbstractPAdESTestValidation {

    @Override
    protected DSSDocument getSignedDocument() {
        return new InMemoryDocument(getClass().getResourceAsStream("/validation/pades-two-sig-copied-tst.pdf"));
    }

    @Override
    protected void checkNumberOfSignatures(DiagnosticData diagnosticData) {
        super.checkNumberOfSignatures(diagnosticData);

        assertEquals(2, diagnosticData.getSignatures().size());
    }

    @Override
    protected void checkSignatureLevel(DiagnosticData diagnosticData) {
        for (SignatureWrapper signatureWrapper : diagnosticData.getSignatures()) {
            assertEquals(SignatureLevel.PAdES_BASELINE_T, signatureWrapper.getSignatureFormat());
        }
    }

    @Override
    protected void checkTimestamps(DiagnosticData diagnosticData) {
        List<TimestampWrapper> timestampList = diagnosticData.getTimestampList();
        assertEquals(2, timestampList.size());

        boolean sigTstValidFound = false;
        boolean sigTstInvalidFound = false;
        for (TimestampWrapper timestampWrapper : timestampList) {
            assertEquals(TimestampType.SIGNATURE_TIMESTAMP, timestampWrapper.getType());
            if (timestampWrapper.isMessageImprintDataIntact()) {
                assertTrue(timestampWrapper.isMessageImprintDataFound());
                assertTrue(timestampWrapper.isSignatureIntact());
                assertTrue(timestampWrapper.isSignatureValid());
                sigTstValidFound = true;
            } else {
                assertTrue(timestampWrapper.isMessageImprintDataFound());
                assertTrue(timestampWrapper.isSignatureIntact());
                assertFalse(timestampWrapper.isSignatureValid());
                sigTstInvalidFound = true;
            }
        }
        assertTrue(sigTstValidFound);
        assertTrue(sigTstInvalidFound);

        assertEquals(timestampList.get(0).getDigestAlgoAndValue().getDigestMethod(),
                timestampList.get(1).getDigestAlgoAndValue().getDigestMethod());
        assertArrayEquals(timestampList.get(0).getDigestAlgoAndValue().getDigestValue(),
                timestampList.get(1).getDigestAlgoAndValue().getDigestValue());
    }

}