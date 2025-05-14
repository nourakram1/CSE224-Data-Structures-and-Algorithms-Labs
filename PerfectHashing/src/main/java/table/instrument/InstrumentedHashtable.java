package table.instrument;

import hash.UniversalHash;
import representation.BinaryRepresentable;
import table.Hashtable;

import java.util.List;

public class InstrumentedHashtable<T extends BinaryRepresentable>
        extends Hashtable<T> {

    private int growCount = 0;
    private int rehashCount = 0;
    private int collisionCount = 0;

    @Override
    protected void grow() {
        growCount++;
        super.grow();
    }

    @Override
    protected boolean hash(List<T> elements, List<T> into, UniversalHash<T> universalHash) {
        rehashCount++;
        return super.hash(elements, into, universalHash);
    }

    @Override
    protected boolean collision(T value) {
        if (super.collision(value)) {
            collisionCount++;
            return true;
        }
        return false;
    }

    public int getGrowCount() {
        return growCount;
    }

    public int getRehashCount() {
        return rehashCount;
    }

    public int getCollisionCount() {
        return collisionCount;
    }
}