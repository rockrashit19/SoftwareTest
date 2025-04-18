package task.task1;

public class TaylorSeries {
    public static double calculateCosSeries(double x, int N){
        if (N <= 0) {
            return 0.0;
        }

        x = x % (2 * Math.PI);

        double sum = 0.0;
        double term = 1.0;
        sum = sum + term;
        for (int n = 1; n < N; n++) {
            term = -term * x * x / ((2 * n) * (2 * n - 1));
            sum = sum + term;
        }
        return sum;
    }
}

