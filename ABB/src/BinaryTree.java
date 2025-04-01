public abstract class BinaryTree {
    protected Node root;

    public abstract Node addNodeABBR(Node root, Node newNode);
    public abstract Node deleteNodeR(Node root, int x);
    public abstract void preOrderR(Node root);
    public abstract Node searchABB(Node root, int x);
    public abstract void generateABB(int x);
    public abstract void delete(int x);
}
