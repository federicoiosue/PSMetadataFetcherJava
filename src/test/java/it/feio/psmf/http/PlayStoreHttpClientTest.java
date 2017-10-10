package it.feio.psmf.http;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import it.feio.psmf.BaseTest;
import it.feio.psmf.CachingConfig;
import it.feio.psmf.models.PlayStoreResult;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@ContextConfiguration(classes = CachingConfig.class, loader = AnnotationConfigContextLoader.class)
@PrepareForTest(Jsoup.class)
public class PlayStoreHttpClientTest extends BaseTest {

    final String APP_PACKAGE = "it.feio.android.omninotes";
    final String APP_LANG = "it";

    @Autowired
    ApplicationContext context;

    @SpyBean
    PlayStoreHttpClient playStoreHttpClientMock;

    @Test
    public void get() throws Exception {
        Connection connectionMock = mock(Connection.class);
        when(connectionMock.get()).thenReturn(Jsoup.parse(buildHtml(), "", Parser.htmlParser()));

        PowerMockito.mockStatic(Jsoup.class);
        when(Jsoup.connect(anyString())).thenReturn(connectionMock);

        PlayStoreResult resultOne = playStoreHttpClientMock.get(APP_PACKAGE, "");
        PlayStoreResult resultTwo= playStoreHttpClientMock.get(APP_PACKAGE, "");

        assertEquals(resultOne, resultTwo);
    }

    @Test
    public void buildAppUrl() throws Exception {
        String url = new PlayStoreHttpClient().buildAppUrl(APP_PACKAGE, APP_LANG);
        assertEquals(PlayStoreHttpClient.PLAY_STORE_URL + "?id=" + APP_PACKAGE + "&hl=" + APP_LANG, url);
    }

}