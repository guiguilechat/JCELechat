package fr.guiguilechat.jcelechat.jcesi.holders.transform;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.jcesi.holders.BoolHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.rw.RWHolder;

public class BoolTransformPairTest {

	@Test(timeOut = 1000L)
	void testNotEmpty() {
		RWHolder<List<Integer>> source1 = new RWHolder<>();
		BoolHolder empty1 = source1.test(List::isEmpty);
		RWHolder<List<Integer>> source2 = new RWHolder<>();
		BoolHolder empty2 = source2.test(List::isEmpty);
		BoolHolder and = empty1.and(empty2);
		BoolHolder or = empty1.or(empty2);
		BoolHolder xor = empty1.xor(empty2);

		Assert.assertFalse(empty1.isAvailable());
		Assert.assertFalse(empty2.isAvailable());
		Assert.assertFalse(and.isAvailable());
		Assert.assertFalse(or.isAvailable());
		Assert.assertFalse(xor.isAvailable());

		List<Integer> data1 = List.of(4, 1, 5);
		source1.set(data1);
		Assert.assertTrue(empty1.isAvailable());
		Assert.assertFalse(empty2.isAvailable());
		Assert.assertFalse(and.isAvailable());
		Assert.assertFalse(or.isAvailable());
		Assert.assertFalse(xor.isAvailable());

		List<Integer> data2 = List.of();
		source2.set(data2);
		Assert.assertTrue(empty1.isAvailable());
		Assert.assertTrue(empty2.isAvailable());
		Assert.assertTrue(and.isAvailable());
		Assert.assertTrue(or.isAvailable());
		Assert.assertTrue(xor.isAvailable());

		Assert.assertFalse(and.get());
		Assert.assertTrue(or.get());
		Assert.assertTrue(xor.get());

	}

}
