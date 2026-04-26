package AlKhalil2.morphology.derived.analyzer.factory;

import AlKhalil2.morphology.derived.formulas.interfaces.IFormulasDerived;
import AlKhalil2.morphology.derived.formulas.model.Formulas;
import AlKhalil2.morphology.derived.postag.interfaces.IPOSTagDerived;
import AlKhalil2.morphology.derived.root.interfaces.IRootDerived;
import AlKhalil2.morphology.derived.type.interfaces.ITypeDerived;
import AlKhalil2.morphology.derived.unvoweled.interfaces.IUnvoweledDerived;
import AlKhalil2.morphology.derived.unvoweled.model.Unvoweled;
import AlKhalil2.morphology.derived.voweled.interfaces.IVoweledDerived;
import AlKhalil2.stemmer.model.Segment;
import AlKhalil2.util.ArabCharUtil;
import AlKhalil2.util.ArabMethod;
import AlKhalil2.util.Settings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AnalyzerDerivedFactory {
  protected IFormulasDerived formula;

  protected IPOSTagDerived posTag;

  protected IRootDerived roots;

  protected ITypeDerived type;

  protected IUnvoweledDerived unvoweled;

  protected IVoweledDerived voweled;

  protected Map<String, Map> possiblePattern;

  public Set getPossibleRoots(String st, String rules) {
    Set<String> Root = new HashSet();
    Iterator<String> it_rules = Arrays.<String>asList(rules.split(" ")).iterator();
    while (it_rules.hasNext()) {
      String rule = it_rules.next();
      String root = "";
      char[] Rul = rule.toCharArray();
      for (char r : Rul) {
        if (ArabCharUtil.isNumeric(r) && r != '0') {
          char c = st.charAt(Integer.parseInt(r + "") - 1);
          if (ArabCharUtil.isHamza(c)) {
            root = root + 'ء';
          } else {
            root = root + c;
          }
        } else if (ArabCharUtil.isHamza(r)) {
          root = root + 'ء';
        } else {
          root = root + r;
        }
      }
      root = getRoot(root, rule);
      if (root != null)
        Root.add(root);
    }
    return Root;
  }

  public String getRoot(String root, String rule) {
    if (root.length() == 3) {
      if (this.roots.isEmptyTrilateralRootList())
        this.roots.addTrilateralRootList();
      if (this.roots.containsTrilateralRoot(root))
        return root;
    } else {
      if (this.roots.isEmptyQuadriliteralRootList())
        this.roots.addQuadriliteralRootList();
      if (this.roots.containsQuadriliteralRoot(root))
        return root;
    }
    return null;
  }

  public String idRoots(String root, int lenRoot, int lenStem) {
    if (lenRoot == 3) {
      if (!this.roots.containsTrilateralCharRoot(root.charAt(0)))
        this.roots.addTrilateralRootMap(root.charAt(0));
      return this.roots.getLenTrilateralRoot(root, lenStem);
    }
    if (!this.roots.containsQuadriliteralCharRoot(root.charAt(0)))
      this.roots.addQuadriliteralRootMap(root.charAt(0));
    return this.roots.getLenQuadriliteralRoot(root, lenStem);
  }

  public Set Intersection(Set L1, List<String> L2) {
    Set<String> result = new HashSet();
    Iterator<String> it_L2 = L2.iterator();
    while (it_L2.hasNext()) {
      String str_L2 = it_L2.next();
      if (L1.contains(str_L2))
        result.add(str_L2);
    }
    return result;
  }

  public String getInfoResults(Formulas formulas, int lenStem) {
    String strResult = "";
    if (!this.voweled.containsIdVoweledPatternDiac(lenStem))
      this.voweled.addVoweledPatternDiacMap(lenStem);
    int id = Integer.parseInt(formulas.getIdDiacPattern());
    strResult = strResult + "" + this.voweled.getVoweledPatternDiac(lenStem, id);
    if (Settings.patternchoice) {
      String[] strCanonic = formulas.getIdCanonicPattern().split("\\.");
      id = Integer.parseInt(strCanonic[1]);
      int len = Integer.parseInt(strCanonic[0]);
      if (!this.voweled.containsIdVoweledPatternCanonic(len))
        this.voweled.addVoweledPatternCanonicMap(len);
      strResult = strResult + ":" + this.voweled.getVoweledPatternCanonic(len, id);
    }
    if (Settings.lemmechoice) {
      String[] strCanonic = formulas.getIdDiacLemme().split("\\.");
      id = Integer.parseInt(strCanonic[1]);
      int len = Integer.parseInt(strCanonic[0]);
      if (!this.voweled.containsIdVoweledLemmeDiac(len))
        this.voweled.addVoweledLemmeDiacMap(len);
      strResult = strResult + ":" + this.voweled.getVoweledLemmeDiac(len, id);
    }
    if (Settings.patternlemmechoice) {
      String[] strCanonic = formulas.getIdCanonicLemme().split("\\.");
      id = Integer.parseInt(strCanonic[1]);
      int len = Integer.parseInt(strCanonic[0]);
      if (!this.voweled.containsIdVoweledLemmeCanonic(len))
        this.voweled.addVoweledLemmeCanonicMap(len);
      strResult = strResult + ":" + this.voweled.getVoweledLemmeCanonic(len, id);
    }
    if (Settings.typechoice) {
      id = Integer.parseInt(formulas.getIdType());
      if (this.type.isEmpty())
        this.type.addType();
      strResult = strResult + ":" + this.type.getType(id);
    }
    strResult = strResult + ":" + formulas.getIdPOSTag();
    return strResult;
  }

  public Map possibleSolutions(String normalizedWord, Segment segment) {
    Map<Object, Object> result = new HashMap<Object, Object>();
    boolean addResult = false;
    String stem1 = segment.getStem();
    String stem = stem1.charAt(0) + stem1.substring(1).replaceAll("[أإئؤ]", "ء");
    int lenDiac = stem.length();
    if (!this.unvoweled.containsIdUnvoweled(lenDiac))
      this.unvoweled.addUnvoweledPatternDiacMap(lenDiac);
    Iterator<Unvoweled> it_unvoweled = this.unvoweled.getUnvoweledList(lenDiac).iterator();
    while (it_unvoweled.hasNext()) {
      Unvoweled unvoweledForm = it_unvoweled.next();
      String unvoweled = unvoweledForm.getVal();
      String s_unvoweled = unvoweled;
      s_unvoweled = s_unvoweled.charAt(0) + s_unvoweled.substring(1).replaceAll("[أإئؤ]", "ء");
      if (ArabMethod.isDiacPattern(stem, s_unvoweled)) {
        Set<String> roots = getPossibleRoots(stem, unvoweledForm.getRules());
        if (!roots.isEmpty()) {
          Map<Object, Object> ROOT = new HashMap<Object, Object>();
          addResult = false;
          Iterator<String> it_roots = roots.iterator();
          while (it_roots.hasNext()) {
            String root = it_roots.next();
            int lenRoot = root.length();
            int lenStem = segment.getStem().length();
            String idRoot = idRoots(root, lenRoot, lenStem);
            if (idRoot != null && !"".equals(idRoot)) {
              if (!this.formula.containsValFormula(lenStem))
                this.formula.addFormulaList(lenStem);
              List idRoots = this.formula.getIdsDiacPattern(lenStem, idRoot);
              Set idsUnvoweled = new HashSet();
              idsUnvoweled.addAll(Arrays.asList(unvoweledForm.getIds().split(" ")));
              List res = this.formula.getInfoResult(Intersection(idsUnvoweled, idRoots), idRoot, lenStem);
              if (!res.isEmpty()) {
                ROOT.put(root, res);
                addResult = true;
              }
            }
          }
          if (addResult)
            result.put(unvoweled, ROOT);
        }
      }
    }
    this.possiblePattern.put(stem1, result);
    return result;
  }
}
