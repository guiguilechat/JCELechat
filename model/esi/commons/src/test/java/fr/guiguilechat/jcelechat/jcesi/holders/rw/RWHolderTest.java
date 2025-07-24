package fr.guiguilechat.jcelechat.jcesi.holders.rw;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RWHolderTest {

	@Test(timeOut = 1000L)
	void testSetGet() {
		RWHolder<List<Integer>> test = new RWHolder<>();
		Assert.assertFalse(test.isAvailable());

		List<Integer> data = List.of(4, 1, 5);
		test.set(data);
		Assert.assertTrue(test.isAvailable());
		Assert.assertEquals(test.get(), data);

		List<Integer> data2 = List.of(40, 15, 25);
		test.set(data2);
		Assert.assertTrue(test.isAvailable());
		Assert.assertEquals(test.get(), data2);
	}

	@Test(timeOut = 1000L)
	void testListener() {
		int[] called = { 0 };
		RWHolder<List<Integer>> test = new RWHolder<>();
		Assert.assertFalse(test.isAvailable());
		Assert.assertEquals(called[0], 0);
		test.addListener(n -> called[0]++);
		Assert.assertEquals(called[0], 0);

		List<Integer> data = List.of(4, 1, 5);
		test.set(data);
		Assert.assertEquals(called[0], 1);
		test.addListener(n -> called[0]++);
		Assert.assertEquals(called[0], 2);

	}
}
