package fr.guiguilechat.jcelechat.jcesi.connected.modeled;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected;
import fr.guiguilechat.jcelechat.jcesi.connected.ESIConnected.R_Verify;

/**
 * access to the verify method( not in swagger)
 *
 */
public class Verify {

	private static final Logger logger = LoggerFactory.getLogger(Verify.class);

	private final CountDownLatch latch = new CountDownLatch(1);

	private R_Verify verify = null;

	public Verify(ESIConnected raw) {
		if (raw == null) {
			verify=ESIConnected.NULLVERIFY;
		} else {
			new Thread(() -> {
				verify = raw.verify();
				latch.countDown();
			}).start();
		}
	}

	/** return true if the verification process is ok */
	public boolean check() {
		if (verify != null) {
			return true;
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			logger.warn("while getting verification informations", e);
		}
		return verify != null;
	}

	public int characterID() {
		if (!check()) {
			throw new NullPointerException();
		}
		return verify.CharacterID();
	}

	public String characterName() {
		if (!check()) {
			throw new NullPointerException();
		}
		return verify.CharacterName();
	}

	public String expiresOn() {
		if (!check()) {
			throw new NullPointerException();
		}
		return verify.ExpiresOn();
	}

	public String scopes() {
		if (!check()) {
			throw new NullPointerException();
		}
		return verify.Scopes();
	}

	public String tokenType() {
		if (!check()) {
			throw new NullPointerException();
		}
		return verify.TokenType();
	}

	public String characterOwnerHash() {
		if (!check()) {
			throw new NullPointerException();
		}
		return verify.CharacterOwnerHash();
	}

}
