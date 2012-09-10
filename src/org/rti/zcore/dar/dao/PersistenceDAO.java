package org.rti.zcore.dar.dao;

import java.sql.Connection;
import java.sql.Date;

public interface PersistenceDAO {

    public void updatePatientValues(Connection conn, Long patientId, int formFieldId, int value, Date dateVisit, Long encounterId);

}