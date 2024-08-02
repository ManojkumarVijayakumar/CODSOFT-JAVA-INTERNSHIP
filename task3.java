import java.util.Scanner;
class Bank {
    private double bal;
    public Bank(double amount) {
        bal = amount;
    }
    public double getBalance() {
        return bal;
    }
    public boolean deposit(double amount) {
        if (amount > 0) {
            bal += amount;
            return true;
        }
        return false;
    }
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= bal) {
            bal -= amount;
            return true;
        }
        return false;
    }
}
class ATM {
    private Bank bank;
    private Scanner sc;

    public ATM(Bank bank) {
        this.bank = bank;
        this.sc = new Scanner(System.in);
    }
    public void menu() {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private void checkBalance() {
        System.out.println("Current balance: $" + bank.getBalance());
    }
    private void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        if (bank.deposit(amount)) {
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Deposit failed. Please enter a valid amount.");
        }
    }
    private void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();
        sc.nextLine(); // Consume newline character

        if (bank.withdraw(amount)) {
            System.out.println("Successfully withdrew $" + amount);
        } else {
            System.out.println("Withdrawal failed. Check your balance and try again.");
        }
    }
}
public class task3 {
    public static void main(String[] args) {
        Bank bank = new Bank(10000.00);
        ATM atm = new ATM(bank);
        atm.menu();
    }
}