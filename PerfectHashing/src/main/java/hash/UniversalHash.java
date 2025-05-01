package hash;

import representation.BinaryRepresentable;
import util.MathUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UniversalHash<T extends BinaryRepresentable> {
    private final int BINARY_LENGTH;
    private final int blocks;
    private final List<BigInteger> h;

    public UniversalHash(int capacity, int fixedBinaryLength) {
        this.blocks = MathUtils.ceilLog2(capacity);
        this.BINARY_LENGTH = fixedBinaryLength;
        this.h = new ArrayList<>(blocks);

        Random rand = new Random();
        for (int i = 0; i < blocks; i++) {
            h.add(new BigInteger(this.BINARY_LENGTH, rand));
        }
    }

    public int getHashCode(T value) {
        String binaryRepresentation = value.getBinaryRepresentation();
        if (binaryRepresentation.length() != this.BINARY_LENGTH)
            throw new IllegalArgumentException(
                    String.format("Binary length does not match, expected %d, got: %d.",
                            BINARY_LENGTH, binaryRepresentation.length()));

        int hashCode = 0;
        BigInteger x = new BigInteger(binaryRepresentation, 2);

        for (int i = 0; i < this.blocks; i++) {
            hashCode |= (h.get(i).and(x).bitCount() % 2) << i;
        }

        return hashCode;
    }
}