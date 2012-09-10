package org.rti.zcore.dar.report.valueobject;

public class RegimenReport {

	private String code;
	private String name;
	private Integer regimenGroup;
	private double count;
	private double percentage;
	private int countInt;
	private int newEstimatedArtPatients;
	private int totalEstimatedArtPatients;

	private int totalAdults;
	private int totalChildren;
	private int totalMale;
	private int totalFemale;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public int getTotalAdults() {
		return totalAdults;
	}
	public void setTotalAdults(int totalAdults) {
		this.totalAdults = totalAdults;
	}
	public int getTotalChildren() {
		return totalChildren;
	}
	public void setTotalChildren(int totalChildren) {
		this.totalChildren = totalChildren;
	}
	public int getTotalMale() {
		return totalMale;
	}
	public void setTotalMale(int totalMale) {
		this.totalMale = totalMale;
	}
	public int getTotalFemale() {
		return totalFemale;
	}
	public void setTotalFemale(int totalFemale) {
		this.totalFemale = totalFemale;
	}
	public int getNewEstimatedArtPatients() {
		return newEstimatedArtPatients;
	}
	public void setNewEstimatedArtPatients(int newEstimatedArtPatients) {
		this.newEstimatedArtPatients = newEstimatedArtPatients;
	}
	public int getTotalEstimatedArtPatients() {
		return totalEstimatedArtPatients;
	}
	public void setTotalEstimatedArtPatients(int totalEstimatedArtPatients) {
		this.totalEstimatedArtPatients = totalEstimatedArtPatients;
	}
	public int getCountInt() {
		return countInt;
	}
	public void setCountInt(int countInt) {
		this.countInt = countInt;
	}
	public Integer getRegimenGroup() {
		return regimenGroup;
	}
	public void setRegimenGroup(Integer regimenGroup) {
		this.regimenGroup = regimenGroup;
	}


}
