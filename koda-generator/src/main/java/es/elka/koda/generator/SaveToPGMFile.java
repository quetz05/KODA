package es.elka.koda.generator;

import org.apache.commons.math3.stat.StatUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;


/***
 * Klasa odpowiada za zapisanie wygenerowanych danych do pliku PGM.
 */
public class SaveToPGMFile {

    private Integer width;
    private Integer height;
    private double[] values;

    /***
     * Zapis danych do pliku PGM
     *
     * @param values tablica wygenerowanych danych
     * @param width  szerokosc obrazka
     * @param height wysokosc obrazka
     */
    public void save(double[] values, Integer width, Integer height) {
        this.values = values;
        this.width = width;
        this.height = height;

        try {
            FileWriter fstream = new FileWriter("output.pgm");
            BufferedWriter out = new BufferedWriter(fstream);

            // Naglowek
            out.write(this.writeHeader());

            int x = 0;
            for (int i = 0; i < width; i++)
                for (int j = 0; j < height; j++) {
                    out.write((int) values[x] + "  ");
                    x++;
                }
            out.write('\n');

            out.close();
        } catch (Exception e) {
            System.err.println("\tSaving error : " + e.getMessage());
        }
    }

    /***
     * Generowanie nagłówka do pliku PMG.
     *
     * @return String z nagłówkiem
     * @throws Exception W przypadku gdy nie ma wartosci do zapisania (brak najwiekszego elementu tablicy)
     */
    private String writeHeader() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("P2\n");
        sb.append("# KODA Generator Elka.Es.Chu.Wdu\n");
        sb.append(width);
        sb.append(" ");
        sb.append(height);
        sb.append("\n");
        sb.append((int) findMaxNumber());
        sb.append("\n");

        return sb.toString();
    }

    /***
     * Zwraca największą wartośc w tablicy.
     *
     * @return największą liczbę w tablicy wartości.
     * @throws Exception @throws Exception W przypadku gdy nie ma wartosci do zapisania (brak najwiekszego elementu tablicy)
     */
    private double findMaxNumber() throws Exception {

        double maxVal = StatUtils.max(values);

        if (maxVal == Double.NaN) {
            throw new Exception("Empty values list doesn't have max value");
        } else {
            return maxVal;
        }
    }
}
