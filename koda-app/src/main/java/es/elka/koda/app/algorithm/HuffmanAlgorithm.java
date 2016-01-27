package es.elka.koda.app.algorithm;

import es.elka.koda.app.coder.HuffmanAlgorithmServer;
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
public class HuffmanAlgorithm implements HuffmanAlgorithmServer {

    /**
     * Mapa hashująca zawierająca węzły drzewa z symbolami - przyspiesza sprawdzanie czy węzeł o danym symbolu jest w drzewie
     */
    private Map<Byte, Node> nodes;
    private Map<Byte, BitsWrapper> dictionary;
    private HuffmanTree tree;


    /**
     * Metoda inicjalizująca początkową postać drzewa.
     */
    public void initialize()
    {
        //TODO - zaimplementować inicjalizowanie drzewa - deterministyczne

    }

    /**
     * Konstruktor algorytmu Huffmana
     */
    public HuffmanAlgorithm()
    {
        nodes = new HashMap<Byte, Node>();
        tree = new HuffmanTree();
        dictionary = new HashMap<Byte, BitsWrapper>();
    }

    /**
     * Metoda czyszcząca zawartość danych: drzewa, listy węzłów oraz słownika
     *
     */
    public void clearData()
    {
        nodes = new HashMap<Byte, Node>();
        tree = new HuffmanTree();
        dictionary = new HashMap<Byte, BitsWrapper>();
    }

    /**
     * Metoda dodająca do drzewa algorytmu kolejne wystąpienie symbolu ze
     * strumienia danych. Jeśli symbol już istnieje w drzewie wartość
     * wagi jego węzła jest inkrementowana. Jeśli jeszcze go nie ma, jest
     * do drzewa dodawany.
     *
     * @param symbol kolejny symbol występujący w strumieniu danych
     */
    public void addAndModify(byte symbol)
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
     * Metoda przyjmująca dane (ciąg bajtów) i na ich podstawie tworząca drzewo,
     * które posłuży potem do stworzenia słownika i zakodowania danych
     *
     * @param data dane
     * @param clear true jeśli drzewo i słownik symboli mają być czyszczone przed użyciem, false w przeciwnym wypadku
     */
    public void run(byte data[], boolean clear)
    {
        if(clear)
            clearData();

        for (byte b : data)
        {
            addAndModify(b);
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
     * Metoda drukująca słownik na ekran.
     */
    public void printDictionary()
    {
        for(Map.Entry<Byte, Node> entry : nodes.entrySet())
            System.out.println(entry.getKey() + ": " + getSymbolKey(entry.getValue()));
    }

    /**
     * Metoda odczytująca wartość klucza w postaci ciągu bitów dla danego węzła drzewa (zasada: idąc od korzenia - w lewo 0, w prawo 1)
     * @param node węzeł drzewa, dla którego ma zostać policzony klucz
     * @return klucz w formie ciągu bitów
     */
    private BitsWrapper getSymbolKey(Node node)
    {
        Vector<Node> path = tree.getPath(node);
        Collections.reverse(path);
        BitSet key = new BitSet(path.size()-1);
        byte counter = 0;

        for(int i = 0; i <path.size() - 1; i++)
        {
            if(path.get(i).leftChild == path.get(i+1))
                key.set(i,false);
            else
                key.set(i,true);

            counter++;
        }
        return new BitsWrapper(key,counter);
    }

    /**
     * Metoda tworząca słownik dla symboli na podstawie drzewa.
     *
     */
    public void createDictionary()
    {
        dictionary = new HashMap<Byte, BitsWrapper>();
        for(Map.Entry<Byte, Node> entry : nodes.entrySet())
        {
            dictionary.put(entry.getKey(), getSymbolKey(entry.getValue()));
        }
    }

    /**
     * Metoda zwracająca istniejący słownik symboli.
     *
     * @return słownik w postaci <symbol, ciąg bitów>
     */
    public Map<Byte, BitsWrapper> getDictionary()
    {
        return dictionary;
    }
}
