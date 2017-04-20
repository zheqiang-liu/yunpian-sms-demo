package cn.com.servision.demo.sms;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;

@Service
public class SMSService {
	
	private static final String API_KEY = "API KEY";
	
	public void send(String mobile, String text) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("apikey", API_KEY);
		params.put("mobile", mobile);
		params.put("text", text);
		params.put("uid", mobile);
		
		HttpRequest request = HttpRequest
				.post("https://us.yunpian.com/v2/sms/single_send.json")
				.form(params);
		System.out.println(request.body());
	}

	public void processUserReply(String sms_reply) {
		
		String smsReplyJsonStr = null;
		try {
			smsReplyJsonStr = URLDecoder.decode(sms_reply, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
		JSONObject jsonObj = JSON.parseObject(smsReplyJsonStr);
		String extend = jsonObj.getString("extend");
		String baseExtend = jsonObj.getString("base_extend");
		String id = jsonObj.getString("id");
		String mobile = jsonObj.getString("mobile");
		String replyTime = jsonObj.getString("reply_time");
		String text = jsonObj.getString("text");
		String _sign = jsonObj.getString("_sign");
		
		// check 
		{
			String base = new StringBuilder()
					.append(baseExtend).append(",")
					.append(extend).append(",")
					.append(id).append(",")
					.append(mobile).append(",")
					.append(replyTime).append(",")
					.append(text).append(",")
					.append(API_KEY)
					.toString();
			String sign = DigestUtils.md5DigestAsHex(base.getBytes());
			if (! _sign.equals(sign)) {
				throw new IllegalArgumentException("invalid sms reply: " + jsonObj.toJSONString());
			}
		}
		System.out.println(jsonObj.toJSONString());
	}
	
}
