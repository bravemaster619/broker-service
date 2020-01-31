/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.Broker.provider;

import Broker.SharePrice;
import org.me.Broker.currency.CurrencyConversionWS;
import org.me.Broker.exception.CurrencyNotFoundException;
import org.me.Broker.currency.CurrencyConversionWS_Service;
/**
 *
 * @author brave
 */
public class CurrencyExchangeProvider {
    
    /**
     * Converts currency of given SharePrice to target currency
     * @param sharePrice SharePrice to be exchanged
     * @param targetCurrency String 
     * @return SharePrice
     */
    public SharePrice exchange(SharePrice sharePrice, String targetCurrency) {
        // open port for currency service
        CurrencyConversionWS_Service service = new CurrencyConversionWS_Service();
        // port listens for "http://localhost:8080/CurrencyConvertor/CurrencyConversionWS?wsdl"
        CurrencyConversionWS port = service.getCurrencyConversionWSPort();
        
        String curCurrency = sharePrice.getCurrency();
        try {
            // Using currency service method
            double rate = port.getConversionRate(curCurrency, targetCurrency);
            sharePrice.setCurrency(targetCurrency);
            sharePrice.setValue(sharePrice.getValue() * rate);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return sharePrice;
    }
    
}
