package fr.guiguilechat.jcelechat.libs.exports.items;

import java.util.function.Predicate;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.libs.exports.items.TypesTools;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.types.module.ShieldExtender;

public class TypesToolsTests {

	@Test
	public void testTokenPredicate() {
		ShieldExtender test1 = new ShieldExtender();
		test1.metalevelold = 5;
		test1.name = "test";
		test1.techlevel = 2;

		ShieldExtender test2 = new ShieldExtender();
		test2.metalevelold = 3;
		test2.name = "nope";
		test2.techlevel = 1;

		Predicate<EveType> namePred1 = TypesTools.tokenPredicateName("test");
		Predicate<EveType> namePred2 = TypesTools.tokenPredicateName("extender");
		Assert.assertTrue(namePred1.test(test1));
		Assert.assertFalse(namePred1.test(test2));
		Assert.assertTrue(namePred2.test(test1));
		Assert.assertTrue(namePred2.test(test2));

		Predicate<EveType> metaPred3 = TypesTools.tokenPredicateMLevel("m:3");
		Predicate<EveType> metaPred3b = TypesTools.tokenPredicateMLevel("ml:3");
		Predicate<EveType> metaPred5 = TypesTools.tokenPredicateMLevel("m:5");
		Assert.assertNull(TypesTools.tokenPredicateMLevel("t:1"));
		Assert.assertNull(TypesTools.tokenPredicateMLevel("test"));
		Assert.assertFalse(metaPred3.test(test1));
		Assert.assertTrue(metaPred3.test(test2));
		Assert.assertFalse(metaPred3b.test(test1));
		Assert.assertTrue(metaPred3b.test(test2));
		Assert.assertTrue(metaPred5.test(test1));
		Assert.assertFalse(metaPred5.test(test2));

		Predicate<EveType> techPred1 = TypesTools.tokenPredicateTLevel("t:1");
		Predicate<EveType> techPred2 = TypesTools.tokenPredicateTLevel("t:2");
		Predicate<EveType> techPredNot2 = TypesTools.makeTokenPredicate(TypesTools.NEGATE_START + "t:2");
		Assert.assertNull(TypesTools.tokenPredicateTLevel("m:1"));
		Assert.assertNull(TypesTools.tokenPredicateTLevel("test"));
		Assert.assertFalse(techPred1.test(test1));
		Assert.assertTrue(techPred1.test(test2));
		Assert.assertTrue(techPred2.test(test1));
		Assert.assertFalse(techPred2.test(test2));
		Assert.assertFalse(techPredNot2.test(test1));
		Assert.assertTrue(techPredNot2.test(test2));
	}

}
