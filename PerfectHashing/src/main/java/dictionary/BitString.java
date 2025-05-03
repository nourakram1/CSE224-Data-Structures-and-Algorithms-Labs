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
        if (this == o) return true;
        if (!(o instanceof BitString)) return false;
        BitString that = (BitString) o;
        return binaryString.equals(that.binaryString);
    }

    @Override
    public int hashCode() {
        return binaryString.hashCode();
    }
    
}
