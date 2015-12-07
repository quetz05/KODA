package es.elka.koda.app.tree;

/**
 * Created by Quetz on 2015-12-06.
 *
 * Klasa węzła dla drzewa binarnego (o maksymalnie dwóch potomkach)
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
     * @param symbol znak alfabetu (klucz węzła)
     * @param weight waga węzła
     * @param index indeks węzła
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
