package network.tool.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TsharkEth0Traffic {

    public static void main(String[] args) {
        try {
            // Command to capture 10 packets on eth0 interface
            String command = "tshark -i enp2s0 -c 10";
            // Execute the command
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            System.out.println("Capturing 10 packets on eth0 interface:");
            // Read and print each line from the tshark output
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to finish
            process.waitFor();
            System.out.println("Capture completed.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

