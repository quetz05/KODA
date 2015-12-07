package es.elka.koda.app.algorithm;

import es.elka.koda.app.tree.HuffmanTree;
import es.elka.koda.app.tree.Node;
import java.util.*;

/**
 * Created by Quetz on 2015-12-06.
 *
 * Klasa adaptacyjnego algorytmu Huffmana
 *
 * @see HuffmanTree, Node
 */
public class HuffmanAlgorithm {

    /**
     * Mapa hashująca zawierająca węzły drzewa z symbolami - przyspiesza sprawdzanie czy węzeł o danym symbolu jest w drzewie
     */
    private Dictionary<Character, Node> nodes;
    private HuffmanTree tree;

    /**
     * Konstruktor algorytmu Huffmana
     */
    public HuffmanAlgorithm()
    {
        nodes = new Hashtable<>();
        tree = new HuffmanTree();
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
            ArrayDeque<Node> path = tree.getPath(node);

            // inkrementacja wagi wszystkich węzłów ze ścieżki
            for (Node n : path)
                node.weight++;

            // porządkowanie drzewa po zwiększeniu wag
            tree.doOrdering(node);
        }
        // jeśli symbolu jeszcze nie ma w drzewie
        else
        {
            // stworzenie nowego węzła
            Node newNode = new Node(symbol);
            // umieszczenie węzła w mapie (na miejscu węzła o najmniejszej wadze)
            nodes.put(symbol, newNode);
            // umieszczenie węzła w drzewie
            tree.addNode(node);

            // po dodaniu węzła doOrdering wykonuje się automatycznie, więc nie trzeba do wywołyawć po raz drugi
        }

    }
}
