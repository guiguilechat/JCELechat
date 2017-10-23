package fr.guiguilechat.eveonline.programs.gui.panes.provision;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.database.yaml.LPOffer;
import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.Settings.TeamDescription.Provision;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ProvisionOverview extends GridPane implements EvePane {

	private static final Logger logger = LoggerFactory.getLogger(ProvisionOverview.class);

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	public ProvisionOverview(Manager parent) {
		this.parent = parent;
	}

	LinkedHashMap<Integer, LPOffer> lpoffersbyId = null;

	boolean shown = false;

	public void update() {
		getChildren().clear();
		if (!shown) {
			return;
		}
		if (lpoffersbyId == null) {
			lpoffersbyId = new LinkedHashMap<>();
			for (LPOffer lpo : db().getLPOffers()) {
				lpoffersbyId.put(lpo.id, lpo);
			}
		}
		int row=0;
		Provision p = parent().getFTeamProvision();
		for(Entry<Integer, Integer> e : p.lpoffers.entrySet()) {
			LPOffer lpo = lpoffersbyId.get(e.getKey());
			add(new Label("lp offer " + lpo.offer_name), 0, row);
			add(new Label(""+e.getValue()), 1, row);
			Button btn = new Button("delete");
			btn.setOnAction(ev -> parent().provisionLPOffer(lpo, 0));
			add(btn, 2, row);
			row++;
		}
		for (Entry<Integer, Integer> e : p.total.entrySet()) {
			add(new Label(db().getElementById(e.getKey())), 0, row);
			add(new Label("" + e.getValue()), 1, row);
			row++;
		}
	}

	@Override
	public void onNewProvision(int itemID, int qtty) {
		update();
	}

	@Override
	public void onFocusedTeam(String teamName) {
		update();
	}

	@Override
	public void onDelTeam(String name) {
		update();
	}

	@Override
	public void onIsShown(boolean shown) {
		logger.debug("Provision overview is shown " + shown);
		this.shown = shown;
		update();
	}

}
