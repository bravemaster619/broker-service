/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.Broker.provider;

import Broker.SharePrice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.me.Broker.exception.CompanyNotFoundException;
import org.me.Broker.exception.ProviderNotAvailableException;

/**
 * RestApiSharePriceProvider handles up-to-date company share price info operation
 * @author brave
 */
public class RestApiSharePriceProvider implements SharePriceProvider {
    
    // localy hosted service that imitates real-time company stock price RESTful API
    public static final String apiUrl = "http://localhost/restapi/finnhub.php";
    // this api key is from finnhub.io
    public static final String apiKey = "bnvqat7rh5rbvm1rdu40";
    
    /**
     * Gets SharePrice info from a REST api
     * @param comSymbol String
     * @param currency String
     * @return SharePrice
     * @throws ProviderNotAvailableException
     * @throws CompanyNotFoundException 
     */
    public SharePrice getSharePrice(String comSymbol, String currency) throws ProviderNotAvailableException, CompanyNotFoundException {
        String address = apiUrl + "?symbol=" + comSymbol + "&token=" + apiKey;
        SharePrice sharePrice = null;
        XMLGregorianCalendar xmlGrgCalendar = null;
        CurrencyExchangeProvider provider = new CurrencyExchangeProvider();
        try {
            String json = getResponse(address);
            JSONObject obj = (JSONObject) new JSONParser().parse(json);
            if (obj.get("c") != null) {
                sharePrice = new SharePrice();
                sharePrice.setCurrency("USD");
                sharePrice.setValue((double) obj.get("c"));
                xmlGrgCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar();
                sharePrice.setLastUpdate(xmlGrgCalendar);
                provider.exchange(sharePrice, currency);
            }
        } catch (Exception ex) {
            //Logger.getLogger(RestApiSharePriceProvider.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return sharePrice;
    }
    
    /**
     * Utility function that retrieves response of a GET request.
     * @param address String
     * @return String
     * @throws IOException
     * @throws ProviderNotAvailableException 
     */
    protected String getResponse(String address) throws IOException, ProviderNotAvailableException{
        
         try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if (conn != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String input;
                while((input = br.readLine())!= null) {
                    sb.append(input);
                }
                br.close();
                return sb.toString();
            } else {
                throw new ProviderNotAvailableException("Connection is null");
            }
         } catch(Exception e) {
             throw new ProviderNotAvailableException(e.getMessage());
         }
         
//        HttpGet request = new HttpGet(address);
//        
//        System.out.println("getting response");
//        
//        CloseableHttpResponse response = null;
//        
//        try {
//            response = httpClient.execute(request);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println("entity got");
//        
//        HttpEntity entity = response.getEntity();
//        
//        if (entity != null) {
//            String result = EntityUtils.toString(entity);
//            return result;
//        } else {
//            throw new ProviderNotAvailableException("Http entity is null.");
//        }

    }
    
}
