package table;

import factory.StubFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.RepetitionInfo;
import stub.Stub;
import table.instrument.InstrumentedHashtable;

public class InstrumentedHashtableTest
        extends AbstractTableTest<Stub, InstrumentedHashtable<Stub>> {

    private int totalCollisionCount = 0;
    private int totalGrowCount = 0;
    private int totalRehashCount = 0;


    public InstrumentedHashtableTest() {
        super(new StubFactory());
    }

    @Override
    protected InstrumentedHashtable<Stub> createTable(int capacity) {
        return new InstrumentedHashtable<>();
    }

    @AfterEach
    public void tearDown(RepetitionInfo repetitionInfo) {
        totalCollisionCount += table.getCollisionCount();
        totalGrowCount += table.getGrowCount();
        totalRehashCount += table.getRehashCount();

        if (repetitionInfo.getCurrentRepetition() == REPEAT_COUNT) {
            System.out.printf("In %d repetitions:%n", REPEAT_COUNT);
            System.out.printf("Number of collisions: %d%n", totalCollisionCount);
            System.out.printf("Number of grows: %d%n", totalGrowCount);
            System.out.printf("Number of rehashes: %d%n", totalRehashCount);
        }
    }
}