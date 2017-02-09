package jp.lambdamagic.web.scraping;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WebPageTest {

	@Test
	public void getContent_returnGivenWebPageContent() {
		WebPage webPage = new WebPage("testContent");
		assertThat(webPage.getContent(), is("testContent"));
	}

	@Test
	public void getH1_returnH1OfGivenWebPageContent() {
		WebPage webPage = new WebPage("<h1>test</h1>");
		assertThat(webPage.getH1(), is("test"));
	}

	@Test
	public void getTitle_returnTitleOfGivenWebPageContent() {
		WebPage webPage = new WebPage("<title>test</title>");
		assertThat(webPage.getTitle(), is("test"));
	}

}
