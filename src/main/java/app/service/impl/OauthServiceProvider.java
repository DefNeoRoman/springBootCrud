package app.service.impl;

import app.config.OauthServiceGoogleConfig;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class OauthServiceProvider {
    private final Logger logger = Logger.getLogger(OauthServiceProvider.class.getName());
    private final
    OauthServiceGoogleConfig serviceGoogleConfig;

    @Autowired
    public OauthServiceProvider(OauthServiceGoogleConfig serviceGoogleConfig) {
        logger.info("init Oauth Service Provider");
        this.serviceGoogleConfig = serviceGoogleConfig;
    }


    public OAuth20Service getGoogleService() {

        return new ServiceBuilder().apiKey(serviceGoogleConfig.getApiKey())
                .apiSecret(serviceGoogleConfig.getApiSecret())
                .scope(serviceGoogleConfig.getScope())
                .callback(serviceGoogleConfig.getCallback())
                .build(serviceGoogleConfig.getApiClass());
    }
}
