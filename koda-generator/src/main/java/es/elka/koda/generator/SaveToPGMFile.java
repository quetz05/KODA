package es.elka.koda.generator;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.StatUtils;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;


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

        byte[] bytes = new byte[values.length];
        for (int i = 0; i < values.length; ++i) {
            bytes[i] = (byte) ((int) (values[i]));
        }
        try {
            FileOutputStream fos = new FileOutputStream("output.pgm");
            fos.write(this.writeHeader());
            int x = 0;
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    fos.write(bytes[x]);
                    x++;
                }
                fos.write("\n".getBytes());
            }
            fos.close();
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
    private byte[] writeHeader() throws Exception {
        ArrayList<Byte> arrayList = new ArrayList<>();

        Collections.addAll(arrayList, ArrayUtils.toObject("P2\n".getBytes()));
        Collections.addAll(arrayList, ArrayUtils.toObject("# KODA Generator Elka.Es.Chu.Wdu\n".getBytes()));
        arrayList.add(width.byteValue());
        Collections.addAll(arrayList, ArrayUtils.toObject(" ".getBytes()));
        arrayList.add(height.byteValue());
        Collections.addAll(arrayList, ArrayUtils.toObject("\n".getBytes()));
        arrayList.add(new Byte((byte) findMaxNumber()));
        Collections.addAll(arrayList, ArrayUtils.toObject("\n".getBytes()));
        byte[] bytes = new byte[arrayList.size()];

        for (int i = 0; i < arrayList.size(); ++i) {
            bytes[i] = arrayList.get(i);
        }


        return bytes;
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
