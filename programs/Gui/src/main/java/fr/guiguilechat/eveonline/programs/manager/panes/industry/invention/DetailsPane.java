package fr.guiguilechat.eveonline.programs.manager.panes.industry.invention;

import java.util.HashMap;
import java.util.Map.Entry;

import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.Settings.JobActivity;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import fr.guiguilechat.eveonline.programs.manager.panes.ScrollAdd;
import fr.guiguilechat.eveonline.programs.manager.panes.TypedField;
import fr.guiguilechat.eveonline.programs.manager.panes.industry.invention.InventionGainAlgorithm.InventionProdData;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class DetailsPane extends VBox implements EvePane {

	protected final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	protected EvePane[] children;

	@Override
	public EvePane[] subEvePanes() {
		return children;
	}

	private Label name = new Label();
	private TypedField<Integer> nbcycles = TypedField.positivIntField(1);
	private GridPane requirements = new GridPane();
	private Button addbtn = new Button("add");

	InventionProdData data = null;

	public DetailsPane(Manager parent) {
		this.parent = parent;

		getChildren().add(name);

		HBox row = new HBox();
		nbcycles.setOnScroll(new ScrollAdd.IntScrollAdd(1, nbcycles));

		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		nbcycles.textProperty().addListener((observable, oldValue, newValue) -> {
			pause.setOnFinished(event -> updateRequirements());
			pause.playFromStart();
		});
		addbtn.setOnAction(e -> addShopList());
		row.getChildren().addAll(nbcycles, new Label("cycles"), addbtn);
		getChildren().add(row);

		getChildren().add(new Label("required"));

		getChildren().add(requirements);
	}

	protected void setInvention(InventionProdData ipd) {
		data = ipd;
		if (ipd == null) {
			name.setText("");
		} else {
			name.setText(ipd.productName);
			nbcycles.setValue(1);
		}
		updateRequirements();
	}

	protected void updateRequirements() {
		requirements.getChildren().clear();
		if (data != null) {
			requirements.addRow(0, new Label("isks"),
					new Label(InventionGainAlgorithm.formatPrice(nbcycles.getValue() * data.installCost)));
			int row = 1;
			for (Entry<String, Double> e : data.requirements.entrySet()) {
				requirements.addRow(row, new Label(e.getKey()),
						new Label("" + (int) Math.ceil(nbcycles.getValue() * e.getValue())));
				row++;
			}
		}
	}

	protected void addShopList() {
		// need to wait in case another modification
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			throw new UnsupportedOperationException("catch this", e1);
		}
		if (data != null) {
			HashMap<String, Integer> shoplist = new HashMap<>();
			for (Entry<String, Double> e : data.requirements.entrySet()) {
				shoplist.put(e.getKey(), (int) Math.ceil(nbcycles.getValue() *
						e.getValue()));
			}
			parent.addShop(shoplist);
			parent.addJob(data.bpoName, JobActivity.invent, data.decryptor, nbcycles.getValue());
			parent.addJob(data.bpiName, JobActivity.manufacture, data.bpiME + "/" + data.bpiTE,
					(int) Math.floor(data.cycleAvgProd * nbcycles.getValue()));
			nbcycles.setValue(0);
			detailsTP.setExpanded(false);
		}

	}

	private TitledPane detailsTP = null;

	public void setTitledPane(TitledPane detailstp) {
		detailsTP = detailstp;
	}

}
