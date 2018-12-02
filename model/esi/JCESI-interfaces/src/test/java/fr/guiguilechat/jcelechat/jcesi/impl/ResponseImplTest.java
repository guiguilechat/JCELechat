package fr.guiguilechat.jcelechat.jcesi.impl;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ResponseImplTest {

	@Test
	public void testConstruction() {
		String returned = "a";
		ResponseImpl<String> test = new ResponseImpl<>(200, "ok", returned, null);
		Assert.assertTrue(test.isOk());
		Assert.assertFalse(test.isRedirect());
		Assert.assertFalse(test.isClientError());
		Assert.assertFalse(test.isServerError());

		test = new ResponseImpl<>(300, "ok", returned, null);
		Assert.assertFalse(test.isOk());
		Assert.assertTrue(test.isRedirect());
		Assert.assertFalse(test.isClientError());
		Assert.assertFalse(test.isServerError());

		test = new ResponseImpl<>(400, "ok", returned, null);
		Assert.assertFalse(test.isOk());
		Assert.assertFalse(test.isRedirect());
		Assert.assertTrue(test.isClientError());
		Assert.assertFalse(test.isServerError());

		test = new ResponseImpl<>(500, "ok", returned, null);
		Assert.assertFalse(test.isOk());
		Assert.assertFalse(test.isRedirect());
		Assert.assertFalse(test.isClientError());
		Assert.assertTrue(test.isServerError());
	}

}
