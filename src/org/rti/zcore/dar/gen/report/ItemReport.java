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
 * JavaBean ItemReport generated from database;
 * generated by DynasiteSourceGenerator, inspired by XslBeanGenerator by Klaus Berg.
 *
 * @author Chris Kelley
 *         Date: 2012-02-10
 *         Time: 14:53:36
 *         Form Name: Item
 *         Form Id: 131
 */

/**
 * @hibernate.joined-subclass table="item"
 * @hibernate.joined-subclass-key column="id"
 */
public class ItemReport extends EncounterData {

private transient String code;	//code field2152
private String codeR;
private transient String name;	//name field2153
private String nameR;
private transient Long item_group_id;	//item_group_id field2154
private String item_group_idR;
private transient String unit;	//unit field2155
private String unitR;
private transient Integer pack_size;	//pack_size field2276
private String pack_sizeR;
private transient Boolean use_in_report;	//use_in_report field2294
private String use_in_reportR;


 /**
  * @return
  * @hibernate.property column="code"
  */
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public String getCodeR() {
        return this.codeR;
    }

    public void setCodeR(String codeR) {
        this.codeR = codeR;
    }



 /**
  * @return
  * @hibernate.property column="name"
  */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getNameR() {
        return this.nameR;
    }

    public void setNameR(String nameR) {
        this.nameR = nameR;
    }



 /**
  * @return
  * @hibernate.property column="item_group_id"
  */
    public Long getItem_group_id() {
        return this.item_group_id;
    }

    public void setItem_group_id(Long item_group_id) {
        this.item_group_id = item_group_id;
    }



    public String getItem_group_idR() {
        return this.item_group_idR;
    }

    public void setItem_group_idR(String item_group_idR) {
        this.item_group_idR = item_group_idR;
    }



 /**
  * @return
  * @hibernate.property column="unit"
  */
    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }



    public String getUnitR() {
        return this.unitR;
    }

    public void setUnitR(String unitR) {
        this.unitR = unitR;
    }



 /**
  * @return
  * @hibernate.property column="pack_size"
  */
    public Integer getPack_size() {
        return this.pack_size;
    }

    public void setPack_size(Integer pack_size) {
        this.pack_size = pack_size;
    }



    public String getPack_sizeR() {
        return this.pack_sizeR;
    }

    public void setPack_sizeR(String pack_sizeR) {
        this.pack_sizeR = pack_sizeR;
    }



 /**
  * @return
  * @hibernate.property column="use_in_report"
  */
    public Boolean getUse_in_report() {
        return this.use_in_report;
    }

    public void setUse_in_report(Boolean use_in_report) {
        this.use_in_report = use_in_report;
    }



    public String getUse_in_reportR() {
        return this.use_in_reportR;
    }

    public void setUse_in_reportR(String use_in_reportR) {
        this.use_in_reportR = use_in_reportR;
    }



}