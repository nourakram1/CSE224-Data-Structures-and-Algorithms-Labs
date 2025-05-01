package table;

import factory.StubFactory;
import stub.Stub;

class HashtableTest extends AbstractTableTest<Stub, Hashtable<Stub>> {

    HashtableTest() {
        super(new StubFactory());
    }

    @Override
    public Hashtable<Stub> createTable(int capacity) {
        return new Hashtable<>(capacity);
    }
}