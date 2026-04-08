import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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
        int sum = 0;
        if(nums == null) return sum;
        for(int num : nums) {
            if(num % 2 != 0)
                sum += num;
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
        if(words == null) {
            throw new NullPointerException();
        }
        if(words.size() == 0) {
            throw new IllegalArgumentException();
        }
        String shortestWord = null;
        for(String word : words) {
            if(shortestWord == null) {
                shortestWord = word;
                continue;
            }
            if(word.length() < shortestWord.length() || (word.length() == shortestWord.length() && word.compareTo(shortestWord) < 0) ) {
                shortestWord = word;
            }
        }
        return shortestWord;
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
        if(ages == null) {
            throw new NullPointerException();
        }
        Set<String> eighteenOrOlder = new HashSet<>();
        for(Map.Entry<String, Integer> person : ages.entrySet()) {
            if(person.getValue() >= 18) {
                eighteenOrOlder.add(person.getKey());
            }
        }
        return eighteenOrOlder;
    }

    /**
     * Returns the biggest number in a linked list.
     * 
     * @param head the head of the linked list
     * @return the biggest number in the list
     * @throws IllegalArgumentException if head is null
     */
    public static int biggestNumber(ListNode<Integer> head) {
        if(head == null) {
            throw new IllegalArgumentException();
        }
        ListNode<Integer> current = head;
        int biggestNum = -Integer.MIN_VALUE;
        while(current != null) {
            if(current.data > biggestNum) {
                biggestNum = current.data;
            }
            current = current.next;
        }
        return biggestNum;
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
        Map<T, Integer> frequencyMap = new HashMap<>();
        if(head == null) {
            return frequencyMap;
        }

        ListNode<T> current = head;

        while(current != null) {
            T value = current.data;
            frequencyMap.put(value, frequencyMap.getOrDefault(value, 0)+1);
            current = current.next;
        }

        return frequencyMap;
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
        if(root == null) {
            return 0;
        }

        return 1 + Math.max(levelCount(root.left), levelCount(root.right));
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
        if(root == null || level < 1) {
            return 0;
        }
        if(level == 1) {
            return root.data;
        }

        return sumAtLevel(root.left, level-1) + sumAtLevel(root.right, level-1);
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
        Stack<BinaryTreeNode<Integer>> stk = new Stack<>();
        if(root != null) {
            stk.push(root);
        }

        int treeSum = 0;
        int listSum = 0;

        while(!stk.isEmpty()) {
            BinaryTreeNode<Integer> node = stk.pop();
            treeSum += node.data;

            if(node.left != null) {
                stk.push(node.left);
            }
            if(node.right != null) {
                stk.push(node.right);
            }
        }

        ListNode<Integer> current = head;
        while(current != null) {
            listSum += current.data;
            current = current.next;
        }

        return treeSum == listSum;
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
        if(root == null) {
            return 0;
        }

        int sum = root.data;

        for(TreeNode<Integer> child : root.children) {
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
        if(root == null) {
            return 0;
        }

        int count = 0;
        if(root.children.size() == 1) {
            count++;
        }

        for(TreeNode<?> child : root.children) {
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
    public static <T> int maxDepth(Map<T, List<T>> tree, T root) {
        if(tree == null || root == null) return 0;

        //find root and if its actually in the tree
        boolean rootInTree = tree.containsKey(root);
        if (!rootInTree) {
            for (List<T> children : tree.values()) {
                if (children.contains(root)) {
                    rootInTree = true;
                    break;
                }
            }
        }
        if(!rootInTree) return 0;
        //check if its a leaf
        if(!tree.containsKey(root)) return 1;

        int maxDepth = 0;
        for(T child : tree.get(root)) {
            int depth = maxDepth(tree, child);
            maxDepth = Math.max(depth, maxDepth);
        }

        return 1+maxDepth;
    }
}