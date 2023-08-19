import java.util.Scanner;
public class FundAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double balance = 0.0;

        while (true) {
            System.out.println("Finance Analyzer App");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. Check Balance");
            System.out.println("4. Leave");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter income amount: ");
                    double income = scanner.nextDouble();
                    balance += income;
                    System.out.println("Income added successfully");
                    break;
                case 2:
                    System.out.print("Enter expense amount ");
                    double expense = scanner.nextDouble();
                    balance -= expense;
                    System.out.println("Expense added successfully.");
                    break;
                case 3:
                    System.out.println("Current balance: $" + balance);
                    break;
                case 4:
                    System.out.println("Exiting the app.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
