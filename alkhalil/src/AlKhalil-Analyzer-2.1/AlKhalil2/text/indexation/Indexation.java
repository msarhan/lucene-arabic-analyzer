package AlKhalil2.text.indexation;

import AlKhalil2.text.normalization.Normalization;
import AlKhalil2.util.ArabMethod;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Indexation {
  private static Map<String, Integer> iWordsID = new HashMap<String, Integer>();
  
  private static Map<Integer, String> iIDWords = new HashMap<Integer, String>();
  
  private static Map<Integer, String> iWordsPos = new HashMap<Integer, String>();
  
  public Indexation() {
    iWordsID = new HashMap<String, Integer>();
    iIDWords = new HashMap<Integer, String>();
    iWordsPos = new HashMap<Integer, String>();
  }
  
  public static Map<String, Integer> getiWordsID() {
    return iWordsID;
  }
  
  public static Map<Integer, String> getiIDWords() {
    return iIDWords;
  }
  
  public static Map<Integer, String> getiWordsPos() {
    return iWordsPos;
  }
  
  public void setIndexation(String str) {
    Normalization normalized = new Normalization();
    normalized.setNormalizationString(str);
    int icomp = 1;
    List<String> list = new ArrayList<String>();
    list.addAll(Normalization.getNormalizedWord());
    int size = list.size();
    for (int i = 0; i < size; i++) {
      String word = list.get(i);
      String left = (i - 1 >= 0 && i - 1 < list.size()) ? list.get(i - 1) : "";
      String right = (i + 1 >= 0 && i + 1 < list.size()) ? list.get(i + 1) : "";
      if (!left.equals("") && 
        ArabMethod.isSeparator(left))
        left = ""; 
      if (!right.equals("") && 
        ArabMethod.isSeparator(right))
        right = ""; 
      if (!ArabMethod.isSeparator(word) && !iWordsID.containsKey(word)) {
        iIDWords.put(Integer.valueOf(icomp), word);
        iWordsID.put(word, Integer.valueOf(icomp));
        icomp++;
      } 
      if (!right.equals("") && !iWordsID.containsKey(right)) {
        iIDWords.put(Integer.valueOf(icomp), right);
        iWordsID.put(right, Integer.valueOf(icomp));
        icomp++;
      } 
      String stLeft = "";
      int idleft = 0;
      int lgleft = 0;
      if (!left.equals("")) {
        idleft = ((Integer)iWordsID.get(left)).intValue();
        if (iWordsPos.containsKey(Integer.valueOf(idleft))) {
          lgleft = (((String)iWordsPos.get(Integer.valueOf(idleft))).split(" ")).length;
        } else {
          lgleft = 1;
        } 
        stLeft = "" + idleft + "." + lgleft;
      } else {
        stLeft = "0";
      } 
      String stRight = "";
      int idright = 0;
      int lgright = 0;
      if (!right.equals("")) {
        idright = ((Integer)iWordsID.get(right)).intValue();
        if (iWordsPos.containsKey(Integer.valueOf(idright))) {
          lgright = (((String)iWordsPos.get(Integer.valueOf(idright))).split(" ")).length + 1;
        } else {
          lgright = 1;
        } 
        if (idright == idleft && lgright == lgleft)
          lgright++; 
        stRight = "" + idright + "." + lgright;
      } else {
        stRight = "0";
      } 
      String st = "";
      st = stLeft + ";" + stRight;
      if (!ArabMethod.isSeparator(word))
        if (!iWordsPos.containsKey(iWordsID.get(word))) {
          iWordsPos.put(iWordsID.get(word), st);
        } else {
          String stt = iWordsPos.get(iWordsID.get(word));
          stt = stt + " " + st;
          iWordsPos.put(iWordsID.get(word), stt);
        }  
    } 
  }
  
  public static String getLeftContext(int id, int pos, int size) {
    String str = "";
    int i = 0;
    boolean pass = true;
    str = (String)iIDWords.get(Integer.valueOf(id)) + " " + str;
    while (pass && i < size) {
      String posit = ((String)iWordsPos.get(Integer.valueOf(id))).split(" ")[pos - 1].split(";")[0];
      if (!"0".equals(posit)) {
        id = Integer.parseInt(posit.split("\\.")[0]);
        pos = Integer.parseInt(posit.split("\\.")[1]);
        str = (String)iIDWords.get(Integer.valueOf(id)) + " " + str;
      } else {
        pass = false;
      } 
      i++;
    } 
    return str.trim();
  }
  
  public static String getRightContext(int id, int pos, int size) {
    String str = "";
    int i = 0;
    boolean pass = true;
    str = str + " " + (String)iIDWords.get(Integer.valueOf(id));
    while (pass && i < size) {
      String posit = ((String)iWordsPos.get(Integer.valueOf(id))).split(" ")[pos - 1].split(";")[1];
      if (!"0".equals(posit)) {
        id = Integer.parseInt(posit.split("\\.")[0]);
        pos = Integer.parseInt(posit.split("\\.")[1]);
        str = str + " " + (String)iIDWords.get(Integer.valueOf(id));
      } else {
        pass = false;
      } 
      i++;
    } 
    return str.trim();
  }
  
  public static String getHTML() {
    String htmlHead = "";
    String htmlBody = "";
    String htmlFooter = "";
    htmlHead = htmlHead + "<html>\n";
    htmlHead = htmlHead + "<head>\n";
    htmlHead = htmlHead + "<style>\n";
    htmlHead = htmlHead + "* {margin: 0 auto; padding: 0;}\n";
    htmlHead = htmlHead + "html, body { height: 100%; }\n";
    htmlHead = htmlHead + "body{margin: 0 auto;line-height: 100%;}\n";
    htmlHead = htmlHead + ".main {font-size:16px;padding-top:0px;padding-bottom:10px;padding-left:10px;padding-right:10px;}\n";
    htmlHead = htmlHead + ".content{padding-top:10px}\n";
    htmlHead = htmlHead + "table {width:100%;font-size:14px;}\n";
    htmlHead = htmlHead + "td,th{padding:7px;text-align:center;}\n";
    htmlHead = htmlHead + "th{background-color:#C4C4C4;}\n";
    htmlHead = htmlHead + "</style>\n";
    htmlHead = htmlHead + "</head>\n";
    htmlBody = htmlBody + "<body dir=\"ltr\" bgcolor='#F4F4F4'>\n";
    htmlBody = htmlBody + "<div class='main'>\n";
    htmlBody = htmlBody + "<div class='content'>\n";
    htmlBody = htmlBody + "<table class=\"\" align=\"right\"  dir='rtl' border=1 style=\"width:20%\">\n";
    htmlBody = htmlBody + "<tr>\n";
    htmlBody = htmlBody + "<th align=\"right\" style=\"width:50%\">\n";
    htmlBody = htmlBody + "عدد الكلمات :";
    htmlBody = htmlBody + "<font color=red>";
    htmlBody = htmlBody + " " + Normalization.nbTotalWord;
    htmlBody = htmlBody + "</font>";
    htmlBody = htmlBody + "</th>\n";
    htmlBody = htmlBody + "</tr>\n";
    htmlBody = htmlBody + "<tr>\n";
    htmlBody = htmlBody + "<th align=\"right\" style=\"width:50%\">\n";
    htmlBody = htmlBody + "عدد الكلمات بدون تكرار :";
    htmlBody = htmlBody + "<font color=red>";
    htmlBody = htmlBody + " " + iWordsPos.size();
    htmlBody = htmlBody + "</font>";
    htmlBody = htmlBody + "</th>\n";
    htmlBody = htmlBody + "</tr>\n";
    htmlBody = htmlBody + "</table>\n";
    htmlBody = htmlBody + "<br>\n";
    htmlBody = htmlBody + "<center>";
    htmlBody = htmlBody + "<table dir='rtl' BORDER=1 >";
    htmlBody = htmlBody + "<tr>\n";
    htmlBody = htmlBody + "<th style=\"width:50%\">\n";
    htmlBody = htmlBody + "السياقات\n";
    htmlBody = htmlBody + "</th>\n";
    htmlBody = htmlBody + "<th style=\"width:15%\">\n";
    htmlBody = htmlBody + "مواقع الكلمات\n";
    htmlBody = htmlBody + "</th>\n";
    htmlBody = htmlBody + "<th style=\"width:15%\">\n";
    htmlBody = htmlBody + "التكرار\n";
    htmlBody = htmlBody + "</th>\n";
    htmlBody = htmlBody + "<th style=\"width:20%\">\n";
    htmlBody = htmlBody + "الكلمة\n";
    htmlBody = htmlBody + "</th>\n";
    htmlBody = htmlBody + "</tr>\n";
    int ic = 0;
    Iterator<String> it = iWordsID.keySet().iterator();
    while (it.hasNext()) {
      String word = it.next();
      int id = ((Integer)getiWordsID().get(word)).intValue();
      htmlBody = htmlBody + "<tr>\n";
      String[] strs = ((String)getiWordsPos().get(Integer.valueOf(id))).split(" ");
      int p = 1;
      if (strs.length > 0) {
        String[] stl = strs[0].split(";");
        if (stl.length == 2) {
          String srt = "";
          if (!"0".equals(stl[0]))
            srt = srt + getLeftContext(Integer.parseInt(stl[0].split("\\.")[0]), Integer.parseInt(stl[0].split("\\.")[1]), 5); 
          srt = srt + "<font color=red> " + (String)getiIDWords().get(Integer.valueOf(id)) + "</font> ";
          if (!"0".equals(stl[1]))
            srt = srt + getRightContext(Integer.parseInt(stl[1].split("\\.")[0]), Integer.parseInt(stl[1].split("\\.")[1]), 5); 
          htmlBody = htmlBody + "<td align=\"right\" >\n";
          htmlBody = htmlBody + srt + "\n";
          htmlBody = htmlBody + "</td>\n";
        } 
        htmlBody = htmlBody + "<td align=\"right\" >\n";
        htmlBody = htmlBody + p + "\n";
        htmlBody = htmlBody + "</td>\n";
        p++;
      } 
      htmlBody = htmlBody + "<td  align=\"center\" rowspan=\"" + strs.length + "\" >\n";
      htmlBody = htmlBody + strs.length + "\n";
      htmlBody = htmlBody + "</td>\n";
      htmlBody = htmlBody + "<td  align=\"center\" rowspan=\"" + strs.length + "\" >\n";
      htmlBody = htmlBody + (String)getiIDWords().get(Integer.valueOf(id)) + "\n";
      htmlBody = htmlBody + "</td>\n";
      htmlBody = htmlBody + "</tr>\n";
      ic += strs.length;
      for (int i = 1; i < strs.length; i++) {
        htmlBody = htmlBody + "<tr>\n";
        String[] stl = strs[i].split(";");
        if (stl.length == 2) {
          String srt = "";
          if (!"0".equals(stl[0]))
            srt = srt + getLeftContext(Integer.parseInt(stl[0].split("\\.")[0]), Integer.parseInt(stl[0].split("\\.")[1]), 5); 
          srt = srt + "<font color=red> " + (String)getiIDWords().get(Integer.valueOf(id)) + "</font> ";
          if (!"0".equals(stl[1]))
            srt = srt + getRightContext(Integer.parseInt(stl[1].split("\\.")[0]), Integer.parseInt(stl[1].split("\\.")[1]), 5); 
          htmlBody = htmlBody + "<td align=\"right\" >\n";
          htmlBody = htmlBody + srt + "\n";
          htmlBody = htmlBody + "</td>\n";
        } 
        htmlBody = htmlBody + "<td align=\"right\" >\n";
        htmlBody = htmlBody + p + "\n";
        htmlBody = htmlBody + "</td>\n";
        htmlBody = htmlBody + "</tr>\n";
        p++;
      } 
    } 
    htmlBody = htmlBody + "</table>\n";
    htmlBody = htmlBody + "</center>\n";
    htmlBody = htmlBody + "</div>\n";
    htmlBody = htmlBody + "</div>\n";
    htmlBody = htmlBody + "</body>\n";
    htmlBody = htmlBody + "</html>\n";
    return htmlHead + htmlBody;
  }
  
  public void setIndexationFile(File nameFile, String nameCharset, boolean unvowled) {
    Normalization normalized = new Normalization();
    normalized.setNormalizationFile(nameFile, nameCharset, unvowled);
    int icomp = 1;
    List<String> list = new ArrayList<String>();
    list.addAll(Normalization.getNormalizedWord());
    int size = list.size();
    for (int i = 0; i < size; i++) {
      String word = list.get(i);
      String left = (i - 1 >= 0 && i - 1 < list.size()) ? list.get(i - 1) : "";
      String right = (i + 1 >= 0 && i + 1 < list.size()) ? list.get(i + 1) : "";
      if (!left.equals("") && 
        ArabMethod.isSeparator(left))
        left = ""; 
      if (!right.equals("") && 
        ArabMethod.isSeparator(right))
        right = ""; 
      if (!ArabMethod.isSeparator(word) && !iWordsID.containsKey(word)) {
        iIDWords.put(Integer.valueOf(icomp), word);
        iWordsID.put(word, Integer.valueOf(icomp));
        icomp++;
      } 
      if (!right.equals("") && !iWordsID.containsKey(right)) {
        iIDWords.put(Integer.valueOf(icomp), right);
        iWordsID.put(right, Integer.valueOf(icomp));
        icomp++;
      } 
      String stLeft = "";
      int idleft = 0;
      int lgleft = 0;
      if (!left.equals("")) {
        idleft = ((Integer)iWordsID.get(left)).intValue();
        if (iWordsPos.containsKey(Integer.valueOf(idleft))) {
          lgleft = (((String)iWordsPos.get(Integer.valueOf(idleft))).split(" ")).length;
        } else {
          lgleft = 1;
        } 
        stLeft = "" + idleft + "." + lgleft;
      } else {
        stLeft = "0";
      } 
      String stRight = "";
      int idright = 0;
      int lgright = 0;
      if (!right.equals("")) {
        idright = ((Integer)iWordsID.get(right)).intValue();
        if (iWordsPos.containsKey(Integer.valueOf(idright))) {
          lgright = (((String)iWordsPos.get(Integer.valueOf(idright))).split(" ")).length + 1;
        } else {
          lgright = 1;
        } 
        if (idright == idleft && lgright == lgleft)
          lgright++; 
        stRight = "" + idright + "." + lgright;
      } else {
        stRight = "0";
      } 
      String st = "";
      st = stLeft + ";" + stRight;
      if (!ArabMethod.isSeparator(word))
        if (!iWordsPos.containsKey(iWordsID.get(word))) {
          iWordsPos.put(iWordsID.get(word), st);
        } else {
          String stt = iWordsPos.get(iWordsID.get(word));
          stt = stt + " " + st;
          iWordsPos.put(iWordsID.get(word), stt);
        }  
    } 
  }
}
