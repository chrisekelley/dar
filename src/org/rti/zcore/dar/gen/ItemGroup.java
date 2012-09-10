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
 * JavaBean ItemGroup generated from database;
 * generated by DynasiteSourceGenerator, inspired by XslBeanGenerator by Klaus Berg.
 *
 * @author Chris Kelley
 *         Date: 2012-02-10
 *         Time: 14:44:47
 *         Form Name: Item Group 
 *         Form Id: 130
 */

/**
 * @hibernate.joined-subclass table="item_group"
 * @hibernate.joined-subclass-key column="id"
 */
public class ItemGroup extends EncounterData {

private String name;	//name field2151
private String short_name;	//short_name field2273


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





 /**
  * @return
  * @hibernate.property column="short_name"
  */
    public String getShort_name() {
        return this.short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }





}
