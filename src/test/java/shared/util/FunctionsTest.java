package shared.util;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import shared.util.Functions;

public class FunctionsTest {

    @Test
    public void testLog2() {
        assertEquals(3.0, Functions.log2(8.0), 1e-20);
    }
}
