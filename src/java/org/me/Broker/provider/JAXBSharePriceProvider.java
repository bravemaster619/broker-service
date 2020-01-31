/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.Broker.provider;

import org.me.Broker.exception.CompanyNotFoundException;
import org.me.Broker.exception.ProviderNotAvailableException;
import Broker.CompanyShares;
import Broker.SharePrice;
import Broker.SharesType;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.Marshaller;



/**
 * JAXBSharePriceProvider handles XML marshalling and unmarshalling of CompanyShares
 * @author brave
 */
public class JAXBSharePriceProvider implements SharePriceProvider {
    
    // base directory of this project
    public static final String baseDir = "E:\\freelancer\\qtr\\ghanim\\BrokerServiceSOAP";
    // xml file that holds CompanyShares object
    public File xmlFile = new File(baseDir + "/src/java/Shares.xml");
    
    public JAXBSharePriceProvider() {
        if (!xmlFile.exists()) {
            try{
                xmlFile.createNewFile();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * Unmarshals Shares.xml and finds SharePrice of given company symbol and convert its currency to given currency
     * @param comSymbol String company symbol
     * @param currency String currency of company symbol
     * @return SharePrice
     * @throws ProviderNotAvailableException
     * @throws CompanyNotFoundException 
     */
    public SharePrice getSharePrice(String comSymbol, String currency) throws ProviderNotAvailableException, CompanyNotFoundException {
        CompanyShares cmpsh = getCompanyShares();
        List<SharesType> myShares = cmpsh.getSharesList();
        SharePrice sharePrice = null;
        // finds SharePrice matching to given company symbol
        for (Iterator<SharesType> it = myShares.iterator(); it.hasNext();) {
            SharesType shareInfo = it.next();
            if (shareInfo.getCompanySymbol().toUpperCase().equals(comSymbol.toUpperCase())) {
                sharePrice = shareInfo.getSharePrice();
            }
        }
        if (sharePrice == null) {
            throw new CompanyNotFoundException("Cannot find company: " + comSymbol);
        }
        // CurrencyExchangeProvider will listen to CurrencyConvertor wsdl
        CurrencyExchangeProvider provider = new CurrencyExchangeProvider();
        provider.exchange(sharePrice, currency);
        return sharePrice;
    }
    
    /**
     * Gets entire CompanyShares object
     * @return CompanyShares
     */
    public CompanyShares getCompanyShares() {
        CompanyShares cmpsh = new CompanyShares();
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(cmpsh.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            cmpsh = (CompanyShares) unmarshaller.unmarshal(xmlFile); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            ex.printStackTrace();
//            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        return cmpsh;
    }
    
    /**
     * Marshal given CompanyShares object and write it to Shares.xml
     * @param cmpsh CompanyShares
     * @return boolean true if saved, false otherwise
     */
    public boolean saveCompanyShares(CompanyShares cmpsh) {
        try {            
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(cmpsh.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(cmpsh, xmlFile);
            return true;
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
//            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
            ex.printStackTrace();
            return false;
        }
    }
    
}
