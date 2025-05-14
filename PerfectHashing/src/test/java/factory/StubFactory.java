package factory;

import stub.Stub;
import util.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class StubFactory implements Factory<Stub> {

    protected static final int MAX_LIST_SIZE = 10;
    protected static final Random rand = new Random();

    @Override
    public Stub createInstance() {
        return new Stub(rand.nextInt());
    }

    @Override
    public List<Stub> createInstances() {
        return createInstances(rand.nextInt(MAX_LIST_SIZE));
    }

    @Override
    public List<Stub> createInstances(int n) {
        if (n < 0)
            throw new IllegalArgumentException("The number of instances cannot be negative");

        return ListUtils.arrayList(n, this::createInstance);
    }

    @Override
    public List<Stub> createDuplicateInstances(List<Stub> uniqueStubs) {
        List<Stub> duplicateStubs = new ArrayList<>(ListUtils.merge(uniqueStubs, uniqueStubs));
        Collections.shuffle(duplicateStubs);
        return duplicateStubs;
    }
}