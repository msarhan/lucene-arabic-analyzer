package AlKhalil2.util;

public class CreatHamza {
  public static String correctHamza(String word) {
    word = word.replaceAll("[أإئؤ]", "ء");
    String wrd = "";
    word = startHamza(word);
    String wrdD = word.replaceAll("[ًٌٍَُِّْ]", "");
    int len = word.length();
    int ip = 0;
    if (word.startsWith("اءْ"))
      word = "ائْ" + word.substring(3); 
    for (int i = 0; i < len; i++) {
      if (word.charAt(i) == 'ء') {
        if (ip + 1 == wrdD.length()) {
          wrd = wrd + endHamza(word, i, len);
        } else if (isNibraHamza(word, i, len)) {
          wrd = wrd + "ئ";
        } else if (isWawHamza(word, i, len)) {
          wrd = wrd + "ؤ";
        } else if (isAlifHamza(word, i, len)) {
          if (i + 2 < len && word.charAt(i + 1) == 'َ' && word.charAt(i + 2) == 'ا') {
            wrd = wrd + "آ";
            i += 2;
          } else if (i + 3 < len && word.charAt(i + 1) == 'ّ' && word.charAt(i + 2) == 'َ' && word.charAt(i + 3) == 'ا') {
            wrd = wrd + "آَّ";
            i += 3;
          } else if (i + 3 < len && word.charAt(i + 1) == 'َ' && word.charAt(i + 2) == 'ء' && word.charAt(i + 3) == 'ْ') {
            wrd = wrd + "آ";
            i += 3;
          } else {
            wrd = wrd + "أ";
          } 
        } else {
          wrd = wrd + word.charAt(i);
        } 
      } else {
        wrd = wrd + word.charAt(i);
      } 
      if (!ArabCharUtil.isDiacritic(word.charAt(i)))
        ip++; 
    } 
    return wrd;
  }
  
  public static String startHamza(String word) {
    String wordF = "";
    if (word.length() > 1 && word.charAt(0) == 'ء') {
      wordF = wordF + ((word.charAt(1) == 'ِ') ? 1573 : 1571);
    } else {
      wordF = wordF + word.charAt(0);
    } 
    wordF = wordF + word.substring(1);
    if (wordF.startsWith("أَا"))
      wordF = "آ" + wordF.substring(3); 
    return wordF;
  }
  
  public static String endHamza(String word, int pos, int len) {
    if (pos > 1 && word.charAt(pos - 1) == 'ِ')
      return "ئ"; 
    if (pos > 1 && word.charAt(pos - 1) == 'َ')
      return "أ"; 
    if (pos > 3 && word.charAt(pos - 1) == 'ُ' && word.charAt(pos - 2) == 'ّ' && word.charAt(pos - 3) == 'و')
      return "ء"; 
    if (pos > 1 && word.charAt(pos - 1) == 'ُ')
      return "ؤ"; 
    return "ء";
  }
  
  private static boolean isNibraHamza(String word, int pos, int len) {
    if (pos > 0 && word.charAt(pos - 1) == 'ِ')
      return true; 
    if ((pos > 0 && word.charAt(pos - 1) == 'ي') || (pos > 1 && word.charAt(pos - 1) == 'ْ' && word.charAt(pos - 2) == 'ي'))
      return true; 
    if (pos > 1 && word.charAt(pos - 1) == 'ْ' && isNoRelatifCaracter(word.charAt(pos - 2)) && pos + 2 < len && (word.charAt(pos + 1) == 'َ' || word.charAt(pos + 1) == 'ً') && word.charAt(pos + 2) == 'ا')
      return true; 
    if (pos > 0 && (word.charAt(pos - 1) == 'ا' || word.charAt(pos - 1) == 'ي' || word.charAt(pos - 1) == 'و') && ((pos + 1 < len && word.charAt(pos + 1) == 'ِ') || (pos + 2 < len && word.charAt(pos + 2) == 'ِ' && word.charAt(pos + 1) == 'ّ')))
      return true; 
    return (pos > 0 && (word.charAt(pos - 1) == 'ْ' || word.charAt(pos - 1) == 'َ' || word.charAt(pos - 1) == 'ُ') && ((pos + 1 < len && word.charAt(pos + 1) == 'ِ') || (pos + 2 < len && word.charAt(pos + 2) == 'ِ' && word.charAt(pos + 1) == 'ّ')));
  }
  
  private static boolean isWawHamza(String word, int pos, int len) {
    if (pos > 0 && (word.charAt(pos - 1) == 'ا' || word.charAt(pos - 1) == 'ْ') && word.charAt(pos - 1) != 'ي' && word.charAt(pos - 1) != 'و') {
      if (pos + 1 < len && word.charAt(pos + 1) == 'ُ') {
        if (pos + 2 < len)
          return (word.charAt(pos + 2) != 'و'); 
        return false;
      } 
      if (pos + 2 < len && word.charAt(pos + 2) == 'ُ' && word.charAt(pos + 1) == 'ّ') {
        if (pos + 3 < len)
          return (word.charAt(pos + 3) != 'و'); 
        return false;
      } 
    } 
    if (pos > 0 && word.charAt(pos - 1) == 'َ' && ((pos + 1 < len && word.charAt(pos + 1) == 'ُ') || (pos + 2 < len && word.charAt(pos + 2) == 'ُ' && word.charAt(pos + 1) == 'ّ')))
      return true; 
    return (pos > 0 && word.charAt(pos - 1) == 'ُ' && ((pos + 1 < len && word.charAt(pos + 1) != 'ِ') || (pos + 2 < len && word.charAt(pos + 2) != 'ِ' && word.charAt(pos + 1) == 'ّ'))) ? ((pos <= 2 || word.charAt(pos - 2) != 'ّ' || word.charAt(pos - 3) != 'و')) : false;
  }
  
  private static boolean isAlifHamza(String word, int pos, int len) {
    if (pos > 0 && word.charAt(pos - 1) == 'َ' && ((pos + 1 < len && word.charAt(pos + 1) == 'ْ') || (pos + 1 < len && word.charAt(pos + 1) == 'َ') || (pos + 2 < len && word.charAt(pos + 2) == 'َ' && word.charAt(pos + 1) == 'ّ')))
      return true; 
    if (pos > 1 && word.charAt(pos - 1) == 'ْ' && word.charAt(pos - 2) == 'و' && pos + 1 < len && word.charAt(pos + 1) == 'َ')
      return false; 
    if (pos > 0 && word.charAt(pos - 1) == 'ْ') {
      if (pos + 2 < len && word.charAt(pos + 1) == 'ً' && word.charAt(pos + 2) == 'ا')
        return false; 
      if (pos + 4 < len && word.charAt(pos + 1) == 'َ' && word.charAt(pos + 2) == 'ا' && word.charAt(pos + 3) == 'ن' && word.charAt(pos + 4) == 'ِ')
        return false; 
      if ((pos + 1 < len && word.charAt(pos + 1) == 'َ') || (pos + 2 < len && word.charAt(pos + 1) == 'ّ' && word.charAt(pos + 2) == 'َ'))
        return true; 
    } 
    return false;
  }
  
  private static boolean isNoRelatifCaracter(char caracter) {
    switch (caracter) {
      case 'د':
      case 'ذ':
      case 'ر':
      case 'ز':
      case 'و':
        return false;
    } 
    return true;
  }
  
  public static String correctStemHamza(String str) {
    int len = str.length();
    String res = "";
    for (int i = 0; i < len; i++) {
      if (str.charAt(i) == 'ء' || str.charAt(i) == 'أ') {
        if (i + 1 < len && str.charAt(i + 1) == 'ا') {
          if (i - 1 >= 0 && str.charAt(i - 1) == 'ا') {
            res = res + str.charAt(i);
          } else {
            res = res + "آ";
            i++;
          } 
        } else {
          res = res + str.charAt(i);
        } 
      } else {
        res = res + str.charAt(i);
      } 
    } 
    return res;
  }
}
