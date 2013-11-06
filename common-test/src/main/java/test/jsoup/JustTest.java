package test.jsoup;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 利用HttpClient获取html代码，然后使用jsoup对html代码进行解析
 * @author Administrator
 *
 */
public class JustTest {
	public static void main(String[] args) {
		String html = getHtmlByUrl("http://www.m1905.com/mdb/film/comment/");
		if (html != null && !"".equals(html)) {
			Document doc = Jsoup.parse(html);
			Elements linksElements = doc.select("div#movMO_liftBOX dl");
			for (Element ele : linksElements) {
				String typeName = ele.select("div>a").attr("href");
				
				
				System.out.println("content :" + typeName);
			}
		}
	}

	/**
	 * 根据URL获得所有的html信息
	 * 
	 * @param url
	 * @return
	 */
	public static String getHtmlByUrl(String url) {
		String html = null;
		HttpClient httpClient = new DefaultHttpClient();// 创建httpClient对象
		HttpGet httpget = new HttpGet(url);// 以get方式请求该URL
		try {
			HttpResponse responce = httpClient.execute(httpget);// 得到responce对象
			int resStatu = responce.getStatusLine().getStatusCode();// 返回码
			if (resStatu == HttpStatus.SC_OK) {// 200正常 其他就不对
				// 获得相应实体
				HttpEntity entity = responce.getEntity();
				if (entity != null) {
					html = EntityUtils.toString(entity);// 获得html源代码
//					System.out.println(html);
				}
			}
		} catch (Exception e) {
			System.out.println("访问【" + url + "】出现异常!");
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return html;
	}
}