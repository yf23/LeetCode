/*
    Heaters

    Winter is coming! Your first job during the contest is to design a standard heater
    with fixed warm radius to warm all the houses.

    Now, you are given positions of houses and heaters on a horizontal line,
    find out minimum radius of heaters so that all houses could be covered by those heaters.

    So, your input will be the positions of houses and heaters seperately,
    and your expected output will be the minimum radius standard of heaters.

    Note:
    Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
    Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
    As long as a house is in the heaters' warm radius range, it can be warmed.
    All the heaters follow your radius standard and the warm radius will the same.

    Example 1:
    Input: [1,2,3],[2]
    Output: 1
    Explanation: The only heater was placed in the position 2,
    and if we use the radius 1 standard, then all the houses can be warmed.

    Example 2:
    Input: [1,2,3,4],[1,4]
    Output: 1
    Explanation: The two heater was placed in the position 1 and 4.
    We need to use radius 1 standard, then all the houses can be warmed.

    Yu Fu, Jan/3/2017
 */
 
public class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        int rad = 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        
        int i = 0;
        int j = 0;
        
        while (j < heaters.length && i < houses.length) {
            // Make sure heater j is always at right side of house i.
            // Except the last heater.
            if (j < heaters.length - 1 && houses[i] > heaters[j]) j++;
            
            else {
                int disToPrevHeater = 0, disToNextHeater = 0;
                
                // House i is at right of the last heater.
                // The distance to the next heater is infinity.
                if (houses[i] > heaters[heaters.length - 1]) {
                    disToPrevHeater = houses[i] - heaters[j];
                    disToNextHeater = Integer.MAX_VALUE;
                // House i is at left of the first heater.
                // The distance to the previous heater is infinity.
                } else if (j == 0) {
                    disToPrevHeater = Integer.MAX_VALUE;
                    disToNextHeater = heaters[0] - houses[i];
                // House i is between heater j and j - 1.
                } else {
                    disToPrevHeater = houses[i] - heaters[j - 1];
                    disToNextHeater = heaters[j] - houses[i];
                }
                
                // Update radius.
                rad = Math.max(rad, Math.min(disToPrevHeater, disToNextHeater));
                i++;                
            }
        }
        
        return rad;
    }
}