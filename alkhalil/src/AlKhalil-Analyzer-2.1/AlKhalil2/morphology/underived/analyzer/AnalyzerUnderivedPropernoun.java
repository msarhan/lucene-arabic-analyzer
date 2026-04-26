package AlKhalil2.morphology.underived.analyzer;

import AlKhalil2.morphology.result.model.Result;
import AlKhalil2.morphology.underived.analyzer.factory.AnalyzerUnderivedFactory;
import AlKhalil2.morphology.underived.model.Propernoun;
import AlKhalil2.stemmer.model.Segment;
import AlKhalil2.util.ArabMethod;
import AlKhalil2.util.Settings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AnalyzerUnderivedPropernoun extends AnalyzerUnderivedFactory {
  public List analyzedSegment(String normalizedWord, String unvoweledWord, Segment segment) {
    if (this.unvoweled.isEmpty())
      this.unvoweled.addUnvoweledForm(); 
    List<Result> result = new ArrayList();
    String stem = segment.getStem();
    if (this.unvoweled.containsVal(stem) && segment.getProclitic().getClasse().indexOf("C1") != -1 && segment.getEnclitic().getClasse().indexOf("C1") != -1) {
      if (this.voweled.isEmpty())
        this.voweled.addVoweledForm(); 
      String[] lpn = this.unvoweled.getUnvoweledFormValue(segment.getStem()).split(" ");
      List listPN = new ArrayList();
      listPN.addAll(Arrays.asList(lpn));
      Iterator<String> it_pn = listPN.iterator();
      while (it_pn.hasNext()) {
        int Ipn = Integer.parseInt(it_pn.next());
        Propernoun pn = new Propernoun();
        boolean valid = false;
        String vowledWord = "";
        String pf = segment.getProclitic().getVoweledform();
        String sf = segment.getEnclitic().getVoweledform();
        String pfNoDec = segment.getProclitic().getVoweledform();
        String sfNoDec = segment.getEnclitic().getVoweledform();
        if (Ipn < this.voweled.size()) {
          pn = (Propernoun)this.voweled.getVoweledFormValue(Ipn);
          if (segment.getProclitic().getClasse().equals("N4") && segment.getProclitic().getUnvoweledform().endsWith("ل") && pn.getVoweledform().startsWith("ا")) {
            vowledWord = pf + pn.getVoweledform().substring(1) + sf;
          } else {
            vowledWord = pf + pn.getVoweledform() + sf;
          } 
          valid = true;
        } 
        String lemme = pn.getVoweledform();
        if (valid && !ArabMethod.notCompatible(normalizedWord, vowledWord, segment, false)) {
          if (!Settings.prefchoice || pf.equals("")) {
            pf = "#";
            pfNoDec = "#";
          } else {
            pf = pf + " : " + segment.getProclitic().getDesc();
          } 
          if (!Settings.suffixchoice || sf.equals("")) {
            sf = "#";
            sfNoDec = "#";
          } else {
            sf = sf + " : " + segment.getEnclitic().getDesc();
          } 
          String type = "";
          if (Settings.typechoice) {
            if (this.type.isEmpty())
              this.type.addAllType(); 
            type = this.type.getTypeValue(Integer.parseInt(pn.getType()));
          } else {
            type = "#";
          } 
          result.add(new Result(vowledWord, pf, pfNoDec, stem, type, "#", "#", lemme, "#", "#", "#", sf, sfNoDec, "102"));
        } 
      } 
    } 
    return result;
  }
}
