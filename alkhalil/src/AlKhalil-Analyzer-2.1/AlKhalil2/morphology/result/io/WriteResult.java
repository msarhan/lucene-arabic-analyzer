package AlKhalil2.morphology.result.io;

import AlKhalil2.morphology.result.model.Result;
import AlKhalil2.text.indexation.Indexation;
import AlKhalil2.text.normalization.Normalization;
import AlKhalil2.util.Settings;
import AlKhalil2.util.constants.Static;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WriteResult {
  public void writeResultsXML(String pathOutFile, String NameCharset, HashMap<String, List<Result>> result, List<String> statistics) {
    try {
      OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(pathOutFile), NameCharset);
      out.write("<?xml version=\"1.0\" encoding=\"windows-1256\" ?>\n");
      out.write("<results>\n");
      out.write("\t<statistics>\n");
      out.write("\t\t<nbwords>" + (String)statistics.get(0) + "</nbwords>\n");
      out.write("\t\t<nbwordsT>" + (String)statistics.get(1) + "</nbwordsT>\n");
      out.write("\t\t<pnbwordsT>" + (String)statistics.get(2) + "</pnbwordsT>\n");
      out.write("\t\t<nbwordsNT>" + (String)statistics.get(3) + "</nbwordsNT>\n");
      out.write("\t\t<pnbwordsNT>" + (String)statistics.get(4) + "</pnbwordsNT>\n");
      out.write("\t\t<nbwords_sc>" + (String)statistics.get(5) + "</nbwords_sc>\n");
      out.write("\t</statistics>\n");
      for (String normalized : result.keySet()) {
        List<Result> r = result.get(normalized);
        out.write("\t<result word='" + normalized + "' nbresult='" + r.size() + "'>\n");
        if (r.isEmpty()) {
          out.write("\t\t<comment>لا توجد نتائج</comment>\n");
        } else {
          Iterator<Result> it_r = r.iterator();
          while (it_r.hasNext()) {
            Result e_r = it_r.next();
            out.write("\t\t<s_result>\n");
            if (Settings.vowchoice)
              out.write("\t\t\t<voweled>" + e_r.getVoweledWord() + "</voweled>\n"); 
            if (Settings.prefchoice)
              out.write("\t\t\t<prefixe>" + e_r.getPrefix() + "</prefixe>\n"); 
            if (Settings.stemchoice)
              out.write("\t\t\t<stem>" + e_r.getStem() + "</stem>\n"); 
            if (Settings.typechoice)
              out.write("\t\t\t<type>" + e_r.getType() + "</type>\n"); 
            if (Settings.diacpatternchoice)
              out.write("\t\t\t<diac>" + e_r.getDiacPatternStem() + "</diac>\n"); 
            if (Settings.patternchoice)
              out.write("\t\t\t<canonic>" + e_r.getPatternStem() + "</canonic>\n"); 
            if (Settings.lemmechoice)
              out.write("\t\t\t<lemme>" + e_r.getLemma() + "</lemme>\n"); 
            if (Settings.patternlemmechoice)
              out.write("\t\t\t<pat_lemme>" + e_r.getLemma() + "</pat_lemme>\n"); 
            if (Settings.rootchoice)
              out.write("\t\t\t<root>" + e_r.getRoot() + "</root>\n"); 
            if (Settings.poschoice)
              out.write("\t\t\t<pos>" + e_r.getPostag() + "</pos>\n"); 
            if (Settings.suffixchoice)
              out.write("\t\t\t<suffixe>" + e_r.getSuffix() + "</suffixe>\n"); 
            out.write("\t\t</s_result>\n");
          } 
        } 
        out.write("\t</result>\n");
      } 
      out.write("</results>\n");
      out.close();
    } catch (FileNotFoundException e) {
      System.out.println("Erreur : AlKhalil2.Result.ResultXML.java ::" + e);
    } catch (IOException e) {
      System.out.println("Erreur : AlKhalil2.Result.ResultXML.java :: " + e);
    } 
  }
  
  public void writeStatisticsXML(String pathOutFile, String NameCharset, List<String> statistics) {
    try {
      OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(pathOutFile), NameCharset);
      out.write("<?xml version=\"1.0\" encoding=\"windows-1256\" ?>\n");
      out.write("<statistics>\n");
      out.write("\t<nbwords>" + (String)statistics.get(0) + "</nbwords>\n");
      out.write("\t<nbwordsT>" + (String)statistics.get(1) + "</nbwordsT>\n");
      out.write("\t<pnbwordsT>" + (String)statistics.get(2) + "</pnbwordsT>\n");
      out.write("\t<nbwordsNT>" + (String)statistics.get(3) + "</nbwordsNT>\n");
      out.write("\t<pnbwordsNT>" + (String)statistics.get(4) + "</pnbwordsNT>\n");
      out.write("\t<nbwords_sc>" + (String)statistics.get(5) + "</nbwords_sc>\n");
      out.write("</statistics>\n");
      out.close();
    } catch (FileNotFoundException e) {
      System.out.println("Erreur : AlKhalil2.Result.ResultXML.java ::" + e);
    } catch (IOException e) {
      System.out.println("Erreur : AlKhalil2.Result.ResultXML.java :: " + e);
    } 
  }
  
  public void writeResults(String nameFileOut, String nameCharset, Map sol) {
    File file = new File("Resultat/");
    if (!file.exists() || !file.isDirectory())
      file.mkdir(); 
    file = new File("Resultat/Analyzer/");
    if (!file.exists() || !file.isDirectory())
      file.mkdir(); 
    try {
      OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(new File(nameFileOut)), nameCharset);
      out.write("<?xml version=\"1.0\" encoding=\"windows-1256\" ?>\n");
      out.write("<results>\n");
      List<String> Lword = new ArrayList<String>();
      Lword.addAll(sol.keySet());
      Collections.sort(Lword);
      Iterator<String> it_nor = Lword.iterator();
      while (it_nor.hasNext()) {
        String normalized = it_nor.next();
        List<Result> r = (List)sol.get(normalized);
        out.write("\t<result word='" + normalized + "' nbresult='" + r.size() + "'>\n");
        if (r.isEmpty()) {
          out.write("\t\tلا توجد نتائج\n");
        } else {
          Iterator<Result> it_r = r.iterator();
          while (it_r.hasNext()) {
            Result e_r = it_r.next();
            out.write("\t\t<s_result ");
            if (Settings.vowchoice)
              out.write("voweled=\"" + e_r.getVoweledWord() + "\" "); 
            if (Settings.prefchoice)
              out.write("prefixe=\"" + e_r.getPrefix() + "\" "); 
            if (Settings.stemchoice)
              out.write("stem=\"" + e_r.getStem() + "\" "); 
            if (Settings.typechoice)
              out.write("type=\"" + e_r.getType() + "\" "); 
            if (Settings.diacpatternchoice)
              out.write("diac=\"" + e_r.getDiacPatternStem() + "\" "); 
            if (Settings.patternchoice)
              out.write("canonic=\"" + e_r.getPatternStem() + "\" "); 
            if (Settings.lemmechoice)
              out.write("lemme=\"" + e_r.getLemma() + "\" "); 
            if (Settings.patternlemmechoice)
              out.write("pat_lemme=\"" + e_r.getPatternLemma() + "\" "); 
            if (Settings.rootchoice)
              out.write("root=\"" + e_r.getRoot() + "\" "); 
            if (Settings.poschoice)
              out.write("pos=\"" + e_r.getPostag() + "\" "); 
            if (Settings.suffixchoice)
              out.write("suffixe=\"" + e_r.getSuffix() + "\" "); 
            out.write("/>\n");
          } 
        } 
        out.write("\t</result>\n");
      } 
      out.write("</results>\n");
      out.close();
    } catch (FileNotFoundException e) {
      System.out.println("Erreur : AlKhalil2.Result.ResultXML.java ::" + e);
    } catch (IOException e) {
      System.out.println("Erreur : AlKhalil2.Result.ResultXML.java :: " + e);
    } 
  }
  
  public void writeResultsNTraiterXML(String pathOutFile, String NameCharset, Map<String, List<Result>> result) {
    try {
      OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(pathOutFile), NameCharset);
      out.write("<?xml version=\"1.0\" encoding=\"windows-1256\" ?>\n");
      out.write("<results>\n");
      for (String normalized : result.keySet()) {
        List<Result> r = result.get(normalized);
        if (r.isEmpty())
          out.write("\t<result word='" + normalized + "' />\n"); 
      } 
      out.write("</results>\n");
      out.close();
    } catch (FileNotFoundException e) {
      System.out.println("Erreur : AlKhalil2.Result.ResultXML.java ::" + e);
    } catch (IOException e) {
      System.out.println("Erreur : AlKhalil2.Result.ResultXML.java :: " + e);
    } 
  }
  
  public void writeResultsTraiterXML(String pathOutFile, String NameCharset, Map<String, List<Result>> result) {
    try {
      OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(pathOutFile), NameCharset);
      out.write("<?xml version=\"1.0\" encoding=\"windows-1256\" ?>\n");
      out.write("<results>\n");
      for (String normalized : result.keySet()) {
        List<Result> r = result.get(normalized);
        if (!r.isEmpty()) {
          out.write("\t<result word='" + normalized + "' nbresult='" + r.size() + "'>\n");
          Iterator<Result> it_r = r.iterator();
          while (it_r.hasNext()) {
            Result e_r = it_r.next();
            out.write("\t\t<s_result>\n");
            if (Settings.vowchoice)
              out.write("\t\t\t<voweled>" + e_r.getVoweledWord() + "</voweled>\n"); 
            if (Settings.prefchoice)
              out.write("\t\t\t<prefixe>" + e_r.getPrefix() + "</prefixe>\n"); 
            if (Settings.stemchoice)
              out.write("\t\t\t<stem>" + e_r.getStem() + "</stem>\n"); 
            if (Settings.typechoice)
              out.write("\t\t\t<type>" + e_r.getType() + "</type>\n"); 
            if (Settings.diacpatternchoice)
              out.write("\t\t\t<diac>" + e_r.getDiacPatternStem() + "</diac>\n"); 
            if (Settings.patternchoice)
              out.write("\t\t\t<canonic>" + e_r.getPatternStem() + "</canonic>\n"); 
            if (Settings.lemmechoice)
              out.write("\t\t\t<lemme>" + e_r.getLemma() + "</lemme>\n"); 
            if (Settings.patternlemmechoice)
              out.write("\t\t\t<pat_lemme>" + e_r.getPatternLemma() + "</pat_lemme>\n"); 
            if (Settings.rootchoice)
              out.write("\t\t\t<root>" + e_r.getRoot() + "</root>\n"); 
            if (Settings.poschoice)
              out.write("\t\t\t<pos>" + e_r.getPostag() + "</pos>\n"); 
            if (Settings.suffixchoice)
              out.write("\t\t\t<suffixe>" + e_r.getSuffix() + "</suffixe>\n"); 
            out.write("\t\t</s_result>\n");
          } 
          out.write("\t</result>\n");
        } 
      } 
      out.write("</results>\n");
      out.close();
    } catch (FileNotFoundException e) {
      System.out.println("Erreur : AlKhalil2.Result.ResultXML.java ::" + e);
    } catch (IOException e) {
      System.out.println("Erreur : AlKhalil2.Result.ResultXML.java :: " + e);
    } 
  }
  
  public void writeResultsRoot_Lemme_Words(String pathOutFile, String NameCharset, Map sol) {
    File file = new File("ResultatSol/");
    if (!file.exists() || !file.isDirectory())
      file.mkdir(); 
    file = new File("ResultatSol/Analyzer/");
    if (!file.exists() || !file.isDirectory())
      file.mkdir(); 
    String Str = "ResultatSol/Analyzer/" + pathOutFile;
    try {
      OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(new File(Str)), NameCharset);
      out.write("<?xml version=\"1.0\" encoding=\"windows-1256\" ?>\n");
      out.write("<results>\n");
      List<Comparable> r_Root = new ArrayList();
      r_Root.addAll(sol.keySet());
      Collections.sort(r_Root);
      Iterator<Comparable> it_rRoot = r_Root.iterator();
      while (it_rRoot.hasNext()) {
        String root = (String)it_rRoot.next();
        out.write("\t<root val=\"" + root + "\">\n");
        List<Comparable> r_Lemme = new ArrayList();
        r_Lemme.addAll(((Map)sol.get(root)).keySet());
        Collections.sort(r_Lemme);
        Iterator<Comparable> it_rLemme = r_Lemme.iterator();
        while (it_rLemme.hasNext()) {
          String Lemme = (String)it_rLemme.next();
          out.write("\t\t<lemme val=\"" + Lemme + "\">\n");
          List<Comparable> r_Words = new ArrayList();
          r_Words.addAll(((Map)((Map)sol.get(root)).get(Lemme)).keySet());
          Collections.sort(r_Words);
          Iterator<Comparable> it_rWords = r_Words.iterator();
          while (it_rWords.hasNext()) {
            String Word = (String)it_rWords.next();
            out.write("\t\t\t<word val=\"" + Word + "\">\n");
            List r_Pos = new ArrayList();
            r_Pos = (List)((Map)((Map)sol.get(root)).get(Lemme)).get(Word);
            Iterator<String> it_rPos = r_Pos.iterator();
            while (it_rPos.hasNext()) {
              String Pos = it_rPos.next();
              out.write("\t\t\t\t<pos val=\"" + Pos + "\" />\n");
            } 
            out.write("\t\t\t</word>\n");
          } 
          out.write("\t\t</lemme>\n");
        } 
        out.write("\t</root>\n");
      } 
      out.write("</results>\n");
      out.close();
    } catch (FileNotFoundException e) {
      System.out.println("Erreur : AlKhalil2.Result.ResultXML.java ::" + e);
    } catch (IOException e) {
      System.out.println("Erreur : AlKhalil2.Result.ResultXML.java :: " + e);
    } 
  }
  
  public void writeResultsRoot(String nameFile, String NameCharset, List<Comparable> sol) {
    try {
      OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(new File(nameFile)), NameCharset);
      Collections.sort(sol);
      Iterator<String> it_sol = (Iterator)sol.iterator();
      while (it_sol.hasNext()) {
        String solution = it_sol.next();
        out.write(solution + "\n");
      } 
      out.close();
    } catch (FileNotFoundException e) {
      System.out.println("Erreur : AlKhalil2.Result.ResultXML.java ::" + e);
    } catch (IOException e) {
      System.out.println("Erreur : AlKhalil2.Result.ResultXML.java :: " + e);
    } 
  }
  
  public void writeResultsLemme(String nameFile, String NameCharset, List sol) {
    Set setLemme = new HashSet();
    setLemme.addAll(sol);
    List<Comparable> sol1 = new ArrayList();
    sol1.addAll(setLemme);
    try {
      OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(new File(nameFile)), NameCharset);
      Collections.sort(sol1);
      Iterator<Comparable> it_sol = sol1.iterator();
      while (it_sol.hasNext()) {
        String solution = (String)it_sol.next();
        out.write(solution + "\n");
      } 
      out.close();
    } catch (FileNotFoundException e) {
      System.out.println("Erreur : AlKhalil2.Result.ResultXML.java ::" + e);
    } catch (IOException e) {
      System.out.println("Erreur : AlKhalil2.Result.ResultXML.java :: " + e);
    } 
  }
  
  public static StringBuilder writeResultsToHTML() {
    StringBuilder str = new StringBuilder();
    str.append("<table class=\"htmltab\" float=\"center\" WIDTH=90% BORDER=1 dir=\"rtl\">").append("\n");
    int cmp = 0;
    str.append("<tr>").append("\n");
    str.append("<th>الدخل</th>").append("\n");
    cmp++;
    if (Settings.vowchoice) {
      str.append("<th>الكلمة المشكولة</th>").append("\n");
      cmp++;
    } 
    if (Settings.prefchoice) {
      str.append("<th>السابق</th>").append("\n");
      cmp++;
    } 
    if (Settings.stemchoice) {
      str.append("<th>الجذع</th>").append("\n");
      cmp++;
    } 
    if (Settings.typechoice) {
      str.append("<th>نوع الكلمة</th>").append("\n");
      cmp++;
    } 
    if (Settings.diacpatternchoice);
    if (Settings.patternchoice) {
      str.append("<th>وزن الكلمة</th>").append("\n");
      cmp++;
    } 
    if (Settings.lemmechoice) {
      str.append("<th>الفرع</th>").append("\n");
      cmp++;
    } 
    if (Settings.patternlemmechoice) {
      str.append("<th>وزن الفرع</th>").append("\n");
      cmp++;
    } 
    if (Settings.rootchoice) {
      str.append("<th>الجذر</th>").append("\n");
      cmp++;
    } 
    if (Settings.poschoice) {
      str.append("<th>الحالة الإعرابية</th>").append("\n");
      cmp++;
    } 
    if (Settings.suffixchoice) {
      str.append("<th>اللاحق</th>").append("\n");
      cmp++;
    } 
    str.append("</tr>").append("\n");
    Iterator<String> it = Static.allResults.keySet().iterator();
    while (it.hasNext()) {
      String words = it.next();
      List<Result> result = (List)Static.allResults.get(words);
      if (result.isEmpty()) {
        str.append("<tr>").append("\n");
        str.append("<td>").append(words).append("</td>").append("\n");
        str.append("<td colspan=").append(cmp).append(">لا توجد نتائج لتحليل هذه الكلمة</td>").append("\n");
        str.append("</tr>");
        continue;
      } 
      int size = result.size();
      str.append("<tr>").append("\n");
      str.append("<td rowspan=").append(size).append(" >").append(words).append("</td>").append("\n");
      Iterator<Result> itRes = result.iterator();
      if (itRes.hasNext()) {
        Result r = itRes.next();
        if (Settings.vowchoice)
          str.append("<td>").append(r.getVoweledWord()).append("</td>").append("\n"); 
        if (Settings.prefchoice)
          str.append("<td>").append(r.getPrefix()).append("</td>").append("\n"); 
        if (Settings.stemchoice)
          str.append("<td>").append(r.getStem()).append("</td>").append("\n"); 
        if (Settings.typechoice)
          str.append("<td>").append(r.getType()).append("</td>").append("\n"); 
        if (Settings.diacpatternchoice);
        if (Settings.patternchoice)
          str.append("<td>").append(r.getPatternStem()).append("</td>").append("\n"); 
        if (Settings.lemmechoice)
          str.append("<td>").append(r.getLemma()).append("</td>").append("\n"); 
        if (Settings.patternlemmechoice)
          str.append("<td>").append(r.getPatternLemma()).append("</td>").append("\n"); 
        if (Settings.rootchoice)
          str.append("<td>").append(r.getRoot()).append("</td>").append("\n"); 
        if (Settings.poschoice)
          str.append("<td>").append(r.getPostag()).append("</td>").append("\n"); 
        if (Settings.suffixchoice)
          str.append("<td>").append(r.getSuffix()).append("</td>").append("\n"); 
      } 
      str.append("</tr>").append("\n");
      while (itRes.hasNext()) {
        Result r = itRes.next();
        str.append("<tr>").append("\n");
        if (Settings.vowchoice)
          str.append("<td>").append(r.getVoweledWord()).append("</td>").append("\n"); 
        if (Settings.prefchoice)
          str.append("<td>").append(r.getPrefix()).append("</td>").append("\n"); 
        if (Settings.stemchoice)
          str.append("<td>").append(r.getStem()).append("</td>").append("\n"); 
        if (Settings.typechoice)
          str.append("<td>").append(r.getType()).append("</td>").append("\n"); 
        if (Settings.diacpatternchoice);
        if (Settings.patternchoice)
          str.append("<td>").append(r.getPatternStem()).append("</td>").append("\n"); 
        if (Settings.lemmechoice)
          str.append("<td>").append(r.getLemma()).append("</td>").append("\n"); 
        if (Settings.patternlemmechoice)
          str.append("<td>").append(r.getPatternLemma()).append("</td>").append("\n"); 
        if (Settings.rootchoice)
          str.append("<td>").append(r.getRoot()).append("</td>").append("\n"); 
        if (Settings.poschoice)
          str.append("<td>").append(r.getPostag()).append("</td>").append("\n"); 
        if (Settings.suffixchoice)
          str.append("<td>").append(r.getSuffix()).append("</td>").append("\n"); 
        str.append("</tr>").append("\n");
      } 
    } 
    str.append("</table>");
    return str;
  }
  
  public static void writeResultsIndexationToXml(File nameFile, String nameCharset) {
    OutputStreamWriter out = null;
    try {
      out = new OutputStreamWriter(new FileOutputStream(nameFile), nameCharset);
      Iterator<Integer> itp = Indexation.getiWordsPos().keySet().iterator();
      out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
      out.write("<Indexation>\n");
      while (itp.hasNext()) {
        int stp = ((Integer)itp.next()).intValue();
        List<String> ilistStrs = new ArrayList<String>();
        ilistStrs.addAll(Arrays.asList(((String)Indexation.getiWordsPos().get(Integer.valueOf(stp))).split(" ")));
        int p = 1;
        out.write("\t<words id=\"" + stp + "\" val=\"" + (String)Indexation.getiIDWords().get(Integer.valueOf(stp)) + "\" nbPos=\"" + ilistStrs.size() + "\" >\n");
        Iterator<String> itStrs = ilistStrs.iterator();
        while (itStrs.hasNext()) {
          String stStrs = itStrs.next();
          String[] stl = stStrs.split(";");
          if (stl.length == 2) {
            out.write("\t\t<pos idpos=\"" + p + "\" prev=\"" + stl[0] + "\" next=\"" + stl[1] + "\" />\n");
            p++;
          } 
        } 
        out.write("\t</words>\n");
      } 
      out.write("</Indexation>\n");
      out.close();
    } catch (IOException ex) {
      System.out.println("Erreur : " + ex);
    } 
  }
  
  public static void writeResultsIndexationToHtml(File nameFile, String nameCharset) {
    OutputStreamWriter out = null;
    try {
      out = new OutputStreamWriter(new FileOutputStream(nameFile), nameCharset);
      String cellBeg = "<td align=\"right\"><font style=\"font-size:12px\">";
      String cellEnd = "</font></td>";
      out.write("<html><head><style>table{border-collapse:collapse;}table,th,td{border:1px solid black;font-size: 12px;}p{font-size: 12px;}</style></head><body lang=\"ar-SA\" dir=\"ltr\"><center><font size=5> <b>الفَهرَسَة <br> Indexing</b></font></center><br>\n");
      out.write("<p align=\"right\">");
      out.write("عدد الكلمات :");
      out.write("<font color=red>");
      out.write(Normalization.nbTotalWord);
      out.write("</font></p>");
      out.write("<p align=\"right\">");
      out.write("عدد الكلمات بدون تكرار :");
      out.write("<font color=red>");
      out.write(Indexation.getiWordsPos().size());
      out.write("</font></p>");
      out.write("\n");
      out.write("<DIV ALIGN=RIGHT>\n");
      out.write("<TABLE DIR=\"RTL\" BORDER=1 style=\"width:100%\">\n");
      out.write("<tr valign=top> \n");
      out.write(cellBeg + "<B>السياقات</B>" + cellEnd + cellBeg + "<B> مواقع الكلمات </B>" + cellEnd + cellBeg + "<B>التكرار</B>" + cellEnd + cellBeg + "<B>الكلمة</B>" + cellEnd + "</TR>" + "\n");
      out.write("<DIV ALIGN=RIGHT>\n");
      out.write("<DIV ALIGN=RIGHT>\n");
      out.write("<DIV ALIGN=RIGHT>\n");
      out.write("<DIV ALIGN=RIGHT>\n");
      Iterator<Integer> itp = Indexation.getiWordsPos().keySet().iterator();
      while (itp.hasNext()) {
        int stp = ((Integer)itp.next()).intValue();
        out.write("<tr valign=top> ");
        String[] strs = ((String)Indexation.getiWordsPos().get(Integer.valueOf(stp))).split(" ");
        int p = 1;
        if (strs.length > 0) {
          String[] stl = strs[0].split(";");
          if (stl.length == 2) {
            String srt = "";
            if (!"0".equals(stl[0]))
              srt = srt + Indexation.getLeftContext(Integer.parseInt(stl[0].split("\\.")[0]), Integer.parseInt(stl[0].split("\\.")[1]), 5); 
            srt = srt + " <font color=red>" + (String)Indexation.getiIDWords().get(Integer.valueOf(stp)) + "</font> ";
            if (!"0".equals(stl[1]))
              srt = srt + Indexation.getRightContext(Integer.parseInt(stl[1].split("\\.")[0]), Integer.parseInt(stl[1].split("\\.")[1]), 5); 
            out.write("<td align=\"right\" style=\"width:50%\"><font style=\"font-size:12px\">");
            out.write(srt);
            out.write(cellEnd);
          } 
          out.write("<td align=\"right\" style=\"width:15%\"><font style=\"font-size:12px\">");
          out.write(p);
          out.write(cellEnd);
          p++;
        } 
        out.write("<td align=\"right\" rowspan=\"" + strs.length + "\" style=\"width:15%\"><font style=\"font-size:12px\">");
        out.write(strs.length);
        out.write(cellEnd);
        out.write("<td align=\"right\" rowspan=\"" + strs.length + "\" style=\"width:20%\"><font style=\"font-size:12px\">");
        out.write(Indexation.getiIDWords().get(Integer.valueOf(stp)));
        out.write(cellEnd);
        out.write("</TR>");
        for (int i = 1; i < strs.length; i++) {
          out.write("<TR VALIGN=TOP> ");
          String[] stl = strs[i].split(";");
          if (stl.length == 2) {
            String srt = "";
            if (!"0".equals(stl[0]))
              srt = srt + Indexation.getLeftContext(Integer.parseInt(stl[0].split("\\.")[0]), Integer.parseInt(stl[0].split("\\.")[1]), 5); 
            srt = srt + " <font color=red>" + (String)Indexation.getiIDWords().get(Integer.valueOf(stp)) + "</font> ";
            if (!"0".equals(stl[1]))
              srt = srt + Indexation.getRightContext(Integer.parseInt(stl[1].split("\\.")[0]), Integer.parseInt(stl[1].split("\\.")[1]), 5); 
            out.write("<td align=\"right\" style=\"width:50%\"><font style=\"font-size:12px\">");
            out.write(srt);
            out.write(cellEnd);
          } 
          out.write("<td align=\"right\" style=\"width:15%\"><font style=\"font-size:12px\">");
          out.write(p);
          out.write(cellEnd);
          out.write("</TR>");
          p++;
        } 
      } 
      out.close();
    } catch (IOException ex) {
      System.out.println("Erreur : " + ex);
    } 
  }
  
  public static void writeResultsIndexationToCsv(File nameFile, String nameCharset) {
    OutputStreamWriter out = null;
    try {
      out = new OutputStreamWriter(new FileOutputStream(nameFile), nameCharset);
      Iterator<Integer> itp = Indexation.getiWordsPos().keySet().iterator();
      out.write("الكلمات;التكرار;موقع الكلمات;السياق\n");
      while (itp.hasNext()) {
        int stp = ((Integer)itp.next()).intValue();
        String[] strs = ((String)Indexation.getiWordsPos().get(Integer.valueOf(stp))).split(" ");
        int p = 1;
        for (String str : strs) {
          out.write((String)Indexation.getiIDWords().get(Integer.valueOf(stp)) + ";");
          out.write(strs.length + ";");
          String[] stl = str.split(";");
          if (stl.length == 2) {
            out.write(p + ";");
            p++;
            String srt = "";
            if (!"0".equals(stl[0]))
              srt = srt + Indexation.getLeftContext(Integer.parseInt(stl[0].split("\\.")[0]), Integer.parseInt(stl[0].split("\\.")[1]), 5); 
            srt = srt + " " + (String)Indexation.getiIDWords().get(Integer.valueOf(stp)) + " ";
            if (!"0".equals(stl[1]))
              srt = srt + Indexation.getRightContext(Integer.parseInt(stl[1].split("\\.")[0]), Integer.parseInt(stl[1].split("\\.")[1]), 5); 
            out.write(srt + "\n");
          } 
        } 
      } 
      out.close();
    } catch (IOException ex) {
      System.out.println("Erreur : " + ex);
    } 
  }
}
