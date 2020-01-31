/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.Broker;
import Broker.CompanyShares;
import Broker.SharePrice;
import Broker.SharesType;
import java.util.List;
import org.junit.Test;
import junit.framework.*;
import org.me.Broker.exception.CompanyNotFoundException;
import org.me.Broker.exception.ProviderNotAvailableException;
import org.me.Broker.provider.JAXBSharePriceProvider;
import org.me.Broker.provider.RestApiSharePriceProvider;
/**
 *
 * @author brave
 */
public class RestApiSharePriceProviderTest extends TestCase {
    
    @Test
    public void testGetSharePrice() throws ProviderNotAvailableException, CompanyNotFoundException {
        RestApiSharePriceProvider provider = new RestApiSharePriceProvider();
        SharePrice sharePrice = provider.getSharePrice("AAPL", "");
        System.out.println(sharePrice.getCurrency() + " " + sharePrice.getValue());
        
    }
    
}
