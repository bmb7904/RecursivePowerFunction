package bernardi;

import java.math.BigInteger;

/**
 * Personal project for writing a power function. Experimental comparison of time taken
 * for calculate the power method.
 *
 * @author Brett Bernardi
 * @date October 14, 2021
 */
public class Main {

    static long counter = 0;

    public static void main(String[] args) {
        double base = 1.0000;
        int exponent = -2147483648;

        double result;
        long startTime = System.nanoTime();
        result = pow(base, exponent);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;

        System.out.println("Result of POW: " + result);
        System.out.println("In " + totalTime + " nanoseconds!");

        startTime = System.nanoTime();
        result = powSlower(base, exponent);
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Slower Recursive version: " + result);
        System.out.println("In " + totalTime + " nanoseconds!");

        startTime = System.nanoTime();
        result = powIterative(base, exponent);
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Iteraitve version: " + result);
        System.out.println("In " + totalTime + " nanoseconds!");

        System.out.println("\n\n\n\n" +  counter  + " recursive calls!");


    }

    /**
     * I started by using "squaring method" for this power function. This is based on
     * the fact that, for all x such that x ∈ [-∞, ∞], and for all n such that n ∈ ℤ,
     *
     *      x^n = x^(n/2) * x^(n/2), if n = 2n for all N.
     *
     * And,
     *      x^n = x^(n/2) * x^(n/2) * x,  if n = 2n + 1 for all N.
     *
     *      And the base cases:
     *
     *      x^n = 1, if n = 0.
     *      x^n = x, if n = 1.
     *
     * Also,
     *      x^n = ( 1 / x^(-n)), if n < 0.
     *
     * Using this recursive relationship, you can break the power function down into
     * smaller problems. Unfortunately, it is not faster than the naive, iterative
     * method for the power function. You are still doing the same amount of
     * calculations. If you store the result of the pow() function and use it instead
     * of recursively calling it in in certain situations, you can cut the number of
     * calculations down. For instance, 2^4 is 3 calculations for thenaive, iterative
     * power method as well as the regular recursive method. For my modified recursive
     * method, however, it is only 2 calculations! This difference becomes significant
     * as n -> ∞.
     * <p>
     * This method much quicker when I compared it experimentally against the naive,
     * iterative and the regular recursive, both below.
     *
     * @author Brett Bernardi
     */
    public static double pow(double x, int n) {

        // base case
        if (n == 1) {
            return x;
        }
        // if n == 0, regardless of base, return 1.
        if(n == 0) {
            return 1.0;
        }

        // for negative exponents, find pow(x, abs(n)), and return reciprocal.
        if(n < 0) {
            return (1 / pow(x, n*-1));
        }

        // If n is not divisible by 2.
        if (n % 2 != 0) {
            double out2 = pow(x, n / 2);
            return out2 * out2 * x;
        }

        // save computation by storing it in variable, and using that result in the
        // calculation. There are many different out's, during the course of this
        // function recursively calling itself!
        double out = pow(x, n / 2);
        return out * out;
    }

    /**
     * Slower Recursive Version! No optimization.
     */
    public static double powSlower(double x, int n) {

        if (n == 1) {
            return x;
        }

        if (n % 2 != 0) {
            return powSlower(x, n / 2) * powSlower(x, n / 2) * x;
        }

        // if n == 0, regardless of base, return 1.
        if(n == 0) {
            return 1.0;
        }

        // for negative exponents, find pow(x, abs(n)), and return reciprocal.
        if(n < 0) {
            return (1 / pow(x, n*-1));
        }

        return powSlower(x, n / 2) * powSlower(x, n / 2);
        // save computation by storing it in variable
        /*double out = pow(x, n/2);
        System.out.println("Multiplying " + x + "^" + n + " * " + x + "^" + n);
        return out * out;*/

    }

    /**
     * The naive, iterative method for the power function.
     */
    public static double powIterative(double x, int n) {
        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            double result = x;
            n *= -1;
            for (int i = 1; i < n; i++) {
                result *= x;
            }

            return (1 / result);
        }

        double result = x;
        for (int i = 1; i < n; i++) {
            result *= x;
        }

        return result;
    }
}

