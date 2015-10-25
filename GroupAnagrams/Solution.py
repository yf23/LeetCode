'''
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

    Use dictionary. Keys are alphabetically sorted characters of words as String.
    Values are list of anagrams of such characters in key.

    Yu Fu, Oct 24 2015
'''

class Solution(object):
    def groupAnagrams(self, strs):
        """
        :type strs: List[str]
        :rtype: List[List[str]]
        """
        my_dict = {}

        for word in strs:
            # Order characters in the word as String
            word_ordered = ''.join(sorted(word))

            # Build or Add the word to corresponding key-value pairs
            if word_ordered in my_dict:
                my_dict[word_ordered].append(word)
            else:
                my_dict[word_ordered] = [word]

        # Output
        result = []
        for key in my_dict.keys():
            my_dict[key].sort()
            result.append(my_dict[key])

        return result