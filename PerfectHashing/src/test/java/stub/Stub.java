package stub;

import org.apache.commons.lang3.StringUtils;
import representation.BinaryRepresentable;

public class Stub implements BinaryRepresentable {

    private final Integer value;

    public Stub(Integer value) {
        this.value = value;
    }

    @Override
    public String getBinaryRepresentation() {
        return StringUtils.leftPad(Integer.toBinaryString(value), Integer.SIZE, '0');
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Stub && value.equals(((Stub) o).value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}