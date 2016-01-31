package es.elka.koda.app.decoder;

import java.util.Arrays;

public class CodeWrapper {

    public Boolean[] bb;

    public CodeWrapper(Boolean[] bb) {
        this.bb = bb;

//        if(swap) {
//            for (int j = bb.length; j > 0; --j) {
//                this.bb[bb.length - j] = bb[j - 1];
//            }
//        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CodeWrapper mojMap = (CodeWrapper) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(bb, mojMap.bb);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bb);
    }
}
