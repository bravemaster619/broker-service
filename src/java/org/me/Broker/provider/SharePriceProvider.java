/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.Broker.provider;

import org.me.Broker.exception.CompanyNotFoundException;
import org.me.Broker.exception.ProviderNotAvailableException;
import Broker.SharePrice;

/**
 * BrokerWebService will make good use of this interface.
 * For XML based operation(offline mode), it will use JAXBSharePriceProvider which implements this.
 * For REST API operation(up-to-date info), it will use RestApiSharePriceProvider
 * @author brave
 */
public interface SharePriceProvider {
    
    public SharePrice getSharePrice(String comSymbol, String currency) throws ProviderNotAvailableException, CompanyNotFoundException;
    
}