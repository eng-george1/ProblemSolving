package LeetCode.Amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieNode {
    private static Node root;

    public TrieNode() {
        root = new Node();
    }

    class Node {
        String value;
        Map<Character, Node> children = new HashMap<>();
        boolean EoW;

        public Node() {
        }

        public Node(String c) {
            this.value = c;
        }

    }

    // insert

    private void addString(String s) {
        Map<Character, Node> children = root.children;
        StringBuilder sb = new StringBuilder();
        Node current = root;

        for (char c : s.toCharArray()) {
            sb.append(c);

            if (children.containsKey(c)) {
                current = children.get(c);
            } else {
                current = new Node(sb.toString());
                children.put(c, current);
            }

            children = current.children;
        }

        current.EoW = true;
    }

    // autocomplete

    private List<String> getMatchesList(String s) {
        List<String> matches = new ArrayList<>();

        Node current = root;
        for (char c : s.toCharArray()) {
            if (current.children.containsKey(c))
                current = current.children.get(c);
            else
                return matches;
        }

        findMatches(current, matches);
        return matches;
    }

    // helper with recursion

    private void findMatches(Node n, List<String> matches) {
        if (n.EoW) {
            matches.add(n.value);
        }
        for (Map.Entry<Character, Node> nodeMap : n.children.entrySet()) {

            findMatches(nodeMap.getValue(), matches);
        }
    }   

}