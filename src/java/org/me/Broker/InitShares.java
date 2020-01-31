/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.Broker;
import Broker.*;
import static java.lang.System.out;
import java.util.List;
/**
 *
 * @author GJ
 */
public class InitShares  {
    //public static void main(String[] args) {
        public static CompanyShares cmpsh = new CompanyShares();
        public static List<SharesType> myShares = cmpsh.getSharesList();
        public static SharesType shareComp = new SharesType();
        //shareComp.setCompanyName("GJ");
        //shareComp.setCompanySymbol("APPL");
        //shareComp.setAvailableShares(20);
        //Myshares.add(shareComp);
        /*try {            
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(cmpsh.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(cmpsh, System.out);
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        System.out.println(Myshares);*/
    //}
    //Broker.SharesType.class.shareComp.setCompanyName("gj");
    //shareComp.setSymbol("APPL");
    //shares.add()
//....
//    ....
    
    //cmpsh.setCompanyName();
}
