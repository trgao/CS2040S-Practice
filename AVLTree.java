public class AVLTree {
    private static class TreeNode {
        int key;
        int height;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        TreeNode(int key) {
            this.key = key;
            this.height = 0;
        }
    }

    public TreeNode root;

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
}
