/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        
    Map<Integer, TreeNode> nodeMap = new HashMap<>();
        Set<Integer> childNodes = new HashSet<>();
        
        for (int[] description : descriptions) {
            int parent = description[0];
            int child = description[1];
            boolean isLeft = description[2] == 1;

            nodeMap.putIfAbsent(parent, new TreeNode(parent));
            nodeMap.putIfAbsent(child, new TreeNode(child));

            if (isLeft) {
                nodeMap.get(parent).left = nodeMap.get(child);
            } else {
                nodeMap.get(parent).right = nodeMap.get(child);
            }

            childNodes.add(child);
        }

        for (int parent : nodeMap.keySet()) {
            if (!childNodes.contains(parent)) {
                return nodeMap.get(parent); // root node
            }
        }

        return null; // just in case
    }
}

