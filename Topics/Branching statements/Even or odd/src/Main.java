import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int input = scanner.nextInt();
            if (input == 0) {
                break;
            } else {
                System.out.println(input % 2 == 0 ? "even" : "odd");
            }
        }
    }
}