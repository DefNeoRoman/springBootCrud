import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.model.Verifier;
import com.github.scribejava.core.oauth.OAuth20Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestRole {

    private static String clientId = "47900127570-nnom98ab0tdrsuavgtbiraplocr77rn8.apps.googleusercontent.com";
    private static String clientSecret="_0bGyS-XhD3TalqGvFAdw4Be";
    private static final String NETWORK_NAME = "http://localhost:8080";
    private static final String PROTECTED_RESOURCE_URL = "http://localhost:8080/rest/user/all";

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
                .apiSecret(clientSecret).scope("profile")
                .callback("http://localhost:8080/login")
                .build(GoogleApi20.instance());
        final Scanner in = new Scanner(System.in);
        System.out.println("=== " + NETWORK_NAME + "'s OAuth Workflow ===");
        System.out.println();
        // Obtain the Authorization URL
        System.out.println("Fetching the Authorization URL...");
        final String authorizationUrl = service.getAuthorizationUrl();
        System.out.println("Got the Authorization URL!");
        System.out.println("Now go and authorize ScribeJava here:");
        System.out.println(authorizationUrl);
        System.out.println("And paste the authorization code here");
        System.out.print(">>");
        final String code = in.nextLine();
        System.out.println();
        // Trade the Request Token and Verfier for the Access Token
        System.out.println("Trading the Request Token for an Access Token...");
        final OAuth2AccessToken accessToken = service.getAccessToken(new Verifier(code));
        System.out.println("Got the Access Token!");
        System.out.println("(The raw response looks like this: " + accessToken.getRawResponse() + "')");
        System.out.println();
        System.out.println("Now we're going to access a protected resource...");
        final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL,service);
        service.signRequest(accessToken, request);
        final String response = service.getAuthorizationUrl();
        System.out.println("Got it! Lets see what we found...");
        System.out.println();
        System.out.println(response);
        System.out.println(response);
        System.out.println();
        System.out.println("Thats it man! Go and build something awesome with ScribeJava! :)");
    }


}
