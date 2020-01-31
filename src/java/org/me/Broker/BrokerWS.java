/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.Broker;

import Broker.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.me.Broker.InitShares;
import org.me.Broker.provider.JAXBSharePriceProvider;
import org.me.Broker.provider.RestApiSharePriceProvider;

/**
 *
 * @author GJ
 */
@WebService(serviceName = "BrokerWS")
public class BrokerWS {

//    public static final String baseDir = "/Users/GJ/Desktop/TESTING/BrokerServiceSOAP";
    
    /**
     * This is a sample web service operation
     */
    
    
    /*@WebMethod(operationName = "addShares")
    public void addShares() {
        InitShares.shareComp = new SharesType();
        InitShares.shareComp.setCompanyName("GJ");
        InitShares.shareComp.setCompanySymbol("APPL");
        InitShares.shareComp.setAvailableShares(20);
        InitShares.myShares.add(InitShares.shareComp);
        //SharesType shareComp = new SharesType();
        InitShares.shareComp = new SharesType();
        InitShares.shareComp.setCompanyName("GJ");
        InitShares.shareComp.setCompanySymbol("APPL2");
        InitShares.shareComp.setAvailableShares(20);
        InitShares.myShares.add(InitShares.shareComp);
    }*/
    
    /**
     *
     * @return
     */
    @WebMethod(operationName = "listShares")
    public List<SharesType> listShares(/*@WebParam(name = "name"*/) {
        JAXBSharePriceProvider provider = new JAXBSharePriceProvider();
        CompanyShares cmpsh = provider.getCompanyShares();
        List<SharesType> myShares = cmpsh.getSharesList();
        return myShares;
    }
    
    @WebMethod(operationName = "buyShares")
    public Broker.SharesType buyShares(@WebParam() String sym,@WebParam(name = "name") int noShare) throws FileNotFoundException {
        JAXBSharePriceProvider provider = new JAXBSharePriceProvider();
        CompanyShares cmpsh = provider.getCompanyShares();
        List<SharesType> myShares = cmpsh.getSharesList();
        int i ;
        int index = 0;
        for (i = 0; i < myShares.size(); i++) { 
  
            // accessing each element of array
           SharesType element;
           element = myShares.get(i);
           if (element.getCompanySymbol().equals(sym)){
               index = i;// saving the index of the wanted company from teh list
           }
        }
        SharesType currentCompany;
        currentCompany = myShares.get(index);// gfetting the shetype of the specific company
        int numberOfShares = currentCompany.getAvailableShares();
        if (numberOfShares>=noShare){// subtracting the shares 
            currentCompany.setAvailableShares(numberOfShares-noShare);
        }
        provider.saveCompanyShares(cmpsh);
        return currentCompany ;
    }

    
    @WebMethod(operationName = "SearchForShares")
    public SharesType SearchForShares(@WebParam() String sym) throws FileNotFoundException {
        JAXBSharePriceProvider provider = new JAXBSharePriceProvider();
        CompanyShares cmpsh = provider.getCompanyShares();
        List<SharesType> myShares = cmpsh.getSharesList();
        int i ;
        int index = 0;
        for (i = 0; i < myShares.size(); i++) { 
  
            // accessing each element of array
           SharesType element;
           element = myShares.get(i);
           if (element.getCompanySymbol().equals(sym)){
               index = i;// saving the index of the wanted company from teh list
           }
        }
        SharesType currentCompany;
        currentCompany = myShares.get(index);// getting the sharetype of the specific company
        
        return currentCompany ;
    }
    
    @WebMethod(operationName = "Greater_Shares_Than")
    public List<SharesType> Greater_Shares_Than(@WebParam() int shr) throws FileNotFoundException {
        JAXBSharePriceProvider provider = new JAXBSharePriceProvider();
        CompanyShares cmpsh = provider.getCompanyShares();
        List<SharesType> myShares = cmpsh.getSharesList();
        //List<SharesType> mySharesv1 = myShares;
        //int SizeOfList = mySharesv1.size();
        int i ;
        List<Integer> indexs = new ArrayList<>();
        for (i = 0; i < myShares.size(); i++) { // getting only the index that satisfies the condition
  
            // accessing each element of array
           SharesType element;
           element = myShares.get(i);
           if (element.getAvailableShares()>=shr){
               indexs.add(i);// saving the index of the wanted company from the list
              
           }
        }
        
        for (i = 0; i < indexs.size(); i++) { 
            SharesType ele = new SharesType();
            int ind = indexs.get(i);// value of the index list at index i
            ele = myShares.get(ind);
            myShares.add(ele);
        }
              
        return myShares;
    }
//    /**
//     * Web service operation
//     */
//    @WebMethod(operationName = "Get-Currency")
//    public SharePrice getCurrency(/*@WebParam(name = "name"*/) {
//        SharePrice Curr = new SharePrice();
//        try {
//            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(Curr.getClass().getPackage().getName());
//            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
//            Curr = (SharePrice) unmarshaller.unmarshal(new java.io.File(baseDir + "/src/java/SharesP.xml")); //NOI18N
//        } catch (javax.xml.bind.JAXBException ex) {
//            // XXXTODO Handle exception
//            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
//        }
//        SharePrice va = new SharePrice();
//        
//        va.setCurrency(Curr.getCurrency());
//        va.setLastUpdate(Curr.getLastUpdate());
//        va.setValue(Curr.getValue());
//        return va;
//    }

    /**
     * Save given share price to Shares.xml. Since it's XML-based, JAXBSharePriceProvider is called
     * @param comSymbol String company symbol
     * @param value double company share price
     * @return SharePrice saved SharePrice object
     */
    @WebMethod(operationName="saveSharePrice")
    public SharePrice saveSharePrice(@WebParam(name="comSymbol") String comSymbol, @WebParam(name="value") double value) {
        JAXBSharePriceProvider provider = new JAXBSharePriceProvider();
        CompanyShares cmpsh = provider.getCompanyShares();
        List<SharesType> myShares = cmpsh.getSharesList();
        SharesType shareList = new SharesType();
        XMLGregorianCalendar xmlGrgCalendar = null;
        // search matching share type
        // isNew is a flag that indicates if given company symbol is new
        boolean isNew = true;
        for (Iterator<SharesType> it = myShares.iterator(); it.hasNext();) {
            SharesType tmpShare = it.next();
            if(comSymbol.equals(tmpShare.getCompanySymbol())) {
                shareList = tmpShare;
                isNew = false;
            }
        }
        SharePrice sharePrice = shareList.getSharePrice();
        if (sharePrice == null) {
            sharePrice = new SharePrice();
            shareList.setSharePrice(sharePrice);
        }
        sharePrice.setValue(value);
        sharePrice.setCurrency("GBP");
        // if given company symbol is new, register a new company share information
        if(isNew) {
            shareList.setCompanyName(comSymbol);
            // default share amount is 100
            shareList.setAvailableShares(100);
            shareList.setCompanySymbol(comSymbol);
            myShares.add(shareList);
        }
        try{
            xmlGrgCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar();
            // set last update date now
            sharePrice.setLastUpdate(xmlGrgCalendar);
        } catch(Exception e) {
            e.printStackTrace();
        }
        provider.saveCompanyShares(cmpsh);
        return sharePrice;
    }
    
    /**
     * get share price of a persisted company. Since it's XML-based, JAXBSharePriceProvider is used.
     * @param comSymbol String  company symbol
     * @param currency String currency of share price
     * @return SharePrice
     */
    @WebMethod(operationName="getSharePrice")
    public SharePrice getSharePrice(@WebParam(name="comSymbol") String comSymbol, @WebParam(name="currency") String currency) throws Exception {
        JAXBSharePriceProvider provider = new JAXBSharePriceProvider();
        return provider.getSharePrice(comSymbol, currency);
    }
    
    
    /**
     * get live up-to-date share price from a REST api. Since it's RESTful API-based, RestApiSharePriceProvider is used
     * @param comSymbol String company symbol
     * @param currency String currency of share price
     * @return SharePrice
     */
    @WebMethod(operationName="getLiveSharePrice")
    public SharePrice getLiveSharePrice(@WebParam(name="comSymbol") String comSymbol, @WebParam(name="currency") String currency) throws Exception {
        RestApiSharePriceProvider provider = new RestApiSharePriceProvider();
        return provider.getSharePrice(comSymbol, currency);
    }

}
