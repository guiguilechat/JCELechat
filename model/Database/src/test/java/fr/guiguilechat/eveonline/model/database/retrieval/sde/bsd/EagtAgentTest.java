package fr.guiguilechat.eveonline.model.database.retrieval.sde.bsd;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.model.sde.bsd.EagtAgents;

public class EagtAgentTest {

	@Test
	public void testLoad() {
		ArrayList<EagtAgents> agents = EagtAgents.load();
		boolean found = false;
		for (EagtAgents a : agents) {
			if (a.agentID == 3008416) {
				Assert.assertEquals(a.corporationID, 1000002);
				Assert.assertEquals(a.locationID, 60000004);
				found = true;
			}
		}
		Assert.assertTrue(found);
	}

}
