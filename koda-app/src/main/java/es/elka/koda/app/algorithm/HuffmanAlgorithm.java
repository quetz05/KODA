package es.elka.koda.app.algorithm;

import es.elka.koda.app.tree.BinaryTree;
import es.elka.koda.app.tree.Node;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by Quetz on 2015-12-06.
 *
 * Klasa adaptacyjnego algorytmu Huffmana
 */
public class HuffmanAlgorithm {

    private Dictionary<Character, Node> nodes;
    private BinaryTree tree;

    /**
     * Konstruktor algorytmu Huffmana
     */
    public HuffmanAlgorithm()
    {
        nodes = new Hashtable<>();
        tree = new BinaryTree();
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
            node.weight++;
        }
        // jeśli symbolu jeszcze nie ma w drzewie
        else
        {
            // TODO - cała funkcja dodająca element do drzewa

        }

    }
}
