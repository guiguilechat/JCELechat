package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.jcesi.holders.common.RWHolder;

public class BoolHolderTest {

	@Test(timeOut = 1000L)
	void testTransform() {
		RWHolder<List<Integer>> source = new RWHolder<>();
		BoolHolder empty = source.test(List::isEmpty);
		BoolHolder notEmpty = empty.not();
		Assert.assertFalse(empty.isAvailable());
		Assert.assertFalse(notEmpty.isAvailable());

		List<Integer> data = List.of(4, 1, 5);
		source.set(data);
		Assert.assertFalse(empty.get());
		Assert.assertTrue(notEmpty.get());

		List<Integer> data2 = List.of();
		source.set(data2);
		Assert.assertTrue(empty.get());
		Assert.assertFalse(notEmpty.get());
	}

	@Test(timeOut = 1000L)
	void testTransformPair() {
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
