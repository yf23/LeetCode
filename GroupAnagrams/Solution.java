/*
    Group Anagrams

    Given an array of strings, group anagrams together.

    For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
    Return:

    [
      ["ate", "eat","tea"],
      ["nat","tan"],
      ["bat"]
    ]

    Note:
    For the return value, each inner list's elements must follow the lexicographic order.
    All inputs will be in lower-case.

    Use HashMap. Keys are alphabetically sorted characters of words as String.
    Values are list of anagrams of such characters in key.
    
    Yu Fu, Oct 24 2015
*/

import java.util.*;

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>();
        
        for (String word : strs) {
            
            // Order characters in the word as String
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String orderedWord = new String(chars);

            // Build or Add the word to corresponding key-value pairs
            if (myMap.containsKey(orderedWord)) {
                myMap.get(orderedWord).add(word);
            } else {
                ArrayList<String> anaList = new ArrayList<String>();
                anaList.add(word);
                myMap.put(orderedWord, anaList);
            }
        }
        
        // Output
        List<List<String>> result = new ArrayList<List<String>>();
        for (String key : myMap.keySet()) {
            Collections.sort(myMap.get(key));
            result.add(myMap.get(key));
        }
        
        return result;
    }
}