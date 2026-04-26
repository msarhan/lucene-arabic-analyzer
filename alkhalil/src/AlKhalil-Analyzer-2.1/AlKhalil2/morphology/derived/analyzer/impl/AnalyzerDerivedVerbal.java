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

public class AnalyzerDerivedVerbal extends AnalyzerDerivedFactory implements IAnalyzerDerived {
  public List analyzedSegment(String normalizedWord, String unvoweledWord, Segment segment) {
    List result = new ArrayList();
    String stem = segment.getStem();
    if (stem.length() >= 1 && stem.length() <= 9 && segment.getProclitic().getClasse().indexOf("N") == -1 && segment.getEnclitic().getClasse().indexOf("N") == -1)
      result = getPossiblesSolutions(normalizedWord, segment); 
    return result;
  }
  
  public List getPossiblesSolutions(String normalizedWord, Segment segment) {
    List<Result> result = new ArrayList();
    Map<String, Map> possibleSolutions = new HashMap<String, Map>();
    String Stem = segment.getStem();
    if (this.possiblePattern.containsKey(Stem)) {
      possibleSolutions = this.possiblePattern.get(Stem);
    } else {
      possibleSolutions = possibleSolutions(normalizedWord, segment);
    } 
    Iterator<String> it_unvoweled = possibleSolutions.keySet().iterator();
    while (it_unvoweled.hasNext()) {
      String s_unvoweled = it_unvoweled.next();
      Map Roots = possibleSolutions.get(s_unvoweled);
      Iterator<String> it_Roots = Roots.keySet().iterator();
      while (it_Roots.hasNext()) {
        String s_root = it_Roots.next();
        List<Formulas> solutionPatterns = (List)Roots.get(s_root);
        Iterator<Formulas> it_solutionPatterns = solutionPatterns.iterator();
        while (it_solutionPatterns.hasNext()) {
          Formulas sol = it_solutionPatterns.next();
          int lenStem = segment.getStem().length();
          String[] Solutions = getInfoResults(sol, lenStem).split(":");
          int i = 0;
          String diac = Solutions[i];
          i++;
          String canonicPattern = "";
          if (Settings.patternchoice) {
            canonicPattern = Solutions[i];
            i++;
          } 
          String lemme = "";
          if (Settings.lemmechoice) {
            lemme = ArabMethod.diaclemme(s_root, Solutions[i]);
            i++;
          } 
          String lemmepattern = "";
          if (Settings.patternlemmechoice) {
            lemmepattern = Solutions[i];
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
          String trans = this.posTag.getPosTag(idposTags).getTrans();
          String aug = this.posTag.getPosTag(idposTags).getAug();
          String[] vowledWords = (new ArabMethod()).Vowelize(segment, diac, ncg);
          String vowledWord = vowledWords[0];
          if (isValidVerbalSolution(segment, vowledWords, normalizedWord, type, ncg, cas, trans)) {
            String[] Sol = Interpret(segment, s_root, type, ncg, cas, aug, trans);
            String stem = CreatHamza.correctStemHamza(segment.getStem());
            if (Static.StaticUnvoweled) {
              if (vowledWord.equals(normalizedWord)) {
                Result result1 = new Result(vowledWord, Sol[0], Sol[5], stem, Sol[1], diac, canonicPattern, lemme, lemmepattern, s_root, Sol[2], Sol[3], Sol[6], Sol[4]);
                result.add(result1);
              } 
              continue;
            } 
            Result r = new Result(vowledWord, Sol[0], Sol[5], stem, Sol[1], diac, canonicPattern, lemme, lemmepattern, s_root, Sol[2], Sol[3], Sol[6], Sol[4]);
            result.add(r);
          } 
        } 
      } 
    } 
    return result;
  }
  
  public boolean isValidVerbalSolution(Segment segment, String[] vowledWords, String normalizedWord, String type, String ncg, String cas, String trans) {
    boolean valid = true;
    String prefcalss = segment.getProclitic().getClasse();
    String suffcalss = segment.getEnclitic().getClasse();
    String vowledWord = vowledWords[0];
    if (prefcalss.equals("V1") && !cas.equals("ر")) {
      valid = false;
      return valid;
    } 
    if (prefcalss.equals("V2") && !cas.equals("ن")) {
      valid = false;
      return valid;
    } 
    if (prefcalss.equals("V3") && !cas.equals("ج")) {
      valid = false;
      return valid;
    } 
    if (prefcalss.equals("C2") && type.indexOf("أمر") != -1) {
      valid = false;
      return valid;
    } 
    if (!suffcalss.equals("C1") && type.indexOf("مجهول") != -1) {
      valid = false;
      return valid;
    } 
    if (suffcalss.equals("V2") && type.indexOf("أمر") != -1) {
      valid = false;
      return valid;
    } 
    if (suffcalss.equals("V3") && type.indexOf("أمر") != -1) {
      valid = false;
      return valid;
    } 
    if (suffcalss.equals("V4") && ncg.indexOf("6") == -1) {
      valid = false;
      return valid;
    } 
    if (ArabMethod.notCompatible(normalizedWord, vowledWord, segment, false)) {
      valid = false;
      return valid;
    } 
    return valid;
  }
  
  public String[] Interpret(Segment seg, String root, String type, String ncg, String cas, String aug, String trans) {
    String[] result = new String[7];
    String priority = "2";
    String prefix1 = "";
    String suffix1 = "";
    String prefix = "";
    String suffix = "";
    if (seg.getProclitic().getVoweledform().equals("")) {
      prefix1 = "#";
      result[5] = "#";
    } else {
      prefix1 = seg.getProclitic().getVoweledform() + ": " + seg.getProclitic().getDesc();
    } 
    if (seg.getEnclitic().getVoweledform().equals("")) {
      suffix1 = "#";
      result[6] = "#";
    } else {
      suffix1 = seg.getEnclitic().getVoweledform() + ": " + seg.getEnclitic().getDesc();
      result[6] = seg.getEnclitic().getVoweledform();
    } 
    if (Settings.typechoice) {
      String[] Type_Priority = type.split(";");
      result[1] = Type_Priority[0];
      priority = priority + Type_Priority[1];
    } 
    String pos = "";
    if (root.length() == 3) {
      pos = pos + "ثلاثي";
    } else {
      pos = pos + "رباعي";
    } 
    if (aug.equals("جر")) {
      pos = pos + " مجرد";
    } else {
      pos = pos + " مزيد";
    } 
    if (cas.equals("ج")) {
      pos = pos + " مجزوم";
      priority = priority + "3";
    } else if (cas.equals("ر")) {
      pos = pos + " مرفوع";
      priority = priority + "1";
    } else if (cas.equals("ن")) {
      pos = pos + " منصوب";
      priority = priority + "2";
    } 
    pos = pos + " " + getNcgVerbs(Integer.parseInt(ncg));
    if (trans.equals("ل")) {
      pos = pos + " لازم";
      priority = priority + "1";
    } else {
      if (trans.equals("م")) {
        pos = pos + " متعد";
        priority = priority + "2";
      } else {
        pos = pos + " متعد ولازم";
      } 
      priority = priority + "3";
    } 
    prefix = getValuePrefix(type, ncg);
    suffix = getValueSuffix(type, ncg, cas);
    result[2] = pos;
    result[4] = priority.toString();
    if (!prefix.equals("")) {
      if (prefix1.equals("#")) {
        result[0] = prefix;
      } else {
        result[0] = prefix1 + " + " + prefix;
      } 
    } else {
      result[0] = prefix1;
    } 
    if (!suffix.equals("")) {
      if (suffix1.equals("#")) {
        result[3] = suffix;
      } else {
        result[3] = suffix + " + " + suffix1;
      } 
    } else {
      result[3] = suffix1;
    } 
    return result;
  }
  
  public String getTypePriorityValue(String type) {
    if (type.equals("مم"))
      return "11"; 
    if (type.equals("مج"))
      return "12"; 
    if (type.equals("ضم"))
      return "21"; 
    if (type.equals("ضءم"))
      return "21"; 
    if (type.equals("ضج"))
      return "22"; 
    if (type.equals("ضءج"))
      return "22"; 
    if (type.equals("أ"))
      return "3"; 
    if (type.equals("أء"))
      return "3"; 
    return "3";
  }
  
  public String getNcgVerbs(int ncg) {
    switch (ncg) {
      case 1:
        return "مسند إلى المتكلم_أنا،";
      case 2:
        return "مسند إلى المتكلمين_نحن،";
      case 3:
        return "مسند إلى المخَاطب_أنتَ،";
      case 4:
        return "مسند إلى المخاطبة_أنتِ،";
      case 5:
        return "مسند إلى المخاطبَين_أنتما،";
      case 6:
        return "مسند إلى المخاطبِين_أنتم،";
      case 7:
        return "مسند إلى المخاطَبات_أنتن،";
      case 8:
        return "مسند إلى الغائب_هو،";
      case 9:
        return "مسند إلى الغائبَة_هي،";
      case 10:
        return "مسند إلى الغائبين_هما،";
      case 11:
        return "مسند إلى الغائبتَين_هما،";
      case 12:
        return "مسند إلى الغائبين_هم،";
      case 13:
        return "مسند إلى الغائبات_هن،";
    } 
    return "";
  }
  
  public String getValuePrefix(String type, String ncg) {
    int incg = Integer.parseInt(ncg);
    if (type.equals("فعل مضارع مبني للمعلوم") || type.equals("فعل مضارع مبني للمجهول") || type.equals("فعل مضارع مؤكد مبني للمجهول") || type.equals("فعل مضارع مؤكد مبني للمعلوم")) {
      switch (incg) {
        case 1:
          return "أ: حرف المضارعة";
        case 2:
          return "ن: حرف المضارعة";
        case 3:
          return "ت: حرف المضارعة";
        case 4:
          return "ت: حرف المضارعة";
        case 5:
          return "ت: حرف المضارعة";
        case 6:
          return "ت: حرف المضارعة";
        case 7:
          return "ت: حرف المضارعة";
        case 8:
          return "ي: حرف المضارعة";
        case 9:
          return "ت: تاء الغائبة";
        case 10:
          return "ي: حرف المضارعة";
        case 11:
          return "ت: حرف المضارعة";
        case 12:
          return "ي: حرف المضارعة";
        case 13:
          return "ي: حرف المضارعة";
      } 
      return "";
    } 
    return "";
  }
  
  public String getValueSuffix(String type, String ncg, String cas) {
    int incg = Integer.parseInt(ncg);
    if (type.equals("فعل أمر")) {
      switch (incg) {
        case 4:
          return "ي: ياء المخاطبة";
        case 5:
          return "ا: ألف المثنى";
        case 6:
          return "وا: واو الجماعة";
        case 7:
          return "ن: نون النسوة";
      } 
      return "";
    } 
    if (type.equals("فعل أمر مؤكد")) {
      switch (incg) {
        case 3:
          return "ن: نون التوكيد";
        case 4:
          return "ن: نون التوكيد";
        case 5:
          return "ن: نون التوكيد";
        case 6:
          return "ن: نون التوكيد";
        case 7:
          return "ن: نون التوكيد";
      } 
      return "";
    } 
    if (type.equals("فعل ماض مبني للمجهول") || type.equals("فعل ماض مبني للمعلوم")) {
      switch (incg) {
        case 1:
          return "ت: تاء المتكلم";
        case 2:
          return "نا: نون المتكلمين";
        case 3:
          return "ت: تاء المخاطب";
        case 4:
          return "ت: تاء المخاطبة";
        case 5:
          return "تما: تاء المخاطبين";
        case 6:
          return "تم: تاء المخاطبين";
        case 7:
          return "تن: تاء المخاطبات";
        case 9:
          return "ت: تاء التأنيث الساكنة";
        case 10:
          return "ا:ألف الاثنين";
        case 11:
          return "تا: تاء التأنيت وألف الاثنين";
        case 12:
          return "وا: واو الجماعة";
        case 13:
          return "ن: نون النسوة";
      } 
      return "";
    } 
    if (type.equals("فعل مضارع مبني للمعلوم") || type.equals("فعل مضارع مبني للمجهول")) {
      switch (incg) {
        case 4:
          return (cas.equals("ن") || cas.equals("ج")) ? "ي: ياء المخاطبة" : "ين: ياء المخاطبة والنون علامة الرفع";
        case 5:
          return (cas.equals("ن") || cas.equals("ج")) ? "ا: ألف المثنى" : "ان: ألف المثنى والنون علامة الرفع";
        case 6:
          return (cas.equals("ن") || cas.equals("ج")) ? "وا: واو الجماعة" : "ون: واو الجماعة والنون علامة الرفع";
        case 7:
          return "ن: نون النسوة";
        case 10:
          return (cas.equals("ن") || cas.equals("ج")) ? "ا: ألف المثنى" : "ان: ألف المثنى والنون علامة الرفع";
        case 11:
          return (cas.equals("ن") || cas.equals("ج")) ? "ا: ألف المثنى" : "ان: ألف المثنى والنون علامة الرفع";
        case 12:
          return (cas.equals("ن") || cas.equals("ج")) ? "وا: واو الجماعة" : "ون: واو الجماعة والنون علامة الرفع";
        case 13:
          return "ن: نون النسوة";
      } 
      return "";
    } 
    if (type.equals("فعل مضارع مؤكد مبني للمجهول") || type.equals("فعل مضارع مؤكد مبني للمعلوم")) {
      switch (incg) {
        case 1:
          return "ن: نون التوكيد";
        case 2:
          return "ن: نون التوكيد";
        case 3:
          return "ن: نون التوكيد";
        case 4:
          return "ن: نون التوكيد";
        case 5:
          return "ن: نون التوكيد";
        case 6:
          return "ن: نون التوكيد";
        case 7:
          return "ن: نون التوكيد";
        case 8:
          return "ن: نون التوكيد";
        case 9:
          return "ن: نون التوكيد";
        case 10:
          return "ن: نون التوكيد";
        case 11:
          return "ن: نون التوكيد";
        case 12:
          return "ن: نون التوكيد";
        case 13:
          return "ن: نون التوكيد";
      } 
      return "";
    } 
    return "";
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
