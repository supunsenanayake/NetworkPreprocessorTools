package network.tool.demo.app;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import network.tool.service.TsharkEth0LiveTraffic;

@RestController
public class StreamDataController {
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/stream-data")
	public SseEmitter streamData(@RequestParam("opt") String opt, @RequestParam("parm2") String parm2,
			@RequestParam("parm3") String pram3) {

		System.out.println("queryParams" + opt + "__" + parm2 + "__" + pram3 + "__");
		SseEmitter emitter = new SseEmitter();

		// Example: Use a service to fetch and send data to the emitter

		if (opt.equals("opt1")) {
			new Thread(() -> {
				try {
					TsharkEth0LiveTraffic.GivenCmd(emitter, parm2);

					emitter.complete();
				} catch (Exception e) {
					emitter.completeWithError(e);
				}
			}).start();
		} else if (opt.equals("opt2")) {
			new Thread(() -> {
				try {
					TsharkEth0LiveTraffic.GivenSrcDes(emitter, parm2, pram3);

					emitter.complete();
				} catch (Exception e) {
					emitter.completeWithError(e);
				}
			}).start();
		} else if (opt.equals("opt3")) {
			new Thread(() -> {
				try {
					TsharkEth0LiveTraffic.get10Packets(emitter, Integer.parseInt(parm2));
					emitter.complete();
					System.out.println("SSSSSSSSSSSSSSSSS");
				} catch (Exception e) {
					emitter.completeWithError(e);
				}
			}).start();

		
		}

		return emitter;
	}
}
