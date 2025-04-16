package fr.guiguilechat.jcelechat.jcesi;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.lelouet.tools.holders.interfaces.collections.SetHolder;

public class ConnectedImplTest {

	@Test
	public void testUserAgent() {
		ConnectedImpl test = new ConnectedImpl() {

			@Override
			public SetHolder<String> getRoles() {
				return null;
			}
		};

		Assert.assertEquals(test.getUserAgent(),
				"jcelechat (lechatguigui@gmail.com +https://github.com/guiguilechat/JCELechat/)");
	}

}
