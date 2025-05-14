package plot.space;

import org.openjdk.jol.info.GraphLayout;
import table.Table;

import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

public class DeepSizeMeasurer {

    /**
     * Measure deep size (in MB) for each n in ns using the provided factory.
     * @param ns list of table sizes
     * @param factory factory to create table instances
     * @return list of sizes in megabytes
     */
    public List<Double> measure(List<Integer> ns, IntFunction<Table<?>> factory) {
        return ns.stream()
            .map(n -> {
                System.gc();
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}

                Table<?> table = factory.apply(n);
                long sizeBytes = GraphLayout.parseInstance(table).totalSize();
                return sizeBytes / (1024.0 * 1024.0); // Convert to MB
            })
            .collect(Collectors.toList());
    }
}