/**
 * This class represents a simple solution for Ex1.
 * As defined here: https://docs.google.com/document/d/1AJ9wtnL1qdEs4DAKqBlO1bXCM6r6GJ_J/r/edit/edit
 * In this assignment, we will design a number formatting converter and calculator.
 * In general, we will use Strings as numbers over basis of binary till Hexa.
 * [2-16], 10-16 are represented by A,B,..G.
 * The general representation of the numbers is as a String with the following format:
 * <number><b><base> e.g., “135bA” (i.e., “135”, as 10 is the default base), “100111b2”, “12345b6”,”012b5”, “123bG”, “EFbG”.
 * The following are NOT in the format (not a valid number):
 * “b2”, “0b1”, “123b”, “1234b11”, “3b3”, “-3b5”, “3 b4”, “GbG”, "", null,
 * You should implement the following static functions:
 */
public class Ex1 {
    /**
     * Convert the given number (num) to a decimal representation (as int).
     * It the given number is not in a valid format returns -1.
     *
     * @param num a String representing a number in basis [2,16]
     * @return
     */
    public static int number2Int(String num) {
        if (!isNumber(num)) {
            return -1;
        }

        // split the chain in 2
        String[] parts = num.split("b");
        if (parts.length != 2) {
            return -1;
        }

        String numString = parts[0];
        String basePart = parts[1];

        // find the base
        int baseInt;
        if (Character.isDigit(basePart.charAt(0))) {
            baseInt = Integer.parseInt(basePart); // if the base is a number
        } else if (basePart.charAt(0) >= 'A' && basePart.charAt(0) <= 'G') {
            baseInt = basePart.charAt(0) - 'A' + 10; // if the base is a letter
        } else {
            return -1; // Invalid base
        }

        // Converting the number
        int ans = 0;
        for (int j = 0; j < numString.length(); j++) {
            char c = numString.charAt(j);
            int digit;
            if (Character.isDigit(c)) {
                digit = c - '0'; // If char is a letter , convert it to its numerical value
            } else {
                digit = c - 'A' + 10; // else, calculate the value of the letter
            }
            if (digit < 0 || digit >= baseInt) {
                return -1; // number not in the base
            }
            ans = ans * baseInt + digit;
        }
        return ans;
    }



    /**
     * This static function checks if the given String (g) is in a valid "number" format.
     *
     * @param a a String representing a number
     * @return true iff the given String is in a number format
     */
    public static boolean isNumber(String a) {
        // check for the validity of the chain
        if (a == null || a.isEmpty()) {
            return false;
        }

        // if there is no b , it must be in base 10
        if (!a.contains("b")) {
            for (int i = 0; i < a.length(); i++) {
                if (!Character.isDigit(a.charAt(i))) {
                    return false;
                }
            }
            return true;
        }

        // split into base and number
        String[] parts = a.split("b");
        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            return false; // invalid format
        }

        String numberPart = parts[0];
        String basePart = parts[1];

        // check for the base
        int base;
        if (Character.isDigit(basePart.charAt(0))) {
            base = Integer.parseInt(basePart);
        } else if (basePart.charAt(0) >= 'A' && basePart.charAt(0) <= 'G') {
            base = basePart.charAt(0) - 'A' + 10;
        } else {
            return false; //wrong base
        }

        if (base < 2 || base > 16) {
            return false; // base not in the interval
        }

        // check for every letter of number part
        for (int i = 0; i < numberPart.length(); i++) {
            char c = numberPart.charAt(i);
            int digit;
            if (Character.isDigit(c)) {
                digit = c - '0'; // If char is a number , convert it to its numerical value (from ascii)
            } else {
                digit = c - 'A' + 10; // else, calculate the value of the letter
            }
            if (digit < 0 || digit >= base) {
                return false; // invalid base
            }
        }

        return true;
    }




    /**
         * Calculate the number representation (in basis base)
         * of the given natural number (represented as an integer).
         * If num<0 or base is not in [2,16] the function should return "" (the empty String).
         *
         * @param num  the natural number (include 0).
         * @param base the basis [2,16]
         * @return a String representing a number (in base) equals to num, or an empty String (in case of wrong input).
         */
        public static String int2Number(int num, int base) {
            String ans = "";
            if (num < 0 || base < 2 || base > 16) {
                return ans;
            }

            if (num == 0) {
                ans = "0b" + base; // Special case for 0
                return ans;
            }

            StringBuilder numString = new StringBuilder();
            int result = num;

            while (result != 0) {
                int remainder = result % base;
                if (remainder < 10) {
                    numString.append((char) ('0' + remainder)); // add number from 1 to 9
                } else {
                    numString.append((char) ('A' + (remainder - 10))); // add letters
                }
                result /= base;
            }

            numString.reverse(); // mirror to get the right string

            ans = numString + "b" + base;
            return ans;
        }

    /**
     * Checks if the two numbers have the same value.
     *
     * @param n1 first number
     * @param n2 second number
     * @return true iff the two numbers have the same values.
     */
    public static boolean equals(String n1, String n2) {
        boolean ans = true;

        if (n1 == null || n2 == null) {
            ans = false;
        } else {
            int value1 = number2Int(n1);
            int value2 = number2Int(n2);

            if (value2 == -1 || value1 != value2) {
                ans = false;
            }
        }

        return ans;
    }

    /**
     * This static function search for the array index with the largest number (in value).
     * In case there are more than one maximum - returns the first index.
     * Note: you can assume that the array is not null and is not empty, yet it may contain null or none-valid numbers (with value -1).
     *
     * @param arr an array of numbers
     * @return the index in the array in with the largest number (in value).
     */
    public static int maxIndex(String[] arr) {
            int ans = -1;
            int maxValue = Integer.MIN_VALUE;
            for (int i = 0; i < arr.length; i++) {
                if(!isNumber(arr[i])){
                    continue;
                }
                if(number2Int(arr[i])>maxValue){
                    maxValue = number2Int(arr[i]);
                    ans = i;

                }
            }

            return ans; // Return the index of the maximum valid number
        }

    }

