package AlKhalil2.morphology.derived.analyzer.impl;

import AlKhalil2.morphology.derived.analyzer.factory.AnalyzerDerivedFactory;
import AlKhalil2.morphology.derived.analyzer.interfaces.IAnalyzerDerived;
import AlKhalil2.morphology.derived.formulas.model.Formulas;
import AlKhalil2.morphology.result.model.Result;
import AlKhalil2.stemmer.model.Segment;
import AlKhalil2.util.ArabMethod;
import AlKhalil2.util.CreatHamza;
import AlKhalil2.util.Settings;
import AlKhalil2.util.constants.Static;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AnalyzerDerivedNominal extends AnalyzerDerivedFactory implements IAnalyzerDerived {
  private boolean possibleSolution;
  
  public List analyzedSegment(String normalizedWord, String unvoweledWord, Segment segment) {
    List result = new ArrayList();
    String stem = segment.getStem();
    if (stem.length() >= 2 && stem.length() <= 11 && segment.getProclitic().getClasse().indexOf("V") == -1 && segment.getEnclitic().getClasse().indexOf("V") == -1)
      result = getPossiblesSolutions(normalizedWord, segment); 
    return result;
  }
  
  public List getPossiblesSolutions(String normalizedWord, Segment segment) {
    List<Result> result = new ArrayList();
    Map<String, Map<String, List>> possibleSolutions = new HashMap<String, Map<String, List>>();
    String Stem = segment.getStem();
    if (this.possiblePattern.containsKey(Stem)) {
      possibleSolutions = this.possiblePattern.get(Stem);
    } else {
      possibleSolutions = possibleSolutions(normalizedWord, segment);
    } 
    Iterator<String> it_unvoweled = possibleSolutions.keySet().iterator();
    while (it_unvoweled.hasNext()) {
      String s_unvoweled = it_unvoweled.next();
      Map<String, List> Roots = possibleSolutions.get(s_unvoweled);
      Iterator<String> it_Roots = Roots.keySet().iterator();
      while (it_Roots.hasNext()) {
        String s_root = it_Roots.next();
        List<Formulas> solutionPatterns = Roots.get(s_root);
        Iterator<Formulas> it_solPatterns = solutionPatterns.iterator();
        while (it_solPatterns.hasNext()) {
          Formulas sol = it_solPatterns.next();
          int lStem = segment.getStem().length();
          String[] Solutions = getInfoResults(sol, lStem).split(":");
          int i = 0;
          String diac = Solutions[i];
          i++;
          String canonicPattern = "";
          if (Settings.patternchoice) {
            canonicPattern = Solutions[i];
            i++;
          } 
          String lemma = "";
          if (Settings.lemmechoice) {
            lemma = ArabMethod.diaclemme(s_root, Solutions[i]);
            i++;
          } 
          String lemmapattern = "";
          if (Settings.patternlemmechoice) {
            lemmapattern = Solutions[i];
            i++;
          } 
          String type = "";
          if (Settings.typechoice) {
            type = Solutions[i];
            i++;
          } 
          int idposTags = Integer.parseInt(Solutions[i]);
          if (this.posTag.isEmpty())
            this.posTag.addPosTag(); 
          String ncg = this.posTag.getPosTag(idposTags).getNcg();
          String cas = this.posTag.getPosTag(idposTags).getCas();
          String[] vowledWords = (new ArabMethod()).Vowelize(segment, diac, ncg);
          String vowledWord = vowledWords[0];
          if (isValidNominalSolution(segment, ncg, vowledWords, normalizedWord)) {
            String[] Sol = Interpret(segment, s_root, type, ncg, cas);
            if (this.possibleSolution) {
              String stem = CreatHamza.correctStemHamza(segment.getStem());
              if (Static.StaticUnvoweled) {
                if (vowledWord.equals(normalizedWord)) {
                  Result result1 = new Result(vowledWord, Sol[0], Sol[5], stem, Sol[1], diac, canonicPattern, lemma, lemmapattern, s_root, Sol[2], Sol[3], Sol[6], Sol[4]);
                  result.add(result1);
                } 
                continue;
              } 
              Result r = new Result(vowledWord, Sol[0], Sol[5], stem, Sol[1], diac, canonicPattern, lemma, lemmapattern, s_root, Sol[2], Sol[3], Sol[6], Sol[4]);
              result.add(r);
            } 
          } 
        } 
      } 
    } 
    return result;
  }
  
  private boolean isValidNominalSolution(Segment segment, String ncg, String[] vowledWords, String normalizedWord) {
    String prefcalss = segment.getProclitic().getClasse();
    String vowledWord = vowledWords[0];
    if ((vowledWord.indexOf("ً") != -1 || vowledWord.indexOf("ٍ") != -1 || vowledWord.indexOf("ٌ") != -1) && (!segment.getEnclitic().getUnvoweledform().equals("") || prefcalss.equals("N1") || prefcalss.equals("N2") || prefcalss.equals("N3") || prefcalss.equals("N5")))
      return false; 
    if ((prefcalss.equals("N2") || prefcalss.equals("C2") || prefcalss.equals("C3")) && (ncg.equals("13") || ncg.equals("14") || ncg.equals("15") || ncg.equals("16") || ncg.equals("17") || ncg.equals("18")))
      return false; 
    if ((prefcalss.equals("N4") || prefcalss.equals("N5")) && !ncg.equals("13") && !ncg.equals("14") && !ncg.equals("15") && !ncg.equals("16") && !ncg.equals("17") && !ncg.equals("18"))
      return false; 
    return !ArabMethod.notCompatible(normalizedWord, vowledWord, segment, false);
  }
  
  public String[] Interpret(Segment seg, String root, String type, String ncg, String cas) {
    String[] result = new String[7];
    StringBuilder prefix = new StringBuilder();
    StringBuilder suffix = new StringBuilder();
    StringBuilder priority = new StringBuilder();
    boolean pass = true;
    this.possibleSolution = true;
    prefix.append("");
    suffix.append("");
    priority.append("1");
    if (seg.getProclitic().getVoweledform().equals("")) {
      result[0] = "#";
      result[5] = "#";
    } else {
      result[0] = seg.getProclitic().getVoweledform() + ": " + seg.getProclitic().getDesc();
      result[5] = seg.getProclitic().getVoweledform();
    } 
    if (seg.getEnclitic().getVoweledform().equals("")) {
      result[3] = "#";
      result[6] = "#";
    } else {
      result[3] = seg.getEnclitic().getVoweledform() + ": " + seg.getEnclitic().getDesc();
      result[6] = seg.getEnclitic().getVoweledform();
    } 
    if (Settings.typechoice) {
      String[] type_Priority = type.split(";");
      result[1] = type_Priority[0];
      priority.append(type_Priority[1]);
    } 
    if ((ncg.equals("2") || ncg.equals("8") || ncg.equals("14")) && seg.getStem().endsWith("ة")) {
      suffix.append("ة: تاء التأنيث");
      pass = true;
    } else if (ncg.equals("4") || ncg.equals("10") || ncg.equals("16")) {
      if (seg.getStem().endsWith("تان")) {
        suffix.append("ة: تاء التأنيث + ان: علامة الإعراب");
        pass = true;
      } else if (seg.getStem().endsWith("تين")) {
        suffix.append("ة: تاء التأنيث + ين: علامة الإعراب");
        pass = true;
      } else if (seg.getStem().endsWith("تا")) {
        suffix.append("ة: تاء التأنيث + ا: علامة الإعراب");
        pass = false;
      } else if (seg.getStem().endsWith("تي")) {
        suffix.append("ة: تاء التأنيث + ي: علامة الإعراب");
        pass = false;
      } 
    } else if (ncg.equals("6") || ncg.equals("12") || ncg.equals("18")) {
      if (seg.getStem().endsWith("ات")) {
        suffix.append("ات: تاء التأنيث");
        pass = true;
      } 
    } else if (ncg.equals("3") || ncg.equals("9") || ncg.equals("15")) {
      if (seg.getStem().endsWith("ان")) {
        suffix.append("ان: علامة الإعراب");
        pass = true;
      } else if (seg.getStem().endsWith("ين")) {
        suffix.append("ين: علامة الإعراب");
        pass = true;
      } else if (seg.getStem().endsWith("ا")) {
        suffix.append("ا: علامة الإعراب");
        pass = false;
      } else if (seg.getStem().endsWith("ي")) {
        suffix.append("ي: علامة الإعراب");
        pass = false;
      } 
    } else if (ncg.equals("5") || ncg.equals("11") || ncg.equals("17")) {
      if (seg.getStem().endsWith("ون")) {
        suffix.append("ون: علامة الإعراب");
        pass = true;
      } else if (seg.getStem().endsWith("ين")) {
        suffix.append("ين: علامة الإعراب");
        pass = true;
      } else if (seg.getStem().endsWith("و")) {
        suffix.append("و: علامة الإعراب");
        pass = false;
      } else if (seg.getStem().endsWith("ي")) {
        suffix.append("ي: علامة الإعراب");
        pass = false;
      } 
    } 
    if (ncg.equals("0") || ncg.equals("-1") || ncg.equals("-2") || ncg.equals("-3")) {
      int idNcg = Integer.parseInt(ncg);
      String str1 = getPOSTagValue(idNcg);
      String str2 = getPOSTagPriority(idNcg);
      result[2] = str1;
      priority.append(str2);
    } else {
      int idNcg = Integer.parseInt(ncg);
      String str1 = getPOSTagValue(idNcg);
      String str2 = getPOSTagPriority(idNcg);
      if (seg.getProclitic().getClasse().equals("N1") || seg.getProclitic().getClasse().equals("N2") || seg.getProclitic().getClasse().equals("N3") || seg.getProclitic().getClasse().equals("N5")) {
        if (pass) {
          str1 = str1 + " في حالة التعريف";
          str2 = str2 + "1";
        } else {
          str1 = "";
          str2 = "";
          this.possibleSolution = false;
        } 
      } else if (cas.equals("إض")) {
        str1 = str1 + " في حالة الإضافة";
        str2 = str2 + "2";
      } else {
        str1 = str1 + " نكرة";
        str2 = str2 + "3";
      } 
      if (this.possibleSolution) {
        result[2] = str1;
        priority.append(str2);
      } 
    } 
    result[4] = priority.toString();
    if (result[0].equals("#") && !prefix.toString().equals("")) {
      result[0] = prefix.toString();
    } else if (!result[0].equals("#") && !prefix.toString().equals("")) {
      result[0] = result[0] + " + " + prefix.toString();
    } 
    if (result[3].equals("#") && !suffix.toString().equals("")) {
      result[3] = suffix.toString();
    } else if (!result[3].equals("#") && !suffix.toString().equals("")) {
      result[3] = suffix.toString() + " + " + result[3];
    } 
    return result;
  }
  
  public String getPOSTagValue(int n) {
    switch (n) {
      case 1:
        return "مفرد مذكر مرفوع";
      case 2:
        return "مفرد مؤنث مرفوع";
      case 3:
        return "مثنى مذكر مرفوع";
      case 4:
        return "مثنى مؤنث مرفوع";
      case 5:
        return "جمع مذكر مرفوع";
      case 6:
        return "جمع مؤنث مرفوع";
      case 7:
        return "مفرد مذكر منصوب";
      case 8:
        return "مفرد مؤنث منصوب";
      case 9:
        return "مثنى مذكر منصوب";
      case 10:
        return "مثنى مؤنث منصوب";
      case 11:
        return "جمع مذكر منصوب";
      case 12:
        return "جمع مؤنث منصوب";
      case 13:
        return "مفرد مذكر مجرور";
      case 14:
        return "مفرد مؤنث مجرور";
      case 15:
        return "مثنى مذكر مجرور";
      case 16:
        return "مثنى مؤنث مجرور";
      case 17:
        return "جمع مذكر مجرور";
      case 18:
        return "جمع مؤنث مجرور";
      case 0:
        return "مفرد مذكر";
      case -1:
        return "مفرد مؤنث";
      case -2:
        return "جمع مذكر";
      case -3:
        return "جمع مؤنث";
      case 19:
        return "مفرد مرفوع";
      case 20:
        return "مفرد منصوب";
      case 21:
        return "مفرد مجرور";
      case 22:
        return "مثنى مرفوع";
      case 23:
        return "مثنى منصوب";
      case 24:
        return "مثنى مجرور";
      case 25:
        return "جمع مرفوع";
      case 26:
        return "جمع منصوب";
      case 27:
        return "جمع مجرور";
    } 
    return "";
  }
  
  public String getPOSTagPriority(int n) {
    switch (n) {
      case 1:
        return "111";
      case 2:
        return "121";
      case 3:
        return "211";
      case 4:
        return "221";
      case 5:
        return "311";
      case 6:
        return "321";
      case 7:
        return "112";
      case 8:
        return "122";
      case 9:
        return "212";
      case 10:
        return "222";
      case 11:
        return "312";
      case 12:
        return "322";
      case 13:
        return "113";
      case 14:
        return "123";
      case 15:
        return "213";
      case 16:
        return "223";
      case 17:
        return "313";
      case 18:
        return "323";
      case 0:
        return "11";
      case -1:
        return "12";
      case -2:
        return "31";
      case -3:
        return "32";
      case 19:
        return "101";
      case 20:
        return "102";
      case 21:
        return "103";
      case 22:
        return "201";
      case 23:
        return "202";
      case 24:
        return "203";
      case 25:
        return "301";
      case 26:
        return "302";
      case 27:
        return "303";
    } 
    return "";
  }
  
  public String getTypeValue(String type) {
    if (type.equals("آ"))
      return "اسم آلة"; 
    if (type.equals("جا"))
      return "اسم جامد"; 
    if (type.equals("زمك"))
      return "اسم زمان أو مكان"; 
    if (type.equals("صأ"))
      return "مصدر أصلي"; 
    if (type.equals("صر"))
      return "مصدر مرة"; 
    if (type.equals("صم"))
      return "مصدر ميمي"; 
    if (type.equals("صن"))
      return "مصدر صناعي"; 
    if (type.equals("صه"))
      return "مصدر هيئة"; 
    if (type.equals("فا"))
      return "اسم فاعل"; 
    if (type.equals("فض"))
      return "اسم تفضيل"; 
    if (type.equals("مف"))
      return "اسم مفعول"; 
    if (type.equals("مفا"))
      return "مبالغة اسم الفاعل"; 
    if (type.equals("نس"))
      return "نسبة"; 
    if (type.equals("وش"))
      return "صفة مشبهة"; 
    return type;
  }
  
  public String getTypePriorityValue(String type) {
    if (type.equals("آ"))
      return "15"; 
    if (type.equals("جا"))
      return "01"; 
    if (type.equals("زمك"))
      return "14"; 
    if (type.equals("صأ"))
      return "07"; 
    if (type.equals("صر"))
      return "16"; 
    if (type.equals("صم"))
      return "08"; 
    if (type.equals("صن"))
      return "16"; 
    if (type.equals("صه"))
      return "17"; 
    if (type.equals("فا"))
      return "10"; 
    if (type.equals("فض"))
      return "13"; 
    if (type.equals("مف"))
      return "11"; 
    if (type.equals("مفا"))
      return "10"; 
    if (type.equals("نس"))
      return "16"; 
    if (type.equals("وش"))
      return "12"; 
    return "19";
  }
  
  public void clear() {
    this.formula.clear();
    this.posTag.clear();
    this.roots.clear();
    this.type.clear();
    this.unvoweled.clear();
    this.voweled.clear();
    this.possiblePattern.clear();
  }
}
