/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "accounts", catalog = "ProductIntro", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accounts.findAll", query = "SELECT a FROM Accounts a")
    , @NamedQuery(name = "Accounts.findByAccount", query = "SELECT a FROM Accounts a WHERE a.account = :account")
    , @NamedQuery(name = "Accounts.findByPass", query = "SELECT a FROM Accounts a WHERE a.pass = :pass")
    , @NamedQuery(name = "Accounts.findByLastName", query = "SELECT a FROM Accounts a WHERE a.lastName = :lastName")
    , @NamedQuery(name = "Accounts.findByFirstName", query = "SELECT a FROM Accounts a WHERE a.firstName = :firstName")
    , @NamedQuery(name = "Accounts.findByBirthday", query = "SELECT a FROM Accounts a WHERE a.birthday = :birthday")
    , @NamedQuery(name = "Accounts.findByGender", query = "SELECT a FROM Accounts a WHERE a.gender = :gender")
    , @NamedQuery(name = "Accounts.findByPhone", query = "SELECT a FROM Accounts a WHERE a.phone = :phone")
    , @NamedQuery(name = "Accounts.findByIsUse", query = "SELECT a FROM Accounts a WHERE a.isUse = :isUse")
    , @NamedQuery(name = "Accounts.findByRoleInSystem", query = "SELECT a FROM Accounts a WHERE a.roleInSystem = :roleInSystem")})
public class Accounts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "account", nullable = false, length = 20)
    private String account;
    @Basic(optional = false)
    @Column(name = "pass", nullable = false, length = 20)
    private String pass;
    @Column(name = "lastName", length = 50)
    private String lastName;
    @Basic(optional = false)
    @Column(name = "firstName", nullable = false, length = 30)
    private String firstName;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "gender")
    private Boolean gender;
    @Column(name = "phone", length = 20)
    private String phone;
    @Column(name = "isUse")
    private Boolean isUse;
    @Column(name = "roleInSystem", length = 20)
    private String roleInSystem;

    public Accounts() {
    }

    public Accounts(String account) {
        this.account = account;
    }

    public Accounts(String account, String pass, String firstName) {
        this.account = account;
        this.pass = pass;
        this.firstName = firstName;
    }

    public Accounts(String account, String pass, String lastName, String firstName, Date birthday, Boolean gender, String phone) {
        this.account = account;
        this.pass = pass;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.roleInSystem = "customer";
        this.isUse = true;
    }

    public Accounts(String account, String pass, String lastName, String firstName, Date birthday, Boolean gender, String phone, Boolean isUse, String roleInSystem) {
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

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsUse() {
        return isUse;
    }

    public void setIsUse(Boolean isUse) {
        this.isUse = isUse;
    }

    public String getRoleInSystem() {
        return roleInSystem;
    }

    public void setRoleInSystem(String roleInSystem) {
        this.roleInSystem = roleInSystem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (account != null ? account.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Accounts)) {
            return false;
        }
        Accounts other = (Accounts) object;
        if ((this.account == null && other.account != null) || (this.account != null && !this.account.equals(other.account))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Accounts[ account=" + account + " ]";
    }
    
}
