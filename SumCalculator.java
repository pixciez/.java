// Easy Level: Sum of Integers using Autoboxing/Unboxing
import java.util.*;

public class SumCalculator {
    public static int calculateSum(List<Integer> numbers) {
        Integer sum = 0; // Autoboxing
        for (Integer num : numbers) {
            sum += num; // Autounboxing
        }
        return sum;
    }
    
    public static List<Integer> parseStringArray(String[] strNumbers) {
        List<Integer> numbers = new ArrayList<>();
        for (String str : strNumbers) {
            numbers.add(Integer.parseInt(str)); // Parsing string to Integer (autoboxing)
        }
        return numbers;
    }
    
    public static void main(String[] args) {
        String[] input = {"1", "2", "3", "4", "5"};
        List<Integer> numbers = parseStringArray(input);
        int sum = calculateSum(numbers);
        System.out.println("Sum: " + sum);
    }
}

/* Objective: Learn autoboxing/unboxing and string parsing in Java
 * Learning Outcomes:
 * 1. Understand Wrapper classes and their parsing methods
 * 2. Learn autoboxing/unboxing behavior
 * 3. Convert String inputs to Integer and perform calculations
 */
