import java.util.Scanner;

/**
 * Intro2CS, Ex1 - very basic "main template"
 * Make sure your implementation of this main performs as the Ex1Sol.jar solution implement all needed functions.
 *
 */
public class Ex1Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num1 = "", num2 = "", quit = "quit";
        while (!num1.equals(quit)) {
            System.out.println();
            System.out.println("Ex1 class solution:");
            System.out.println("Enter a string as number#1 (or \"quit\" to end the program): ");
            num1 = sc.next();
            if (!num1.equals("quit")) {
                boolean n1IsNumber = Ex1.isNumber(num1);
                int n1Value = Ex1.number2Int(num1);
                System.out.println("num1= " + num1 + " is number: " + n1IsNumber + " , value: " + n1Value);
                if (!n1IsNumber) {
                    System.out.println("ERROR: num1 is in the wrong format! (" + num1 + ")");
                    break;
                }

                // ask for second number
                System.out.println("Enter a string as number#2 (or \"quit\" to end the program): ");
                num2 = sc.next();
                if (num2.equals(quit)) {
                    break;
                }

                // check if num2 is valid
                boolean n2IsNumber = Ex1.isNumber(num2);
                int n2Val = Ex1.number2Int(num2);
                System.out.println("num2= " + num2 + " is number: " + n2IsNumber + " , value: " + n2Val);
                if (!n2IsNumber) {
                    System.out.println("ERR0R: num2 is in the wrong format! (" + num2 + ")");
                    break;
                }

                // asking for the base of the output
                System.out.println("Enter a base for output: (a number [2,16])");
                int outBase = sc.nextInt();
                if (outBase < 2 || outBase > 16) {
                    System.out.println("ERR: The base must be between 2 and 16.");
                    break;
                }

                // calculate de sum of the product
                int sumVal = n1Value + n2Val;
                int mulVal = n1Value * n2Val;

                //convert to base
                String sumStr = Ex1.int2Number(sumVal, outBase);
                String mulStr = Ex1.int2Number(mulVal, outBase);
                //print result
                System.out.println(num1 + " + " + num2 + " = " + sumStr);
                System.out.println(num1 + " * " + num2 + " = " + mulStr);

                // find max value
                String[] arr = {num1, num2, sumStr, mulStr};
                int maxI = Ex1.maxIndex(arr);
                System.out.println("Max number over [" + arr[0] + ", " + arr[1] + ", " + arr[2] + ", " + arr[3] + "] is: " + arr[maxI]);
            }
        }
        System.out.println("quiting now...");
    }
}

