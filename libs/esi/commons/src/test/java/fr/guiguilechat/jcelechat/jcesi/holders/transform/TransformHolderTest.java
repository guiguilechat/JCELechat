package fr.guiguilechat.jcelechat.jcesi.holders.transform;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.RWHolder;

public class TransformHolderTest {

	@Test(timeOut = 1000L)
	void testTransform() {
		RWHolder<List<Integer>> source = new RWHolder<>();
		Holder<Integer> max = source.map(
				l -> l.stream().reduce(Integer.MIN_VALUE, Math::max));

		List<Integer> data = List.of(4, 1, 5);
		source.set(data);
		Assert.assertTrue(max.isAvailable());
		Assert.assertEquals(max.get(), 5);

		List<Integer> data2 = List.of(40, 15, 75);
		source.set(data2);
		Assert.assertTrue(max.isAvailable());
		Assert.assertEquals(max.get(), 75);
	}

}
