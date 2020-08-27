package fr.guiguilechat.jcelechat.model.sde.locations.algos;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestHSRegionStager {

	@Test
	public void testNextSolution() {
		int[] solution = new int[] { 1, 2 };
		Assert.assertTrue(BruteStager.nextValuation(solution, 3));
		Assert.assertEquals(solution[0], 1);
		Assert.assertEquals(solution[1], 3);
		Assert.assertTrue(BruteStager.nextValuation(solution, 3));
		Assert.assertEquals(solution[0], 2, "got " + IntStream.of(solution).boxed().collect(Collectors.toList()));
		Assert.assertEquals(solution[1], 3);
		Assert.assertFalse(BruteStager.nextValuation(solution, 3));

	}

}
