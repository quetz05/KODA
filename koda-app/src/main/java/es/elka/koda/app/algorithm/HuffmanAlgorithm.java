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

    public HuffmanAlgorithm()
    {
        nodes = new Hashtable<>();
        tree = new BinaryTree();
    }


    private Dictionary<Character, Node> nodes;
    private BinaryTree tree;
}
