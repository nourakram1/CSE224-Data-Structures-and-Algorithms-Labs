package hash;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UniversalHash {
    private final int BINARY_LENGTH;
    private final int blocks;
    private final List<BigInteger> h;

    public UniversalHash(int capacity, int fixedBinaryLength) {
        this.blocks = ceilLog2(capacity);
        this.BINARY_LENGTH = fixedBinaryLength;
        this.h = new ArrayList<>(blocks);

        Random rand = new Random();
        for(int i = 0; i < blocks; i++) {
            h.add(new BigInteger(this.BINARY_LENGTH, rand));
        }
    }

    public int hash(String binaryRepresentation) {
        if (binaryRepresentation.length() != this.BINARY_LENGTH)
            throw new IllegalArgumentException("Invalid Binary Representation Length.");

        int hashCode = 0;
        BigInteger x = new BigInteger(binaryRepresentation, 2);

        for(int i = 0; i < this.blocks; i++) {
            hashCode |= (h.get(i).and(x).bitCount() % 2) << i;
        }

        return hashCode;
    }

    private int ceilLog2(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        // if n is already a power of two, we want exactly log2(n),
        // otherwise we want the next integer.
        // (n - 1) shifts any power‐of‐two boundary down to the previous block.
        return 32 - Integer.numberOfLeadingZeros(n - 1);
    }
}