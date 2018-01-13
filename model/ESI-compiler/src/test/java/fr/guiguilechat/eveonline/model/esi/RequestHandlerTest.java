package fr.guiguilechat.eveonline.model.esi;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RequestHandlerTest {

	@Test
	public void testFlatten() {
		RequestHandler rh = new RequestHandler() {

			@Override
			public <T> T convert(String line, Class<? extends T> clazz) {
				return null;
			}

			@Override
			public String connectPost(String url, Map<String, String> content, boolean connected) {
				return null;
			}

			@Override
			public String connectGet(String url, boolean connected) {
				return null;
			}
		};
		Assert.assertEquals(rh.flatten(null), null);
		Assert.assertEquals(rh.flatten("lol"), "lol");
		Assert.assertEquals(rh.flatten(12), "12");
		Assert.assertEquals(rh.flatten(new String[] { "l", "o", "l" }), "l,o,l");
		Assert.assertEquals(rh.flatten(new Double[] { 0., 1., 2. }), "0.0,1.0,2.0");
		Assert.assertEquals(rh.flatten(new Float[] { 0.f, 1.f, 2.f }), "0.0,1.0,2.0");
		Assert.assertEquals(rh.flatten(new Integer[] { 1, 2, 3 }), "1,2,3");
		Assert.assertEquals(rh.flatten(new Long[] { 1l, 2l, 3l }), "1,2,3");
	}

}
