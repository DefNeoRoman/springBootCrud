package app.service.impl;

import app.config.OauthServiceGoogleConfig;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OauthServiceProvider {

    private final
    OauthServiceGoogleConfig serviceGoogleConfig;

    @Autowired
    public OauthServiceProvider(OauthServiceGoogleConfig serviceGoogleConfig) {
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
