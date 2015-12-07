package es.elka.koda.app;

import es.elka.koda.app.algorithm.HuffmanAlgorithm;

public class Application {


    public static void main(String[] args) {

        HuffmanAlgorithm algo = new HuffmanAlgorithm();

        algo.addSymbol('a');
        algo.addSymbol('b');
        algo.addSymbol('c');
        algo.addSymbol('d');

        algo.printTree();
        System.out.println();

        algo.addSymbol('d');
        algo.printTree();
    }
}
