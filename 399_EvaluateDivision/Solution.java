/*
    Evaluate Division

    Equations are given in the format A / B = k,
    where A and B are variables represented as strings,
    and k is a real number (floating point number).
    Given some queries, return the answers. If the answer does not exist, return -1.0.

    Example:
    Given a / b = 2.0, b / c = 3.0. 
    queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
    return [6.0, 0.5, -1.0, 1.0, -1.0 ].

    The input is: vector<pair<string, string>> equations,
                  vector<double>& values,
                  vector<pair<string, string>> queries,
    where equations.size() == values.size(), and the values are positive.
    This represents the equations. Return vector<double>.

    According to the example above:
    equations = [ ["a", "b"], ["b", "c"] ],
    values = [2.0, 3.0],
    queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].

    The input is always valid.
    You may assume that evaluating the queries will result in
    no division by zero and there is no contradiction.

    Solution:
    Construct a graph where each variables string is a node,
    and if a / b = k, there is an edge a -> b with weight k.
    Also, there is an edge b -> a with weight 1/k.
    Construct the graph, and start a BFS search from each node.
    In BFS connect all possible nodes to start node. For example,

    Before:
       m      n
    a ---> b ---> c        a / b = m, b / c = n

    After:
       m*n
    a -----> c             a / c = m * n

    At the end, we will have all possible division relations
    which can be inferred from given information.

    Yu Fu, 11/29/2016
 */
public class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Set<String> nodes = new HashSet<String>();
        Map<String, Map<String, Double>> edges = new HashMap<String, Map<String, Double>>();
        
        // Construct graph
        for (int i = 0; i < equations.length; i++) {
            String a = equations[i][0];
            String b = equations[i][1];
            nodes.add(a);
            nodes.add(b);
            
            if (!edges.containsKey(a)) edges.put(a, new HashMap<String, Double>());
            if (!edges.containsKey(b)) edges.put(b, new HashMap<String, Double>());
            
            edges.get(a).put(b, values[i]);
            edges.get(b).put(a, 1.0 / values[i]);
        }
        
        // BFS search to connnect all possible nodes.
        for (String node : nodes) bfs(nodes, edges, node);
        
        // Handle queries
        double[] results = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String a = queries[i][0];
            String b = queries[i][1];
            // Two same variables, always 1.0
            if (nodes.contains(a) && a.equals(b)) results[i] = 1.0;
            // Retrieve edge weight of a -> b
            else if (nodes.contains(a) && edges.get(a).containsKey(b)) results[i] = edges.get(a).get(b);
            // Unable to process query
            else results[i] = -1.0;
        }
        return results;
    }
    
    // BFS to connect all possible nodes with start node.
    private void bfs(Set<String> nodes, Map<String, Map<String, Double>> edges, String start) {
        Set<String> visited = new HashSet<String>();
        Queue<String> q = new LinkedList<String>();
        q.add(start);
        visited.add(start);
        while (!q.isEmpty()) {
            String a = q.remove();
            // Find all variables b such that a / b exists.
            for (String b : edges.get(a).keySet()) {
                if (!visited.contains(b)) {
                    q.add(b);
                    visited.add(b);
                    // If start / b does not exist,
                    if (!edges.get(start).containsKey(b)) {
                        double m = edges.get(start).get(a);     // start / a = m
                        double n = edges.get(a).get(b);         //     a / b = n
                        edges.get(start).put(b, m * n);         // start / b = m * n
                        edges.get(b).put(start, 1 / m / n);     // b / start = 1 / (m * n)
                    }
                }
            }
        }
    }
}