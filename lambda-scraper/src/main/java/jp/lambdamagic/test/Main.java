package jp.lambdamagic.test;

import java.io.IOException;
import java.net.MalformedURLException;

import jp.lambdamagic.json.JSONWriter;
import jp.lambdamagic.json.data.JSONObject;
import jp.lambdamagic.pipeline.DataSource;
import jp.lambdamagic.pipeline.Pipeline;
import jp.lambdamagic.web.http.HttpClient;
import jp.lambdamagic.web.scraping.WebPage;

public class Main {

	public static void main(String[] args) throws MalformedURLException, IOException {
		HttpClient httpClient = new HttpClient();
		
		Pipeline.from(DataSource.asDataSource("https://beauty.hotpepper.jp/slnH000311705/"))
				.map(url -> httpClient.get(url))
				.map(response -> new WebPage(response.getText()))
				.map(page -> new JSONObject() {
					{
						set("title", page.getTitle());
						set("h1", page.getH1());
						set("description", page.getDivByClass("slnTopImgDescription"));
					}
				})
				.print()
				.map(new JSONWriter("test.json"))
				.run();
	}
}
