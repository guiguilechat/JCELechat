package fr.guiguilechat.jcelechat.jcesi.holders.collections;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ListToolsTest {

	@Test
	public void testSubListCopys() {
		List<Integer> test = List.of(1, 2, 3, 4);

		Assert.assertEquals(ListTools.subListCopy(test, 0, -1), test);
		Assert.assertEquals(ListTools.subListCopy(test, 1, -2), List.of(2, 3));
		Assert.assertEquals(ListTools.subListCopy(test, -3, 3), List.of(2, 3));

		Assert.assertEquals(ListTools.subListCopyFrom(test, 0), test);
		Assert.assertEquals(ListTools.subListCopyFrom(test, 2), List.of(3, 4));
		Assert.assertEquals(ListTools.subListCopyFrom(test, -1), List.of(4));
		Assert.assertEquals(ListTools.subListCopyFrom(test, -10), test);

		Assert.assertEquals(ListTools.subListCopyTo(test, -1), test);
		Assert.assertEquals(ListTools.subListCopyTo(test, 2), List.of(1, 2));
	}

}
