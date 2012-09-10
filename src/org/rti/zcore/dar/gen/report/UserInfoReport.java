package org.rti.zcore.dar.gen.report;

import org.rti.zcore.EncounterData;
import org.rti.zcore.Patient;
import java.sql.Date;
import java.util.Set;
import java.sql.Time;
import java.sql.Timestamp;
import org.rti.zcore.AuditInfo;
import java.util.TreeSet;

/**
 * JavaBean UserInfoReport generated from database;
 * generated by DynasiteSourceGenerator, inspired by XslBeanGenerator by Klaus Berg.
 *
 * @author Chris Kelley
 *         Date: 2012-02-10
 *         Time: 14:53:56
 *         Form Name: User Data
 *         Form Id: 170
 */

/**
 * @hibernate.joined-subclass table="user_info"
 * @hibernate.joined-subclass-key column="id"
 */
public class UserInfoReport extends EncounterData {

private transient String username;	//username field2266
private String usernameR;
private transient String password;	//password field2267
private String passwordR;
private transient String email;	//email field2270
private String emailR;
private transient String forenames;	//forenames field2268
private String forenamesR;
private transient String lastname;	//lastname field2269
private String lastnameR;
private transient String mobile;	//mobile field2271
private String mobileR;
private transient String phone;	//phone field2272
private String phoneR;


 /**
  * @return
  * @hibernate.property column="username"
  */
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getUsernameR() {
        return this.usernameR;
    }

    public void setUsernameR(String usernameR) {
        this.usernameR = usernameR;
    }



 /**
  * @return
  * @hibernate.property column="password"
  */
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getPasswordR() {
        return this.passwordR;
    }

    public void setPasswordR(String passwordR) {
        this.passwordR = passwordR;
    }



 /**
  * @return
  * @hibernate.property column="email"
  */
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getEmailR() {
        return this.emailR;
    }

    public void setEmailR(String emailR) {
        this.emailR = emailR;
    }



 /**
  * @return
  * @hibernate.property column="forenames"
  */
    public String getForenames() {
        return this.forenames;
    }

    public void setForenames(String forenames) {
        this.forenames = forenames;
    }



    public String getForenamesR() {
        return this.forenamesR;
    }

    public void setForenamesR(String forenamesR) {
        this.forenamesR = forenamesR;
    }



 /**
  * @return
  * @hibernate.property column="lastname"
  */
    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }



    public String getLastnameR() {
        return this.lastnameR;
    }

    public void setLastnameR(String lastnameR) {
        this.lastnameR = lastnameR;
    }



 /**
  * @return
  * @hibernate.property column="mobile"
  */
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



    public String getMobileR() {
        return this.mobileR;
    }

    public void setMobileR(String mobileR) {
        this.mobileR = mobileR;
    }



 /**
  * @return
  * @hibernate.property column="phone"
  */
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getPhoneR() {
        return this.phoneR;
    }

    public void setPhoneR(String phoneR) {
        this.phoneR = phoneR;
    }



}
