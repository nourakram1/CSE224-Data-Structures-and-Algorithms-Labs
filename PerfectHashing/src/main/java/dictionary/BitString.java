package dictionary;

import representation.BinaryRepresentable;

public class BitString implements BinaryRepresentable {
    private final String binaryString;

    public BitString(String binaryString) {
        this.binaryString = binaryString;
    }

    @Override
    public String getBinaryRepresentation() {
        return binaryString;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof BitString && ((BitString) o).binaryString.equals(binaryString);
    }

    @Override
    public int hashCode() {
        return binaryString.hashCode();
    }
    
}
