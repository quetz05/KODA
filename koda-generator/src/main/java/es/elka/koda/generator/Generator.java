package es.elka.koda.generator;

import org.apache.commons.math3.distribution.AbstractRealDistribution;

/**
 * Główna klasa generatora.
 */
public class Generator {

    private AbstractRealDistribution distribution;

    private double[] items;
    private Integer size;

    /***
     * Ustawienie rozkładu
     *
     * @param distribution rozkład
     * @return obiekt tej klasy
     */
    public Generator setDistribution(AbstractRealDistribution distribution) {
        this.distribution = distribution;
        return this;
    }

    /***
     * Wygenerowanie wartości siza * size
     *
     * @param size pierwiastek kwadratowy z liczba wartosci do wygenerowania
     * @return obiekt tej klasy
     */
    public Generator generate(Integer size) {

        this.size = size;

        int numOfItems = size * size;

        //FIXME brak dystrybucji
        items = new double[numOfItems];


        int generatedNumber = 0;
        do {
            double value = distribution.sample();
            if (value >= 0 && value <= 255) {
                items[generatedNumber] = value;
                generatedNumber++;
            }
        } while (generatedNumber < numOfItems);

        return this;
    }

    /***
     * Narysowanie histogramu
     *
     * @return obiekt tej klasy
     */
    public Generator drawHistogram() {
        new HistogramDrawer().draw(this.items);
        return this;
    }

    /***
     * Zapis do pliku
     *
     * @return obiekt tej klasy
     */
    public Generator saveFile() {
        new SaveToPGMFile().save(this.items, this.size, this.size);
        return this;
    }

}
