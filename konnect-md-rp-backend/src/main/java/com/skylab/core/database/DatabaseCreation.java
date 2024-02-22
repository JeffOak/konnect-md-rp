package com.skylab.core.database;

import org.firebirdsql.management.FBManager;

import java.io.File;

public class DatabaseCreation {

    public void verifyDatabase() {
        File database = new File("C:\\KonnectMDRP\\KNC_MD_RP.FDB");
        if (!database.exists()) {
            try (FBManager fbManager = new FBManager()) {
                fbManager.setServer("localhost");
                fbManager.setPort(3050);
                fbManager.start();
                fbManager.createDatabase("C:\\KonnectMDRP\\" + "KNC_MD_RP.FDB", "sysdba", "masterkey");
                fbManager.stop();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


}
