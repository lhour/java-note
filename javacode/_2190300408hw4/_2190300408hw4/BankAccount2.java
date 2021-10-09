package _2190300408hw4;

import java.text.DecimalFormat;
import _2190300408hw4.mylog.Logger;

public class BankAccount2 {

    private static volatile int LAST_ACCOUNT_NUMBER = 100000;

    private String ownerName;

    private volatile int accountNumber;

    private double balance;

    public BankAccount2() {
        this("", 0);
    }

    public BankAccount2(String initName) {
        this(initName, 0);
    }

    public BankAccount2(String initName, float initBal) {
        ownerName = initName;
        accountNumber = ++ LAST_ACCOUNT_NUMBER;
        balance = initBal;
    }

    /**
     * 存款
     * 
     * @param {double} anAmount
     * @return {balance}
     */
    public double deposit(double anAmount) {
        balance += anAmount;
        Logger.info("#" + accountNumber + " 存入：$" + new DecimalFormat("#.####").format(anAmount));
        return balance;
    }

    /**
     * 取款
     * 
     * @param {double} anAmount
     * @return {balance}
     */
    public double withdraw(double anAmount) {
        if (balance < anAmount) {
            Logger.warn("#" + accountNumber + " 取出：$0 [取款失败，余额不足]");
            return anAmount;
        }
        balance -= anAmount;
        Logger.info("#" + accountNumber + " 取出：$" + new DecimalFormat("#.####").format(anAmount));
        return anAmount;
    }

    public static BankAccount2 example1() {
        BankAccount2 ba = new BankAccount2();
        ba.setOwnerName("LiHong");
        ba.deposit(1000);
        return ba;
    }

    public static BankAccount2 example2() {
        BankAccount2 ba = new BankAccount2();
        ba.setOwnerName("ZhaoWei");
        ba.deposit(1000);
        ba.deposit(2000);
        return ba;
    }

    public static BankAccount2 emptyAccountExample() {
        BankAccount2 ba = new BankAccount2();
        ba.setOwnerName("HeLi");
        return ba;
    }

    /**
     * @return the ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * @param ownerName the ownerName to set
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * @return the accountNumber
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * 
     * @param accountNumber the accountNumber to set
     */
    // private void setAccountNumber(int accountNumber) {
    //     this.accountNumber = accountNumber;
    // }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    // private void setBalance(double balance) {
    //     this.balance = balance;
    // }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "BankAccount [# " + accountNumber + " with balance $" + new DecimalFormat("####.####").format(balance)
                + " ]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        BankAccount ba = (BankAccount) obj;
        if (ba.getAccountNumber() != this.getAccountNumber()) {
            return false;
        }
        return true;
    }
}
