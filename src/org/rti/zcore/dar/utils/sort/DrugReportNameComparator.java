package org.rti.zcore.dar.utils.sort;

import java.util.Comparator;

import org.rti.zcore.dar.report.valueobject.DrugReport;

public class DrugReportNameComparator implements Comparator {
    public int compare(Object o, Object o1) {
        try {
        	DrugReport e, e1;
            e = (DrugReport) o;
            e1 = (DrugReport) o1;
            if (e.getName().compareTo(e1.getName())> 0) {
                return 1;
            } else if (e.getName().compareTo(e1.getName()) < 0) {
                return -1;
            }
        } catch (ClassCastException ex) {
            return 0;
        }
        return 0;
    }
}
