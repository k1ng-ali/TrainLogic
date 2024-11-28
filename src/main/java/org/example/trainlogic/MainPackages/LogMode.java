package org.example.trainlogic.MainPackages;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class LogMode {
    static User user;
    private static Config config;

    public LogMode(Config config) {
        LogMode.config = config;
    }

    public LogMode(User user, Config config) {
        this.user = user;
        this.config = config;
    }

    public void setUser(User user) {
        LogMode.user = user;
    }

    public static void LogWrite(String key) throws IOException {
        if (config.isDebugMode()) {
            FileWriter logWrite = new FileWriter("logProgramm.txt", true);
            logWrite.write(user.getUsername() + "  " + new Date() + ": " + key + "\n");
            logWrite.close();
        }
    }
}
