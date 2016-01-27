package es.elka.koda.app.tree;
import java.util.*;

/**
 * Created by Quetz on 2015-12-06.
 *
 * Klasa drzewa, przygotowana specjalnie dla algorytmu adaptacyjnego Huffmana.
 * Węzły drzewa posiadają maksymalnie dwóch potomków.
 * Każdy węzeł, który nie jest liściem ma indeks 0 i nie ma przypisanego żadnego symbolu.
 *
 * @see Node
 */
public class HuffmanTree {

    /**
     * Korzeń drzewa.
     */
    private Node root;
    /**
     * Węzeł o najmniejszej wadze - potrzebny do przyspieszenia algorytmu
     * dodawania węzłów.
     */
    public Node currentSmallestNode;
    /**
     * Lista węzłów drzewa, pomocna przy optymalizacji czasu działania niektórych metod w klasie
     */
    public List<Node> nodeList;

    /**
     * Konstruktor drzewa Huffmana.
     */
    public HuffmanTree()
    {
        nodeList = new ArrayList<Node>();
    }


    /**
     * Metoda dodająca węzeł do drzewa, zgodnie z poniższym algorytmem:
     * Jeśli drzewo jest puste, nowy węzeł staje się korzeniem, jego indeks ustawiany jest na jeden, a waga inkrementowana
     * i metoda kończy działanie.
     * Jeśli drzewo nie jest puste szukany jest węzeł (liść) z najmniejszym indeksem, a następnie
     * jest on zastępowany nowym węzła pośrednim, którego dziećmi są nowy korzeń i
     * ten zastępowany.
     * W kolejnej fazie następuje aktualizacja wag drzewa (dla wszystkich węzłów ze ścieżki od korzenia do nowego węzła).
     * Później zmieniane są indeksy nowego węzła, węzła zastępowanego i nowego węzła pośredniego na (kolejno):
     * 1, 2, 3. Wartości wszystkich pozostałych węzłów są zwiększane o 2.
     * Na koniec wywoływany jest algorytm związany porządkujący węzły drzewa zgodnie z indeksami i wagą.
     *
     * @param newNode
     */
    public void addNode(Node newNode)
    {
        nodeList.add(newNode);
        if(root == null)
        {
            root = newNode;
            currentSmallestNode = root;
            root.weight++;
            root.index = 1;
        }
        else if(root.isLeaf())
        {
            Node emptyNode = new Node(currentSmallestNode.weight);
            nodeList.add(emptyNode);

            // 'przepięcia' węzłów
            Node temp = root;
            emptyNode.leftChild = newNode;
            emptyNode.rightChild = temp;
            root = emptyNode;
            // przypisanie rodzica
            root.leftChild.parent = root;
            root.rightChild.parent = root;

            root.weight ++;
            newNode.weight++;

            root.leftChild.index = 1;
            root.rightChild.index = 2;
            root.index = 3;

            currentSmallestNode = newNode;
        }
        else
        {

            // TODO - uproszczenie po dodaniu parenta

            // szukanie ścieżki w celu zmiany wskazań węzłów
            Vector<Node> path = new Vector<>();
            getPath(root,currentSmallestNode, path);

            // stworzenie nowego węzła pośredniego
            Node emptyNode = new Node(currentSmallestNode.weight);

            // dostęp do 'ojca' najmniejszego węzła
            path.removeElementAt(0);
            Node parent =  path.elementAt(0);

            // 'przepięcia' węzłów - faktyczne dodanie nowego węzła do drzewa
            parent.leftChild = emptyNode;
            emptyNode.rightChild = currentSmallestNode;
            emptyNode.leftChild = newNode;
            currentSmallestNode = newNode;

            // przypisanie rodzica
            emptyNode.rightChild.parent = emptyNode;
            emptyNode.leftChild.parent = emptyNode;
            emptyNode.parent = parent;


            // zmiana indeksów modyfikowanych węzłów
            newNode.index = 1;
            emptyNode.rightChild.index = 2;
            emptyNode.index = 3;

            // zwiększenie indeksów pozostałych węzłów
            for(Node n : nodeList)
                if(n != emptyNode.leftChild && n != emptyNode.rightChild)
                    n.index += 2;

            // dodanie do listy węzłów nowego, pustego węzła
            nodeList.add(emptyNode);

            doOrdering(newNode);
        }
    }


//    private Boolean getPath(Node beginNode, Node searchedNode, Vector<Node> path)
//    {
//        if(beginNode == null)
//            return false;
//
//        if(beginNode == searchedNode ||
//                getPath(beginNode.leftChild, searchedNode, path) ||
//                getPath(beginNode.rightChild, searchedNode, path))
//        {
//            path.add(beginNode);
//            return true;
//        }
//        return false;
//    }

    /**
     * Metoda szukająca ścieżki do konkretnego węzła w drzewie, ropoczynając od konkretnego węzła.
     *
     * @param beginNode węzeł od którego przeszukujemy wgłąb
     * @param searchedNode szukany węzeł
     * @param path lista ze ścieżką do węzła (zawierająca kolejne węzły począwszy od szukanego węzła)
     *
     * @return true jeśli ścieżka do węzła została znaleziona (węzeł istnieje), false w przeciwnym wypadku
     */
    private Boolean getPath(Node beginNode, Node searchedNode, Vector<Node> path)
    {
        Node temp = searchedNode;
        while(true)
        {
            path.add(temp);
            if (temp == beginNode)
                break;
            else
                temp = temp.parent;
        }
        return true;
    }

    /**
     * Metoda zwracająca ścieżkę od korzenia do danego węzła.
     *
     * @param searchedNode szukany węzeł
     * @return ścieżka (w postaci klasy Deque<Node> kolejnych węzłów od szukanego węzła do korzenia
     */
    public Vector<Node> getPath(Node searchedNode)
    {
        Vector<Node> path = new Vector<>();
        getPath(root, searchedNode, path);
        return path;
    }

    /**
     * Metoda przeglądająca drzewo od konkretnego węzła (któremu zwiększa wagę o 1) i porządkująca jego strukturę.
     *
     * @param node - węzeł do porządkowania
     */
    public void doOrdering(Node node)
    {
        node.weight++;

        if(node == root)
            return;

        for(Node n : nodeList)
            if(n.isLeaf() && node.isLeaf() && node.weight -1 == n.weight)
            {
                if(currentSmallestNode == node)
                    currentSmallestNode = n;

                swapNodes(node, n);
                break;
            }

        if(node.parent == null)
            System.out.println("O NIE! Jesteśmy zgubieni!");
        doOrdering(node.parent);
    }

    /**
     * Metoda 'przepinająca' ze sobą dwa węzły (razem z poddrzewami).
     *
     * @param first pierwszy węzeł
     * @param second drugi węzeł
     */
    private void swapNodes(Node first, Node second)
    {
        Node tempParent = first.parent;
        Node tempLeft = first.leftChild;
        Node tempRight = first.rightChild;

        first.parent = second.parent;
        first.leftChild = second.leftChild;
        first.rightChild = second.rightChild;

        second.parent = tempParent;
        second.leftChild = tempLeft;
        second.rightChild = tempRight;
    }

    /**
     * Metoda czyszcząca struktrę drzewa i wszystkie jego elementy
     */
    public void clear()
    {
        root = null;
        nodeList.clear();
        // TODO zrobić pełne czyszczenie

    }

    /**
     * Metoda drukująca całe drzewo na konsolę.
     */
    public void print()
    {
        printNode(root,"|----");
    }

    /**
     * Metoda drukująca konkretne poddrzewo na konsolę.
     *
     * @param node korzeń poddrzewa, które ma być drukowane
     * @param tabulator tabulator, dzięki któremu wyświetlone drzewo jest bardziej przejrzyste
     */
    private void printNode(Node node, String tabulator)
    {
        if(node != null)
        {
            System.out.println(tabulator + node.symbol + " [" + node.index + "," + node.weight + "]");
            tabulator = "   " + tabulator;

            printNode(node.leftChild, tabulator);
            printNode(node.rightChild, tabulator);
        }
    }
}
