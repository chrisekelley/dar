package org.rti.zcore.dar.utils;

import java.sql.Timestamp;

/**
 * Interfaced used for DateofVisit order sorting for MshPatientTransactions.
 * @author ckelley
 *
 */
public interface DateOfVisitOrderable {
	Timestamp getDateofVisit();

    void  setDateofVisit(Timestamp dateofVisit);
}
