package com.peng.weibo.util.tools;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by PS on 2016/7/20.
 */
public class GetNetTime {

	/**
	 * 获取指定网站的日期时间
	 *
	 * @return
	 * @author SHANHY
	 * @date 2015年11月27日
	 */
    public static Date getWebsiteDatetime() {
        String webUrl1 = "http://www.bjtime.cn";// bjTime
        String webUrl2 = "http://www.baidu.com";// 百度
        String webUrl3 = "http://www.taobao.com";// 淘宝
        String webUrl4 = "http://www.ntsc.ac.cn";// 中国科学院国家授时中心
        String webUrl5 = "http://www.360.cn";// 360
        String webUrl6 = "http://www.beijing-time.org";// beijing-time
		try {
			URL url = new URL(webUrl3);// 取得资源对象
			URLConnection uc = url.openConnection();// 生成连接对象
			uc.connect();// 发出连接
			long ld = uc.getDate();// 读取网站日期时间
			Date date = new Date(ld);// 转换为标准时间对象
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 输出北京时间
			return date;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}