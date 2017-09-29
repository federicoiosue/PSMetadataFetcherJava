package it.feio.psmf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.feio.psmf.models.PlayStoreResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MetadataFetcherController {

    static final String GREETING = "I just answer HELLO to check that everything works. I'm stupid, I know.!";

    @RequestMapping("/hello")
    public String hello() {
        return GREETING;
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET}, produces = "application/json")
    public String fetchMetadata(@RequestParam("url") String appUrl) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(new PlayStoreResult());
    }
}
