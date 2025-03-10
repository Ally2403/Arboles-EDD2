
public class App {
    public static void main(String[] args) throws Exception {
        ABB myAbb = new ABB();
        //Generando el árbol
        myAbb.generateABB(15);
        myAbb.generateABB(5);
        myAbb.generateABB(3);
        myAbb.generateABB(10);
        myAbb.generateABB(30);
        myAbb.generateABB(17);
        myAbb.generateABB(40);
        myAbb.generateABB(35);
        myAbb.generateABB(50);
        System.out.println();

        ABB B = new ABB();
        B.generateABB(15);
        B.generateABB(5);
        B.generateABB(3);
        B.generateABB(10);
        B.generateABB(30);
        B.generateABB(17);
        B.generateABB(40);
        B.generateABB(35);
        B.generateABB(50);

        ABB C = new ABB();
        C.generateABB(15);
        C.generateABB(9);
        C.generateABB(7);
        C.generateABB(11);
        C.generateABB(31);
        C.generateABB(18);
        C.generateABB(40);
        C.generateABB(38);
        C.generateABB(51);

        //PUNTO 1 TALLER: COMPARAR QUE DOS ÁRBOLES SEAN IDÉNTICOS
        System.out.println("Son el árbol A y el B iguales?");
        Process compare = new Process();
        if(compare.Compare(myAbb.root, B.root)){
            System.out.println("True");
        }else{
            System.out.println("False");
        }
        System.out.println();

        //PUNTO 2 TALLER: MOSTRAR TODOS LOS NODOS EN UN NIVEL N
        System.out.println("Los nodos en el nivel 3 son: ");
        myAbb.nodosEnNivelN(myAbb.root, 3, 0);
        System.out.println();

        //PUNTO 3 TALLER: COMPROBAR QUE UN ÁRBOL ES UN ABB
        System.out.println("Comprobar que un árbol es un ABB");
        if(myAbb.comprobarABB(myAbb.root) != myAbb.numNodos(myAbb.root)){
            System.out.println("No es un ABB");
        }else{
            System.out.println("Sí es un ABB");
        }
        System.out.println();

        //PUNTO 4 TALLER: CONSTRUIR UN ÁRBOL A PARTIR DE SU PREORDEN E INORDEN
        System.out.println("Construir un árbol a partir de su inorden y preorden");
        Process construir = new Process();
        String preorder = "GAECDIBFH";
        String inorder = "ACEDGIBFH";
        System.out.println("Preorder: GAECDIBFH");
        System.out.println("Inorder: ACEDGIBFH");
        ABB reconstruido = new ABB();
        reconstruido.root = construir.construirABB(preorder, inorder);
        System.out.println("Reconstruido exitosamente");

        //Verificar que el preorden sea el inicial dado
        System.out.println("Verificar que el preorder sea el inicial dado:");
        System.out.println(construir.preOrderASCII(reconstruido.root));
        System.out.println();

        //PUNTO 5 TALLER: PALABRAS ALMACENADAS EN UN ÁRBOL BINARIO DESDE LA RAÍZ HASTA CADA HOJA
        System.out.println("Creamos el árbol:");
        String preorder1 = "ARBOLOREPA";
        String inorder1 = "OLBROARPAE";
        System.out.println("Preorder: ARBOLOREPA");
        System.out.println("Inorder: OLBROARPAE");
        ABB arbolPalabras = new ABB();
        arbolPalabras.root = construir.construirABB(preorder1, inorder1);
        System.out.println("Las palabras encontradas en este árbol son: ");
        arbolPalabras.palabras(arbolPalabras.root, "");
        System.out.println();

        //PUNTO 6 TALLER: INVERTIR LOS HIJOS DE UN SUBÁRBOL
        System.out.println("Invertiremos los hijos del nodo A");
        String preorder2 = "ABDECFG";
        String inorder2 = "DBEAFCG";
        System.out.println("Preorder: ABDECFG");
        System.out.println("Inorder: FCBADBE");
        ABB arbolInvertido = new ABB();
        arbolInvertido.root = construir.construirABB(preorder2, inorder2);
        System.out.println("El árbol ha sido invertido y su nuevo preorder es: ");
        arbolInvertido.invertir(arbolInvertido.root, "A");
        System.out.println(construir.preOrderASCII(arbolInvertido.root));
        System.out.println();

        //PUNTO 7 TALLER: BUSCAR EL TÍO DE UN NODO
        System.out.println("Buscaremos el tío del nodo D en el Árbol ABDECFG(PUNTO6)");
        if(arbolInvertido.tio(arbolInvertido.root, "D") == null){
            System.out.println("No se encontró el tío del nodo D");
        }else{
            System.out.println("El tío del nodo D es: " + (char)(arbolInvertido.tio(arbolInvertido.root, "D")).data + "");
        }
        System.out.println();

        //PUNTO 9.2 TALLER: VER SI DOS ÁRBOLES ABB TIENEN MÍNIMO 2 NODOS IGUALES
        System.out.println("Veremos si el árbol B y el C tienen dos nodos iguales");
        System.out.println("Preorder de B");
        B.preOrderR(B.root);
        System.out.println();
        System.out.println("Preorder de C");
        C.preOrderR(C.root);
        int cont = 0;
        construir.dosComunes(B.root, C.root, cont);
        System.out.println();
        if(construir.sn){
            System.out.println("Sí tienen dos elementos en común");
        }else{
            System.out.println("No tienen dos elementos en común");
        }

        /*
        //Buscando un elemento
        System.out.println("Buscaremos el elemento 0");
        if (myAbb.searchABB(myAbb.root, 0)) {
            System.out.println("Elemento 0 encontrado");
        } else {
            System.out.println("Elemento no encontrado");
        }
        System.out.println();

        //Recorridos
        System.out.println("El preorder de este árbol es: ");
        myAbb.preOrderR(myAbb.root);
        System.out.println();
        System.out.println("El inorder de este árbol es: ");
        myAbb.inOrderR(myAbb.root);
        System.out.println();
        System.out.println("El postorder de este árbol es: ");
        myAbb.postOrderR(myAbb.root);
        System.out.println();
        System.out.println("Level order");
        myAbb.levelOrderTraversal(myAbb.root);
        System.out.println();

        //Eliminar un elemento
        System.out.println("Si se elimina el 0");
        myAbb.delete(0);
        System.out.println("Level order resultante");
        myAbb.levelOrderTraversal(myAbb.root);
        System.out.println();

        //Procedimientos a parte
        System.out.println("Procedimientos a parte");
        System.out.println("La suma final de los elementos del árbol es " + myAbb.sum(myAbb.root));
        System.out.println("El número total de nodos en el árbol es " + myAbb.numNodos(myAbb.root));
        System.out.println("La altura del árbol es: " + myAbb.height(myAbb.root));
        */
    }
}
