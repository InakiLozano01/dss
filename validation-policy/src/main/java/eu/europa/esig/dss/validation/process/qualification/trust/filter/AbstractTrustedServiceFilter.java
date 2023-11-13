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
package eu.europa.esig.dss.validation.process.qualification.trust.filter;

import eu.europa.esig.dss.diagnostic.TrustServiceWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract filter defining the main logic of filters
 *
 * @deprecated since DSS 5.13.
 *      Use {@code eu.europa.esig.dss.validation.process.qualification.trust.filter.AbstractTrustServiceFilter} instead
 */
@Deprecated
public abstract class AbstractTrustedServiceFilter implements TrustServiceFilter {

    /**
     * Default constructor
     */
    @Deprecated
    protected AbstractTrustedServiceFilter() {
        // empty
    }

    @Override
    @Deprecated
    public List<TrustServiceWrapper> filter(List<TrustServiceWrapper> originServices) {
        List<TrustServiceWrapper> result = new ArrayList<>();
        for (TrustServiceWrapper service : originServices) {
            if (isAcceptable(service)) {
                result.add(service);
            }
        }
        return result;
    }

    /**
     * Checks whether the {@code service} is acceptable
     *
     * @param service {@link TrustServiceWrapper} to check
     * @return TRUE if the {@code service} is acceptable, FALSE otherwise
     */
    @Deprecated
    protected abstract boolean isAcceptable(TrustServiceWrapper service);

}
