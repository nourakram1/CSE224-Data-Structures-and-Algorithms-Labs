package table;

import factory.StubFactory;
import stub.Stub;

public class HierarchicalHashtableTest
        extends AbstractTableTest<Stub, HierarchicalHashtable<Stub>> {

    HierarchicalHashtableTest() {
        super(new StubFactory());
    }

    @Override
    public HierarchicalHashtable<Stub> createTable(int capacity) {
        return new HierarchicalHashtable<>(capacity);
    }
}