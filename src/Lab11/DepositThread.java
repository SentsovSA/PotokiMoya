package Lab11;

class DepositThread extends Thread {
    private BankAccount account;
    private double depositAmount;

    public DepositThread(BankAccount account, double depositAmount) {
        this.account = account;
        this.depositAmount = depositAmount;
    }

    public void run() {
        account.deposit(depositAmount);
    }
}
