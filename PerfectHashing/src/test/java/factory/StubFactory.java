package factory;

import stub.Stub;
import util.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class StubFactory implements Factory<Stub> {

    protected final int MAX_LIST_SIZE = 10;
    protected final Random rand = new Random();

    @Override
    public Stub createInstance() {
        return new Stub(rand.nextInt());
    }

    @Override
    public List<Stub> createUniqueInstances() {
        return ListUtils.unique(createInstances());
    }

    @Override
    public List<Stub> createDuplicateInstances(List<Stub> uniqueStubs) {
        List<Stub> duplicateStubs = new ArrayList<>(ListUtils.merge(uniqueStubs, uniqueStubs));
        Collections.shuffle(duplicateStubs);
        return duplicateStubs;
    }

    private List<Stub> createInstances() {
        return ListUtils.arrayList(rand.nextInt(MAX_LIST_SIZE), this::createInstance);
    }
}
