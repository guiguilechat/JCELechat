package fr.guiguilechat.eveonline.programs.panes;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.programs.manager.panes.TypedField;
import javafx.embed.swing.JFXPanel;

public class TypedFieldTest {

	@Test
	public void testModif() {
		@SuppressWarnings("unused")
		JFXPanel fxPanel = new JFXPanel();
		TypedField<Integer> tested = TypedField.intField(0);
		tested.setText("a");
		Assert.assertEquals(tested.getText(), "0");
		tested.setText("45678");
		Assert.assertEquals(tested.getText(), "45678");
	}

}
