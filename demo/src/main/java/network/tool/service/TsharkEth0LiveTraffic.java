package network.tool.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class TsharkEth0LiveTraffic {
	public static void GivenCmd(SseEmitter emitter, String cmd) {
		try {
			// Command to capture packets on eth0 interface continuously
//			String command = "tshark -i enp2s0";
			// Execute the command
			Process process = Runtime.getRuntime().exec(cmd);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			System.out.println("Capturing packets on eth0 interface (live):");

			// Create a separate thread to read and print the output continuously
			Thread outputThread = new Thread(() -> {
				String line;
				try {
					while ((line = reader.readLine()) != null) {
						emitter.send(line);
						System.out.println(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			// Start the thread
			outputThread.start();

			// Wait for the process to finish if it ever does
			process.waitFor();
			System.out.println("Capture completed.");

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void GivenSrcDes(SseEmitter emitter, String src, String dst) {

		// Command to capture packets on eth0 interface continuously
		ProcessBuilder processBuilder = new ProcessBuilder("./test.sh", src, dst);
		processBuilder.directory(new File("/opt/Testcode/script/2-19/")); // Set the working directory
		processBuilder.redirectErrorStream(true); // Combine stdout and stderr

		try {
			Process process = processBuilder.start();

			// Read output
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				emitter.send(line);
			}

			int exitCode = process.waitFor();
			System.out.println("Exited with code: " + exitCode);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void get10Packets(SseEmitter emitter, int seconds) {

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
					emitter.send(line);
				}

				// Wait for the process to exit
				int exitCode = process.waitFor();
				System.out.println("Exited with code: " + exitCode);

				// Sleep for 10 seconds before capturing the next set of packets
				Thread.sleep(seconds * 1000);

			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
				// In case of an interruption, exit the loop
				break;
			}
		}
	}

//	public static void main(String[] args) {
////		try {
////			// Command to capture packets on eth0 interface continuously
////			String command ="/opt/Testcode/script/2-19/test.sh 192.168.1.52 138.199.14.78";//"tshark -i enp2s0 -f' src host 192.168.1.52 '" ;//"tshark -i enp2s0";  -f \"src host 192.168.1.52 and dst host 138.199.14.78 \"
////			// Execute the command
////			Process process = Runtime.getRuntime().exec(command);
////			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
////
////			System.out.println("command  "+ command);
////
////			// Create a separate thread to read and print the output continuously
////			Thread outputThread = new Thread(() -> {
////				String line;
////				try {
////					while ((line = reader.readLine()) != null) {
////						System.out.println(line);
////					}
////				} catch (IOException e) {
////					e.printStackTrace();
////				}
////			});
////
////			// Start the thread
////			outputThread.start();
////
////			// Wait for the process to finish if it ever does
////			process.waitFor();
////			System.out.println("Capture completed.");
////
////		} catch (IOException | InterruptedException e) {
////			e.printStackTrace();
////		}
//		
//		ProcessBuilder processBuilder = new ProcessBuilder("./test.sh", "192.168.1.52", "138.199.14.78");
//		processBuilder.directory(new File("/opt/Testcode/script/2-19/")); // Set the working directory
//		processBuilder.redirectErrorStream(true); // Combine stdout and stderr
//
//		try {
//		    Process process = processBuilder.start();
//
//		    // Read output
//		    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//		    String line;
//		    while ((line = reader.readLine()) != null) {
//		        System.out.println(line);
//		    }
//
//		    int exitCode = process.waitFor();
//		    System.out.println("Exited with code: " + exitCode);
//		} catch (IOException | InterruptedException e) {
//		    e.printStackTrace();
//		}
//	}
}
