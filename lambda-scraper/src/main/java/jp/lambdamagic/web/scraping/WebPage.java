package jp.lambdamagic.web.scraping;

import jp.lambdamagic.text.Strings;

public class WebPage {

	private String content;

	private String title;
	private String h1;

	public WebPage(String content) {
		this.content = content;

		this.title = cropTag(content, "h1");
		this.h1 = cropTag(content, "title");
	}

	public String getContent() {
		return content;
	}

	public String getTitle() {
		return title;
	}

	public String getH1() {
		return h1;
	}
	
	public String getDivByClass(String className) {
		return Strings.crop(Strings.crop(content,  className, "/div>"), ">", "<");
	}

	private String cropTag(String content, String targetTag) {
		return Strings.crop(Strings.crop(content, "<" + targetTag, targetTag + ">"), ">", "<");
	}

}
