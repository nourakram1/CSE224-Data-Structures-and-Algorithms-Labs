package factory;

import java.util.List;

public interface Factory<S> {

    S createInstance();

    List<S> createUniqueInstances();

    List<S> createDuplicateInstances(List<S> uniqueInstances);
}