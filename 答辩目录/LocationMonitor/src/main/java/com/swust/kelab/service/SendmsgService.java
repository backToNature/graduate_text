package com.swust.kelab.service;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.swust.kelab.service.engine.JobLauncherDetails;

@Service("sendmsgservice")
public class SendmsgService {
	private static Log log = LogFactory.getLog(JobLauncherDetails.class);

	public static void Send(String phone, int ImsiId, int ImeiId, String name,
			String longitude, String latitude) throws Exception {
		try {
			HttpClient client = new HttpClient();
			PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn/");
			post.addRequestHeader("Content-Type",
					"application/x-www-form-urlencoded;charset=utf-8");
			NameValuePair[] data = {
					new NameValuePair("Uid", "干哥牌白开水"),
					new NameValuePair("Key", "c7c8b0ece96d4e9545db"),
					new NameValuePair("smsMob", phone),
					new NameValuePair("smsText", "姓名:" + name + "\n"
							+ "ImsiID:" + ImsiId + "\n" + "ImeiID:" + ImeiId
							+ "\n" + "经度:" + longitude + "\n" + "纬度:"
							+ latitude + "\n" + "【西南科技大学互联网作战指挥系统】") };
			post.setRequestBody(data);
			client.executeMethod(post);
			Header[] headers = post.getResponseHeaders();
			int statusCode = post.getStatusCode();
			log.debug("statusCode:" + statusCode);
			for (Header h : headers) {
				log.debug(h.toString());
			}
			String result = new String(post.getResponseBodyAsString().getBytes(
					"utf-8"));
			log.debug(result);
			post.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
