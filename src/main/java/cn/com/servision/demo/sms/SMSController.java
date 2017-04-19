package cn.com.servision.demo.sms;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
		
		String smsReplyJsonStr = URLDecoder.decode(sms_reply, "utf-8");
		System.out.println(smsReplyJsonStr);
		// important
		return "SUCCESS";
	}
	
	@RequestMapping(value = "/test-send", method = RequestMethod.GET)
	public String send() {
		
		smsService.send("mobile", "content");
		return "SUCCESS";
	}
	
}
