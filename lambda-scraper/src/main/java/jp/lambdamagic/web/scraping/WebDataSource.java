package jp.lambdamagic.web.scraping;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;

import jp.lambdamagic.pipeline.DataSource;
import jp.lambdamagic.text.Encodings;
import jp.lambdamagic.web.http.HttpClient;
import jp.lambdamagic.web.http.HttpResponse;
import jp.lambdamagic.web.http.HttpStatusCode;

public class WebDataSource implements DataSource<WebPage> {

	private WebPage webPage;

	public WebDataSource(String url, String encoding) throws MalformedURLException, IOException {
		HttpClient httpClient = new HttpClient();
		HttpResponse httpResponse = httpClient.get(url);

		if (httpResponse.getStatusCode() == HttpStatusCode.OK) {
			this.webPage = new WebPage(httpResponse.getText(encoding));
		}
	}
	
	public WebDataSource(String url) throws MalformedURLException, IOException {
		this(url, Encodings.UTF_8);
	}

	@Override
	public Optional<WebPage> readData() {
		Optional<WebPage> result = Optional.ofNullable(webPage);
		webPage = null;
		
		return result;
	}

}
