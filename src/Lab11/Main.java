package Lab11;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        int numDeposits = 5;
        double depositAmount = 200;

        int numWithdrawals = 5;
        double maxWithdrawal = 300;

        DepositThread depositThread = new DepositThread(account, depositAmount);
        WithdrawThread withdrawThread = new WithdrawThread(account, numWithdrawals, maxWithdrawal);

        depositThread.start();
        withdrawThread.start();
    }
}