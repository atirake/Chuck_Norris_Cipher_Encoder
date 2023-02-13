package chucknorris;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            StringBuilder stringBuilder = new StringBuilder();
            System.out.println("Please input operation (encode/decode/exit):");
            String inputString = scanner.nextLine();
            switch (inputString) {
                case "encode" -> encode(scanner, stringBuilder);
                case "decode" -> decode(scanner, stringBuilder);
                case "exit" -> {
                    System.out.println("Bye!");
                    System.exit(0);
                }
                default -> System.out.println("There is no '" + inputString + "' operation");
            }
        }
    }

    public static void encode(Scanner scanner, StringBuilder stringBuilder) {
        System.out.println("Input string:");
        char[] inputString = scanner.nextLine().toCharArray();

        for (char c : inputString) {
            StringBuilder stringBuilder1 = new StringBuilder(Integer.toBinaryString(c));
            stringBuilder1.insert(0, "0".repeat(Math.max(0, 7 - stringBuilder1.length())));
            stringBuilder.append(stringBuilder1);
        }
        displayEncodedString(stringBuilder);
    }

    public static void displayEncodedString(StringBuilder stringBuilder) {
        System.out.println("Encoded string:");
        while (!stringBuilder.isEmpty()) {
            if (stringBuilder.charAt(0) == '1') {
                System.out.print("0 ");
                while (!stringBuilder.isEmpty()) {
                    if (stringBuilder.charAt(0) == '1') {
                        System.out.print("0");
                        stringBuilder.deleteCharAt(0);
                    } else {
                        System.out.print(" ");
                        break;
                    }
                }
            } else {
                System.out.print("00 ");
                while (!stringBuilder.isEmpty()) {
                    if (stringBuilder.charAt(0)  == '0') {
                        System.out.print("0");
                        stringBuilder.deleteCharAt(0);
                    } else {
                        System.out.print(" ");
                        break;
                    }
                }
            }
        }
        System.out.println();
    }

    public static void decode(Scanner scanner, StringBuilder stringBuilder) {
        boolean valid = true;
        System.out.println("Input encoded string:");
        String[] inputString = scanner.nextLine().split(" ");
        int i = 0;
        while (i < inputString.length) {
            if (inputString[i].equals("0")) {
                try {
                    stringBuilder.append("1".repeat(inputString[i + 1].length()));
                } catch (ArrayIndexOutOfBoundsException e) {
                    valid = false;
                    break;
                }

            } else if (inputString[i].equals("00")) {
                try {
                    stringBuilder.append("0".repeat(inputString[i + 1].length()));
                } catch (ArrayIndexOutOfBoundsException e) {
                    valid = false;
                    break;
                }

            } else {
                valid = false;
                break;
            }
            i += 2;
        }
        if (stringBuilder.length() % 7 != 0) {
            valid = false;
        }
        if (valid) {
            displayDecodedString(stringBuilder);
        } else {
            System.out.println("Encoded string is not valid.");
        }
    }

    public static void displayDecodedString(StringBuilder stringBuilder) {
        System.out.println("Decoded string:");
        while (!stringBuilder.isEmpty()) {
            StringBuilder packet = new StringBuilder();
            char[] pack = stringBuilder.toString().toCharArray();
            for (int j = 0; j < 7; j++) {
                packet.append(pack[j]);
                stringBuilder.deleteCharAt(0);
            }
            int temp = Integer.parseInt(packet.toString(), 2);
            if (temp >= 0 && temp <= 9) {
                System.out.print(temp);
            } else {
                System.out.print((char) temp);
            }
        }
        System.out.println();
    }
}