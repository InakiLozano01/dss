package eu.europa.esig.dss.validation.process.bbb;

import eu.europa.esig.dss.detailedreport.jaxb.XmlConstraintsConclusion;
import eu.europa.esig.dss.diagnostic.CertificateWrapper;
import eu.europa.esig.dss.i18n.I18nProvider;
import eu.europa.esig.dss.policy.jaxb.CertificateValuesConstraint;
import eu.europa.esig.dss.policy.jaxb.MultiValuesConstraint;
import eu.europa.esig.dss.utils.Utils;
import eu.europa.esig.dss.validation.process.ChainItem;
import eu.europa.esig.dss.validation.process.ValidationProcessUtils;

import java.util.Collections;
import java.util.List;

/**
 * Abstract class to check if the given certificate matches one of the defined conditions
 * @param <T> {@code XmlConstraintsConclusion}
 *
 */
public abstract class AbstractCertificateCheckItem<T extends XmlConstraintsConclusion> extends ChainItem<T> {

    /** The constraint value */
    private final CertificateValuesConstraint constraint;

    /**
     * Default constructor
     *
     * @param i18nProvider {@link I18nProvider}
     * @param result the result
     * @param constraint {@link MultiValuesConstraint}
     */
    protected AbstractCertificateCheckItem(I18nProvider i18nProvider, T result, CertificateValuesConstraint constraint) {
        super(i18nProvider, result, constraint);
        this.constraint = constraint;
    }

    /**
     * Default constructor with Id
     *
     * @param i18nProvider {@link I18nProvider}
     * @param result the result
     * @param certificate {@link CertificateWrapper}
     * @param constraint {@link MultiValuesConstraint}
     */
    protected AbstractCertificateCheckItem(I18nProvider i18nProvider, T result, CertificateWrapper certificate,
                                           CertificateValuesConstraint constraint) {
        super(i18nProvider, result, constraint, certificate.getId());
        this.constraint = constraint;
    }

    /**
     * Checks the certificate
     *
     * @param certificate {@link CertificateWrapper} to check
     * @return TRUE if the certificate matches the constraint, FALSE otherwise
     */
    protected boolean processCertificateCheck(CertificateWrapper certificate) {
        if (constraint == null) {
            return false;
        }

        MultiValuesConstraint certificateExtensionsConstraint = constraint.getCertificateExtensions();
        List<String> expectedCertificateExtensions = certificateExtensionsConstraint != null ?
                certificateExtensionsConstraint.getId() : Collections.emptyList();
        MultiValuesConstraint certificatePoliciesConstraint = constraint.getCertificatePolicies();
        List<String> expectedCertificatePolicies = certificatePoliciesConstraint != null ?
                certificatePoliciesConstraint.getId() : Collections.emptyList();

        return (Utils.isCollectionNotEmpty(expectedCertificateExtensions) && Utils.isCollectionNotEmpty(certificate.getCertificateExtensionsOids()) &&
                        ValidationProcessUtils.processValuesCheck(certificate.getCertificateExtensionsOids(), expectedCertificateExtensions)) ||
                (Utils.isCollectionNotEmpty(expectedCertificatePolicies) && Utils.isCollectionNotEmpty(certificate.getCertificatePoliciesOids()) &&
                        ValidationProcessUtils.processValuesCheck(certificate.getCertificatePoliciesOids(), expectedCertificatePolicies));
    }

}
