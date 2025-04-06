import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class TaylorSeriesTest {
    private static final double DELTA = 1e-8;
    private static final double LOW_ACC = 1e-2;
    private static final double HIGH_ACC = 1e-12;
    double pInf = Double.POSITIVE_INFINITY;
    double nInf = Double.NEGATIVE_INFINITY;

    @Test
    @DisplayName("Test cos(0)")
    void testCosAtZero() {
        double result = TaylorSeries.calculateCosSeries(0, 10);
        assertEquals(1, result, DELTA, "cos(0) should be 1.0");
    }

    @ParameterizedTest
    @DisplayName("Test small positive x values")
    @CsvSource({
            "0.5235987756, 10",
            "0.5, 10"
    })
    void testCosSmallPositive(double x, int n){
        double expected = Math.cos(x);
        double result = TaylorSeries.calculateCosSeries(x, n);
        assertEquals(expected, result, DELTA, "Test failed for x = " + x);
    }

    @ParameterizedTest
    @CsvSource({
            "-0.5235987756, 10",
            "-0.5, 10"
    })
    @DisplayName("Test small negative x values (check symmetry)")
    void testCosSmallNegative(double x, int n) {
        double expected = Math.cos(x); // Math.cos is already symmetric
        double actual = TaylorSeries.calculateCosSeries(x, n);
        assertEquals(expected, actual, DELTA, "Test failed for x = " + x);
    }

    @ParameterizedTest
    @CsvSource({
            "1.5707963268, 10",
            "1.57, 10"
    })
    @DisplayName("Test x near PI/2 (cos(x) near 0)")
    void testCosNearPiOver2(double x, int n) {
        double expected = Math.cos(x);
        double actual = TaylorSeries.calculateCosSeries(x, n);
        assertEquals(expected, actual, DELTA, "Test failed for x = " + x + " (expected near 0)");
    }

    @ParameterizedTest
    @CsvSource({
            "3.1415926536, 10",
            "3.14, 10"
    })
    @DisplayName("Test x near PI (cos(x) near -1)")
    void testCosNearPi(double x, int n) {
        double expected = Math.cos(x);
        double actual = TaylorSeries.calculateCosSeries(x, n);
        assertEquals(expected, actual, DELTA, "Test failed for x = " + x + " (expected near -1)");
    }

    @ParameterizedTest
    @CsvSource({
            "6.2831853072, 10",
            "10.0, 10",
            "-10.0, 10",
            "4.0, 10"
    })
    @DisplayName("Test large |x| values (accuracy depends on N)")
    void testCosLargeX(double x, int n) {
        double expected = Math.cos(x);
        double actual = TaylorSeries.calculateCosSeries(x, n);
        assertEquals(expected, actual, LOW_ACC,
                "Test failed for large x = " + x);
    }

    @Test
    @DisplayName("Test N = 0")
    void testCosNZero() {
        assertEquals(0.0, TaylorSeries.calculateCosSeries(1.0, 0), DELTA,
                "N=0 should result in sum 0.0");
    }

    @Test
    @DisplayName("Test N = 1")
    void testCosNOne() {
        assertEquals(1.0, TaylorSeries.calculateCosSeries(1.0, 1), DELTA,
                "N=1 should result in sum 1.0 (first term)");
        assertEquals(1.0, TaylorSeries.calculateCosSeries(5.0, 1), DELTA,
                "N=1 should result in sum 1.0 (first term)");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -5})
    @DisplayName("Test negative N")
    void testCosNNegative(int n) {
        assertEquals(0.0, TaylorSeries.calculateCosSeries(1.0, n), DELTA,
                "Negative N should result in sum 0.0");
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            0.0,
            0.7853981634,
            1.5707963268,
            3.1415926536,
            -0.5235987756,
            4.0
    })
    @DisplayName("Test Periodicity cos(x) approx equals cos(x + 2pi)")
    void testCosPeriodicityPlus2Pi(double x) {
        int n = 20;
        double result_x = TaylorSeries.calculateCosSeries(x, n);
        double result_x_plus_2pi = TaylorSeries.calculateCosSeries(x + 2*Math.PI, n);

        assertEquals(result_x, result_x_plus_2pi, DELTA,
                "Periodicity (+2pi) test failed for x = " + x + " with N = " + n);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            0.0,
            0.7853981634,
            1.5707963268,
            3.1415926536,
            -0.5235987756,
            4.0
    })
    @DisplayName("Test Periodicity cos(x) approx equals cos(x - 2pi)")
    void testCosPeriodicityMinus2Pi(double x) {
        int n = 20;
        double result_x = TaylorSeries.calculateCosSeries(x, n);
        double result_x_plus_2pi = TaylorSeries.calculateCosSeries(x - 2*Math.PI, n);

        assertEquals(result_x, result_x_plus_2pi, DELTA,
                "Periodicity (-2pi) test failed for x = " + x + " with N = " + n);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            0.0,
            0.7853981634,
            1.5707963268,
            3.1415926536,
            -0.5235987756,
            4.0
    })
    @DisplayName("Test Periodicity cos(x) approx equals cos(x + 4pi)")
    void testCosPeriodicityPlus4Pi(double x) {
        int n = 20;
        double result_x = TaylorSeries.calculateCosSeries(x, n);
        double result_x_plus_2pi = TaylorSeries.calculateCosSeries(x + 4*Math.PI, n);

        assertEquals(result_x, result_x_plus_2pi, DELTA,
                "Periodicity (+4pi) test failed for x = " + x + " with N = " + n);
    }

    @Test
    @DisplayName("Check with low accuracy")
    void testLowAcc(){
        double x = 0.7853981634;
        double result = TaylorSeries.calculateCosSeries(x, 10);
        double expected = Math.cos(x);
        assertEquals(expected, result, LOW_ACC, "Test failed for acc = " + LOW_ACC);
    }

    @Test
    @DisplayName("Check with high accuracy")
    void testHighAcc(){
        double x = 0.7853981634;
        double result = TaylorSeries.calculateCosSeries(x, 10);
        double expected = Math.cos(x);
        assertEquals(expected, result, HIGH_ACC, "Test failed for acc = " + HIGH_ACC);
    }

    @Test
    @DisplayName("Check with positive inf")
    void testPositiveInf(){
        double expected = Math.cos(pInf);
        double result = TaylorSeries.calculateCosSeries(pInf, 10);
        assertEquals(expected, result, DELTA, "Test failed for x = inf");
    }

    @Test
    @DisplayName("Check with negative inf")
    void testNegativeInf(){
        double expected = Math.cos(nInf);
        double result = TaylorSeries.calculateCosSeries(nInf, 10);
        assertEquals(expected, result, DELTA, "Test failed for x = -inf");
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            0.1,
            0.5,
            Math.PI / 6,
            1.0,
            Math.PI / 2,
            Math.PI,
            2.5,
            4.0
    })
    @DisplayName("Test Even Function Property: cos(x) = cos(-x)")
    void testSymmetryProperty(double positiveX) {
        int n = 10;
        double resultPositive = TaylorSeries.calculateCosSeries(positiveX, n);
        double resultNegative = TaylorSeries.calculateCosSeries(-positiveX, n);
        assertEquals(resultPositive, resultNegative, HIGH_ACC,
                "Symmetry property f(x) = f(-x) failed for x = " + positiveX + " with N = " + n);
    }
}



