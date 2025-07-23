package fr.guiguilechat.jcelechat.jcesi.holders.transform;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.jcesi.holders.BoolHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.rw.RWHolder;

public class TransformBoolHolderTest {

	@Test(timeOut = 1000L)
	void testNotEmpty() {
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

}
