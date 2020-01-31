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
import org.me.Broker.provider.JAXBSharePriceProvider;
/**
 *
 * @author brave
 */
public class DefaultSharePriceProviderTest extends TestCase{
    
    @Test
    public void testDefaultProvider() {
        JAXBSharePriceProvider testProvider = new JAXBSharePriceProvider();
        try  {
            SharePrice sharePrice = testProvider.getSharePrice("IBM", "USD");
            System.out.println("Currency: " + sharePrice.getCurrency());
            System.out.println("Value: " + sharePrice.getValue());
            System.out.println("Last Updated: " + sharePrice.getLastUpdate());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
