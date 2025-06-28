package fr.guiguilechat.jcelechat.jcesi.connected;

import static fr.guiguilechat.jcelechat.jcesi.connected.SsoFlow.*;

public class EsiConnectionExample {

	// public static final String LOCAL_CALLBACK = "http://localhost/callback/";
	public static final String LOCAL_CALLBACK = "http://localhost:8080/login/oauth2/code/eve";

	// access flow to the sso
	public static void main(String[] args) {
		SsoFlow sso = SsoFlow.V1();
		// 1 we need app id and app secret.
		String appID = null, appSecret = null;
		// if args were specified we assume they are the app id and app secret.
		if (args.length > 1) {
			appID = args[0];
			appSecret = args[1];
		} else {
			// request user to create api by directing him to the site.
			// the user should copy the appID, then copy the appCode
			openBrowserForDevCreate();
			appID = extractStringFromClipboard();
			System.out.println("api id is " + appID);
			appSecret = extractStringFromClipboard();
			System.out.println("api code is " + appSecret);
		}

		if (!checkAppId(appID)) {
			System.out.println("incorrect app ID " + appID);
			return;
		}
		if (!checkAppSecret(appSecret)) {
			System.out.println("incorect app secret " + appSecret);
			return;
		}
		// the api + code is transformed into the basic code used in headers :
		String basicCode = encode(appID, appSecret);

		// 2 request user to accept the connection of his app to his account
		// the user should copy the url of the error page
		String authCode = sso.getCodeByClipboard(appID, LOCAL_CALLBACK,
		    "esi-characters.read_contacts.v1",
		    "esi-wallet.read_character_wallet.v1"
		// G_ICOAccess.SCOPES
		//
		);
		System.out.println("auth code is " + authCode);

		// 3 get a refresh token. The couple basicCode+refreshtoken allow us to
		// access the app later.
		String refreshToken = sso.getRefreshToken(basicCode, authCode);
		System.out.println("refresh token is " + refreshToken);

		// 4 get an access token from the refreshToken. We need the access token to
		// actually ask the esi.
		String accessToken = sso.getAccessToken(basicCode, refreshToken).token;
		System.out.println("acces token is " + accessToken);

		// 5 call verify to be sure you have a correct

	}

}
