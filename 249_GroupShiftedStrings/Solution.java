/*
    Group Shifted Strings

    Given a string, we can "shift" each of its letter to its successive letter,
    for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

    "abc" -> "bcd" -> ... -> "xyz"
    Given a list of strings which contains only lowercase alphabets,
    group all strings that belong to the same shifting sequence.

    For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
    A solution is:
    [
      ["abc","bcd","xyz"],
      ["az","ba"],
      ["acef"],
      ["a","z"]
    ]

    Yu Fu, 09/29/2016
 */

public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        HashMap<String, List<String>> mp = new HashMap<String, List<String>>();
        for (String s : strings) {
            // Shift all chars in the String so that first char is 'a'.
            // Use result as key.
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < s.length(); i++) {
                int cur = s.charAt(i) - s.charAt(0);
                if (cur > 0) cur %= 26;
                else if (cur < 0) {
                    cur = 26 + (cur % 26);
                }
                sb.append((char) (cur + 'a'));
            }
            
            // Put String into HashMap with corresponding key.
            String key = sb.toString();
            if (!mp.containsKey(key)) {
                mp.put(key, new ArrayList<String>());
            }
            mp.get(key).add(s);
        }
        
        // Return value list as result.
        List<List<String>> result = new ArrayList<List<String>>();
        for (String key : mp.keySet()) {
            result.add(mp.get(key));
        }
        return result;       
    }
}