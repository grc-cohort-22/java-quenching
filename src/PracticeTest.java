import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import org.junit.jupiter.api.Test;

public class PracticeTest {

    // ===== oddSum ============================================================

    @Test
    void oddSum_mixedOddsEvensAndNegatives() {
        int[] nums = { 17, -4, 0, 22, 13, -7, 8, 2, 11 };
        // odds: 17 + 13 + (-7) + 11 = 34
        assertEquals(34, Practice.oddSum(nums));
    }

    @Test
    void oddSum_allEvens() {
        int[] nums = { 12, 44, 100, 2, 8, 6 };
        assertEquals(0, Practice.oddSum(nums));
    }

    @Test
    void oddSum_nullArrayReturnsZero() {
        int[] nums = null;
        assertEquals(0, Practice.oddSum(nums));
    }

    @Test
    void oddSum_onlyOneOdd() {
        int[] nums = { 24, 50, 92, 7, 18 };
        assertEquals(7, Practice.oddSum(nums));
    }

    @Test
    void oddSum_emptyArrayReturnsZero() {
        int[] nums = {};
        assertEquals(0, Practice.oddSum(nums));
    }

    @Test
    void oddSum_allOdds() {
        int[] nums = { 1, 3, 5, 7, 9 };
        assertEquals(25, Practice.oddSum(nums));
    }

    // ===== shortestWord =====================================================

    @Test
    void shortestWord_basicShortest() {
        Set<String> words = new HashSet<>(Arrays.asList(
                "kumquat", "date", "fig", "grape", "cherry"));
        assertEquals("fig", Practice.shortestWord(words));
    }

    @Test
    void shortestWord_lexicographicTiebreaker() {
        Set<String> words = new HashSet<>(Arrays.asList(
                "turnip", "as", "be", "to", "melon"));
        // shortest length = 2 ; among {"as","be","to"} lexicographically smallest is
        // "as"
        assertEquals("as", Practice.shortestWord(words));
    }

    @Test
    void shortestWord_allSameLength() {
        Set<String> words = new HashSet<>(Arrays.asList(
                "ape", "arc", "bat", "bay"));
        // all length 3; smallest lexicographically is "ape"
        assertEquals("ape", Practice.shortestWord(words));
    }

    @Test
    void shortestWord_singleWord() {
        Set<String> words = new HashSet<>(Arrays.asList("watermelon"));
        assertEquals("watermelon", Practice.shortestWord(words));
    }

    @Test
    void shortestWord_emptySetThrowsIllegalArgumentException() {
        Set<String> words = new HashSet<>();
        assertThrows(IllegalArgumentException.class, () -> Practice.shortestWord(words));
    }

    @Test
    void shortestWord_nullSetThrowsNullPointerException() {
        Set<String> words = null;
        assertThrows(NullPointerException.class, () -> Practice.shortestWord(words));
    }

    // ===== adults ============================================================

    @Test
    void adults_mixedAgesWithBoundaryIncluded() {
        Map<String, Integer> ages = new HashMap<>();
        ages.put("Ana", 17);
        ages.put("Ben", 18);
        ages.put("Caz", 41);
        ages.put("Dee", 16);
        ages.put("Eli", 18);

        Set<String> result = Practice.adults(ages);
        Set<String> expected = new HashSet<>(Arrays.asList("Ben", "Caz", "Eli"));

        assertEquals(expected, result);
    }

    @Test
    void adults_emptyInputGivesEmptySet() {
        Map<String, Integer> ages = new HashMap<>();
        assertTrue(Practice.adults(ages).isEmpty());
    }

    @Test
    void adults_noAdultsAllUnder18() {
        Map<String, Integer> ages = new HashMap<>();
        ages.put("Ira", 6);
        ages.put("Jay", 12);
        ages.put("Kai", 17);
        assertTrue(Practice.adults(ages).isEmpty());
    }

    @Test
    void adults_allAdults() {
        Map<String, Integer> ages = new HashMap<>();
        ages.put("Ana", 18);
        ages.put("Bo", 19);
        ages.put("Cy", 70);

        Set<String> expected = new HashSet<>(Arrays.asList("Ana", "Bo", "Cy"));
        assertEquals(expected, Practice.adults(ages));
    }

    @Test
    void adults_nullMapThrowsNullPointerException() {
        Map<String, Integer> ages = null;
        assertThrows(NullPointerException.class, () -> Practice.adults(ages));
    }

    // ===== biggestNumber =====================================================

    @Test
    void biggestNumber_mixedPosNegAndDuplicates() {
        /*
         * List:
         * 5 -> -11 -> 42 -> 7 -> 42 -> -3 -> 0
         */
        ListNode<Integer> head = new ListNode<>(5,
                new ListNode<>(-11,
                        new ListNode<>(42,
                                new ListNode<>(7,
                                        new ListNode<>(42,
                                                new ListNode<>(-3,
                                                        new ListNode<>(0, null)))))));

        assertEquals(42, Practice.biggestNumber(head));
    }

    @Test
    void biggestNumber_singleNodeNegative() {
        /*
         * List:
         * -8
         */
        ListNode<Integer> head = new ListNode<>(-8, null);
        assertEquals(-8, Practice.biggestNumber(head));
    }

    @Test
    void biggestNumber_allEqualValues() {
        /*
         * List:
         * 13 -> 13 -> 13
         */
        ListNode<Integer> head = new ListNode<>(13,
                new ListNode<>(13,
                        new ListNode<>(13, null)));
        assertEquals(13, Practice.biggestNumber(head));
    }

    @Test
    void biggestNumber_strictlyDescending() {
        /*
         * List:
         * 9 -> 7 -> 5 -> 3 -> 1
         */
        ListNode<Integer> head = new ListNode<>(9,
                new ListNode<>(7,
                        new ListNode<>(5,
                                new ListNode<>(3,
                                        new ListNode<>(1, null)))));

        assertEquals(9, Practice.biggestNumber(head));
    }

    @Test
    void biggestNumber_nullHeadThrowsIllegalArgumentException() {
        ListNode<Integer> head = null;
        assertThrows(IllegalArgumentException.class, () -> Practice.biggestNumber(head));
    }

    // ===== frequencies =======================================================

    @Test
    void frequencies_stringsExampleFromSpecPlusMore() {
        /*
         * List:
         * a -> x -> a -> a -> x -> y -> z -> y
         * Expected: {a=3, x=2, y=2, z=1}
         */
        ListNode<String> head = new ListNode<>("a",
                new ListNode<>("x",
                        new ListNode<>("a",
                                new ListNode<>("a",
                                        new ListNode<>("x",
                                                new ListNode<>("y",
                                                        new ListNode<>("z",
                                                                new ListNode<>("y", null))))))));

        Map<String, Integer> expected = new HashMap<>();
        expected.put("a", 3);
        expected.put("x", 2);
        expected.put("y", 2);
        expected.put("z", 1);

        assertEquals(expected, Practice.frequencies(head));
    }

    @Test
    void frequencies_integersArbitraryOrder() {
        /*
         * List:
         * 7 -> 9 -> 7 -> 7 -> 10 -> 9 -> 10 -> 7 -> 10 -> 10 -> 10
         * Expected: {7=4, 9=2, 10=5}
         */
        ListNode<Integer> head = new ListNode<>(7,
                new ListNode<>(9,
                        new ListNode<>(7,
                                new ListNode<>(7,
                                        new ListNode<>(10,
                                                new ListNode<>(9,
                                                        new ListNode<>(10,
                                                                new ListNode<>(7,
                                                                        new ListNode<>(10,
                                                                                new ListNode<>(10,
                                                                                        new ListNode<>(10,
                                                                                                null)))))))))));

        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(7, 4);
        expected.put(9, 2);
        expected.put(10, 5);

        assertEquals(expected, Practice.frequencies(head));
    }

    @Test
    void frequencies_nullHeadReturnsEmptyMap() {
        ListNode<String> head = null;
        assertTrue(Practice.frequencies(head).isEmpty());
    }

    @Test
    void frequencies_singleNode() {
        /*
         * List:
         * kiwi
         * Expected: {kiwi=1}
         */
        ListNode<String> head = new ListNode<>("kiwi", null);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("kiwi", 1);

        assertEquals(expected, Practice.frequencies(head));
    }

    @Test
    void frequencies_allSameValue() {
        /*
         * List:
         * 4 -> 4 -> 4 -> 4
         * Expected: {4=4}
         */
        ListNode<Integer> head = new ListNode<>(4,
                new ListNode<>(4,
                        new ListNode<>(4,
                                new ListNode<>(4, null))));

        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(4, 4);

        assertEquals(expected, Practice.frequencies(head));
    }

    // ===== levelCount ========================================================

    @Test
    void levelCount_nullRootIsZero() {
        BinaryTreeNode<Integer> root = null;
        assertEquals(0, Practice.levelCount(root));
    }

    @Test
    void levelCount_singleNodeIsOne() {
        /*
         * 42
         */
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(42);
        assertEquals(1, Practice.levelCount(root));
    }

    @Test
    void levelCount_balancedThreeLevels() {
        /*
         * 8
         * / \
         * 3 10
         * / \ \
         * 1 6 14
         */
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(8,
                new BinaryTreeNode<>(3,
                        new BinaryTreeNode<>(1),
                        new BinaryTreeNode<>(6)),
                new BinaryTreeNode<>(10,
                        null,
                        new BinaryTreeNode<>(14)));
        assertEquals(3, Practice.levelCount(root));
    }

    @Test
    void levelCount_unbalancedDeeperLeft() {
        /*
         * 5
         * /
         * 4
         * /
         * 3
         * \
         * 2
         * /
         * 1
         */
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(5,
                new BinaryTreeNode<>(4,
                        new BinaryTreeNode<>(3,
                                null,
                                new BinaryTreeNode<>(2,
                                        new BinaryTreeNode<>(1),
                                        null)),
                        null),
                null);
        assertEquals(5, Practice.levelCount(root));
    }

    @Test
    void levelCount_unbalancedDeeperRight() {
        /*
         * 1
         * \
         * 2
         * \
         * 3
         * /
         * 4
         */
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1,
                null,
                new BinaryTreeNode<>(2,
                        null,
                        new BinaryTreeNode<>(3,
                                new BinaryTreeNode<>(4),
                                null)));

        assertEquals(4, Practice.levelCount(root));
    }

    // ===== sumAtLevel ========================================================

    @Test
    void sumAtLevel_exampleFromSpec_level3Is18() {
        /*
         * 5
         * / \
         * 8 4
         * / \ /
         * 7 9 2
         * /
         * 1
         * Level 3 nodes: 7, 9, 2 -> sum = 18
         */
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(5,
                new BinaryTreeNode<>(8,
                        new BinaryTreeNode<>(7),
                        new BinaryTreeNode<>(9,
                                new BinaryTreeNode<>(1),
                                null)),
                new BinaryTreeNode<>(4,
                        new BinaryTreeNode<>(2),
                        null));

        assertEquals(18, Practice.sumAtLevel(root, 3));
    }

    @Test
    void sumAtLevel_level1IsRootValue() {
        /*
         * 12
         * / \
         * 7 5
         */
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(12,
                new BinaryTreeNode<>(7),
                new BinaryTreeNode<>(5));

        assertEquals(12, Practice.sumAtLevel(root, 1));
    }

    @Test
    void sumAtLevel_levelBeyondDepthIsZero() {
        /*
         * 9
         * \
         * 2
         */
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(9,
                null,
                new BinaryTreeNode<>(2));
        assertEquals(0, Practice.sumAtLevel(root, 5));
    }

    @Test
    void sumAtLevel_levelZeroIsNotPresentSoZero() {
        /*
         * 3
         */
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(3);
        assertEquals(0, Practice.sumAtLevel(root, 0));
    }

    @Test
    void sumAtLevel_emptyTreeIsZero() {
        BinaryTreeNode<Integer> root = null;
        assertEquals(0, Practice.sumAtLevel(root, 2));
    }

    @Test
    void sumAtLevel_level2WithNegativeValues() {
        /*
         * 10
         * / \
         * -3 6
         *
         * Level 2 nodes: -3 and 6 -> sum = 3
         */
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(10,
                new BinaryTreeNode<>(-3),
                new BinaryTreeNode<>(6));

        assertEquals(3, Practice.sumAtLevel(root, 2));
    }

    @Test
    void sumAtLevel_singleNodeLevel2IsZero() {
        /*
         * 8
         */
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(8);
        assertEquals(0, Practice.sumAtLevel(root, 2));
    }

    // ===== sumMatch ==========================================================

    @Test
    void sumMatch_equalSumsNonEmpty() {
        /*
         * Tree:
         * 6
         * / \
         * 4 1
         * / \
         * -2 7
         * Tree sum = 6 + 4 + 1 + (-2) + 7 = 16
         *
         * List:
         * 10 -> 3 -> 3
         * List sum = 16
         */
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(6,
                new BinaryTreeNode<>(4,
                        new BinaryTreeNode<>(-2),
                        new BinaryTreeNode<>(7)),
                new BinaryTreeNode<>(1));

        ListNode<Integer> head = new ListNode<>(10,
                new ListNode<>(3,
                        new ListNode<>(3, null)));

        assertTrue(Practice.sumMatch(root, head));
    }

    @Test
    void sumMatch_notEqual() {
        /*
         * Tree:
         * 5
         * / \
         * 2 2
         * Sum = 9
         *
         * List:
         * 4 -> 4
         * Sum = 8
         */
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(5,
                new BinaryTreeNode<>(2),
                new BinaryTreeNode<>(2));

        ListNode<Integer> head = new ListNode<>(4,
                new ListNode<>(4, null));

        assertFalse(Practice.sumMatch(root, head));
    }

    @Test
    void sumMatch_bothEmptyAreZeroSoTrue() {
        BinaryTreeNode<Integer> root = null;
        ListNode<Integer> head = null;
        assertTrue(Practice.sumMatch(root, head));
    }

    @Test
    void sumMatch_emptyTreeNonEmptyListFalse() {
        BinaryTreeNode<Integer> root = null;
        /*
         * List:
         * 1 -> 2
         */
        ListNode<Integer> head = new ListNode<>(1, new ListNode<>(2, null));
        assertFalse(Practice.sumMatch(root, head));
    }

    @Test
    void sumMatch_nonEmptyTreeEmptyListFalse() {
        /*
         * Tree:
         * 4
         * / \
         * 1 2
         * Sum = 7
         *
         * List:
         * null
         * Sum = 0
         */
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(4,
                new BinaryTreeNode<>(1),
                new BinaryTreeNode<>(2));

        ListNode<Integer> head = null;

        assertFalse(Practice.sumMatch(root, head));
    }

    @Test
    void sumMatch_nonEmptyBothZeroSumsTrue() {
        /*
         * Tree:
         * 2
         * / \
         * -2 0
         * Sum = 0
         *
         * List:
         * -5 -> 5
         * Sum = 0
         */
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(2,
                new BinaryTreeNode<>(-2),
                new BinaryTreeNode<>(0));

        ListNode<Integer> head = new ListNode<>(-5,
                new ListNode<>(5, null));

        assertTrue(Practice.sumMatch(root, head));
    }

    // ===== nbSum =============================================================

    @Test
    void nbSum_nullRootIsZero() {
        TreeNode<Integer> root = null;
        assertEquals(0, Practice.nbSum(root));
    }

    @Test
    void nbSum_singleNode() {
        /*
         * 7
         */
        TreeNode<Integer> root = new TreeNode<>(7);
        assertEquals(7, Practice.nbSum(root));
    }

    @Test
    void nbSum_multipleLevels() {
        /*
         * 5
         * / | \
         * 2 4 1
         * / \
         * 3 6
         *
         * Sum = 5 + 2 + 4 + 1 + 3 + 6 = 21
         */
        TreeNode<Integer> node3 = new TreeNode<>(3);
        TreeNode<Integer> node6 = new TreeNode<>(6);
        TreeNode<Integer> node2 = new TreeNode<>(2);
        TreeNode<Integer> node1 = new TreeNode<>(1);
        TreeNode<Integer> node4 = new TreeNode<>(4, Arrays.asList(node3, node6));
        TreeNode<Integer> root = new TreeNode<>(5, Arrays.asList(node2, node4, node1));

        assertEquals(21, Practice.nbSum(root));
    }

    @Test
    void nbSum_withNegativeValues() {
        /*
         * 10
         * / \
         * -3 -2
         * /
         * 5
         *
         * Sum = 10 + (-3) + (-2) + 5 = 10
         */
        TreeNode<Integer> node5 = new TreeNode<>(5);
        TreeNode<Integer> nodeNeg3 = new TreeNode<>(-3, Arrays.asList(node5));
        TreeNode<Integer> nodeNeg2 = new TreeNode<>(-2);
        TreeNode<Integer> root = new TreeNode<>(10, Arrays.asList(nodeNeg3, nodeNeg2));

        assertEquals(10, Practice.nbSum(root));
    }

    // ===== onlyChildCount ====================================================

    @Test
    void onlyChildCount_nullRootIsZero() {
        TreeNode<Integer> root = null;
        assertEquals(0, Practice.onlyChildCount(root));
    }

    @Test
    void onlyChildCount_singleNodeRootNotCounted() {
        /*
         * 9
         *
         * Root is excluded, so result = 0
         */
        TreeNode<Integer> root = new TreeNode<>(9);
        assertEquals(0, Practice.onlyChildCount(root));
    }

    @Test
    void onlyChildCount_exampleShape() {
        /*
         * A
         * / | \
         * B C D
         * / / \ |
         * E F X G
         * \
         * H
         *
         * Only children: E, G, H
         * Result = 3
         */
        TreeNode<String> nodeE = new TreeNode<>("E");
        TreeNode<String> nodeF = new TreeNode<>("F");
        TreeNode<String> nodeX = new TreeNode<>("X");
        TreeNode<String> nodeH = new TreeNode<>("H");
        TreeNode<String> nodeB = new TreeNode<>("B", Arrays.asList(nodeE));
        TreeNode<String> nodeC = new TreeNode<>("C", Arrays.asList(nodeF, nodeX));
        TreeNode<String> nodeG = new TreeNode<>("G", Arrays.asList(nodeH));
        TreeNode<String> nodeD = new TreeNode<>("D", Arrays.asList(nodeG));
        TreeNode<String> root = new TreeNode<>("A", Arrays.asList(nodeB, nodeC, nodeD));

        assertEquals(3, Practice.onlyChildCount(root));
    }

    @Test
    void onlyChildCount_noOnlyChildren() {
        /*
         * 1
         * / | \
         * 2 3 4
         * / \
         * 5 6
         *
         * Every counted node has at least one sibling.
         * Result = 0
         */
        TreeNode<Integer> node2 = new TreeNode<>(2);
        TreeNode<Integer> node5 = new TreeNode<>(5);
        TreeNode<Integer> node6 = new TreeNode<>(6);
        TreeNode<Integer> node3 = new TreeNode<>(3, Arrays.asList(node5, node6));
        TreeNode<Integer> node4 = new TreeNode<>(4);
        TreeNode<Integer> root = new TreeNode<>(1, Arrays.asList(node2, node3, node4));

        assertEquals(0, Practice.onlyChildCount(root));
    }

    @Test
    void onlyChildCount_chainCountsEveryNonRootNode() {
        /*
         * A
         * |
         * B
         * |
         * C
         * |
         * D
         *
         * Only children: B, C, D
         * Result = 3
         */
        TreeNode<String> nodeD = new TreeNode<>("D");
        TreeNode<String> nodeC = new TreeNode<>("C", Arrays.asList(nodeD));
        TreeNode<String> nodeB = new TreeNode<>("B", Arrays.asList(nodeC));
        TreeNode<String> root = new TreeNode<>("A", Arrays.asList(nodeB));

        assertEquals(3, Practice.onlyChildCount(root));
    }

    // ===== maxDepth ==========================================================

    @Test
    void maxDepth_nullTreeIsZero() {
        Map<String, List<String>> tree = null;
        assertEquals(0, Practice.maxDepth(tree, "A"));
    }

    @Test
    void maxDepth_rootNotPresentIsZero() {
        /*
         * Map:
         * B -> [C]
         *
         * Root asked for: A
         */
        Map<String, List<String>> tree = new HashMap<>();
        tree.put("B", Arrays.asList("C"));

        assertEquals(0, Practice.maxDepth(tree, "A"));
    }

    @Test
    void maxDepth_singleRootIsOne() {
        /*
         * Map:
         * A -> []
         *
         * Tree:
         * A
         *
         * Depth = 1
         */
        Map<String, List<String>> tree = new HashMap<>();
        tree.put("A", new ArrayList<>());

        assertEquals(1, Practice.maxDepth(tree, "A"));
    }

    @Test
    void maxDepth_exampleShapeIsFour() {
        /*
         * Map:
         * A -> [B, C, D]
         * B -> [E, F]
         * D -> [G]
         * G -> [H]
         *
         * Tree:
         * A
         * / | \
         * B C D
         * / \ |
         * E F G
         * |
         * H
         *
         * Longest path: A -> D -> G -> H
         * Depth = 4
         */
        Map<String, List<String>> tree = new HashMap<>();
        tree.put("A", Arrays.asList("B", "C", "D"));
        tree.put("B", Arrays.asList("E", "F"));
        tree.put("D", Arrays.asList("G"));
        tree.put("G", Arrays.asList("H"));

        assertEquals(4, Practice.maxDepth(tree, "A"));
    }

    @Test
    void maxDepth_balancedThreeLevels() {
        /*
         * Map:
         * 1 -> [2, 3]
         * 2 -> [4, 5]
         * 3 -> [6, 7]
         *
         * Tree:
         * 1
         * / \
         * 2 3
         * / \ / \
         * 4 5 6 7
         *
         * Depth = 3
         */
        Map<Integer, List<Integer>> tree = new HashMap<>();
        tree.put(1, Arrays.asList(2, 3));
        tree.put(2, Arrays.asList(4, 5));
        tree.put(3, Arrays.asList(6, 7));

        assertEquals(3, Practice.maxDepth(tree, 1));
    }

    @Test
    void maxDepth_unbalancedBranchingTree() {
        /*
         * Map:
         * M -> [N, O]
         * N -> [P]
         * P -> [Q]
         *
         * Tree:
         * M
         * / \
         * N O
         * |
         * P
         * |
         * Q
         *
         * Longest path: M -> N -> P -> Q
         * Depth = 4
         */
        Map<String, List<String>> tree = new HashMap<>();
        tree.put("M", Arrays.asList("N", "O"));
        tree.put("N", Arrays.asList("P"));
        tree.put("P", Arrays.asList("Q"));

        assertEquals(4, Practice.maxDepth(tree, "M"));
    }
}