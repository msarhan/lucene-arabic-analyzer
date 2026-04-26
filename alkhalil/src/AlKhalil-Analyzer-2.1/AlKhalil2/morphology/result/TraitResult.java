package AlKhalil2.morphology.result;

import AlKhalil2.morphology.result.model.Result;
import AlKhalil2.util.Settings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TraitResult {
  private Result result = new Result();
  
  public Result getResult() {
    return this.result;
  }
  
  public void setResult(Result result) {
    this.result = result;
  }
  
  public List listResult(String voweledWord, String prefix, String stem, String wordType, String wordDiacPattern, String wordCanPattern, String lemme, String lemmePattern, String wordRoot, String pos, String suffix) {
    List<String> result = new ArrayList();
    if (Settings.vowchoice)
      result.add(voweledWord); 
    if (Settings.prefchoice)
      result.add(prefix); 
    if (Settings.stemchoice)
      result.add(stem); 
    if (Settings.typechoice)
      result.add(wordType); 
    if (Settings.diacpatternchoice)
      result.add(wordDiacPattern); 
    if (Settings.patternchoice)
      result.add(wordCanPattern); 
    if (Settings.lemmechoice)
      result.add(lemme); 
    if (Settings.patternlemmechoice)
      result.add(lemmePattern); 
    if (Settings.rootchoice)
      result.add(wordRoot); 
    if (Settings.poschoice)
      result.add(pos); 
    if (Settings.suffixchoice)
      result.add(suffix); 
    return result;
  }
  
  public List nOcurrenceResult(List result) {
    Set<String> hashTemp = new HashSet();
    List<Result> resultat = new ArrayList();
    Iterator<Result> it_result = result.iterator();
    while (it_result.hasNext()) {
      setResult(it_result.next());
      if (!hashTemp.contains(toString())) {
        hashTemp.add(toString());
        resultat.add(this.result);
      } 
    } 
    return resultat;
  }
  
  public void print(Result r) {
    setResult(r);
  }
  
  public static List sortResult_(List<Result> result) {
    if (!result.isEmpty())
      for (int i = 0; i < result.size() - 1; i++) {
        Result ri = result.get(i);
        int ind = i;
        Result temp = ri;
        for (int j = i; j < result.size(); j++) {
          Result rj = result.get(j);
          if (rj.getPriority().compareTo(temp.getPriority()) < 0) {
            ind = j;
            temp = rj;
          } 
        } 
        result.add(i, temp);
        result.remove(i + 1);
        result.add(ind, ri);
        result.remove(ind + 1);
      }  
    return result;
  }
  
  public List sortResultss(List<Result> result) {
    List<String> lRes = new ArrayList();
    int i = 0;
    Iterator<Result> it = result.iterator();
    while (it.hasNext()) {
      Result st = it.next();
      lRes.add(st.getPriority() + "_" + i);
      i++;
    } 
    Collections.sort(lRes);
    List<Result> resul = new ArrayList();
    Iterator<String> iit = lRes.iterator();
    while (it.hasNext()) {
      String st = iit.next();
      String[] str = st.split("_");
      int ind = Integer.parseInt(str[1]);
      resul.add(result.get(ind));
    } 
    return nOcurrenceResult(result);
  }
  
  public String toString() {
    String str = "";
    str = str + (Settings.typechoice ? (this.result.getType() + ";") : "");
    str = str + (Settings.vowchoice ? (this.result.getVoweledWord() + ";") : "");
    str = str + (Settings.prefchoice ? (this.result.getPrefix() + ";" + this.result.getPrefNoDec() + ";") : "");
    str = str + (Settings.stemchoice ? (this.result.getStem() + ";") : "");
    str = str + (Settings.patternchoice ? (this.result.getPatternStem() + ";") : "");
    str = str + (Settings.lemmechoice ? (this.result.getLemma() + ";") : "");
    str = str + (Settings.patternlemmechoice ? (this.result.getPatternLemma() + ";") : "");
    str = str + (Settings.rootchoice ? (this.result.getRoot() + ";") : "");
    str = str + (Settings.typechoice ? (this.result.getType() + ";") : "");
    str = str + (Settings.poschoice ? (this.result.getPostag() + ";") : "");
    str = str + (Settings.suffixchoice ? (this.result.getSuffix() + ";" + this.result.getSufNoDec() + ";") : "");
    return str;
  }
  
  public Result toResult(String str) {
    Result result = new Result();
    String[] Str = str.split(";");
    int i = 0;
    result.setPriority("");
    if (Settings.typechoice) {
      result.setType(Str[i]);
      i++;
    } 
    if (Settings.vowchoice) {
      result.setVoweledWord(Str[i]);
      i++;
    } 
    if (Settings.prefchoice) {
      result.setPrefix(Str[i]);
      i++;
      result.setPrefNoDec(Str[i]);
      i++;
    } 
    if (Settings.stemchoice) {
      result.setStem(Str[i]);
      i++;
    } 
    if (Settings.patternchoice) {
      result.setPatternStem(Str[i]);
      i++;
    } 
    if (Settings.lemmechoice) {
      result.setLemma(Str[i]);
      i++;
    } 
    if (Settings.patternlemmechoice) {
      result.setPatternLemma(Str[i]);
      i++;
    } 
    if (Settings.rootchoice) {
      result.setRoot(Str[i]);
      i++;
    } 
    if (Settings.typechoice) {
      result.setType(Str[i]);
      i++;
    } 
    if (Settings.poschoice) {
      result.setPostag(Str[i]);
      i++;
    } 
    if (Settings.suffixchoice) {
      result.setSuffix(Str[i]);
      i++;
      result.setSufNoDec(Str[i]);
      i++;
    } 
    return result;
  }
  
  public List sorts(List<Result> result) {
    Set<String> strRes = new HashSet<String>();
    Iterator<Result> it = result.iterator();
    while (it.hasNext()) {
      this.result = it.next();
      strRes.add(toString());
    } 
    List<String> lres = new ArrayList();
    lres.addAll(strRes);
    Collections.sort(lres);
    List<Result> Res = new ArrayList();
    Iterator<String> itt = lres.iterator();
    while (itt.hasNext()) {
      String st = itt.next();
      Result res = toResult(st);
      Res.add(res);
    } 
    return Res;
  }
}
