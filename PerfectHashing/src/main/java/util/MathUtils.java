package util;

public interface MathUtils {

    static int nextPowerOfTwo(int n) {
        return n <= 0 ? 1 : 1 << ceilLog2(n);
    }

    static int ceilLog2(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n must be positive");

        return 32 - Integer.numberOfLeadingZeros(n - 1);
    }
}
