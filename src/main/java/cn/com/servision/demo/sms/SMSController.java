package cn.com.servision.demo.sms;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SMSController {
	
	@Autowired
	SMSService smsService;
	
	@RequestMapping(value = "/user/reply")
	public String callback(String sms_reply) throws UnsupportedEncodingException {
		
		smsService.processUserReply(sms_reply);
		// important
		return "SUCCESS";
	}
	
	@RequestMapping(value = "/test-send", method = RequestMethod.GET)
	public String send() {
		
		smsService.send("#{Mobile}", "#{Content}");
		return "SUCCESS";
	}
	
}
