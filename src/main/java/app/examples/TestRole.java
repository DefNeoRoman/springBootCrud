package app.examples;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.model.Verifier;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.util.*;

public class TestRole {

    private static String clientId = "64106157599-j0c0ea9mgj164enuhm6ehdq1tnjp3li3.apps.googleusercontent.com";
    private static String clientSecret="zoYRhHqjIUph2hZXo1fILE9J";
    private static final String NETWORK_NAME = "http://localhost:8080";


    public void test(){
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        List<Integer> integers2 = new ArrayList<>();
        integers2.add(1);
        integers2.add(2);
        List<Integer> result = new ArrayList<>();
        for(Integer i : integers) {
            if(!integers2.contains(i)) {
                result.add(i);
            }
        }
      result.forEach(System.out::println);
    }

    public static void main (String... args){

        final OAuth20Service service = new ServiceBuilder().apiKey(clientId)
                .apiSecret(clientSecret).scope("https://mail.google.com/")
                .callback("http://localhost:8080/googleCallBack")
                .build(GoogleApi20.instance());
        final Scanner in = new Scanner(System.in, "UTF-8");
        System.out.println("=== " + NETWORK_NAME + "'s OAuth Workflow ===");
        System.out.println();
        System.out.println("Fetching the Authorization URL...");
        final Map<String, String> additionalParams = new HashMap<>();
        additionalParams.put("access_type", "offline");
        additionalParams.put("prompt", "consent");
        final String authorizationUrl = service.getAuthorizationUrl(additionalParams);
        System.out.println("Got the Authorization URL!");
        System.out.println("Now go and authorize ScribeJava here:");
        System.out.println(authorizationUrl);
        System.out.println("And paste the authorization code here");
        System.out.print(">>");
        final String code = in.nextLine();
        System.out.println();
        System.out.println("Trading the Request Token for an Access Token...");
        OAuth2AccessToken accessToken = service.getAccessToken(new Verifier(code));
        System.out.println("Got the Access Token!");
        System.out.println("(The raw response looks like this: " + accessToken.getRawResponse() + "')");
        System.out.println("Refreshing the Access Token...");
        accessToken = service.refreshAccessToken(accessToken.getRefreshToken());
        System.out.println("Refreshed the Access Token!");
        System.out.println("(The raw response looks like this: " + accessToken.getRawResponse() + "')");
        System.out.println();
        System.out.println("Now we're going to access a protected resource...");
        System.out.println("Paste fieldnames to fetch (leave empty to get profile, 'exit' to stop example)");
       // final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL,service);
        final OAuthRequest request2 = new OAuthRequest(Verb.GET, "https://www.googleapis.com/gmail/v1/users/me/profile",service);

//        service.signRequest(accessToken, request);
//        String response = request.send().getBody();
        service.signRequest(accessToken,request2);
        System.out.println(request2.send().getBody());

    }
}