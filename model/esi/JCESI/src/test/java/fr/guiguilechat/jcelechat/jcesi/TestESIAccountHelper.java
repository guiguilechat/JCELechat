package fr.guiguilechat.jcelechat.jcesi;

import fr.guiguilechat.jcelechat.jcesi.ESIAccountHelper.AccessToken;

public class TestESIAccountHelper {

	public static void main(String[] args) {
		AccessToken at = ESIAccountHelper.getAccessToken(
				"1", "2");
		System.err.println("got " + at);
	}

}
