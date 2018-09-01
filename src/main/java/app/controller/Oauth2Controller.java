package app.controller;

import app.service.impl.OauthServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;
@Controller
public class Oauth2Controller {
    @Autowired
    private OauthServiceProvider serviceProvider;
    @GetMapping("user/googleEnter")
    public String login(WebRequest request) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("",""));
        // getting request and access token from session
//        Token requestToken = (Token) request.getAttribute(ATTR_OAUTH_REQUEST_TOKEN, SCOPE_SESSION);
//        Token accessToken = (Token) request.getAttribute(ATTR_OAUTH_ACCESS_TOKEN, SCOPE_SESSION);
//        if(requestToken == null || accessToken == null) {
//            // generate new request token
//            OAuth20Service service = serviceProvider.getGoogleService();
//            final Map<String, String> additionalParams = new HashMap<>();
//            additionalParams.put("access_type", "offline");
//            additionalParams.put("prompt", "consent");
//            final String authorizationUrl = service.getAuthorizationUrl(additionalParams);
//            requestToken = service.getRequestToken();
//            request.setAttribute(ATTR_OAUTH_REQUEST_TOKEN, requestToken, SCOPE_SESSION);
//
//            // redirect to linkedin auth page
//            return "redirect:" + service.getAuthorizationUrl(requestToken);
//        }
       return "welcome";
    }
}
