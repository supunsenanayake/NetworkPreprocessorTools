package network.tool.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TsharkCapture {

    public static void main(String[] args) {
        try {
            while (true) {
                // Command to run tshark for 1 second  tshark -i enp2s0 -c 10
                String command = "tshark -i enp2s0 -c 10";
                
                // Execute the command
                Process process = Runtime.getRuntime().exec(command);
                //------
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                System.out.println("Capturing 10 packets on eth0 interface:");
                //---------
                // Read and print each line from the tshark output
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                
                
                // Wait for the process to complete
                process.waitFor();
                
                // Optionally, sleep for 1 second before the next capture
                Thread.sleep(10000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
