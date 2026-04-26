package AlKhalil2.util;

import AlKhalil2.io.File;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public final class Settings {
  public static boolean dbchoice = false;
  
  public static boolean vowchoice = true;
  
  public static boolean prefchoice = true;
  
  public static boolean stemchoice = true;
  
  public static boolean typechoice = true;
  
  public static boolean patternchoice = true;
  
  public static boolean diacpatternchoice = true;
  
  public static boolean lemmechoice = true;
  
  public static boolean patternlemmechoice = true;
  
  public static boolean rootchoice = true;
  
  public static boolean poschoice = true;
  
  public static boolean suffixchoice = true;
  
  public static boolean Tchoice = true;
  
  public static boolean isText = true;
  
  private static int ncoloumns;
  
  public void upDateSettings() {
    String fileIn = "/AlKhalil2/db/settings";
    File settings = new File(fileIn);
    OutputStreamWriter out = null;
    try {
      out = new OutputStreamWriter(new FileOutputStream(settings));
      out.write("dbchoice=" + (dbchoice ? "true" : "false") + "\n");
      out.write("vowchoice=" + (vowchoice ? "true" : "false") + "\n");
      out.write("prefchoice=" + (prefchoice ? "true" : "false") + "\n");
      out.write("stemchoice=" + (stemchoice ? "true" : "false") + "\n");
      out.write("typechoice=" + (typechoice ? "true" : "false") + "\n");
      out.write("diacpatternchoice=" + (diacpatternchoice ? "true" : "false") + "\n");
      out.write("canpatternchoice=" + (patternchoice ? "true" : "false") + "\n");
      out.write("lemmechoice=" + (lemmechoice ? "true" : "false") + "\n");
      out.write("lemmepatternchoice=" + (patternlemmechoice ? "true" : "false") + "\n");
      out.write("rootchoice=" + (rootchoice ? "true" : "false") + "\n");
      out.write("poschoice=" + (poschoice ? "true" : "false") + "\n");
      out.write("suffixchoice=" + (suffixchoice ? "true" : "false") + "\n");
      out.close();
    } catch (IOException ex) {
      System.out.println("Erreur : " + ex);
    } 
  }
  
  public void readSettings() {
    String fileIn = "/AlKhalil2/db/settings";
    InputStream settings = (new File()).openFileXml(fileIn);
    BufferedReader in = null;
    try {
      in = new BufferedReader(new InputStreamReader(settings));
      String line = in.readLine();
      dbchoice = line.endsWith("true");
      line = in.readLine();
      vowchoice = line.endsWith("true");
      line = in.readLine();
      prefchoice = line.endsWith("true");
      line = in.readLine();
      stemchoice = line.endsWith("true");
      line = in.readLine();
      typechoice = line.endsWith("true");
      line = in.readLine();
      diacpatternchoice = line.endsWith("true");
      line = in.readLine();
      patternchoice = line.endsWith("true");
      line = in.readLine();
      lemmechoice = line.endsWith("true");
      line = in.readLine();
      patternlemmechoice = line.endsWith("true");
      line = in.readLine();
      rootchoice = line.endsWith("true");
      line = in.readLine();
      poschoice = line.endsWith("true");
      line = in.readLine();
      suffixchoice = line.endsWith("true");
      in.close();
    } catch (IOException ex) {
      System.out.println("Erreur : " + ex);
    } 
  }
  
  public static void changeSettings(boolean _vowchoice, boolean _prefchoice, boolean _stemchoice, boolean _typechoice, boolean _canpatternchoice, boolean _diacpatternchoice, boolean _lemmechoice, boolean _lemmepatternchoice, boolean _rootchoice, boolean _poschoice, boolean _suffixchoice) {
    vowchoice = _vowchoice;
    prefchoice = _prefchoice;
    stemchoice = _stemchoice;
    typechoice = _typechoice;
    patternchoice = _canpatternchoice;
    diacpatternchoice = _diacpatternchoice;
    lemmechoice = _lemmechoice;
    patternlemmechoice = _lemmepatternchoice;
    rootchoice = _rootchoice;
    poschoice = _poschoice;
    suffixchoice = _suffixchoice;
  }
  
  public static void changeSettings(boolean _dbchoice, boolean _vowchoice, boolean _prefchoice, boolean _stemchoice, boolean _typechoice, boolean _canpatternchoice, boolean _diacpatternchoice, boolean _lemmechoice, boolean _lemmepatternchoice, boolean _rootchoice, boolean _poschoice, boolean _suffixchoice) {
    dbchoice = _dbchoice;
    vowchoice = _vowchoice;
    prefchoice = _prefchoice;
    stemchoice = _stemchoice;
    typechoice = _typechoice;
    patternchoice = _canpatternchoice;
    diacpatternchoice = _diacpatternchoice;
    lemmechoice = _lemmechoice;
    patternlemmechoice = _lemmepatternchoice;
    rootchoice = _rootchoice;
    poschoice = _poschoice;
    suffixchoice = _suffixchoice;
  }
  
  public static int getNumberOfColumn() {
    ncoloumns = 0;
    ncoloumns += vowchoice ? 1 : 0;
    ncoloumns += prefchoice ? 1 : 0;
    ncoloumns += stemchoice ? 1 : 0;
    ncoloumns += typechoice ? 1 : 0;
    ncoloumns += patternchoice ? 1 : 0;
    ncoloumns += lemmechoice ? 1 : 0;
    ncoloumns += patternlemmechoice ? 1 : 0;
    ncoloumns += rootchoice ? 1 : 0;
    ncoloumns += poschoice ? 1 : 0;
    ncoloumns += suffixchoice ? 1 : 0;
    return ncoloumns;
  }
}
