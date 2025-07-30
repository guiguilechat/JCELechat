package fr.guiguilechat.jcelechat.jcesi.holders.collections;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ListHolderTest {

	@Test(timeOut = 1000L)
	public void testTransform() {
		ListRWHolder<Integer> test = new ListRWHolder<>();
		var size = test.size();
		var empty = test.isEmpty();
		var at2 = test.at(2, -1);
		var atm1 = test.at(-1, -1);
		var distinct = test.distinct();
		var dSize = distinct.size();
		Assert.assertFalse(test.isAvailable());
		Assert.assertFalse(size.isAvailable());
		Assert.assertFalse(empty.isAvailable());
		Assert.assertFalse(at2.isAvailable());
		Assert.assertFalse(atm1.isAvailable());
		Assert.assertFalse(distinct.isAvailable());
		Assert.assertFalse(dSize.isAvailable());

		test.set(List.of(1, 1, 2, 3, 3, 4));
		Assert.assertTrue(test.isAvailable());
		Assert.assertTrue(size.isAvailable());
		Assert.assertEquals(size.get(), 6);
		Assert.assertTrue(empty.isAvailable());
		Assert.assertFalse(empty.get());
		Assert.assertTrue(at2.isAvailable());
		Assert.assertEquals(at2.get(), 2);
		Assert.assertTrue(atm1.isAvailable());
		Assert.assertEquals(atm1.get(), 4);
		Assert.assertTrue(distinct.isAvailable());
		Assert.assertTrue(dSize.isAvailable());
		Assert.assertEquals(dSize.get(), 4);

	}

	@Test(timeOut = 1000L)
	public void testMultiListeners() {
		ListRWHolder<Integer> test = new ListRWHolder<>();
		var max = test.mapInt(l -> l.stream().mapToInt(i -> i).max().orElse(-1));
		var min = test.mapInt(l -> l.stream().mapToInt(i -> i).min().orElse(-1));
		var mult = max.sub(min).mult(test.size());

		test.set(List.of(1, 1, 2, 3, 3, 4));
		Assert.assertEquals(min.get(), 1);
		Assert.assertEquals(max.get(), 4);
		// (4-1)×6
		Assert.assertEquals(mult.get(), 18);

		test.set(List.of());
		Assert.assertEquals(min.get(), -1);
		Assert.assertEquals(max.get(), -1);
		Assert.assertEquals(mult.get(), 0);
	}

	@Test(timeOut = 1000L)
	public void testSortDistinct() {
		ListRWHolder<Integer> test = new ListRWHolder<>();
		var sorted = test.sorted(Integer::compareTo);
		var distinctSorted = test.distinct().sorted(Integer::compareTo);

		test.set(List.of(3, 2, 1, 2, 3));
		Assert.assertEquals(sorted.get(), List.of(1, 2, 2, 3, 3));
		Assert.assertEquals(distinctSorted.get(), List.of(1, 2, 3));

	}

	@Test(timeOut = 1000L)
	public void testSubLists() {
		ListRWHolder<Integer> test = new ListRWHolder<>();
		var sub = test.subList(2, -1);
		var idx2 = test.indexOf(2);
		var subFrom2 = test.subListFrom(idx2);

		test.set(List.of(3, 2, 1, 2, 3));
		Assert.assertEquals(sub.get(), List.of(1, 2, 3));
		Assert.assertEquals(idx2.get(), 1);
		Assert.assertEquals(subFrom2.get(), List.of(2, 1, 2, 3));

	}

}
