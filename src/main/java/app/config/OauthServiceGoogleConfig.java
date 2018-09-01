package app.config;

import com.github.scribejava.apis.GoogleApi20;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OauthServiceGoogleConfig {

    @Value("${google.api.key}")
    private String apiKey;
    @Value("${google.api.secret}")
    private String apiSecret;
    @Value("${google.api.callback}")
    private String callback;

    private String scope = "https://mail.google.com/";
    private GoogleApi20 apiClass = GoogleApi20.instance();

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public GoogleApi20 getApiClass() {
        return apiClass;
    }

    public void setApiClass(GoogleApi20 apiClass) {
        this.apiClass = apiClass;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
