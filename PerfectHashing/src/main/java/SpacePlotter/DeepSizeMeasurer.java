package SpacePlotter;

import java.util.List;
import java.util.stream.Collectors;

import org.openjdk.jol.info.GraphLayout;

public class DeepSizeMeasurer {

    /**
     * Measure deep size (in MB) for each n in ns using the provided factory.
     * @param ns list of table sizes
     * @param factory factory to create table instances
     * @return list of sizes in megabytes
     */
    public List<Double> measure(List<Integer> ns, TableFactory factory) {
        return ns.stream()
            .map(n -> {
                System.gc();
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}

                Object table = factory.create(n);
                long sizeBytes = GraphLayout.parseInstance(table).totalSize();
                return sizeBytes / (1024.0 * 1024.0); // Convert to MB
            })
            .collect(Collectors.toList());
    }
}
