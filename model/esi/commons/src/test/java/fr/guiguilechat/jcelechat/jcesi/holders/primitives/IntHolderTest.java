package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import org.testng.Assert;
import org.testng.annotations.Test;

public class IntHolderTest {

	@Test
	public void testTransform() {
		IntRWHolder test = new IntRWHolder();
		Assert.assertFalse(test.isAvailable());
		IntHolder plus2 = test.mapInt(i -> i + 2);
		Assert.assertFalse(plus2.isAvailable());

		test.set(5);
		Assert.assertTrue(test.isAvailable());
		Assert.assertTrue(plus2.isAvailable());
		Assert.assertEquals(plus2.get(), 7);

		IntHolder plus3 = test.mapInt(i -> i + 3);
		Assert.assertTrue(plus3.isAvailable());
		Assert.assertEquals(plus3.get(), 8);

		IntHolder sub = plus3.sub(plus2);
		Assert.assertTrue(sub.isAvailable());
		Assert.assertEquals(sub.get(), 1);
	}

}
