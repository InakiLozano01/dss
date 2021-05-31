package eu.europa.esig.dss.token;

import eu.europa.esig.dss.enumerations.DigestAlgorithm;
import eu.europa.esig.dss.enumerations.EncryptionAlgorithm;
import eu.europa.esig.dss.enumerations.MaskGenerationFunction;
import eu.europa.esig.dss.enumerations.SignatureAlgorithm;
import eu.europa.esig.dss.model.Digest;
import eu.europa.esig.dss.model.SignatureValue;
import eu.europa.esig.dss.model.ToBeSigned;
import eu.europa.esig.dss.spi.DSSSecurityProvider;
import eu.europa.esig.dss.spi.DSSUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore.PasswordProtection;
import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignDigestRSASSAPSSSignatureAlgorithmTest {

    static {
        Security.addProvider(DSSSecurityProvider.getSecurityProvider());
    }

    private static final Logger LOG = LoggerFactory.getLogger(SignDigestRSASSAPSSTest.class);

    private static Collection<SignatureAlgorithm> data() {
        Collection<SignatureAlgorithm> rsaCombinations = new ArrayList<>();
        for (DigestAlgorithm digestAlgorithm : DigestAlgorithm.values()) {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.getAlgorithm(
                    EncryptionAlgorithm.RSA, digestAlgorithm, MaskGenerationFunction.MGF1);
            if (signatureAlgorithm != null) {
                rsaCombinations.add(signatureAlgorithm);
            }
        }
        return rsaCombinations;
    }

    @ParameterizedTest(name = "SignatureAlgorithm {index} : {0}")
    @MethodSource("data")
    public void testPkcs12PSS(SignatureAlgorithm signatureAlgorithm) throws IOException {
        try (Pkcs12SignatureToken signatureToken = new Pkcs12SignatureToken("src/test/resources/user_a_rsa.p12",
                new PasswordProtection("password".toCharArray()))) {

            List<DSSPrivateKeyEntry> keys = signatureToken.getKeys();
            KSPrivateKeyEntry entry = (KSPrivateKeyEntry) keys.get(0);

            ToBeSigned toBeSigned = new ToBeSigned("Hello world".getBytes("UTF-8"));

            SignatureValue signValue = signatureToken.sign(toBeSigned, signatureAlgorithm, entry);
            assertNotNull(signValue.getAlgorithm());
            LOG.info("Sig value : {}", Base64.getEncoder().encodeToString(signValue.getValue()));
            try {
                Signature sig = Signature.getInstance(signValue.getAlgorithm().getJCEId());
                sig.initVerify(entry.getCertificate().getPublicKey());
                sig.update(toBeSigned.getBytes());
                assertTrue(sig.verify(signValue.getValue()));
            } catch (GeneralSecurityException e) {
                Assertions.fail(e.getMessage());
            }

            try {
                Cipher cipher = Cipher.getInstance(entry.getEncryptionAlgorithm().getName(),
                        DSSSecurityProvider.getSecurityProviderName());
                cipher.init(Cipher.DECRYPT_MODE, entry.getCertificate().getPublicKey());
                byte[] decrypted = cipher.doFinal(signValue.getValue());
                LOG.info("Decrypted : {}", Base64.getEncoder().encodeToString(decrypted));
            } catch (GeneralSecurityException e) {
                Assertions.fail(e.getMessage());
            }

            final DigestAlgorithm digestAlgorithm = signatureAlgorithm.getDigestAlgorithm();
            final byte[] digestBinaries = DSSUtils.digest(digestAlgorithm, toBeSigned.getBytes());
            Digest digest = new Digest(digestAlgorithm, digestBinaries);

            SignatureValue signDigestValue = signatureToken.signDigest(digest, signatureAlgorithm, entry);
            assertNotNull(signDigestValue.getAlgorithm());
            assertEquals(signValue.getAlgorithm(), signDigestValue.getAlgorithm());
            LOG.info("Sig value : {}", Base64.getEncoder().encodeToString(signDigestValue.getValue()));

            try {
                Signature sig = Signature.getInstance(signDigestValue.getAlgorithm().getJCEId());
                sig.initVerify(entry.getCertificate().getPublicKey());
                sig.update(toBeSigned.getBytes());
                assertTrue(sig.verify(signDigestValue.getValue()));
            } catch (GeneralSecurityException e) {
                Assertions.fail(e.getMessage());
            }

            try {
                Cipher cipher = Cipher.getInstance(entry.getEncryptionAlgorithm().getName(),
                        DSSSecurityProvider.getSecurityProviderName());
                cipher.init(Cipher.DECRYPT_MODE, entry.getCertificate().getPublicKey());
                byte[] decrypted = cipher.doFinal(signDigestValue.getValue());
                LOG.info("Decrypted : {}", Base64.getEncoder().encodeToString(decrypted));
            } catch (GeneralSecurityException e) {
                Assertions.fail(e.getMessage());
            }

            // should not be equals
            assertNotEquals(Base64.getEncoder().encodeToString(signValue.getValue()), Base64.getEncoder().encodeToString(signDigestValue.getValue()));
        }
    }

}