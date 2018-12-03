package fr.guiguilechat.jcelechat.jcesi.impl;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RequestedImplTest {

	@Test
	public void testConstruction() {
		String returned = "a";
		RequestedImpl<String> test = new RequestedImpl<>(200, "ok", returned, null);
		Assert.assertTrue(test.isOk());
		Assert.assertFalse(test.isRedirect());
		Assert.assertFalse(test.isClientError());
		Assert.assertFalse(test.isServerError());

		test = new RequestedImpl<>(300, "ok", returned, null);
		Assert.assertFalse(test.isOk());
		Assert.assertTrue(test.isRedirect());
		Assert.assertFalse(test.isClientError());
		Assert.assertFalse(test.isServerError());

		test = new RequestedImpl<>(400, "ok", returned, null);
		Assert.assertFalse(test.isOk());
		Assert.assertFalse(test.isRedirect());
		Assert.assertTrue(test.isClientError());
		Assert.assertFalse(test.isServerError());

		test = new RequestedImpl<>(500, "ok", returned, null);
		Assert.assertFalse(test.isOk());
		Assert.assertFalse(test.isRedirect());
		Assert.assertFalse(test.isClientError());
		Assert.assertTrue(test.isServerError());
	}

}
