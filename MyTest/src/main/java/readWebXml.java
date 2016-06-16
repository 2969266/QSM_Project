import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by qsm on 2016/June/8.
 */
public class readWebXml extends DefaultHandler {

    private String keyStore;
    private String keyPass;
    private String trustStore;
    private String trustPass;

    private String keyStoreName = "javax.net.ssl.keyStore";
    private String trustStoreName = "javax.net.ssl.trustStore";
    private String keyStorePass = "javax.net.ssl.keyStorePassword";
    private String trustStorePass = "javax.net.ssl.trustStorePassword";
    private String tmpParamName, tmpParamValue;

    private boolean initParam = false;
    private boolean paraValue = false;
    private boolean paraName = false;

    public String getKeyStore(){
        return keyStore;
    }
    public String getKeyPass(){
        return keyPass;
    }
    public String getTrustStore(){
        return trustStore;
    }

    public String getTrustPass(){
        return trustPass;
    }

    @Override
    public void startDocument() {
        System.out.println("Start document.");
    }

    @Override
    public void endDocument()  {
        System.out.println("End document.");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) {
        if (qName.equals("init-param")) {
            initParam = true;
//            System.out.println("!!!!!!!!!!!!!!!!!!!!! in "+qName);
        }
        if(qName.equals("param-name")){
            paraName = true;
//            System.out.println("!!!!!!!!!!!!!!!!!!!!! in "+qName);
        }
        //            System.out.println("!!!!!!!!!!!!!!!!!!!!! in "+qName);
        if(qName.equals("param-value")) paraValue = true;
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) {
        if (qName.equals("param-name")) {
            paraName = false;
//            System.out.println("!!!!!!!!!!!!!!!!!!!!! out "+qName);
        }
        if (qName.equals("param-value")) {
            paraValue = false;
//            System.out.println("!!!!!!!!!!!!!!!!!!!!! out "+qName);
        }
        if (qName.equals("init-param")) {
            initParam = false;
            tmpParamValue = "";
            tmpParamName = "";
//            System.out.println("!!!!!!!!!!!!!!!!!!!!! out "+qName);
        }
    }


    @Override
    public void characters(char[] ch, int start, int length) {
        // Processing character data inside an element
        if (paraName) {
            tmpParamName = new String(ch, start, length).toString();
        }
        if(paraValue) {
            tmpParamValue = new String(ch, start, length).toString();

            if (tmpParamName.equals(keyStoreName)) {
                keyStore = tmpParamValue;
            }
            if (tmpParamName.equals(trustStoreName)) {
                trustStore = tmpParamValue;
            }
            if (tmpParamName.equals(trustStorePass)) {
                keyPass = tmpParamValue;
            }
            if (tmpParamName.equals(keyStorePass)) {
                trustPass = tmpParamValue;
            }
        }
    }
}
