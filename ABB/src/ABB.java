import java.util.LinkedList;
import java.util.Queue;

public class ABB{
    Node root;

    //CREACIÓN DEL ÁRBOL
    public Node addNodeABBR(Node root, Node newNode) {
        if (root.data > newNode.data) {
            if (root.left == null) {
                root.left = newNode;
                System.out.println("Elemento " + newNode.data + " añadido");
            } else {
                root.left = addNodeABBR(root.left, newNode);
            }
        } else {
            if (root.data < newNode.data) {
                if (root.right == null) {
                    root.right = newNode;
                    System.out.println("Elemento " + newNode.data + " añadido");
                } else {
                    root.right = addNodeABBR(root.right, newNode);
                }
            } else {
                System.out.printf("El elemento %d ya existe \n", newNode.data);
            }
        }
        // Esto se hace para árboles AVL
        root.balance = height(root.left) - height(root.right);
        return root;
    }

    public void generateABB(int data) {
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            System.out.println("La raiz ha sido añadida " + newNode.data);
        } else {
            addNodeABBR(root, newNode);
        }
    }

    //RECORRIDOS POSIBLES EN UN ÁRBOL
    public void preOrderR(Node node){
        if(node != null){
            System.out.print(node.data + " | ");
            preOrderR(node.left);
            preOrderR(node.right);
        }
    }

    public void inOrderR(Node node){
        if(node != null){
            inOrderR(node.left);
            System.out.print(node.data + " | ");
            inOrderR(node.right);
        }
    }

    public void postOrderR(Node node){
        if(node != null){
            postOrderR(node.left);
            postOrderR(node.right);
            System.out.print(node.data + " | ");
        }
    }

    public void levelOrderTraversal(Node node) {
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            Node element = q.poll();
            System.out.print(element.data + " - Balance: " + element.balance + "|");
            if (element.left != null)
                q.add(element.left);
            if (element.right != null)
                q.add(element.right);
        }
        System.out.println();
    }

    //BÚSQUEDA DE UN ELEMENTO EN UN ÁRBOL ABB
    public boolean searchABB(Node root, int x) {
        if (root == null) {
            return false;
        } else if (root.data == x) {
            return true;
        } else if (root.data > x) {
            return searchABB(root.left, x);
        } else {
            return searchABB(root.right, x);
        }
    }

    //ELIMINAR UN ELEMENTO DEL ÁRBOL
    public void delete(int x) {
        // Llamada inicial para eliminar un nodo
        root = deleteNodeR(root, x);
    }

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

    //PROCEDIMIENTOS A PARTE

    // Sumar todos los elementos que hay en un árbol
    public int sum(Node node) {
        if (node != null) {
            return node.data + sum(node.left) + sum(node.right);
        } else {
            return 0;
        }
    }

    // Mostrar cuántos nodos hay en un árbol
    public int numNodos(Node node) {
        if (node != null) {
            return 1 + numNodos(node.left) + numNodos(node.right);
        }
        return 0;
    }

    // La altura de un árbol equivale al camino más largo
    // desde la raiz hasta las hojas
    public int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    //PUNTO 2 TALLER: NODOS EN UN NIVEL N DEL ÁRBOL
    public void nodosEnNivelN(Node node, int n, int c){
        if(node != null){
            c += 1;
            if(c == n){
                System.out.print(node.data + " | ");
            }else{
                nodosEnNivelN(node.left, n, c);
                nodosEnNivelN(node.right, n, c);
            }
        }
    }

    //PUNTO 3 TALLER: COMPROBAR QUE UN ÁRBOL ES UN ABB
    public int comprobarABB(Node node){
        if(node != null){
            if(node.left != null){
                if(node.left.data > node.data){
                    return 0;
                }
            }
            if(node.right != null){
                if(node.right.data < node.data){
                    return 0;
                }
            }
            return 1 + comprobarABB(node.left) + comprobarABB(node.right);
        }else{
            return 0;
        }  
    }

    //PUNTO 5 TALLER: PALABRAS ALMACENADAS EN UN ÁRBOL BINARIO DESDE LA RAÍZ HASTA CADA HOJA
    public void palabras(Node node, String palabra){
        if(node != null){
            if(node.left == null && node.right == null){
                palabra = palabra + (char)node.data;
                System.out.println(palabra);
            }else{
                palabra = palabra + (char)node.data;
                palabras(node.left, palabra);
                palabras(node.right, palabra);
            }
        }
    }

    public void invertir(Node node, String data){
        if(node != null){
            if(((char)node.data + "").equals(data)){
                Node temp = node.right;
                node.right = node.left;
                node.left = temp;
            }else{
                invertir(node.left, data);
                invertir(node.right, data);
            }
        }
    }

    // PUNTO 7 TALLER: ENCONTRAR EL TÍO DE UN NODO
    public Node tio(Node node, String sobrino){
        if(node != null){
            if(node.right != null && node.left != null){
                if(node.left.left != null){
                    if(((char)node.left.left.data + "").equals(sobrino)){
                        return node.right;
                    }
                }
                if(node.left.right != null){
                    if(((char)node.left.right.data + "").equals(sobrino)){
                        return node.right;
                    }
                }
                if(node.right.right != null){
                    if(((char)node.right.right.data + "").equals(sobrino)){
                        return node.left;
                    }
                }
                if(node.right.left != null){
                    if(((char)node.right.left.data + "").equals(sobrino)){
                        return node.left;
                    }
                }
            }
        }else{
            return null;
        }
        Node tio = tio(node.left, sobrino);
        if(tio != null){
            return tio;
        }
        return tio(node.right, sobrino);
    }
}
