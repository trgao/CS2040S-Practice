import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree {
    public static class TreeNode {
        int key;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        TreeNode(int key) {
            this.key = key;
        }
    }

    public TreeNode root;

    public TreeNode search(int key) {
        TreeNode curr = root;
        while (curr != null && curr.key != key) {
            if (key <= curr.key) curr = curr.left;
            else curr = curr.right;
        }
        return curr;
    }

    public void insert(int key) {
        TreeNode node = new TreeNode(key);
        if (root == null) {
            root = node;
            return;
        }
        TreeNode curr = root;
        while (true) {
            if (node.key <= curr.key) {
                if (curr.left == null) {
                    curr.left = node;
                    node.parent = curr;
                    break;
                }
                curr = curr.left;
            } else {
                if (curr.right == null) {
                    curr.right = node;
                    node.parent = curr;
                    break;
                }
                curr = curr.right;
            }
        }
    }

    public void delete(TreeNode node) {
        if (node == root) {
            if (node.left == null && node.right == null) root = null;
            else if (node.left == null) {
                root = node.right;
                node.right.parent = null;
            }
            else if (node.right == null) {
                root = node.left;
                node.left.parent = null;
            }
            else {
                TreeNode next = successor(node);
                delete(next);
                root = next;
                attachChild(next, node.left);
                attachChild(next, node.right);
            }
            clearNode(node);
            return;
        }
        if (node.left == null && node.right == null) removeChild(node.parent, node);
        else if (node.left == null) replaceChild(node.parent, node, node.right);
        else if (node.right == null) replaceChild(node.parent, node, node.left);
        else {
            TreeNode next = successor(node);
            delete(next);
            replaceChild(node.parent, node, next);
            attachChild(next, node.left);
            attachChild(next, node.right);
        }
        clearNode(node);
    }

    private void removeChild(TreeNode parent, TreeNode child) {
        if (parent.left == child) parent.left = null;
        else if (parent.right == child) parent.right = null;
        child.parent = null;
    }

    private void attachChild(TreeNode parent, TreeNode child) {
        if (parent.left == null) parent.left = child;
        else if (parent.right == null) parent.right = child;
        if (child != null) child.parent = parent;
    }

    private void replaceChild(TreeNode parent, TreeNode node, TreeNode child) {
        if (parent.left == node) parent.left = child;
        else if (parent.right == node) parent.right = child;
        child.parent = parent;
    }

    private void clearNode(TreeNode node) {
        node.left = null;
        node.right = null;
        node.parent = null;
    }

    private TreeNode successor(TreeNode node) {
        if (node.right != null) {
            return findMin(node.right);
        }
        TreeNode parent = node.parent;
        while (parent != null && parent.right == node) {
            parent = parent.parent;
            node = node.parent;
        }
        return parent;
    }

    private TreeNode findMin(TreeNode node) {
        if (node == null) return null;
        TreeNode curr = node;
        while (curr.left != null) curr = curr.left;
        return curr;
    }

    public void recursiveBFS(int key) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        _recursiveBFS(key, queue, 1);
    }

    private void _recursiveBFS(int key, Queue<TreeNode> queue, int number) {
        int counter = 0;
        for (int i = 0; i < number; i++) {
            TreeNode node = queue.remove();
            if (node.key == key) {
                System.out.println("Key found");
                return;
            }
            if (node.left != null) {
                queue.add(node.left);
                counter++;
            }
            if (node.right != null) {
                queue.add(node.right);
                counter++;
            }
        }
        if (queue.isEmpty()) {
            System.out.println("Key not found");
            return;
        }
        _recursiveBFS(key, queue, counter);
    }

    public void iterativeBFS(int key) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (node.key == key) {
                System.out.println("Key found");
                return;
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        System.out.println("Key not found");
    }

    public void recursiveDFS(int key) {
        if (!_recursiveDFS(key, root)) System.out.println("Key not found");
    }

    private boolean _recursiveDFS(int key, TreeNode node) {
        if (node == null) return false;
        if (node.key == key) {
            System.out.println("Key found");
            return true;
        }
        return _recursiveDFS(key, node.left) || _recursiveDFS(key, node.right);
    }

    public void iterativeDFS(int key) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.key == key) {
                System.out.println("Key found");
                return;
            }
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        System.out.println("Key not found");
    }

    private void test() {
        this.insert(10);
        this.insert(5);
        this.insert(3);
        this.insert(7);
        this.insert(2);
        this.insert(4);
        this.insert(1);
        this.insert(8);
        this.insert(6);
        this.insert(9);
        this.insert(15);
        this.insert(13);
        this.insert(17);
        this.insert(12);
        this.insert(14);
        this.insert(11);
        this.insert(18);
        this.insert(16);
        this.insert(19);
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.test();
        tree.delete(tree.root);
        tree.recursiveBFS(10);
        tree.recursiveBFS(11);
        tree.iterativeBFS(10);
        tree.iterativeBFS(11);
        tree.recursiveDFS(10);
        tree.recursiveDFS(11);
        tree.iterativeDFS(10);
        tree.iterativeDFS(11);
    }
}
