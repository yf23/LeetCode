/* 
    Encode and Decode Strings

    Design an algorithm to encode a list of strings to a string.
    The encoded string is then sent over the network and is decoded back to the original list of strings.

    Machine 1 (sender) has the function:
    string encode(vector<string> strs) {
      // ... your code
      return encoded_string;
    }

    Machine 2 (receiver) has the function:
    vector<string> decode(string s) {
      //... your code
      return strs;
    }

    So Machine 1 does:
    string encoded_string = encode(strs);

    and Machine 2 does:
    vector<string> strs2 = decode(encoded_string);

    strs2 in Machine 2 should be the same as strs in Machine 1.

    Implement the encode and decode methods.

    Note:
    The string may contain any possible characters out of 256 valid ascii characters.
    Your algorithm should be generalized enough to work on any possible characters.
    Do not use class member/global/static variables to store states.
    Your encode and decode algorithms should be stateless.
    Do not rely on any library method such as eval or serialize methods.
    You should implement your own encode/decode algorithm.

    Solution:
    For String s, convert it to a sequnce of interger such that:
    First integer is the length of s.
    Following integers are ACSII code of each character.
    Example: "abcda"   ->   5, 61, 62, 63, 64, 61
    When decoding, first read an integer as length l,
    then use a for loop to read l integers and convert them back to char.

    Yu Fu, 10/01/2016
 */

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
public class Codec {
    public final String SEP = ",";   // Set the seperator as comma.
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        if (strs.size() == 0) return "";   // If list is empty, return empty String.
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            // Append length
            sb.append(s.length());
            sb.append(SEP);
            // Append each character as integer
            for (int i = 0; i < s.length(); i++) {
                sb.append((int) s.charAt(i));
                sb.append(SEP);
            }
        }
        // Delete last comma
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new ArrayList<String>();
        if (s.length() == 0) return result;
        // Split by comma
        String[] entries = s.split(SEP);

        int i = 0;
        while (i < entries.length) {
            // Read length n
            int len = Integer.parseInt(entries[i]);
            StringBuilder sb = new StringBuilder();
            // Append n following integers as char into StringBuilder 
            for (int j = 1; j <= len; j++) {
                int code = Integer.parseInt(entries[i + j]);
                sb.append((char) code);
            }
            result.add(sb.toString());
            i = i + len + 1;    // Next String length index.
        }
        return result;
    }
}