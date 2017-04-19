package cn.com.servision.demo.sms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.kevinsawicki.http.HttpRequest;

@Service
public class SMSService {
	
	public void send(String mobile, String text) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("apikey", "apikey");
		params.put("mobile", mobile);
		params.put("text", text);
		params.put("uid", mobile);
		
		HttpRequest request = HttpRequest
				.post("https://us.yunpian.com/v2/sms/single_send.json")
				.form(params);
		System.out.println(request.body());
	}
	
	
}
