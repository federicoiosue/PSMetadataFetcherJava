package it.feio.psmf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.feio.psmf.http.PlayStoreHttpClient;
import it.feio.psmf.models.PlayStoreResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MetadataFetcherController.class)
@AutoConfigureMockMvc
public class MetadataFetcherControllerTest extends BaseTest {

    @MockBean
    private PlayStoreHttpClient playStoreHttpClientMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHello() throws Exception {
        mockMvc.perform(get("/hello"))
          .andExpect(status().isOk())
          .andExpect(forwardedUrl(MetadataFetcherController.GREETING));
    }

    @Test
    public void testFetchMetadataShouldFailForMissingParameter() throws Exception {
        mockMvc.perform(get("/"))
          .andExpect(status().is4xxClientError())
        .andDo(result -> "Required String parameter 'app-package' is not present".equals(result.getResponse().getErrorMessage()));
    }

    @Test
    public void testFetchMetadata() throws Exception {
        given(this.playStoreHttpClientMock.get(anyString(), anyString())).willReturn(buildPlayStoreResult());
        mockMvc.perform(get("/it.feio.android.omninotes"))
          .andExpect(status().isOk())
          .andDo(result -> {
              String a = result.getResponse().getForwardedUrl();
              PlayStoreResult res = new ObjectMapper().readValue(a, PlayStoreResult.class);
              assertEquals(PlayStoreResult.class, res.getClass());
              assertEquals(PLAY_STORE_RESULT_SOFTWARE_VERSION, res.getSoftwareVersion());
          });
    }

    @Test
    public void testFetchMetadataWithLang() throws Exception {
        String testedLang = "it";
        String expectedPostfixWithLang = PLAY_STORE_OPERATING_SISTEMS + LANG_POSTFIX + testedLang;

        given(this.playStoreHttpClientMock.get(anyString(), eq(testedLang))).willReturn(buildPlayStoreResult(testedLang));
        given(this.playStoreHttpClientMock.get(anyString(), AdditionalMatchers.not(eq(testedLang)))).willReturn(buildPlayStoreResult());

        mockMvc.perform(get("/it.feio.android.omninotes/" + testedLang))
          .andExpect(status().isOk())
          .andDo(result -> {
              String a = result.getResponse().getForwardedUrl();
              PlayStoreResult res = new ObjectMapper().readValue(a, PlayStoreResult.class);
              assertEquals(expectedPostfixWithLang, res.getOperatingSystems());
          });
        mockMvc.perform(get("/it.feio.android.omninotes/"))
          .andExpect(status().isOk())
          .andDo(result -> {
              String a = result.getResponse().getForwardedUrl();
              PlayStoreResult res = new ObjectMapper().readValue(a, PlayStoreResult.class);
              assertNotEquals(expectedPostfixWithLang, res.getOperatingSystems());
          });
    }

}
