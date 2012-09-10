package org.rti.zcore.dar.utils;

public class HealthCalcUtils {

	/**
	 * Caclulates BMI using the formula bmi = weight/Math.pow(height/100, 2);
	 * @param weight
	 * @param height
	 * @should return a double
	 * @return bmi, rounded
	 */
	public static Float bmiCalc(Float weight, Float height) {
		Double bmi = null;
		bmi = weight/Math.pow(height/100, 2);
		Float bmiRounded = Float.valueOf(Math.round(bmi*100))/100;
		return bmiRounded;
	}
}
