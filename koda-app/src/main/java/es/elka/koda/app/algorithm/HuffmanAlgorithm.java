package es.elka.koda.app.algorithm;

import es.elka.koda.app.tree.HuffmanTree;
import es.elka.koda.app.tree.Node;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.ArrayList;

/**
 * Created by Quetz on 2015-12-06.
 *
 * Klasa adaptacyjnego algorytmu Huffmana
 */
public class HuffmanAlgorithm {

    private Dictionary<Character, Node> nodes;
    private HuffmanTree tree;
    private int maxIndex;

    /**
     * Konstruktor algorytmu Huffmana
     */
    public HuffmanAlgorithm()
    {
        nodes = new Hashtable<>();
        tree = new HuffmanTree();
        maxIndex = 0;
    }

    /**
     * Metoda dodająca do drzewa algorytmu kolejne wystąpienie symbolu ze
     * strumienia danych. Jeśli symbol już istnieje w drzewie wartość
     * wagi jego węzła jest inkrementowana. Jeśli jeszcze go nie ma, jest
     * do drzewa dodawany.
     *
     * @param symbol kolejny symbol występujący w strumieniu danych
     */
    public void addSymbol(char symbol)
    {
        Node node = nodes.get(symbol);
        // jeśli symbol jest już w drzewie
        if(node != null)
        {
            // ustalenie ścieżki do węzła
            ArrayList<Node> path = new ArrayList<>();
            tree.getPath(tree.getRoot(),node, path);

            // inkrementacja wagi wszystkich węzłów ze ścieżki
            for (Node n : path)
                node.weight++;
        }
        // jeśli symbolu jeszcze nie ma w drzewie
        else
        {
            // stworzenie nowego węzła
            Node newNode = new Node(symbol,0,++maxIndex);
            // umieszczenie węzła w mapie (na miejscu węzła o najmniejszej wadze)
            nodes.put(symbol, newNode);
            // umieszczenie węzła w drzewie
            tree.addNode(node);

            // inkrementacja wagi węzłów w ścieżce od węzła do korzenia

            // TODO - cała funkcja dodająca element do drzewa

        }

    }

    /**
     *
     * @param beginNode
     */
    private void incrementPath(Node beginNode)
    {



    }
}
