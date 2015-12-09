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
            // porządkowanie drzewa ze zwiększeniem wag
            tree.doOrdering(node);
        }
        // jeśli symbolu jeszcze nie ma w drzewie
        else
        {
            // stworzenie nowego węzła
            Node newNode = new Node(symbol);
            // umieszczenie węzła w drzewie
            tree.addNode(newNode);
            // umieszczenie węzła w mapie (na miejscu węzła o najmniejszej wadze)
            nodes.put(symbol, newNode);
        }

    }

    /**
     * Metoda drukująca drzewo na ekran.
     *
     */
    public void printTree()
    {
        tree.print();
    }

    /**
     * Metoda odczytująca wartość klucza w postaci ciągu bitów dla danego węzła drzewa
     * @param node węzeł drzewa, dla którego ma zostać policzony klucz
     * @return klucz w formie ciągu bitów
     */
    private BitSet getSymbolKey(Node node)
    {
        BitSet key = new BitSet();

        // TODO Funkcja tworząca klucz na podstawie ścieżki od node'a

        return key;
    }

    /**
     * Metoda tworząca słownik dla symboli na podstawie drzewa
     * @return słownik w postaci <Symbol, Ciąg bitów>
     */
    public Dictionary<Character, BitSet> createDictionary()
    {
        Dictionary<Character, BitSet> dictionary = new Hashtable<>();

        //TODO Funkcja tworząca słownik

        return dictionary;
    }
}
