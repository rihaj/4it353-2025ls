package calculation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class EulersNumber {
    private static final MathContext MC = new MathContext(10000, RoundingMode.HALF_UP);
    private static final int ITERATIONS = 10000;

    public static BigDecimal calculate() {
        BigDecimal eulersNumber = BigDecimal.ONE;
        BigDecimal factorial = BigDecimal.ONE;

        for (int i = 1; i <= ITERATIONS; i++) {
            factorial = factorial.multiply(BigDecimal.valueOf(i));
            eulersNumber = eulersNumber.add(BigDecimal.ONE.divide(factorial, MC));
        }

        return eulersNumber;
    }
}
