package it.feio.psmf;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.feio.psmf.http.PlayStoreHttpClient;
import it.feio.psmf.models.PlayStoreResult;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetadataFetcherController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    static final String GREETING = "I just answer HELLO to check that everything works. I'm stupid, I know.!";

    @Autowired
    PlayStoreHttpClient playStoreHttpClient;

    @RequestMapping("/hello")
    public String hello() {
        return GREETING;
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET}, produces = "application/json")
    public String fetchMetadata(@RequestParam("app-package") String appPackage,
      @RequestParam(value = "lang", defaultValue = "en") String lang) throws IOException {
        log.info("Requesting info for app '" + appPackage + "' using language '" + lang + "'");
        PlayStoreResult playStoreResult = playStoreHttpClient.get(appPackage, lang);
        return new ObjectMapper().writeValueAsString(playStoreResult);
    }
}
