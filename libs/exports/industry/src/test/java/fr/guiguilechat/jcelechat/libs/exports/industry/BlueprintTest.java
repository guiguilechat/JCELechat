package fr.guiguilechat.jcelechat.libs.exports.industry;

import java.time.Instant;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.libs.exports.industry.Blueprint.MaterialReq;

public class BlueprintTest {

	/**
	 * test known values for changed blueprints
	 */
	@Test
	public void testArchiveLoadChanged() {
		Instant before = Instant.parse("2025-03-13T00:00:00z");
		Instant after = Instant.parse("2025-03-14T00:00:00z");
		int modifiedBpId = 688;
		int modifiedTypeIdRequired = 34;
		int requiredBefore = 8000000;
		int requiredAfter = 5200000;

		Blueprint beforeBp = Blueprint.of(modifiedBpId, before);
		Blueprint afterBp = Blueprint.of(modifiedBpId, after);
		MaterialReq<?> matBefore = beforeBp.manufacturing.materials.stream()
				.filter(mr -> mr.id == modifiedTypeIdRequired).findFirst().get();
		MaterialReq<?> matAfter = afterBp.manufacturing.materials.stream()
				.filter(mr -> mr.id == modifiedTypeIdRequired).findFirst().get();
		Assert.assertEquals(matBefore.quantity, requiredBefore);
		Assert.assertEquals(matAfter.quantity, requiredAfter);
	}

}
