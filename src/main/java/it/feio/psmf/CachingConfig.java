package it.feio.psmf;

import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig {

    int CACHE_TTL_IN_MINUTES = 10;

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(final String name) {
                return new ConcurrentMapCache(name,
                  CacheBuilder.newBuilder().expireAfterWrite(CACHE_TTL_IN_MINUTES, TimeUnit.MINUTES).maximumSize(100).build().asMap(),
                  false);
            }
        };
        return cacheManager;
    }

}
