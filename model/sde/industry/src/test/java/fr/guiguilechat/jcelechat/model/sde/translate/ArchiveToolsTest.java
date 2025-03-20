package fr.guiguilechat.jcelechat.model.sde.translate;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ArchiveToolsTest {

	@Test
	public void archiveName2InstantTest() {
		Assert.assertNotNull(ArchiveTools.archiveName2Instant("2025-03-17T11:08.yaml"),
				"could not translate name to archive date");
	}

}
