package _2190300408hw4;

import java.text.DecimalFormat;
import _2190300408hw4.mylog.Logger;
public class BankAccount {

    private String ownerName;

    private volatile int accountNumber;

    private double balance;

    /**
     * 
     */
    public BankAccount() {
        this("", 0, 0);
    }

    /**
     * @param ownerName
     * @param accountNumber
     * @param balance
     */
    public BankAccount(String ownerName, int accountNumber, double balance) {
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    /**
     * 存款
     * 
     * @param {double} anAmount
     * @return {balance}
     */  
    public double deposit(double anAmount) {
        balance += anAmount; 
        Logger.write("#" + accountNumber + " 存入：$" + new DecimalFormat("#.####").format(anAmount));
        Logger.write(toString());
        return balance; 
    } 
    /**
     * 取款
     * 
     * @param {double} anAmount
     * @return {balance}
     */ 
    public double withdraw(double anAmount) {
        if(balance - anAmount < 0){
            Logger.write("#" + accountNumber + " 取出：$0 [取款失败，余额不足]");
            Logger.write(toString());
            return anAmount;
        }
        balance -= anAmount; 
        Logger.write("#" + accountNumber + " 取出：$" + new DecimalFormat("#.####").format(anAmount));
        Logger.write(toString());
        return anAmount;
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
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }
    /**
     * @param balance the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    @Override
    public String toString() {
        return "BankAccount [# " + 
                    accountNumber + 
                    " with balance $" + 
                    new DecimalFormat("####.####").format(balance) + 
                    " ]";
    }
    
}
