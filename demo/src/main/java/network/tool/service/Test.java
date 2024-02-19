package network.tool.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
	 public static void main(String[] args) {
	        while (true) {
	            try {
	                // Command to execute tshark with the specified interface and count
	                String command = "tshark -i enp2s0 -c 10";
	                
	                // Using ProcessBuilder to execute the command
	                ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
	                Process process = processBuilder.start();
	                
	                // Reading the output from the command
	                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	                String line;
	                while ((line = reader.readLine()) != null) {
	                    System.out.println(line);
	                }
	                
	                // Wait for the process to exit
	                int exitCode = process.waitFor();
	                System.out.println("Exited with code: " + exitCode);
	                
	                // Sleep for 10 seconds before capturing the next set of packets
	                Thread.sleep(10000);
	                
	            } catch (IOException | InterruptedException e) {
	                e.printStackTrace();
	                // In case of an interruption, exit the loop
	                break;
	            }
	        }
	    }
}
