package com.techelevator;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogTest {
    @Test
    void testWriteLog_can_create_and_write_to_file() throws Exception {
        String directoryName = "log";
        String fileName = "log.txt";
        File logDirectory = new File(directoryName);

        if (!logDirectory.exists()) {
            logDirectory.mkdir();
        }

        Log.WriteLog("Test Log");

        File logFile = new File(directoryName + "\\" + fileName);
        assertTrue(logFile.exists(), "Log file should be created");

        String logContent = new String(Files.readAllBytes(Paths.get(logFile.getPath())));
        assertTrue(logContent.contains("Test Log"), "Log content should include the log message");

        Files.deleteIfExists(Paths.get(logFile.getPath()));
        logDirectory.delete();
    }

}
