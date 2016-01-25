package es.elka.koda.app.coder;

import es.elka.koda.app.algorithm.BitsWrapper;

import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Coder {
    private HuffmanAlgorithmServer algorithmServer;

    public Coder(HuffmanAlgorithmServer huffmanAlgorithmServer) {
        this.algorithmServer = huffmanAlgorithmServer;
    }

    /**
     * 1. wczytaj pliku pgm,                                                            //w Application
     * a. pomiń nagłówek                                                               //w PgmFileToEncode#ignoreHeader
     * b. zapisz dane w wygodnej strukturze (lista bajtów)                             //w PgmFileToEncode#ignoreHeader
     * 2. inicjalizuj poczatkowa postaci drzewa Huffmana                                //w Coder#encodde
     * 3. wczytaj bajt, jeśli brak kolejnego bajtu to przejdź do punktu 6               //
     * 4. dodaj bajt do drzewa i modyfikuj drzewo (jedna metoda addAndModify)           //
     * 5. skocz do 3                                                                    //
     * 6. wczytaj utworzony słownik                                                     //
     * 7. wczytaj bajt, jeśli brak kolejnego bajtu to przejdź do punktu 10              //
     * 8. przetłumacz byte zgodnie ze słownikiem i zapisz go w strukturze zakodowanej   //
     * 9. skocz do punktu 7                                                             //
     * 10. zapisz plik wynikowy                                                         //w Application
     * a. zapisz nagłówek w którym zamieścisz słownik                                  //
     * b. zapisz zawartość zakodowanego pliku                                          //
     */
    public List<BitSet> encode(List<Byte> data) {
        algorithmServer.initialize();
        data.stream().forEach(algorithmServer::addAndModify);
        algorithmServer.createDictionary();
        Map<Byte, BitsWrapper> dictionary = algorithmServer.getDictionary();
        return data.stream()
                .map(dictionary::get)
                .map(BitsWrapper::getBitSet)
                .collect(Collectors.toList());
    }
}
