package fr.guiguilechat.eveonline.programs.manager.panes.tools.burners;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import fr.guiguilechat.eveonline.programs.manager.Settings.MissionStats;
import fr.guiguilechat.eveonline.programs.manager.panes.ScrollAdd;
import fr.guiguilechat.eveonline.programs.manager.representation.CheckBoxRepresentation;
import fr.guiguilechat.eveonline.programs.manager.representation.PaneWithRepresentation;
import fr.guiguilechat.eveonline.programs.manager.representation.Representation;
import fr.guiguilechat.eveonline.programs.manager.representation.TextFieldRepresentation;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

public class MissionPane extends GridPane implements PaneWithRepresentation {

	public TextFieldRepresentation<Integer> isk_cstt, isk_indexed, lp, align_time_s, timetokill_s;

	public TextFieldRepresentation<Double> warp_speed_aups;

	public CheckBoxRepresentation isBurner, isActive;

	protected MissionStats stats;

	protected List<Representation<?>> fields;

	@Override
	public Collection<Representation<?>> GetRepresentations() {
		return fields;
	}

	public MissionPane(String name, MissionStats options) {
		stats = options;
		setStyle("-fx-border-color: black; -fx-border-width: 1;");
		add(new Label(name), 0, 0);

		isk_cstt = TextFieldRepresentation.positivInteger(options::getIsk_cstt, options::setIsk_cstt);
		isk_cstt.getField().setOnScroll(new ScrollAdd.IntScrollAdd(1000, isk_cstt.getField()));
		isk_cstt.getField().setTooltip(
				new Tooltip("constant isk gain when successfuly doing this mission, in k. includes bounty and BO of loot"));
		addRow(1, new Label("cstt isk"), isk_cstt.getField());

		isk_indexed = TextFieldRepresentation.positivInteger(options::getIsk_indexed, options::setIsk_indexed);
		isk_indexed.getField().setOnScroll(new ScrollAdd.IntScrollAdd(100000, isk_indexed.getField()));
		isk_indexed.getField().setTooltip(
				new Tooltip("indexed isk gain when successfuly doing this mission, in k.\n"
						+ "includes mission reward with bonus for 1.0 system and negotiation 0"));
		addRow(2, new Label("indexed isk"), isk_indexed.getField());

		lp = TextFieldRepresentation.positivInteger(options::getLp, options::setLp);
		lp.getField().setOnScroll(new ScrollAdd.IntScrollAdd(1000, lp.getField()));
		lp.getField().setTooltip(
				new Tooltip("indexed lp gain when successfuly doing this mission.\n"
						+ "includes agent reward, in 1.0 system and security connection at 0"));
		addRow(3, new Label("lp rwrd"), lp.getField());

		align_time_s = TextFieldRepresentation.positivInteger(options::getAlign_time_s, options::setAlign_time_s);
		align_time_s.getField().setOnScroll(new ScrollAdd.IntScrollAdd(1, align_time_s.getField()));
		align_time_s.getField().setTooltip(
				new Tooltip("align time in s of the ship used for that mission"));
		addRow(4, new Label("align time"), align_time_s.getField());

		warp_speed_aups = TextFieldRepresentation.positivDecimal(options::getWarp_speed_aups, options::setWarp_speed_aups);
		warp_speed_aups.getField().setOnScroll(new ScrollAdd.DoubleScrollAdd(1.0, warp_speed_aups.getField()));
		warp_speed_aups.getField().setTooltip(
				new Tooltip("warp speed of the ship used to do that mission"));
		addRow(5, new Label("warp speed"), warp_speed_aups.getField());

		timetokill_s = TextFieldRepresentation.positivInteger(options::getTimetokill_s, options::setTimetokill_s);
		timetokill_s.getField().setOnScroll(new ScrollAdd.IntScrollAdd(10, timetokill_s.getField()));
		timetokill_s.getField().setTooltip(
				new Tooltip("time between landing at the mission gate and leaving it."));
		addRow(6, new Label("ttk"), timetokill_s.getField());

		isBurner = new CheckBoxRepresentation("burner", options::isBurner, options::setBurner);
		isActive = new CheckBoxRepresentation("active", options::isActive, options::setActive);
		addRow(7, isBurner.getBox(), isActive.getBox());

		fields = Arrays.asList(align_time_s, isk_cstt, isk_indexed, lp, timetokill_s, warp_speed_aups, isBurner, isActive);
		for (Representation<?> f : fields) {
			if (f instanceof TextFieldRepresentation) {
				((TextFieldRepresentation<?>) f).getField().setMaxWidth(70);
			}
		}
	}

}
