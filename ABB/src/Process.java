public class Process {

    boolean sn = false;

    //PUNTO 1 TALLER: FALTA COMPARAR LOS INORDEN DE LOS ÁRBOLES PARA
    //VERIFICAR QUE EFECTIVAMENTE SEAN IDENTICOS
    public boolean Compare(Node rootA, Node rootB){
        String preorderA = preOrder(rootA);
        String preorderB = preOrder(rootB);
        return preorderA.equals(preorderB);
    }
    public String preOrder(Node node){
        if(node != null){
            return node.data + preOrder(node.left) + preOrder(node.right);
        }else{
            return "";
        }
    }

    //PUNTO 4 TALLER
    public String preOrderASCII(Node node){
        if(node != null){
            return (char)node.data + preOrderASCII(node.left) + preOrderASCII(node.right);
        }else{
            return "";
        }
    }
    public Node construirABB(String preorder, String inorder){
        if (preorder.isEmpty() || inorder.isEmpty()) return null;

        // La raíz es el primer carácter del preorden
        // Pasa los caracteres a ASCII
        char rootValue = preorder.charAt(0);
        Node root = new Node(rootValue);

        // Buscamos la posición de la raíz en el inorden
        int rootIndex = inorder.indexOf(rootValue); //Empieza desde 0

        // Partimos en subcadenas para los subárboles
        String leftInorder = inorder.substring(0, rootIndex);
        String rightInorder = inorder.substring(rootIndex + 1);
        String leftPreorder = preorder.substring(1, 1 + leftInorder.length());
        String rightPreorder = preorder.substring(1 + leftInorder.length());

        // Construimos recursivamente
        root.left = construirABB(leftPreorder, leftInorder);
        root.right = construirABB(rightPreorder, rightInorder);
        return root;
    }

    // PUNYO 9.2 TALLER: VER SI DOS ÁRBOLES ABB TIENEN POR LO MENOS 2 NODOS IGUALES
    public void dosComunes(Node rootA, Node rootB, int cont){
        if(rootA!= null){
            if(rootA.buscar(rootB, rootA.data)){
                cont += 1; 
            }
            if(cont >= 2){
                this.sn = true;
            }
            dosComunes(rootA.left, rootB, cont);
            dosComunes(rootA.right, rootB, cont);
        }
    }
}
