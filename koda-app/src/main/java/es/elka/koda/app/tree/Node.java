package es.elka.koda.app.tree;

/**
 * Created by Quetz on 2015-12-06.
 *
 * Klasa węzła dla drzewa Huffmana zawierająca symbol, indeks, wagę
 * oraz wskazania na ewentualnych potomków (lewego i/lub prawego).
 *
 * @see HuffmanTree
 */
public class Node {

    public int weight;
    public int index;
    public char symbol;
    public Node leftChild;
    public Node rightChild;

    /**
     * Konstruktor węzła
     *
     * @param symbol znak alfabetu
     * @param weight waga węzła
     * @param index indeks węzła (klucz węzła)
     */
    public Node(char symbol, int weight, int index)
    {
        this.weight = weight;
        this.symbol = symbol;
        this.index = index;
        this.leftChild = null;
        this.rightChild = null;
    }

    /**
     * Konstruktor węzła właściwego/liścia (gdzie ważny jest symbol) z wyzerowanymi wartościami wagi i indeksu
     *
     * @param symbol znak alfabetu
     */
    public Node(char symbol)
    {
        this.weight = 0;
        this.symbol = symbol;
        this.index = 0;
        this.leftChild = null;
        this.rightChild = null;
    }


    /**
     * Kostruktor 'pustego' węzła pośredniego, w którym istotna
     * jest jedynie waga i indeks (który przy inicjalizacju wynosi 0)
     *
     * @param weight
     */
    public Node(int weight)
    {
        this.weight = weight;
        this.symbol = 0;
        this.index = 0;
        this.leftChild = null;
        this.rightChild = null;
    }

    /**
     * Konstruktor kopiujący.
     * @param node węzeł kopiowany
     */
    public Node(Node node)
    {
        this.weight = node.weight;
        this.symbol = node.symbol;
        this.index = node.index;
        this.leftChild = node.leftChild;
        this.rightChild = node.rightChild;
    }

    /**
     * Metoda sprawdzająca czy dany węzeł jest liściem (nie ma potomków)
     *
     * @return true jeśli węzeł jest liściem, false w przeciwnym wypadku
     */
    public boolean isLeaf()
    {
        if(leftChild == null && rightChild == null)
            return true;
        else
            return false;
    }
}
