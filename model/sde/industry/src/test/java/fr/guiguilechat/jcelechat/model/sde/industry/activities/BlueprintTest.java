package fr.guiguilechat.jcelechat.model.sde.industry.activities;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;

public class BlueprintTest {

	@Test
	public void archiveName2InstantTest() {
		Assert.assertNotNull(Blueprint.archiveName2Instant("2025-03-17T11:08.yaml"),
				"could not translate name to archive date");
	}

}
