package Lab11;

import java.util.Random;

class WithdrawThread extends Thread {
    private BankAccount account;
    private Random random = new Random();
    private int numWithdrawals;
    private double maxWithdrawal;

    public WithdrawThread(BankAccount account, int numWithdrawals, double maxWithdrawal) {
        this.account = account;
        this.numWithdrawals = numWithdrawals;
        this.maxWithdrawal = maxWithdrawal;
    }

    public void run() {
        for (int i = 0; i < numWithdrawals; i++) {
            double withdrawalAmount = random.nextDouble() * maxWithdrawal;
            account.withdraw(withdrawalAmount);
        }
    }
}
