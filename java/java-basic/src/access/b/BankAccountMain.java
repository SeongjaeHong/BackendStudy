package access.b;

public class BankAccountMain {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        account.deposit(10000);
        account.withdraw(7000);
        System.out.println("Balance: " + account.getBalance());
        account.deposit(-10000);
    }
}
