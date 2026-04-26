package AlKhalil2.morphology.underived.analyzer;

import AlKhalil2.morphology.result.model.Result;
import AlKhalil2.morphology.underived.analyzer.factory.AnalyzerUnderivedFactory;
import AlKhalil2.morphology.underived.model.Toolwords;
import AlKhalil2.stemmer.clitic.model.Clitic;
import AlKhalil2.stemmer.model.Segment;
import AlKhalil2.util.ArabMethod;
import AlKhalil2.util.CreatHamza;
import AlKhalil2.util.Settings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AnalyzerUnderivedToolwords extends AnalyzerUnderivedFactory {
  public List analyzedSegment(String normalizedWord, String unvoweledWord, Segment segment) {
    List<Result> result = new ArrayList();
    Clitic s = segment.getEnclitic();
    Clitic p = segment.getProclitic();
    List<Toolwords> toolwordlist = possibleToolWords(segment);
    Iterator<Toolwords> it_TW = toolwordlist.iterator();
    while (it_TW.hasNext()) {
      Toolwords tw = it_TW.next();
      if (s.getUnvoweledform().equals("ه")) {
        if (tw.getVoweledform().endsWith("ي") || tw.getVoweledform().endsWith("يْ") || tw.getVoweledform().endsWith("ِ")) {
          s.setVoweledform("هِ");
        } else {
          s.setVoweledform("هُ");
        } 
      } else if (s.getUnvoweledform().equals("هما")) {
        if (tw.getVoweledform().endsWith("ي") || tw.getVoweledform().endsWith("يْ") || tw.getVoweledform().endsWith("ِ")) {
          s.setVoweledform("هِمَا");
        } else {
          s.setVoweledform("هُمَا");
        } 
      } else if (s.getUnvoweledform().equals("هم")) {
        if (tw.getVoweledform().endsWith("ي") || tw.getVoweledform().endsWith("يْ") || tw.getVoweledform().endsWith("ِ")) {
          s.setVoweledform("هِمْ");
        } else {
          s.setVoweledform("هُمْ");
        } 
      } else if (s.getUnvoweledform().equals("هن")) {
        if (tw.getVoweledform().endsWith("ي") || tw.getVoweledform().endsWith("يْ") || tw.getVoweledform().endsWith("ِ")) {
          s.setVoweledform("هِنَّ");
        } else {
          s.setVoweledform("هُنَّ");
        } 
      } 
      String lemme = tw.getVoweledform();
      String vowledWord = p.getVoweledform() + CreatHamza.correctHamza(tw.getVoweledform() + s.getVoweledform());
      String pf = p.getVoweledform();
      String sf = s.getVoweledform();
      String pfNoDec = p.getVoweledform();
      String sfNoDec = s.getVoweledform();
      if (!Settings.prefchoice || p.getVoweledform().equals("")) {
        pf = "#";
        pfNoDec = "#";
      } else {
        pf = pf + " : " + p.getDesc();
      } 
      if (!Settings.suffixchoice || s.getVoweledform().equals("")) {
        sf = "#";
        sfNoDec = "#";
      } else {
        sf = sf + " : " + s.getDesc();
      } 
      String type = "";
      if (Settings.typechoice) {
        if (this.type.isEmpty())
          this.type.addAllType(); 
        int id = Integer.parseInt(tw.getType());
        type = this.type.getTypeValue(id);
      } else {
        type = "#";
      } 
      if (!ArabMethod.notCompatible(normalizedWord, vowledWord)) {
        Result r = new Result(vowledWord, pf, pfNoDec, segment.getStem(), type, "#", "#", lemme, "#", "#", "#", sf, sfNoDec, tw.getPriority());
        result.add(r);
      } 
    } 
    return result;
  }
  
  public List possibleToolWords(Segment segment) {
    List<Toolwords> result = new ArrayList();
    if (this.unvoweled.isEmpty())
      this.unvoweled.addUnvoweledForm(); 
    String stem = segment.getStem();
    if (this.unvoweled.containsVal(stem)) {
      if (this.voweled.isEmpty())
        this.voweled.addVoweledForm(); 
      List<String> TW = Arrays.asList(this.unvoweled.getUnvoweledFormValue(stem).split(" "));
      Iterator<String> it_TW = TW.iterator();
      while (it_TW.hasNext()) {
        int id = Integer.parseInt(it_TW.next());
        Toolwords tw = (Toolwords)this.voweled.getVoweledFormValue(id);
        if (tw.getPrefixeclass().indexOf(segment.getProclitic().getClasse()) != -1 && tw.getSuffixeclass().indexOf(segment.getEnclitic().getClasse()) != -1)
          result.add(tw); 
      } 
    } 
    return result;
  }
}
