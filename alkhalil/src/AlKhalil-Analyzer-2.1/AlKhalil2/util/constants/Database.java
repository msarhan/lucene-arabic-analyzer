package AlKhalil2.util.constants;

import AlKhalil2.util.Settings;

public class Database {
  public static String db = "/AlKhalil2/db/";
  
  public static String enclitic = "enclitic";
  
  public static String xmlEnclitic = db + "clitics/Enclitics.xml";
  
  public static String proclitic = "proclitic";
  
  public static String xmlProclitic = db + "clitics/Proclitics.xml";
  
  public static String xmlExceptional = db + "exceptional/ExceptionalWords.xml";
  
  public static String xmlUnderivedTypeToolwords = db + "underived/toolwords/TypeToolwords.xml";
  
  public static String xmlUnderivedUnvoweledToolwords = db + "underived/toolwords/UnvoweledToolwords.xml";
  
  public static String xmlUnderivedVoweledToolwords = db + "underived/toolwords/VoweledToolwords.xml";
  
  public static String xmlUnderivedTypePropernoun = db + "underived/propernouns/TypePropernoun.xml";
  
  public static String xmlUnderivedUnvoweledPropernoun = db + "underived/propernouns/UnvoweledPropernoun.xml";
  
  public static String xmlUnderivedVoweledPropernoun = db + "underived/propernouns/VoweledPropernoun.xml";
  
  public static String xmlDerivedTypeNoun = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/nouns/type/TypeNominal.xml";
  
  public static String xmlDerivedTypeVerb = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/verbs/type/TypeVerbal.xml";
  
  public static String xmlDerivedPOSTagNoun = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/nouns/postag/POSTagNominal.xml";
  
  public static String xmlDerivedPOSTagVerb = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/verbs/postag/POSTagVerbal.xml";
  
  private static int xmlDerivedUnvoweledNL = 0;
  
  public static void setDerivedNUnvoweled(int len) {
    xmlDerivedUnvoweledNL = len;
  }
  
  public static String getDerivedNUnvoweled() {
    String xmlDerivedUnvoweledN = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/nouns/patterns/stems/" + "unvoweled/UnvoweledStemPatternNoun_" + xmlDerivedUnvoweledNL + ".xml";
    return xmlDerivedUnvoweledN;
  }
  
  private static int xmlDerivedUnvoweledVL = 0;
  
  public static void setDerivedVUnvoweled(int len) {
    xmlDerivedUnvoweledVL = len;
  }
  
  public static String getDerivedVUnvoweled() {
    String xmlDerivedUnvoweledV = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/verbs/patterns/stems/" + "unvoweled/UnvoweledStemPatternVerb_" + xmlDerivedUnvoweledVL + ".xml";
    return xmlDerivedUnvoweledV;
  }
  
  public static int xmlDerivedVoweledPSCNL = 0;
  
  public static void setDerivedVoweledPSCN(int len) {
    xmlDerivedVoweledPSCNL = len;
  }
  
  public static String getDerivedVoweledPSCN() {
    String xmlDerivedVoweledPSCN = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/nouns/patterns/stems/" + "voweled/canonic/VoweledStemCanonicPatternNoun_" + xmlDerivedVoweledPSCNL + ".xml";
    return xmlDerivedVoweledPSCN;
  }
  
  public static int xmlDerivedVoweledPSCVL = 0;
  
  public static void setDerivedVoweledPSCV(int len) {
    xmlDerivedVoweledPSCVL = len;
  }
  
  public static String getDerivedVoweledPSCV() {
    String xmlDerivedVoweledPSCV = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/verbs/patterns/stems/" + "voweled/canonic/VoweledStemCanonicPatternVerb_" + xmlDerivedVoweledPSCVL + ".xml";
    return xmlDerivedVoweledPSCV;
  }
  
  public static int xmlDerivedVoweledPSDNL = 0;
  
  public static void setDerivedVoweledPSDN(int len) {
    xmlDerivedVoweledPSDNL = len;
  }
  
  public static String getDerivedVoweledPSDN() {
    String xmlDerivedVoweledPSDN = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/nouns/patterns/stems/" + "voweled/diac/VoweledStemDiacPatternNoun_" + xmlDerivedVoweledPSDNL + ".xml";
    return xmlDerivedVoweledPSDN;
  }
  
  public static int xmlDerivedVoweledPSDVL = 0;
  
  public static void setDerivedVoweledPSDV(int len) {
    xmlDerivedVoweledPSDVL = len;
  }
  
  public static String getDerivedVoweledPSDV() {
    String xmlDerivedVoweledPSDV = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/verbs/patterns/stems/" + "voweled/diac/VoweledStemDiacPatternVerb_" + xmlDerivedVoweledPSDVL + ".xml";
    return xmlDerivedVoweledPSDV;
  }
  
  public static int xmlDerivedVoweledPLCNL = 0;
  
  public static void setDerivedVoweledPLCN(int len) {
    xmlDerivedVoweledPLCNL = len;
  }
  
  public static String getDerivedVoweledPLCN() {
    String xmlDerivedVoweledPLCN = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/nouns/patterns/lemmas/" + "voweled/canonic/VoweledLemmaCanonicPatternNoun_" + xmlDerivedVoweledPLCNL + ".xml";
    return xmlDerivedVoweledPLCN;
  }
  
  public static int xmlDerivedVoweledPLCVL = 0;
  
  public static void setDerivedVoweledPLCV(int len) {
    xmlDerivedVoweledPLCVL = len;
  }
  
  public static String getDerivedVoweledPLCV() {
    String xmlDerivedVoweledPLCV = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/verbs/patterns/lemmas/" + "voweled/canonic/VoweledLemmaCanonicPatternVerb_" + xmlDerivedVoweledPLCVL + ".xml";
    return xmlDerivedVoweledPLCV;
  }
  
  public static int xmlDerivedVoweledPLDNL = 0;
  
  public static void setDerivedVoweledPLDN(int len) {
    xmlDerivedVoweledPLDNL = len;
  }
  
  public static String getDerivedVoweledPLDN() {
    String xmlDerivedVoweledPLDN = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/nouns/patterns/lemmas/" + "voweled/diac/VoweledLemmaDiacPatternNoun_" + xmlDerivedVoweledPLDNL + ".xml";
    return xmlDerivedVoweledPLDN;
  }
  
  public static int xmlDerivedVoweledPLDVL = 0;
  
  public static char xmlDerivedNQRootChar;
  
  public static char xmlDerivedNTRootChar;
  
  public static char xmlDerivedVQRootChar;
  
  public static char xmlDerivedVTRootChar;
  
  public static void setDerivedVoweledPLDV(int len) {
    xmlDerivedVoweledPLDVL = len;
  }
  
  public static String getDerivedVoweledPLDV() {
    String xmlDerivedVoweledPLDV = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/verbs/patterns/lemmas/" + "voweled/diac/VoweledLemmaDiacPatternVerb_" + xmlDerivedVoweledPLDVL + ".xml";
    return xmlDerivedVoweledPLDV;
  }
  
  public static void setDerivedNQRootC(char C) {
    xmlDerivedNQRootChar = C;
  }
  
  public static String getDerivedNQRootC() {
    String xmlDerivedNQRootC = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/nouns/roots/quadriliteral/" + xmlDerivedNQRootChar + ".xml";
    return xmlDerivedNQRootC;
  }
  
  public static void setDerivedNTRootC(char C) {
    xmlDerivedNTRootChar = C;
  }
  
  public static String getDerivedNTRootC() {
    String xmlDerivedNTRootC = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/nouns/roots/trilateral/" + xmlDerivedNTRootChar + ".xml";
    return xmlDerivedNTRootC;
  }
  
  public static void setDerivedVQRootC(char C) {
    xmlDerivedVQRootChar = C;
  }
  
  public static String getDerivedVQRootC() {
    String xmlDerivedVQRootC = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/verbs/roots/quadriliteral/" + xmlDerivedVQRootChar + ".xml";
    return xmlDerivedVQRootC;
  }
  
  public static void setDerivedVTRootC(char C) {
    xmlDerivedVTRootChar = C;
  }
  
  public static String getDerivedVTRootC() {
    String xmlDerivedVTRootC = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/verbs/roots/trilateral/" + xmlDerivedVTRootChar + ".xml";
    return xmlDerivedVTRootC;
  }
  
  public static String xmlDerivedNQRoot = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/nouns/roots/" + "QuadriliteralRoot.xml";
  
  public static String xmlDerivedNTRoot = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/nouns/roots/" + "TrilateralRoot.xml";
  
  public static String xmlDerivedVQRoot = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/verbs/roots/" + "QuadriliteralRoot.xml";
  
  public static String xmlDerivedVTRoot = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/verbs/roots/" + "TrilateralRoot.xml";
  
  public static int xmlDerivedNFormulasN;
  
  public static int xmlDerivedVFormulasN;
  
  public static void setDerivedNFormulas(int len) {
    xmlDerivedNFormulasN = len;
  }
  
  public static String getDerivedNFormulas() {
    String xmlDerivedNFormulas = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/nouns/formulas/" + "NominalFormulas_" + xmlDerivedNFormulasN + ".xml";
    return xmlDerivedNFormulas;
  }
  
  public static void setDerivedVFormulas(int len) {
    xmlDerivedVFormulasN = len;
  }
  
  public static String getDerivedVFormulas() {
    String xmlDerivedVFormulas = db + "derivedwords/" + (Settings.dbchoice ? "short" : "long") + "/verbs/formulas/" + "VerbalFormulas_" + xmlDerivedVFormulasN + ".xml";
    return xmlDerivedVFormulas;
  }
}
