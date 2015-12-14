package es.elka.koda.app;

import es.elka.koda.app.algorithm.HuffmanAlgorithm;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class ApplicationTest {

    @Test
    public void treeAlgorithmTest() {
        HuffmanAlgorithm algo = new HuffmanAlgorithm();
        byte data[] = {1,2,3,4,5,5,5,3,3,3,1,1,2};
        algo.run(data, true);
        algo.printTree();
    }

    /*
    Moje wlasne testy funkcji
    Zakladaja ze w okreslonej lokalizacji da się dodać plik
    stad domyslnie ignorowane
    */
    @Test
    @Ignore
    public void shouldCodeSourceFile() throws IOException {
        Application application = new Application();

        application.code("8x8.pgm");

    }
}
