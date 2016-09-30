/*
    Unique Word Abbreviation

    An abbreviation of a word follows the form <first letter><number><last letter>.
    Below are some examples of word abbreviations:

    a) it                      --> it    (no abbreviation)

         1
    b) d|o|g                   --> d1g

                  1    1  1
         1---5----0----5--8
    c) i|nternationalizatio|n  --> i18n

                  1
         1---5----0
    d) l|ocalizatio|n          --> l10n

    Assume you have a dictionary and given a word,
    find whether its abbreviation is unique in the dictionary.
    A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

    Example 1: 
    Given dictionary = [ "deer", "door", "cake", "card" ]

    isUnique("dear") -> false
    isUnique("cart") -> true
    isUnique("cane") -> false
    isUnique("make") -> true

    Example 2:
    1) [“dog”]; isUnique(“dig”);   
       False, because “dig” has the same abbreviation with “dog" and “dog” is already in the dictionary.
       It’s not unique.

    2) [“dog”, “dog"]; isUnique(“dog”);  
       True, because “dog” is the only word that has “d1g” abbreviation.

    3) [“dog”, “dig”]; isUnique(“dog”);   
        False, because if we have more than one word match to the same abbreviation,
        this abbreviation will never be unique.

    Yu Fu, 09/29/2016
 */

// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");

public class ValidWordAbbr {
    HashMap<String, Set<String>> dict;

    // Abbreviation string as key.
    // Set of words as value.
    public ValidWordAbbr(String[] dictionary) {
        dict = new HashMap<String, Set<String>>();
        for (String s : dictionary) {
            String abbr = getAbbr(s);
            if (!dict.containsKey(abbr)) dict.put(abbr, new HashSet<String>());
            dict.get(abbr).add(s);
        }
    }

    // Add current word to copy of set with abbr as key.
    // If the set size is one, it's unique.
    public boolean isUnique(String word) {
        String abbr = getAbbr(word);
        if (!dict.containsKey(abbr)) return true;
        Set<String> words = new HashSet<String>(dict.get(abbr));
        words.add(word);
        return words.size() == 1;
    }
    
    // Get abbreviation of word in the form:
    // <first letter><number><last letter>
    private String getAbbr(String word) {
        if (word.length() <= 2) return word;
        StringBuffer sb = new StringBuffer();
        sb.append(word.charAt(0));
        sb.append(word.length() - 2);
        sb.append(word.charAt(word.length() - 1));
        return sb.toString();
    }
}