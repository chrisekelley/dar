package org.rti.zcore.dar.gen;

import org.rti.zcore.dar.gen.*;
import org.rti.zcore.EncounterData;
import org.rti.zcore.Patient;
import java.sql.Date;
import java.util.Set;
import java.sql.Time;
import java.sql.Timestamp;
import org.rti.zcore.AuditInfo;
import java.util.TreeSet;

/**
 * JavaBean RegimenItem_bridge generated from database;
 * generated by DynasiteSourceGenerator, inspired by XslBeanGenerator by Klaus Berg.
 *
 * @author Chris Kelley
 *         Date: 2012-02-13
 *         Time: 18:14:28
 *         Form Name: Regimen Item
 *         Form Id: 181
 */

/**
 * @hibernate.joined-subclass table="regimen_item_bridge"
 * @hibernate.joined-subclass-key column="id"
 */
public class RegimenItem_bridge extends EncounterData {

private Long regimen_id;	//regimen_id field204
private Long item_id;	//item_id field205


 /**
  * @return
  * @hibernate.property column="regimen_id"
  */
    public Long getRegimen_id() {
        return this.regimen_id;
    }

    public void setRegimen_id(Long regimen_id) {
        this.regimen_id = regimen_id;
    }





 /**
  * @return
  * @hibernate.property column="item_id"
  */
    public Long getItem_id() {
        return this.item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }





}
