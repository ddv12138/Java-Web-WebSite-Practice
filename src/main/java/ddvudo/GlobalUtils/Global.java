package ddvudo.GlobalUtils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.DigestUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Global {


	public static Logger Logger(Object obj) {
		return LogManager.getLogger(obj);
	}

	public static String postHTTPRequest(String linkUrl, HashMap<String, String> headers, int retry) {
		HttpURLConnection connection = null;
		try {
			URL url = new URL(linkUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.setUseCaches(false);
			for (String key : headers.keySet()) {
				connection.setRequestProperty(key, headers.get(key));
			}
//			connection.setRequestProperty("Cookie", cookie);
			connection.connect();
			String result = null;
			if (connection.getResponseCode() == 200) {
				try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
					StringBuilder sb = new StringBuilder();
					String tmp;
					while (null != (tmp = br.readLine())) {
						sb.append(tmp);
					}
					result = sb.toString();
				}
			}
			assert result != null;
			String resStr = result.substring(result.indexOf("{"), result.lastIndexOf(")"));
			JSONObject res = JSON.parseObject(resStr);
			if (null != res && !res.isEmpty() && res.getIntValue("errno") == 10001 && res.getString("error").contains("data")) {
				Global.Logger(Global.class).error("被封ip");
				if (retry > 0) {
					Global.Logger(Global.class).error("重新连接");
					retry = retry - 1;
					postHTTPRequest(linkUrl, headers, retry);
				}
			}
			return resStr;
		} catch (Exception e) {
			Global.Logger(Global.class).error(e);
			Global.Logger(Global.class).info("连接失败");
			if (null != connection) {
				connection.disconnect();
				connection = null;
			}
			if (retry > 0) {
				Global.Logger(Global.class).error("重新连接");
				retry = retry - 1;
				return postHTTPRequest(linkUrl, headers, retry);
			}
		} finally {
			if (null != connection) {
				connection.disconnect();
			}
		}
		return null;
	}

	public static String doGetHttpRequest(String httpUrl) {
		HttpURLConnection connection = null;
		InputStream is = null;
		BufferedReader br = null;
		String result = null;// 返回结果字符串
		try {
			// 创建远程url连接对象
			URL url = new URL(httpUrl);
			// 通过远程url连接对象打开一个连接，强转成httpURLConnection类
			connection = (HttpURLConnection) url.openConnection();
			// 设置连接方式：get
			connection.setRequestMethod("GET");
			// 设置连接主机服务器的超时时间：15000毫秒
			connection.setConnectTimeout(15000);
			// 设置读取远程返回的数据时间：60000毫秒
			connection.setReadTimeout(60000);
			// 发送请求
			connection.connect();
			// 通过connection连接，获取输入流
			if (connection.getResponseCode() == 200) {
				is = connection.getInputStream();
				// 封装输入流is，并指定字符集
				br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
				// 存放数据
				StringBuilder sbf = new StringBuilder();
				String temp;
				while ((temp = br.readLine()) != null) {
					sbf.append(temp);
				}
				result = sbf.toString();
			}
		} catch (Exception e) {
			Global.Logger(Global.class).error(e);
			e.printStackTrace();
		} finally {
			// 关闭资源
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			assert connection != null;
			connection.disconnect();// 关闭远程连接
		}
		return result;
	}

	/**
	 * 从网络Url中下载文件
	 */
	public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//设置超时间为3秒
		conn.setConnectTimeout(3 * 1000);
		//防止屏蔽程序抓取而返回403错误
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		//得到输入流
		try (InputStream inputStream = conn.getInputStream()) {
			//获取自己数组
			byte[] getData = readInputStream(inputStream);
			//文件保存位置
			File saveDir = new File(savePath);
			if (!saveDir.exists()) {
				if (!saveDir.mkdir()) {
					throw new IOException("文件夹创建失败");
				}
			}
			File file = new File(saveDir + File.separator + fileName);
			try (FileOutputStream fos = new FileOutputStream(file)) {
				fos.write(getData);
			}
		}
		Global.Logger(Global.class).info("info:" + url + " download success");
	}

	/**
	 * 从输入流中获取字节数组
	 *
	 */
	public static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	public static String passwdEncrypt(String passwd) {
		return DigestUtils.md5DigestAsHex(passwd.getBytes());
	}
}
