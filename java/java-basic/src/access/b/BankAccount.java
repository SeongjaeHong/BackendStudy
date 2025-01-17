package access.b;

public class BankAccount {
    private int balance;

    public BankAccount() {
        balance = 0;
    }

    public void deposit(int money) {
        if (isDepositValid(money)) {
            balance += money;
        }
        else {
            System.out.println("Money is invalid.");
        }
    }

    public void withdraw(int money) {
        if (isDepositValid(money) && balance - 0 > 0) {
            balance -= money;
        }
        else {
            System.out.println("Balance is not enough.");
        }
    }

    public int getBalance() {
        return balance;
    }

    private boolean isDepositValid(int money) {
        return money > 0;
    }
}
