package it.feio.psmf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PlayStoreMetadataFetcherApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(PlayStoreMetadataFetcherApplication.class, args);
    }

}
