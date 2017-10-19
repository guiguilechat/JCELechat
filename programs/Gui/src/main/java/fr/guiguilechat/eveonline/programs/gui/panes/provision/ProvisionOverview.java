package fr.guiguilechat.eveonline.programs.gui.panes.provision;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import fr.guiguilechat.eveonline.database.yaml.LPOffer;
import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.Settings.TeamDescription.Provision;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ProvisionOverview extends GridPane implements EvePane {

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	public ProvisionOverview(Manager parent) {
		this.parent = parent;
	}

	LinkedHashMap<Integer, LPOffer> lpoffersbyId = null;

	protected boolean loaded() {
		return lpoffersbyId != null;
	}

	public void load() {
		if (lpoffersbyId == null) {
			lpoffersbyId = new LinkedHashMap<>();
			for (LPOffer lpo : db().getLPOffers()) {
				lpoffersbyId.put(lpo.id, lpo);
			}
		}
		update();
	}

	public void update() {
		getChildren().clear();
		if (!loaded()) {
			return;
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

}
