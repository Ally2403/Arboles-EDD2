public class AVL extends BinaryTree{

    @Override
    public Node addNodeABBR(Node root, Node newNode) {
        if(root == null){
            return newNode;
        }
        if(root.data == newNode.data){
            System.out.println("El elemento ya está");
            return root;
        }
        if(newNode.data > root.data){
            root.right = addNodeABBR(root.right, newNode);
        }
        if(newNode.data < root.data){
            root.left = addNodeABBR(root.left, newNode);
        }
        root.balance = height(root.left) - height(root.right);
        if(root.balance == 2){
            if(root.left.balance == -1){
                root.left = rSI(root.left);
            }
            return rSD(root);
        }
        if(root.balance == -2){
            if(root.right.balance == 1){
                root.right = rSD(root.right);
            }
            return rSI(root);
        }
        return root;
    }

    public Node rSI(Node node){
        Node aux = node.right;
        node.right = aux.left;
        aux.left = node;
        aux.balance = height(aux.left) - height(aux.right);
        node.balance = height(node.left) - height(node.right);
        return aux;
    }
    public Node rSD(Node node){
        Node aux = node.left;
        node.left = aux.right;
        aux.right = node;
        aux.balance = height(aux.left) - height(aux.right);
        node.balance = height(node.left) - height(node.right);
        return aux;
    }

    @Override
    public Node deleteNodeR(Node node, int x) {
        if (node == null) // No lo encontré
            return null;

        if (node.data == x) { // Lo encontré
            // Caso 1 y 2: No tiene hijos o tiene 1 hijo
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            // Caso 3: Tiene dos hijos

            // Metodo sucessor: Buscar el menor de los mayores
            Node succ = minABBD(node.right);
            node.data = succ.data;
            node.right = deleteNodeR(node.right, succ.data);
            // Metodo predecesor: Buscar el mayor de los menores
            /*
             * Node pred = maxABBI(node.left);
             * node.data = pred.data;
             * node.left = deleteNodeR(node.left, pred.data);
             */
        }

        // Moverse en el árbol mientras no encuentre el nodo
        if (node.data > x)
            node.left = deleteNodeR(node.left, x);
        if (node.data < x)
            node.right = deleteNodeR(node.right, x);

        node.balance = height(node.left) - height(node.right);
        return node; // Después de los condicionales el último retorno es este
    }

    // Para el método sucessor (MENOR DE LOS MAYORES)
    public Node minABBD(Node node) {
        if (node.left == null)
            return node;
        return minABBD(node.left);
    }

    // Para el método predecesor (MAYOR DE LOS MENORES)
    public Node maxABBI(Node node) {
        if (node.right == null)
            return node;
        return maxABBI(node.left);
    }

    public int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }


    @Override
    public void preOrderR(Node node){
        if(node != null){
            System.out.print(node.data + " fb: " + (height(node.left) - height(node.right)) +" | ");
            preOrderR(node.left);
            preOrderR(node.right);
        }
    }

    @Override
    public Node searchABB(Node root, int x) {
        if (root == null) {
            return root;
        } else if (root.data == x) {
            return root;
        } else if (root.data > x) {
            return searchABB(root.left, x);
        } else {
            return searchABB(root.right, x);
        }
    }

    @Override
    public void generateABB(int data) {
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            System.out.println("La raiz ha sido añadida " + newNode.data);
        } else {
            root = addNodeABBR(root, newNode);
        }
    }

    @Override
    public void delete(int x) {
        // Llamada inicial para eliminar un nodo
        root = deleteNodeR(root, x);
    }

}
