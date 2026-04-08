import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Practice {
    /**
     * Returns the sum of the odd numbers in the array.
     * 
     * Returns 0 if the array is null or has no odd numbers.
     * 
     * @param nums an array of numbers
     * @return the sum of the odd numbers in the array
     */
    public static int oddSum(int[] nums) {
        if(nums == null) return 0;
        int sum = 0;
        for(int num: nums) {
            if (num % 2 != 0) {
                sum += num;
            }
        }
        return sum;
    }

    /**
     * Returns the shortest word in the Set.
     * 
     * If multiple words are tied for shortest, returns the one that is smallest
     * lexicographically.
     * 
     * @param words a set of words
     * @return the shortest word in the set with a lexicographic tiebreaker
     * @throws IllegalArgumentException if words is empty
     * @throws NullPointerException if words is null
     */
    public static String shortestWord(Set<String> words) {
        //"kumquat", "date", "fig", "grape", "cherry"
        if (words == null) { throw new NullPointerException(); }
        if (words.isEmpty()) { throw new IllegalArgumentException(); }
        
        int length = Integer.MAX_VALUE;
        String shortest = "";
        for(String word: words){
            if (word.length() < length) {
                shortest = word;
                length = word.length();
            } else if (word.length() == length) {
                if (word.compareTo(shortest) < 0) {
                    shortest = word;
                }
            }
        }

        return shortest;
    }

    /**
     * Returns a set of all the names of people that are 18 years of age or older.
     * 
     * The input maps name to age in years.
     * 
     * @param ages mapping of name to age
     * @return the set of all names of people >= 18 years old
     * @throws NullPointerException if ages is null
     */
    public static Set<String> adults(Map<String, Integer> ages) {
        if (ages == null) { throw new NullPointerException(); }
        Set<String> adult = new HashSet<>();
        for(String age: ages.keySet()){
            if (ages.get(age) >= 18) {
                adult.add(age);
            }
        }
        return adult;
    }

    /**
     * Returns the biggest number in a linked list.
     * 
     * @param head the head of the linked list
     * @return the biggest number in the list
     * @throws IllegalArgumentException if head is null
     */
    public static int biggestNumber(ListNode<Integer> head) {
        if (head == null) { throw new IllegalArgumentException();}
        ListNode<Integer> current = head;
        int max = head.data;
        while (current != null) {
            max = Math.max(max, current.data);
            current = current.next;
        }
        return max;
    }

    /**
     * Returns a frequency map counting how frequently items appear in a linked list.
     * 
     * Example:
     *   Input: a -> x -> a -> a -> x -> y
     *   Output: {a:3, x:2, y:1}
     * 
     * Returns an empty map if head is null
     * 
     * @param <T> the type of data held by the list
     * @param head the head of the list
     * @return a frequency map of values in the list
     */
    public static <T> Map<T, Integer> frequencies(ListNode<T> head) {
        if (head == null) { return new HashMap<>();}
        Map<T, Integer> frequency = new HashMap<>();
        ListNode<T> current = head;
        while (current != null) {
            if (!frequency.containsKey(current.data)) {
                frequency.put(current.data, 1);
            } else {
                frequency.put(current.data, frequency.get(current.data) + 1);
            }
            current = current.next;
        }
        return frequency;
    }


    /**
     * Returns the number of levels in the tree.
     * 
     * An empty tree has 0 levels, a tree with only a root has 1 level.
     * 
     * @param root the root of the tree
     * @return the number of levels in the tree
     */
    public static int levelCount(BinaryTreeNode<?> root) {
        if (root == null) { return 0; }
        int leftTree = levelCount(root.left) + 1;
        int leftRight = levelCount(root.right) + 1;
        return Math.max(leftTree, leftRight);
    }


    /**
     * Returns the sum at a specified level in a binary tree.
     * 
     * For example, if the given level was 3:
     *       5
     *     /   \
     *    8     4
     *   / \   / 
     *  7  9  2
     *    /
     *   1
     * 
     * Nodes at level 3: 7, 9, and 2
     * Sum of nodes at level 3: 18 
     * 
     * The root is considered to be at level 1.
     * 
     * Returns 0 if the tree is empty or if the level is not present in the tree.
     * 
     * @param root the root of the binary tree
     * @param level the level to sum
     * @return the sum of the nodes at the given level
     */
    public static int sumAtLevel(BinaryTreeNode<Integer> root, int level) {
        if (root == null) { return 0;}

        Queue<BinaryTreeNode<Integer>> q = new LinkedList<>(); // 1
        int sum = 0; // 7 + 9 + 2
        int L = 1; // 3
        q.add(root);

        while(!q.isEmpty()){
            for(int i = q.size(); i > 0; i--){
                BinaryTreeNode<Integer> current = q.poll(); // 7 9 2
                if (L == level) {
                    sum += current.data;
                }

                if (current.left != null){ q.add(current.left);}
                if (current.right != null){ q.add(current.right);}
            }
            L += 1;
        }
        return sum;
    }


    /**
     * Returns true if the sum of the values in a given tree is equal to the sum
     * of the values in the given list. 
     * 
     * An empty tree or list is considered to have a sum of 0.
     * 
     * @param root The root of the binary tree
     * @param head The head of the linked list
     * @return true if the sums are equal, false otherwise
     */
    public static boolean sumMatch(BinaryTreeNode<Integer> root, ListNode<Integer> head) {
        int rootSum = sumMatchHelper(root);
        int listSum = sumMatchHelper(head);
        return rootSum == listSum ? true : false;
    }

    public static int sumMatchHelper(BinaryTreeNode<Integer> root){
        if (root == null) return 0;
        int leftTree = sumMatchHelper(root.left);
        int rightTree = sumMatchHelper(root.right);
        return root.data + leftTree + rightTree ;
    }

    public static int sumMatchHelper(ListNode<Integer> head){
        if(head == null) return 0;
        ListNode<Integer> current = head;
        int sum = 0;
        while (current != null) {
            sum += current.data;
            current = current.next;
        }
        return sum;
    }

    /**
     * Returns the sum of all the nodes in a non-binary tree.
     * 
     * Returns 0 if the root is null.
     * 
     * @param root the root of the tree
     * @return the sum of all the tree's values
     */
    public static int nbSum(TreeNode<Integer> root) {
        if (root == null) return 0;
        int sum = root.data;
        for(TreeNode<Integer> child: root.children){
            sum += nbSum(child);
        }
        return sum;
    }

    /**
     * Returns the count of nodes in a non-binary tree that are only children, EXCLUDING the root.
     * 
     * In other words, how many nodes in the tree do NOT have siblings, NOT INCLUDING THE ROOT.
     * 
     * Example:
     *           A
     *       /   |   \
     *      B    C     D
     *     /    / \    |
     *    E    F   X   G
     *                  \
     *                   H
     * 
     * Only children: E, G, and H
     * - E is an only child because B has exactly one child
     * - G is an only child because D has exactly one child
     * - H is an only child because G has exactly one child
     * - A is NOT counted because the root is excluded
     * 
     * Result: 3
     * 
     * 
     * Returns 0 if the root is null.
     * 
     * @param root the root of the tree
     * @return the count of nodes that do not have siblings, EXCLUDING THE ROOT
     */
    public static int onlyChildCount(TreeNode<?> root) {
        if(root == null) return 0;
        int count = 0;

        for(TreeNode<?> child: root.children){
            if (root.children.size() == 1) count++;
            count += onlyChildCount(child);
        }

        return count;
    }

    /**
     * Returns the maximum depth of the tree.
     * 
     * Example map:
     * {
     *   A=[B, C, D],
     *   B=[E, F],
     *   D=[G],
     *   G=[H]
     * }
     * 
     * Tree represented by the map:
     *          A
     *       /  |  \
     *      B   C   D
     *     / \      |
     *    E   F     G
     *               \
     *                H
     * 
     * The longest path from the root to a leaf is:
     * A -> D -> G -> H
     * 
     * So the maximum depth is 4.
     * 
     * A tree with only a root would have a depth of 1.
     * 
     * The tree is represented as a map of parent values to lists of children.
     * 
     * @param <T> the type of the data in the tree
     * @param tree a map of parent values to lists of children
     * @param root the root value of the tree
     * @return the depth of the tree, or 0 if the tree is null or the root is not present in the tree
     */
    /*
    /*
        Map:                          Tree:
        A -> [B, E, C]                 A
        B -> [E]                     / | \
        E -> []                    B   E   C
        C -> [D, Q]                |     /  \
        D -> [Z]                   E    D    Q
        Q -> []                          \
        Z -> []                           Z
    */
   
    public static <T> int maxDepth(Map<T, List<T>> tree, T root) {
        if (root == null || tree == null || !tree.containsKey(root)) return 0;
        int depth = maxDepthHelper(tree, root);
        return depth;
    }
    
    public static <T> int maxDepthHelper(Map<T, List<T>> tree, T root){
        if(!tree.containsKey(root)) return 1;
        int depth = 1;
        List<T> children = tree.get(root);

        for(T child: children) {
            depth = Math.max(maxDepthHelper(tree, child) + 1, depth);
        }
        return depth;
    }
}