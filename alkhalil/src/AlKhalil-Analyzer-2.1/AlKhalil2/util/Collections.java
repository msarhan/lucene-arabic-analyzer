package AlKhalil2.util;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Collections {
  public List regroupList(List<Comparable> list) {
    java.util.Collections.sort(list);
    List<String> l_word = new LinkedList();
    ListIterator<Comparable> it = list.listIterator();
    String word = "";
    String ind = "";
    String chaine = "";
    if (it.hasNext()) {
      String[] str = ((String)it.next()).split("_");
      word = str[0];
      ind = str[1];
      chaine = word + "_" + ind;
    } 
    while (it.hasNext()) {
      String[] str = ((String)it.next()).split("_");
      if (word.equals(str[0])) {
        chaine = chaine + " " + str[1];
        continue;
      } 
      l_word.add(chaine);
      word = str[0];
      ind = str[1];
      chaine = word + "_" + ind;
    } 
    l_word.add(chaine);
    return l_word;
  }
  
  public List regroupListInteger(List<Comparable> list) {
    java.util.Collections.sort(list);
    List<String> l_word = new LinkedList();
    ListIterator<Comparable> it = list.listIterator();
    String word = "";
    int ind = 0;
    String chaine = "";
    if (it.hasNext()) {
      String[] str = ((String)it.next()).split("_");
      word = str[0];
      ind = Integer.parseInt(str[1]);
      chaine = word + "_" + ind;
    } 
    while (it.hasNext()) {
      String[] str = ((String)it.next()).split("_");
      if (word.equals(str[0])) {
        chaine = word + "_" + (ind + Integer.parseInt(str[1]));
        continue;
      } 
      l_word.add(chaine);
      word = str[0];
      ind = Integer.parseInt(str[1]);
      chaine = word + "_" + ind;
    } 
    l_word.add(chaine);
    return l_word;
  }
  
  public static Map<String, Integer> addStringToMap(Map<String, Integer> iMap, String str) {
    if (iMap.containsKey(str)) {
      iMap.put(str, Integer.valueOf(((Integer)iMap.get(str)).intValue() + 1));
    } else {
      iMap.put(str, Integer.valueOf(1));
    } 
    return iMap;
  }
}
