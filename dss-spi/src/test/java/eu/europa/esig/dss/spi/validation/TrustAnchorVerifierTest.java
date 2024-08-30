package eu.europa.esig.dss.spi.validation;

import eu.europa.esig.dss.enumerations.Context;
import eu.europa.esig.dss.model.tsl.CertificateTrustTime;
import eu.europa.esig.dss.model.x509.CertificateToken;
import eu.europa.esig.dss.spi.DSSUtils;
import eu.europa.esig.dss.spi.tsl.TrustedListsCertificateSource;
import eu.europa.esig.dss.spi.x509.CommonTrustedCertificateSource;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrustAnchorVerifierTest {

    @Test
    void trustedSourceTest() {
        String certB64 = "MIID2jCCAzygAwIBAgIQEQmhFq6e3gQzhrtuqx1sKjAKBggqhkjOPQQDBDCBiDELMAkGA1UEBhMCQ1IxJjAkBgNVBAoMHUhlcm1lcyBTb2x1Y2lvbmVzIGRlIEludGVybmV0MRYwFAYDVQQLDA1GaXJtYSBkaWdpdGFsMR8wHQYDVQQDDBZBQyBwb2xpdGljYSBUU0EgSGVybWVzMRgwFgYDVQQFEw9DUEotMy0xMDEtOTE4MTAwHhcNMjEwMzMwMTc1MjQ1WhcNMzIwMzI3MTc1MjQ1WjB8MQswCQYDVQQGEwJDUjEmMCQGA1UECgwdSGVybWVzIFNvbHVjaW9uZXMgZGUgSW50ZXJuZXQxFjAUBgNVBAsMDUZpcm1hIERpZ2l0YWwxEzARBgNVBAMMCkhlcm1lcyBUU0ExGDAWBgNVBAUTD0NQSi0zLTEwMS05MTgxMDCBmzAQBgcqhkjOPQIBBgUrgQQAIwOBhgAEAHDK7hU/w3gbDuoo5Vqcaiz5ME95lHdOWUM65TuY0mnHZPCjimYW37jcKEYPafUCAupRaElO5LPggKxHfGLQK9fjAanRlTxIPmAl68SkpwYfIxeZeBvpfymVsg87YASTv3PY3lLLFi0i4U0/aO2jA92UGeNT+uelidcy9F/1ErOcSsrVo4IBTjCCAUowHwYDVR0jBBgwFoAUVm4BgFh89eU+jDQ3k5yB/Vw3LvcwDAYDVR0TAQH/BAIwADAWBgNVHSUBAf8EDDAKBggrBgEFBQcDCDAOBgNVHQ8BAf8EBAMCBkAwHQYDVR0OBBYEFGEm1wgsmDICKVBvr44pLjsmbRAfMIGOBggrBgEFBQcBAQSBgTB/MDwGCCsGAQUFBzAChjBodHRwczovL2FwcC5maXJtYS1kaWdpdGFsLmNyL2ZpbGVzL2FjLXRzYS12MS5jcnQwPwYIKwYBBQUHMAGGM2h0dHBzOi8vYXBwLmZpcm1hLWRpZ2l0YWwuY3IvaGFwaS9hYy9vY3NwL2FjLXRzYS12MTBBBgNVHR8EOjA4MDagNKAyhjBodHRwczovL2FwcC5maXJtYS1kaWdpdGFsLmNyL2ZpbGVzL2FjLXRzYS12MS5jcmwwCgYIKoZIzj0EAwQDgYsAMIGHAkFKqSNFeD7W9Gpf1fVaaE/ki58f4QDnrGsM3E1UHyryI9z6S5IVxIu2QCE6gwKRGYWopj1fdIFQeAC/R0ZNgXQAHgJCATx4P1dt/2iLQ4nfunRDgji3TpCFh0qZYw+N+RVs2SlVzRCzeujeLCOFyR92HBODZXx8ERUpT7r6MYou9kVCuJQL";
        String caCertB64 = "MIIDwzCCAyWgAwIBAgIQchZPmvJqypVzPW25na7FpDAKBggqhkjOPQQDBDCBgzELMAkGA1UEBhMCQ1IxJjAkBgNVBAoMHUhlcm1lcyBTb2x1Y2lvbmVzIGRlIEludGVybmV0MRYwFAYDVQQLDA1GaXJtYSBkaWdpdGFsMRowGAYDVQQDDBFBQyBSYWl6IEhlcm1lcyBWMTEYMBYGA1UEBRMPQ1BKLTMtMTAxLTkxODEwMB4XDTIxMDMzMDE3MDcwM1oXDTQ5MDMyMzE3MDcwM1owgYgxCzAJBgNVBAYTAkNSMSYwJAYDVQQKDB1IZXJtZXMgU29sdWNpb25lcyBkZSBJbnRlcm5ldDEWMBQGA1UECwwNRmlybWEgZGlnaXRhbDEfMB0GA1UEAwwWQUMgcG9saXRpY2EgVFNBIEhlcm1lczEYMBYGA1UEBRMPQ1BKLTMtMTAxLTkxODEwMIGbMBAGByqGSM49AgEGBSuBBAAjA4GGAAQAqxLqeFumtOJYvc3yKKJXonOo2AMM2UgkFN6UQ9kT/xzeyasPZycP+jXG56QI49hm7elSxDDDbNl6p1xtOVCzVWoBf1YktAuelBkZAVtXomrmkgYRcjWSnZ3ktHDLAGi3gg2ODnABly4RijXlyHdGBj56KuXL6ByjFmH5FqXOlaqZ8P6jggEvMIIBKzCBhQYIKwYBBQUHAQEEeTB3MDgGCCsGAQUFBzAChixodHRwczovL2FwcC5maXJtYS1kaWdpdGFsLmNyL2ZpbGVzL2FjLXYxLmNydDA7BggrBgEFBQcwAYYvaHR0cHM6Ly9hcHAuZmlybWEtZGlnaXRhbC5jci9oYXBpL2FjL29jc3AvYWMtdjEwHwYDVR0jBBgwFoAUnOXvW9wXgKlvjgkLMo3NmjthWm0wEgYDVR0TAQH/BAgwBgEB/wIBADA9BgNVHR8ENjA0MDKgMKAuhixodHRwczovL2FwcC5maXJtYS1kaWdpdGFsLmNyL2ZpbGVzL2FjLXYxLmNybDAOBgNVHQ8BAf8EBAMCAYYwHQYDVR0OBBYEFFZuAYBYfPXlPow0N5Ocgf1cNy73MAoGCCqGSM49BAMEA4GLADCBhwJBD6XOKDHsm3C4vg7IR0s7pJEqy1WyBRBd8jNJDyFIHTNTiPwYPPr1yKajmQXFmWIaF0/53nbONb1qoGx69nn0RLsCQgFX/T7v79V4qiF359N2pKQ8LibHxuhCW2XzMEHiASNBFz03Puhk0NwBrhFRjx+q+jWqPBzCAw0D/lSBZzpMj3sDFQ==";

        CertificateToken certificateToken = DSSUtils.loadCertificateFromBase64EncodedString(certB64);
        CertificateToken caCertificate = DSSUtils.loadCertificateFromBase64EncodedString(caCertB64);

        List<CertificateToken> certificateChain = Arrays.asList(certificateToken, caCertificate);

        TrustAnchorVerifier trustAnchorVerifier = TrustAnchorVerifier.createDefaultTrustAnchorVerifier();
        assertFalse(trustAnchorVerifier.isTrustedAtTime(certificateToken, new Date()));
        assertFalse(trustAnchorVerifier.isTrustedAtTime(caCertificate, new Date()));
        assertFalse(trustAnchorVerifier.isTrustedCertificateChain(certificateChain, new Date()));

        CommonTrustedCertificateSource trustedCertificateSource = new CommonTrustedCertificateSource();
        trustedCertificateSource.addCertificate(caCertificate);
        trustAnchorVerifier.setTrustedCertificateSource(trustedCertificateSource);

        assertFalse(trustAnchorVerifier.isTrustedAtTime(certificateToken, new Date()));
        assertTrue(trustAnchorVerifier.isTrustedAtTime(caCertificate, new Date()));
        assertTrue(trustAnchorVerifier.isTrustedCertificateChain(certificateChain, new Date()));

        TrustedListsCertificateSource trustedListsCertificateSource = new TrustedListsCertificateSource();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        Date startDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 2);
        Date endDate = calendar.getTime();

        Map<CertificateToken, List<CertificateTrustTime>> trustTimeByCertMap = new HashMap<>();

        trustTimeByCertMap.put(caCertificate, Collections.singletonList(new CertificateTrustTime(startDate, endDate)));
        trustedListsCertificateSource.setTrustTimeByCertificates(trustTimeByCertMap);
        trustAnchorVerifier.setTrustedCertificateSource(trustedListsCertificateSource);

        assertFalse(trustAnchorVerifier.isTrustedAtTime(certificateToken, new Date()));
        assertTrue(trustAnchorVerifier.isTrustedAtTime(caCertificate, new Date()));
        assertTrue(trustAnchorVerifier.isTrustedCertificateChain(certificateChain, new Date()));

        calendar.add(Calendar.YEAR, 1);
        Date futureDate = calendar.getTime();

        assertFalse(trustAnchorVerifier.isTrustedAtTime(certificateToken, futureDate));
        assertFalse(trustAnchorVerifier.isTrustedAtTime(caCertificate, futureDate));
        assertFalse(trustAnchorVerifier.isTrustedCertificateChain(certificateChain, futureDate));
        assertFalse(trustAnchorVerifier.isTrustedAtTime(caCertificate, futureDate, Context.TIMESTAMP));
        assertFalse(trustAnchorVerifier.isTrustedAtTime(caCertificate, futureDate, Context.REVOCATION));
        assertFalse(trustAnchorVerifier.isTrustedAtTime(caCertificate, futureDate, Context.SIGNATURE));
        assertFalse(trustAnchorVerifier.isTrustedCertificateChain(certificateChain, futureDate, Context.TIMESTAMP));
        assertFalse(trustAnchorVerifier.isTrustedCertificateChain(certificateChain, futureDate, Context.REVOCATION));
        assertFalse(trustAnchorVerifier.isTrustedCertificateChain(certificateChain, futureDate, Context.SIGNATURE));

        trustAnchorVerifier.setUseSunsetDate(false);

        assertFalse(trustAnchorVerifier.isTrustedAtTime(certificateToken, futureDate));
        assertTrue(trustAnchorVerifier.isTrustedAtTime(caCertificate, futureDate));
        assertTrue(trustAnchorVerifier.isTrustedCertificateChain(certificateChain, futureDate));
        assertTrue(trustAnchorVerifier.isTrustedAtTime(caCertificate, futureDate, Context.TIMESTAMP));
        assertTrue(trustAnchorVerifier.isTrustedAtTime(caCertificate, futureDate, Context.REVOCATION));
        assertTrue(trustAnchorVerifier.isTrustedAtTime(caCertificate, futureDate, Context.SIGNATURE));
        assertTrue(trustAnchorVerifier.isTrustedCertificateChain(certificateChain, futureDate, Context.TIMESTAMP));
        assertTrue(trustAnchorVerifier.isTrustedCertificateChain(certificateChain, futureDate, Context.REVOCATION));
        assertTrue(trustAnchorVerifier.isTrustedCertificateChain(certificateChain, futureDate, Context.SIGNATURE));

        trustAnchorVerifier.setUseSunsetDate(true);
        trustAnchorVerifier.setAcceptTimestampUntrustedCertificateChains(true);

        assertTrue(trustAnchorVerifier.isTrustedAtTime(certificateToken, futureDate, Context.TIMESTAMP));
        assertTrue(trustAnchorVerifier.isTrustedAtTime(caCertificate, futureDate, Context.TIMESTAMP));
        assertTrue(trustAnchorVerifier.isTrustedCertificateChain(certificateChain, futureDate, Context.TIMESTAMP));
        assertFalse(trustAnchorVerifier.isTrustedAtTime(certificateToken, futureDate, Context.REVOCATION));
        assertFalse(trustAnchorVerifier.isTrustedAtTime(caCertificate, futureDate, Context.REVOCATION));
        assertFalse(trustAnchorVerifier.isTrustedCertificateChain(certificateChain, futureDate, Context.REVOCATION));
        assertFalse(trustAnchorVerifier.isTrustedAtTime(certificateToken, futureDate, Context.SIGNATURE));
        assertFalse(trustAnchorVerifier.isTrustedAtTime(caCertificate, futureDate, Context.SIGNATURE));
        assertFalse(trustAnchorVerifier.isTrustedCertificateChain(certificateChain, futureDate, Context.SIGNATURE));

        trustAnchorVerifier.setAcceptTimestampUntrustedCertificateChains(false);
        trustAnchorVerifier.setAcceptRevocationUntrustedCertificateChains(true);

        assertFalse(trustAnchorVerifier.isTrustedAtTime(certificateToken, futureDate, Context.TIMESTAMP));
        assertFalse(trustAnchorVerifier.isTrustedAtTime(caCertificate, futureDate, Context.TIMESTAMP));
        assertFalse(trustAnchorVerifier.isTrustedCertificateChain(certificateChain, futureDate, Context.TIMESTAMP));
        assertTrue(trustAnchorVerifier.isTrustedAtTime(certificateToken, futureDate, Context.REVOCATION));
        assertTrue(trustAnchorVerifier.isTrustedAtTime(caCertificate, futureDate, Context.REVOCATION));
        assertTrue(trustAnchorVerifier.isTrustedCertificateChain(certificateChain, futureDate, Context.REVOCATION));
        assertFalse(trustAnchorVerifier.isTrustedAtTime(certificateToken, futureDate, Context.SIGNATURE));
        assertFalse(trustAnchorVerifier.isTrustedAtTime(caCertificate, futureDate, Context.SIGNATURE));
        assertFalse(trustAnchorVerifier.isTrustedCertificateChain(certificateChain, futureDate, Context.SIGNATURE));
    }

}
