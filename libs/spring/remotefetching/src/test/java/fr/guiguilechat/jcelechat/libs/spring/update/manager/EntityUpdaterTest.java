package fr.guiguilechat.jcelechat.libs.spring.update.manager;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EntityUpdaterTest {

	@Test
	public void testPartition() {
		int maxInList = 3;

		// split 1 elements by 3
		List<Integer> list1 = List.of(1);
		List<List<Integer>> result1 = EntityService.partition(list1, maxInList).toList();
		Assert.assertEquals(result1.size(), 1);
		Assert.assertEquals(result1.get(0).size(), 1);
		Assert.assertEquals(result1.stream().flatMap(List::stream).count(), list1.size());

		// split 3 elements by 3
		List<Integer> list3 = List.of(1, 2, 3);
		List<List<Integer>> result3 = EntityService.partition(list3, maxInList).toList();
		Assert.assertEquals(result3.size(), 1);
		Assert.assertEquals(result3.get(0).size(), 3);
		Assert.assertEquals(result3.stream().flatMap(List::stream).count(), list3.size());

		// split 10 elements by 3
		List<Integer> list10 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		List<List<Integer>> result10 = EntityService.partition(list10, maxInList).toList();
		Assert.assertEquals(result10.size(), 4);
		Assert.assertEquals(result10.get(0).size(), 3);
		Assert.assertEquals(result10.get(3).size(), 1);
		Assert.assertEquals(result10.stream().flatMap(List::stream).count(), list10.size());
	}

}
