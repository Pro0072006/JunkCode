using System.Net.Http.Headers;

namespace Problems_Euler;

public class Problems
{
    public static int Problem_1_MultiplesOf3Or5(int number)
    {
        int sum = 0;

        for (int i = 3; i < number; i++)
        {
            if (i % 3 == 0 || i % 5 == 0)
                sum += i;
        }

        return sum;
    }

    public static int Problem_2_EvenFibonacci(int number)
    {
        int sum = 0;
        int a = 1, b = 2;

        if (b <= number)
            sum += b;

        while (true)
        {
            int nextNumber = a + b;
            a = b;
            b = nextNumber;

            if (b > number)
                break;

            if (b % 2 == 0)
                sum += b;
        }

        return sum;
    }

    public static int Problem_3_LargestPrimeFactor(int number)
    {
        List<int> factors = [];

        int divider = 2;
        int max = 0;
        int maxDivider = number;

        while (number > 1)
        {
            int times = 0;
            bool isDivisible = (number % divider) == 0;

            while (isDivisible)
            {
                times++;
                number /= divider;
                isDivisible = (number % divider) == 0;
            }

            if (times >= max)
            {
                max = times;
                maxDivider = divider;
            }

            divider++;
        }

        return maxDivider;
    }
}
