package AlKhalil2.morphology.exceptional.xml;

import AlKhalil2.morphology.exceptional.model.Exceptional;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLExceptional extends DefaultHandler {
  private Map iExceptionalXml;
  
  public Map parserXml(InputStream path) {
    XMLExceptional parse = new XMLExceptional();
    parse.iExceptionalXml = new HashMap<Object, Object>();
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
    return parse.iExceptionalXml;
  }
  
  public void characters(char[] buffer, int start, int length) {}
  
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if (qName.equalsIgnoreCase("exceptionalword"))
      this.iExceptionalXml.put(attributes.getValue("unvoweledform"), new Exceptional(attributes.getValue("unvoweledform"), attributes.getValue("voweledform"), attributes.getValue("prefix"), attributes.getValue("stem"), attributes.getValue("type"), attributes.getValue("suffix"))); 
  }
  
  public void endElement(String uri, String localName, String qName) {}
}
