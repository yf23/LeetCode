/*
    Two Sum III - Data structure design

    Design and implement a TwoSum class.
    It should support the following operations: add and find.

    add - Add the number to an internal data structure.
    find - Find if there exists any pair of numbers which sum is equal to the value.

    For example,
    add(1); add(3); add(5);
    find(4) -> true
    find(7) -> false

    Solution:
    Use a HashMap to store number and frequency.
    When try to find a number, for each key in HashMap:
    If (number - key) is also a key, return true.
    If (number - key) == key, check if key appeared more than once.

    Yu Fu, 09/29/2016
 */

// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);
public class TwoSum {
    HashMap<Integer, Integer> mp = new HashMap<Integer, Integer>();
    
    // Add the number to an internal data structure.
    public void add(int number) {
        // Maintain the HashMap of numbers and frequency.
        if (!mp.containsKey(number)) {
            mp.put(number, 1);
        } else {
            mp.put(number, mp.get(number) + 1);
        }
    }

    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
        for (int a : mp.keySet()) {
            int b = value - a;
            if (a == b && mp.get(a) > 1) return true;
            if (a != b && mp.containsKey(b)) return true;
        }
        return false;
    }
}