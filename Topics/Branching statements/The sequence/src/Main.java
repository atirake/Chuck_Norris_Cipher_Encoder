import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int elements = 0;
        boolean check = false;
        for (int i = 1; i <= count && !check; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(i + " ");
                elements += 1;
                if (elements == count) {
                    check = true;
                    break;
                }
            }
        }
    }
}