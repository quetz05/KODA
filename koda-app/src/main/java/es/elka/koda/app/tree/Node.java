package es.elka.koda.app.tree;

/**
 * Created by Quetz on 2015-12-06.
 */
public class Node {

    public Node()
    {
        this.weight = 0;
        this.symbol = 0;

        this.leftChild = null;
        this.rightChild = null;
    }

    public Node(char symbol, int weight)
    {
        this.weight = weight;
        this.symbol = symbol;

        this.leftChild = null;
        this.rightChild = null;
    }

    int weight;
    char symbol;

    Node leftChild;
    Node rightChild;
}
