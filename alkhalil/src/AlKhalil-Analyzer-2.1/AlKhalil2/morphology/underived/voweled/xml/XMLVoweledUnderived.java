package AlKhalil2.morphology.underived.voweled.xml;

import AlKhalil2.morphology.underived.model.Propernoun;
import AlKhalil2.morphology.underived.model.Toolwords;
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

public class XMLVoweledUnderived extends DefaultHandler {
  private List iVoweledUnderived;
  
  public List parserXml(InputStream path) {
    XMLVoweledUnderived parse = new XMLVoweledUnderived();
    parse.iVoweledUnderived = new ArrayList();
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
    return parse.iVoweledUnderived;
  }
  
  public void characters(char[] buffer, int start, int length) {}
  
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if (qName.equalsIgnoreCase("propernoun")) {
      this.iVoweledUnderived.add(new Propernoun(attributes.getValue("id"), attributes.getValue("voweledform"), attributes.getValue("type")));
    } else if (qName.equalsIgnoreCase("toolword")) {
      Toolwords voweledForm = new Toolwords(attributes.getValue("id"), attributes.getValue("voweledform"), attributes.getValue("type"), attributes.getValue("prefclass"), attributes.getValue("suffclass"), attributes.getValue("priority"));
      this.iVoweledUnderived.add(voweledForm);
    } 
  }
  
  public void endElement(String uri, String localName, String qName) {}
}
