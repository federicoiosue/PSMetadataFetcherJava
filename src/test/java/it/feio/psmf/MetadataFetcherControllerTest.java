package it.feio.psmf;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.feio.psmf.models.PlayStoreResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MetadataFetcherController.class)
@AutoConfigureMockMvc
public class MetadataFetcherControllerTest {

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
          .andExpect(status().is4xxClientError());
    }

    @Test
    public void testFetchMetadata() throws Exception {
        mockMvc.perform(get("/").param("url", ""))
          .andExpect(status().isOk())
          .andDo(result -> {
              String a = result.getResponse().getForwardedUrl();
              PlayStoreResult res = new ObjectMapper().readValue(a, PlayStoreResult.class);
              assertEquals(PlayStoreResult.class, res.getClass());
          });
    }

}
