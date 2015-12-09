package es.elka.koda.app;

import es.elka.koda.app.algorithm.HuffmanAlgorithm;
import org.junit.Test;

public class ApplicationTest {

    @Test
    public void treeAlgorithmTest() {
        HuffmanAlgorithm algo = new HuffmanAlgorithm();
        byte data[] = {1,2,3,4,5,5,5,3,3,3,1,1,2};
        algo.run(data);
        algo.printTree();
    }
}
