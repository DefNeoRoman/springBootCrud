package app.controller;

import app.model.Role;
import app.model.User;
import app.service.impl.OauthServiceProvider;
import app.service.interfaces.RoleService;
import app.service.interfaces.UserService;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.model.Verifier;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class Oauth2Controller {


    private static final String DEFAULT_USER_NAME = "oAuthUser";
    private final
    UserService userService;
    private final
    RoleService roleService;
    private final
    OauthServiceProvider serviceProvider;
    private final
    Logger logger = Logger.getLogger(Oauth2Controller.class.getName());
    private final
    OAuth20Service googleService;
    @Autowired
    public Oauth2Controller(OauthServiceProvider serviceProvider, UserService userService, RoleService roleService) {
        this.serviceProvider = serviceProvider;
        googleService = serviceProvider.getGoogleService();
        this.userService = userService;
        this.roleService = roleService;

    }

    @GetMapping("user/googleEnter")
    public String login() {
        final Map<String, String> additionalParams = new HashMap<>();
        additionalParams.put("access_type", "offline");
        additionalParams.put("prompt", "consent");
        final String authorizationUrl = googleService.getAuthorizationUrl(additionalParams);
        return "redirect:"+authorizationUrl;
    }
    @GetMapping("googleCallBack")
    public String callBack(@RequestParam String code) {
        OAuth2AccessToken accessToken = googleService.getAccessToken(new Verifier(code));
        accessToken = googleService.refreshAccessToken(accessToken.getRefreshToken());
        final OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.googleapis.com/gmail/v1/users/me/profile",googleService);
        googleService.signRequest(accessToken,request);
        ObjectMapper mapper = new ObjectMapper();
        String body = request.send().getBody();
        User user = new User();
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleByRoleName("ROLE_ADMIN"));
        user.setRoles(roles);
        try {
            HashMap hashMap = mapper.readValue(body, HashMap.class);

            String emailAddress = (String)hashMap.get("emailAddress");
            user.setEmail(emailAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setName(DEFAULT_USER_NAME);
        User userByEmail = userService.getUserByEmail(user.getEmail());
        SecurityContext context = SecurityContextHolder.getContext();
        if(userByEmail == null){
            userService.addUser(user);
            context.setAuthentication(new UsernamePasswordAuthenticationToken(user,"user"));
        }else{
            context.setAuthentication(new UsernamePasswordAuthenticationToken(userByEmail,userByEmail.getPassword()));
        }
        return "redirect:/user";
    }

}
