package fr.guiguilechat.tools;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.tools.javafx.JFXTools;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JFXToolsTest {

	@Test
	public void testConvertDouble() {
		StringProperty sp = new SimpleStringProperty();
		DoubleProperty dp = JFXTools.convertDouble(sp);
		Assert.assertEquals(dp.get(), 0.0);
		sp.set("10");
		Assert.assertEquals(dp.get(), 10.0);
		dp = JFXTools.convertDouble(sp);
		Assert.assertEquals(dp.get(), 10.0);
		sp.set("-15.45");
		Assert.assertEquals(dp.get(), -15.45);
	}

}
