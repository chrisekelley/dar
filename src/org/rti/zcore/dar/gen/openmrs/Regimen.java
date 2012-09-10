package org.rti.zcore.dar.gen.openmrs;

import org.rti.zcore.openmrs.OpenMRSEncounter;
import org.rti.zcore.openmrs.OpenMrsMultiValue;
import org.rti.zcore.openmrs.OpenMrsValue;

/**
 * JavaBean Regimen generated from database;
 * generated by DynasiteSourceGenerator, inspired by XslBeanGenerator by Klaus Berg.
 *
 * @author Chris Kelley
 *         Date: 2012-02-10
 *         Time: 14:44:52
 *         Form Name: Regimen
 *         Form Id: 129
 */

/**
 * @hibernate.joined-subclass table="regimen"
 * @hibernate.joined-subclass-key column="id"
 */
public class Regimen extends OpenMRSEncounter {

private OpenMrsValue code;	//code field2144
private OpenMrsValue name;	//name field2145
private OpenMrsValue description;	//description field2146
private OpenMrsValue arv_option;	//arv_option field2147
private OpenMrsValue regimen_group_id;	//regimen_group_id field2148
private OpenMrsValue disabled;	//disabled field2265


 /**
  * @return
  * @hibernate.property column="code"
  */
    public OpenMrsValue getCode() {
        return this.code;
    }

    public void setCode(OpenMrsValue code) {
        this.code = code;
    }





 /**
  * @return
  * @hibernate.property column="name"
  */
    public OpenMrsValue getName() {
        return this.name;
    }

    public void setName(OpenMrsValue name) {
        this.name = name;
    }





 /**
  * @return
  * @hibernate.property column="description"
  */
    public OpenMrsValue getDescription() {
        return this.description;
    }

    public void setDescription(OpenMrsValue description) {
        this.description = description;
    }





 /**
  * @return
  * @hibernate.property column="arv_option"
  */
    public OpenMrsValue getArv_option() {
        return this.arv_option;
    }

    public void setArv_option(OpenMrsValue arv_option) {
        this.arv_option = arv_option;
    }





 /**
  * @return
  * @hibernate.property column="regimen_group_id"
  */
    public OpenMrsValue getRegimen_group_id() {
        return this.regimen_group_id;
    }

    public void setRegimen_group_id(OpenMrsValue regimen_group_id) {
        this.regimen_group_id = regimen_group_id;
    }





 /**
  * @return
  * @hibernate.property column="disabled"
  */
    public OpenMrsValue getDisabled() {
        return this.disabled;
    }

    public void setDisabled(OpenMrsValue disabled) {
        this.disabled = disabled;
    }





}
