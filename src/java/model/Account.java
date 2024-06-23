package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Tran Quoc Cuong
 */

/**
 * Account DTO class
 * @version 1.0
 */
public class Account implements Serializable {

    private String account;
    private String pass;
    private String lastName;
    private String firstName;
    private Date birthday;
    private boolean gender;
    private String phone;
    private boolean isUse;
    private int roleInSystem;

    // Default constructor
    public Account() {
        this.account = "";
        this.pass = "";
        this.lastName = "";
        this.firstName = "";
        this.birthday = new Date(1900, 1, 1);
        this.gender = true;
        this.phone = "";
        this.isUse = true;
        this.roleInSystem = 1;
    }

    // Constructor with attributes 
    public Account(String account, String pass, String lastName, String firstName,
            Date birthday, boolean gender, String phone, boolean isUse, int roleInSystem) {
        this.account = account;
        this.pass = pass;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.isUse = isUse;
        this.roleInSystem = roleInSystem;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    // True: Male | False: Female
    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    // Begin with 0 end length = 10
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // True: Being used | False: is prevented
    public boolean isIsUse() {
        return isUse;
    }

    public void setIsUse(boolean isUse) {
        this.isUse = isUse;
    }

    // 1: Administrator | other: Staff
    public int getRoleInSystem() {
        return roleInSystem;
    }

    // 1: Administrator | other: Staff
    public void setRoleInSystem(int roleInSystem) {
        this.roleInSystem = roleInSystem;
    }

}
