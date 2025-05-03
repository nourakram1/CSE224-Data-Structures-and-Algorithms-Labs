package SpacePlotter;

@FunctionalInterface
public interface TableFactory {
    Object create(int n);
}