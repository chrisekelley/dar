/*
 *    Copyright 2003 - 2012 Research Triangle Institute
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 */

package org.rti.zcore.dar.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;

import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.rti.zcore.Constants;
import org.rti.zcore.dar.dao.InventoryDAO;
import org.rti.zcore.dar.gen.Item;
import org.rti.zcore.dar.gen.Regimen;
import org.rti.zcore.dar.gen.StockControl;
import org.rti.zcore.dar.report.DailyActivityReport;
import org.rti.zcore.dar.report.valueobject.StockReport;
import org.rti.zcore.exception.ObjectNotFoundException;
import org.rti.zcore.utils.DatabaseUtils;

public class InventoryUtils {
	
	/**
	 * Commons Logging instance.
	 */
	public static Log log = LogFactory.getFactory().getInstance(InventoryUtils.class);

	/**
	 * Checks if expiry date is less than the amount specified.
	 *
	 * @param date1
	 * @param expiry
	 * @ param amount the amount of date or time to be added to the field.
	 * @return
	 */
	public static boolean checkExpiry(java.util.Date date1, java.util.Date expiry, int amount) {

    	boolean result = false;

    	Calendar cal1 = new GregorianCalendar();
    	cal1.setTime(date1);
    	cal1.add(java.util.Calendar.MONTH, +amount);
		long dateInFuture = cal1.getTime().getTime();

    	// date to compare to
    	Calendar cal2 = new GregorianCalendar();
    	cal2.setTime(expiry);
    	long expiry_date_value = cal2.getTime().getTime();

    	if (expiry_date_value < dateInFuture) {
    		result = true;
    	}
    	return result;
    }

	/**
	 * Populates stockReportMap with balances, losses, received, and onHand for each stock item
	 * Provides report on stock by looping through itemMap.
	 * LinkedHashMap is used to preserve the insertion order from the itemMap for display purposes.
	 * @should populate Stock Report Maps
	 * @param conn
	 * @param beginDate
	 * @param endDate
	 * @param siteId
	 * @param itemMap 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 * @throws ObjectNotFoundException
	 */
	public static LinkedHashMap<String, StockReport> populateStockReportMaps(Connection conn, Date beginDate,
			Date endDate, int siteId, LinkedHashMap<Long,Item> itemMap) throws ClassNotFoundException,
			IOException, ServletException, SQLException,
			ObjectNotFoundException {
		String sql;
		HashMap<Long,StockReport> balanceMap = InventoryDAO.getBalanceMap(conn, siteId, null);
		HashMap<Long,StockReport> balanceBFMap = InventoryDAO.getBalanceMap(conn, siteId, beginDate);
		LinkedHashMap<String,StockReport> stockReportMap = new LinkedHashMap<String,StockReport>();
		// Get the stock onHand for each item
		try {
			Integer currentBalance = null;
			/*List<Item> items = null;
			Map queries = QueryLoader.instance().load("/" + Constants.SQL_GENERATED_PROPERTIES);
			sql = (String) queries.get("SQL_RETRIEVE_ALL_ADMIN131") + " ORDER BY item.id";
			ArrayList values = new ArrayList();
			items = DatabaseUtils.getList(conn, Item.class, sql, values);*/
			
			Set<Entry<Long,Item>> itemSet =  itemMap.entrySet();
			for (Entry<Long, Item> entry : itemSet) {
			//for (DropdownItem dropdownItem : list) {
				//Long itemId = Long.valueOf(dropdownItem.getDropdownId());
				//Map.Entry entry = (Map.Entry) iterator.next();
    			Long itemId = entry.getKey();
    			Item item = entry.getValue();
				//Long itemId = item.getId();
				String code = item.getCode().trim().replace(" ", "_");
				// TODO: Loopng through stockChanges for the stockReceived and stockLoss could be refactored to a method similar to InventoryDAO.getBalanceMap, 
				// where the sum of the values of type_of_change = 3263 could be queried.
				List<StockControl> stockChanges = (List<StockControl>) InventoryDAO.getStockChanges(conn, itemId, siteId, beginDate, endDate);
				
				Integer stockReceived = 0;
				Integer stockLoss = 0;
				Integer stockControlIssuedTotal = 0;
				Integer posAdjustments = 0;
				Integer negAdjustments = 0;
				//Integer totalLosses = 0;
				if (stockChanges.size() >0) {
					for (Iterator iterator = stockChanges.iterator(); iterator.hasNext();) {
						StockControl stock = (StockControl) iterator.next();
						Integer changeType = stock.getType_of_change();
						Integer quantity = stock.getChange_value();
						if (quantity != null) {
							switch (changeType.intValue()) {
							case 3263:	// Received
								stockReceived = stockReceived + quantity;
								break;
							case 3265:	// Losses
								stockLoss = stockLoss + quantity;
								break;
							case 3264:	// issued
								stockControlIssuedTotal = stockControlIssuedTotal + quantity;
								break;
							case 3266:	// Pos. Adjust.
								posAdjustments = posAdjustments + quantity;
								break;
							case 3267:	// Neg. Adjust.
								negAdjustments = negAdjustments + quantity;
								break;
							default:
								break;
							}
						}
					}
				}
				StockReport stockReport = balanceMap.get(itemId);
		        if (stockReport == null) {
		        	currentBalance = 0;
		        } else {
		        	currentBalance = stockReport.getOnHand();
		        }
		        StockReport stockReportBbf = balanceBFMap.get(itemId);
		        Integer beginningBalance = 0;
		        if (stockReportBbf == null) {
		        	beginningBalance = 0;
		        } else {
		        	beginningBalance = stockReportBbf.getOnHand();
		        }
				Integer stockOnHand = beginningBalance + stockReceived;
				// keep the report easy-to-read - not a bunch of zeros.
				if (stockLoss == 0) {
					stockLoss = null;
				}
				if (stockReceived == 0) {
					stockReceived = null;
				}
				if (beginningBalance == 0) {
					beginningBalance = null;
				}

				if (currentBalance == 0) {
					if (beginningBalance != null) {
						currentBalance = 0;
					} else {
						currentBalance = null;
					}
				}
				if (stockOnHand == 0) {
					stockOnHand = null;
				}
				if (negAdjustments == 0) {
					negAdjustments = null;
				}
				if (posAdjustments == 0) {
					posAdjustments = null;
				}
				
				StockReport itemStockReport = new StockReport();
				itemStockReport.setId(itemId);
				itemStockReport.setName(item.getName());
				itemStockReport.setUnits(item.getUnit());
				itemStockReport.setItem_group_id(item.getItem_group_id());
				itemStockReport.setBalanceBF(beginningBalance);
				itemStockReport.setBalanceCF(currentBalance);
				itemStockReport.setLosses(stockLoss);
				itemStockReport.setReceived(stockReceived);
				itemStockReport.setNegAdjustments(negAdjustments);
				itemStockReport.setPosAdjustments(posAdjustments);
				itemStockReport.setOnHand(stockOnHand);
				
				stockReportMap.put("item" + code, itemStockReport);
				//log.debug("item" + code + ": " + itemStockReport.getReceived());
			}
		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
		}
		return stockReportMap;
	}

	/**
	 * Retrieve all Encounter records for this form 132 - Patient dispensary
	 * Joins with the patient table so you can use some of the patient fields.
	 * TODO: May be able to remove the join with patient.
	 * @param conn
	 * @param siteID
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws ServletException
	 */
	public static ResultSet getPatientDispensaryEncounters(Connection conn, int siteID, Date beginDate, Date endDate) throws ServletException {
	
		ResultSet rs = null;
	
		String dateRange = "AND date_visit >= ? AND date_visit <= ? ";
		if (endDate == null) {
			dateRange = "AND date_visit = ?";
		}
	
		try {
			if (siteID == 0) {
				String sql = "SELECT encounter.id AS id, date_visit, patient_id, district_patient_id, " +
				"first_name, surname, encounter.site_id, age_at_first_visit, age_category, sex, " +
				"encounter.created_by AS created_by, encounter.created " +
				"FROM encounter, patient " +
				"WHERE encounter.patient_id = patient.id " +
				"AND form_id = 132\n" +
				dateRange +
				"ORDER BY created, surname";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setDate(1, beginDate);
				if (endDate != null) {
					ps.setDate(2, endDate);
				}
				rs = ps.executeQuery();
			} else {
				String sql = "SELECT encounter.id AS id, date_visit, patient_id, district_patient_id, " +
				"first_name, surname, encounter.site_id, age_at_first_visit, age_category, sex, " +
				"encounter.created_by AS created_by, encounter.created " +
				"FROM encounter, patient " +
				"WHERE encounter.patient_id = patient.id " +
				"AND form_id = 132\n" +
				dateRange +
				"AND encounter.site_id = ? " +
				"ORDER BY created, surname";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setDate(1, beginDate);
				if (endDate != null) {
					ps.setDate(2, endDate);
					ps.setInt(3, siteID);
				} else {
					ps.setInt(2, siteID);
				}
				rs = ps.executeQuery();
			}
		} catch (Exception ex) {
			DailyActivityReport.log.error(ex);
		}
	
		return rs;
	}

	/**
	 * 
	 * @param conn
	 * @return map of all Items in database ORDER BY item.name
	 * @should return map of items
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 * @throws ObjectNotFoundException
	 */
	public static LinkedHashMap<Long, Item> populateItemMap(Connection conn) throws ClassNotFoundException,
			IOException, ServletException, SQLException,
			ObjectNotFoundException {
		String sql;
		LinkedHashMap<Long, Item> itemMap = new LinkedHashMap<Long, Item>();
		try {
			List<Item> items = null;
			Map queries = QueryLoader.instance().load("/" + Constants.SQL_GENERATED_PROPERTIES);
			sql = (String) queries.get("SQL_RETRIEVE_ALL_ADMIN131") + " ORDER BY item.name";
			ArrayList values = new ArrayList();
			items = DatabaseUtils.getList(conn, Item.class, sql, values);
			for (Item item : items) {
				Long itemId = item.getId();
				//String code = item.getCode();
				itemMap.put(itemId, item);
			}
		} catch (SQLException e) {
			DailyActivityReport.log.error(e);
			e.printStackTrace();
		}
		return itemMap;
	}
	
	/**
	 * Map of all regimens in the database
	 * @param conn
	 * @return LinkedHashMap<regimenCode:Regimen>
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 * @throws ObjectNotFoundException
	 */
	public static LinkedHashMap<String, Regimen> populateRegimenMap(Connection conn) throws ClassNotFoundException,
	IOException, ServletException, SQLException,
	ObjectNotFoundException {
		String sql;
		LinkedHashMap<String, Regimen> regimenMap = new LinkedHashMap<String, Regimen>();
		try {
			List<Regimen> items = null;
			Map queries = QueryLoader.instance().load("/" + Constants.SQL_GENERATED_PROPERTIES);
			sql = (String) queries.get("SQL_RETRIEVE_ALL_ADMIN129") + " ORDER BY regimen.name";
			ArrayList values = new ArrayList();
			items = DatabaseUtils.getList(conn, Regimen.class, sql, values);
			for (Regimen item : items) {
				//Long itemId = item.getId();
				String code = item.getCode();
				regimenMap.put(code, item);
			}
		} catch (SQLException e) {
			log.error(e);
			e.printStackTrace();
		}
		return regimenMap;
	}
	
	/**
	 * Produces a map of Items based on the sorting of the codeList.
	 * Queries the item table using the code from the codeList.
	 * @param conn
	 * @param codeList
	 * @return map of Items in the order of the codeList.
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws ServletException
	 * @throws SQLException
	 * @throws ObjectNotFoundException
	 */
	public static LinkedHashMap<Long, Item> populateItemMap(Connection conn, ArrayList<String> codeList) throws ClassNotFoundException,
	IOException, ServletException, SQLException,
	ObjectNotFoundException {
		String sql;
		LinkedHashMap<Long, Item> itemMap = new LinkedHashMap<Long, Item>();
		try {
			Map queries = QueryLoader.instance().load("/" + Constants.SQL_GENERATED_PROPERTIES);
			sql = "SELECT id, code AS code, name AS name, item_group_id AS item_group_id, " +
					"unit AS unit, pack_size AS pack_size, use_in_report AS use_in_report " +
					"FROM item WHERE TRIM(code) = ?";
			for (String code : codeList) {
				ArrayList values = new ArrayList();
				//String[] categoryPipeCodeArray = categoryPipeCode.split(":");
				//String category = categoryPipeCodeArray[0];
				//String code = categoryPipeCodeArray[1];
				String codeTruncated = code.replace("item", "");
				values.add(codeTruncated);
				Item item;
				try {
					item = (Item) DatabaseUtils.getBean(conn, Item.class, sql, values);
					itemMap.put(item.getId(), item);
				} catch (ObjectNotFoundException e) {
					log.debug("Code: " + code + " is not in the item table.");
				}
			}
		} catch (SQLException e) {
			DailyActivityReport.log.error(e);
			e.printStackTrace();
		}
		return itemMap;
	}
}
