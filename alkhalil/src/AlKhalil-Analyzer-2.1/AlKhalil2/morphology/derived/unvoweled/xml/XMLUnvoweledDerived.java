package AlKhalil2.morphology.derived.unvoweled.xml;

import AlKhalil2.morphology.derived.unvoweled.model.Unvoweled;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLUnvoweledDerived extends DefaultHandler {
  private List iUnvoweledXml;
  
  public List parserXml(InputStream path) {
    XMLUnvoweledDerived parse = new XMLUnvoweledDerived();
    parse.iUnvoweledXml = new ArrayList();
    try {
      SAXParserFactory spfac = SAXParserFactory.newInstance();
      SAXParser sp = spfac.newSAXParser();
      sp.parse(path, parse);
    } catch (ParserConfigurationException ex) {
      System.out.println(" : " + ex);
    } catch (SAXException ex) {
      System.out.println(" : " + ex);
    } catch (IOException ex) {
      System.out.println(" : " + ex);
    } 
    return parse.iUnvoweledXml;
  }
  
  public void characters(char[] buffer, int start, int length) {}
  
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if (qName.equalsIgnoreCase("pattern")) {
      Unvoweled unvoweledForm = new Unvoweled();
      unvoweledForm.setVal(attributes.getValue("val"));
      unvoweledForm.setRules(attributes.getValue("rules"));
      unvoweledForm.setIds(attributes.getValue("ids"));
      this.iUnvoweledXml.add(unvoweledForm);
    } 
  }
  
  public void endElement(String uri, String localName, String qName) {}
}
