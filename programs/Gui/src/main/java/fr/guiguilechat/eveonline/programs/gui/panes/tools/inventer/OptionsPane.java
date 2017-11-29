package fr.guiguilechat.eveonline.programs.gui.panes.tools.inventer;

import java.util.Map;

import fr.guiguilechat.eveonline.model.apiv2.APIRoot;
import fr.guiguilechat.eveonline.model.apiv2.Account;
import fr.guiguilechat.eveonline.model.apiv2.Account.EveChar;
import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.Settings.InventionParams;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import fr.guiguilechat.eveonline.programs.gui.panes.tools.inventer.InventerPane.StructBonus;
import fr.guiguilechat.eveonline.programs.panes.ScrollAdd;
import fr.guiguilechat.eveonline.programs.panes.TypedField;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.StringConverter;

public class OptionsPane extends HBox implements EvePane {

	protected final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	public Button computeBtn = new Button("COMPUTE");

	public ChoiceBox<String> marketRegion = new ChoiceBox<>();
	public ChoiceBox<EveChar> characterSkills = new ChoiceBox<>();

	public TextField bpPattern = new TextField();
	public CheckBox onlyBest = new CheckBox();
	public TypedField<Double> sellTax, brokerFee;
	public TypedField<Double> copyTax, copyIndex;
	public ChoiceBox<InventerPane.StructBonus> copystruct;
	public TypedField<Double> inventTax, inventIndex;
	public ChoiceBox<InventerPane.StructBonus> inventstruct;
	public TypedField<Double> manufTax, manufIndex;
	public ChoiceBox<InventerPane.StructBonus> manufstruct;

	public OptionsPane(Manager parent) {
		this.parent = parent;
		InventerPane.ALL5.name = "all5";
		characterSkills.getItems().add(InventerPane.ALL5);
		characterSkills.setConverter(new StringConverter<Account.EveChar>() {

			@Override
			public String toString(EveChar object) {
				return object != null ? object.name : "";
			}

			@Override
			public EveChar fromString(String string) {
				// should actually never be called.
				if (string == null) {
					return null;
				}
				return parent().apis.stream().filter(a -> !a.isCorp()).flatMap(ar -> ar.account.characters().stream())
						.filter(c -> string.equals(c.name)).findFirst().orElse(null);
			}
		});
	}

	@Override
	public void onIsShown(boolean shown) {
		if (shown) {
			load();
		}
	}

	boolean loaded = false;

	protected void load() {
		if (loaded) {
			return;
		}
		InventionParams settings = parent().settings.invention;

		// add all the regions to the choicebox
		parent.db().getLocations().entrySet().stream().filter(e -> e.getValue().parentRegion == null).map(Map.Entry::getKey)
		.forEachOrdered(marketRegion.getItems()::add);
		marketRegion.setValue(settings.marketRegion);
		marketRegion.getItems().sort(String::compareToIgnoreCase);

		characterSkills.getSelectionModel()
		.select(characterSkills.getItems().stream().filter(ec -> ec.name.equals(settings.characterSkills)).findFirst()
				.orElse(InventerPane.ALL5));

		sellTax = TypedField.positivDecimal(settings.sellTax);
		sellTax.setTooltip(new Tooltip("percentage of the sale that is due as tax"));
		sellTax.setOnScroll(new ScrollAdd.DoubleScrollAdd(0.1, sellTax));

		brokerFee = TypedField.positivDecimal(settings.brokerFee);
		brokerFee.setTooltip(new Tooltip(
				"when buying at BO value or selling at SO value, the percentage of the transaction that is due as broker fee"));
		brokerFee.setOnScroll(new ScrollAdd.DoubleScrollAdd(0.1, brokerFee));

		onlyBest.setSelected(true);

		copyTax = TypedField.positivDecimal(settings.copyTax);
		copyTax.setTooltip(new Tooltip("station tax on copying"));
		copyTax.setOnScroll(new ScrollAdd.DoubleScrollAdd(0.1, copyTax));

		copyIndex = TypedField.positivDecimal(settings.copyIndex);
		copyIndex.setTooltip(new Tooltip("system copying index"));
		copyIndex.setOnScroll(new ScrollAdd.DoubleScrollAdd(0.1, copyIndex));

		copystruct = new ChoiceBox<>();
		copystruct.getItems().addAll(StructBonus.values());
		copystruct.getSelectionModel()
		.select(settings.copystruct == null ? StructBonus.none : StructBonus.valueOf(settings.copystruct));

		inventTax = TypedField.positivDecimal(settings.inventTax);
		inventTax.setTooltip(new Tooltip("station tax on invention"));
		inventTax.setOnScroll(new ScrollAdd.DoubleScrollAdd(0.1, inventTax));

		inventIndex = TypedField.positivDecimal(settings.inventIndex);
		inventIndex.setTooltip(new Tooltip("system invention index"));
		inventIndex.setOnScroll(new ScrollAdd.DoubleScrollAdd(0.1, inventIndex));

		inventstruct = new ChoiceBox<>();
		inventstruct.getItems().addAll(StructBonus.values());
		inventstruct.getSelectionModel()
		.select(settings.inventstruct == null ? StructBonus.none : StructBonus.valueOf(settings.inventstruct));

		manufTax = TypedField.positivDecimal(settings.manufactureTax);
		manufTax.setTooltip(new Tooltip("station tax on manufacture"));
		manufTax.setOnScroll(new ScrollAdd.DoubleScrollAdd(0.1, manufTax));

		manufIndex = TypedField.positivDecimal(settings.manufactureIndex);
		manufIndex.setTooltip(new Tooltip("system manufacturing index"));
		manufIndex.setOnScroll(new ScrollAdd.DoubleScrollAdd(0.1, manufIndex));

		manufstruct = new ChoiceBox<>();
		manufstruct.getItems().addAll(StructBonus.values());
		manufstruct.getSelectionModel()
		.select(settings.manufstruct == null ? StructBonus.none : StructBonus.valueOf(settings.manufstruct));

		for (Region tf : new Region[] { bpPattern, brokerFee, characterSkills, copyIndex, copyTax, inventIndex, inventTax,
				manufIndex, manufTax, marketRegion, sellTax }) {
			tf.setMaxWidth(70);
		}

		GridPane mainpane = new GridPane();
		mainpane.setStyle("-fx-border-color: black; -fx-border-width: 1;");
		mainpane.addRow(0, new Label("character"), characterSkills);
		mainpane.addRow(1, new Label("market region"), marketRegion);
		mainpane.addRow(2, new Label("broker %"), brokerFee);
		mainpane.addRow(3, new Label("sell tax %"), sellTax);
		mainpane.addRow(4, new Label("product name"), bpPattern);
		bpPattern.setTooltip(new Tooltip("specify a pattern to limit the products. eg \"small\""));
		mainpane.addRow(5, new Label("best descryp"), onlyBest);
		mainpane.addRow(6, computeBtn);

		GridPane copyPane = new GridPane();
		copyPane.setStyle("-fx-border-color: black; -fx-border-width: 1;");
		copyPane.addRow(1, new Label("copy tax %"), copyTax);
		copyPane.addRow(2, new Label("copy index"), copyIndex);
		copyPane.addRow(3, new Label("copy struct"), copystruct);

		GridPane inventPane = new GridPane();
		inventPane.setStyle("-fx-border-color: black; -fx-border-width: 1;");
		inventPane.addRow(1, new Label("invention tax %"), inventTax);
		inventPane.addRow(2, new Label("invention index"), inventIndex);
		inventPane.addRow(3, new Label("invention struct"), inventstruct);

		GridPane manufPane = new GridPane();
		manufPane.setStyle("-fx-border-color: black; -fx-border-width: 1;");
		manufPane.addRow(1, new Label("manufacture tax %"), manufTax);
		manufPane.addRow(2, new Label("manufacture index"), manufIndex);
		manufPane.addRow(3, new Label("manufacture struct"), manufstruct);

		getChildren().addAll(mainpane, copyPane, inventPane, manufPane);
		loaded = true;
	}

	@Override
	public void onNewAPI(APIRoot... apis) {
		if (apis != null) {
			for (APIRoot ar : apis) {
				if (!ar.isCorp()) {
					characterSkills.getItems().addAll(ar.account.characters());
				}
			}
		}
	}

}
