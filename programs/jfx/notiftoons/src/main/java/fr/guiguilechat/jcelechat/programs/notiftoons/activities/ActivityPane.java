package fr.guiguilechat.jcelechat.programs.notiftoons.activities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.Attributes;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_skillqueue;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.types.Skill;
import fr.guiguilechat.jcelechat.programs.notiftoons.settings.NotifToonsSettings;
import fr.guiguilechat.tools.FormatTools;
import fr.lelouet.tools.holders.impl.collections.ListHolderImpl;
import fr.lelouet.tools.holders.interfaces.collections.CollectionHolder;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import javafx.application.Platform;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ActivityPane extends TableView<ActivityData> {

	private static final Logger logger = LoggerFactory.getLogger(ActivityPane.class);
	private static final DateTimeFormatter CELLDATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm");

	private final List<ESIAccount> accounts;

	private final NotifToonsSettings settings;

	public ActivityPane build() {
		TableColumn<ActivityData, LocalDateTime> dateCol = new TableColumn<>("date");
		dateCol.setCellValueFactory(ed -> ed.getValue().time);
		dateCol.setCellFactory(param -> new TableCell<>() {
			@Override
			protected void updateItem(LocalDateTime item, boolean empty) {
				super.updateItem(item, empty);
				if (item != null) {
					setText(item.format(CELLDATE_FORMAT));
				} else {
					setText("");
				}
			}
		});
		dateCol.setMinWidth(140);
		dateCol.setMaxWidth(140);
		dateCol.setSortable(true);
		dateCol.setSortType(SortType.ASCENDING);
		getColumns().add(dateCol);

		TableColumn<ActivityData, ActivityData.TYPE> typeCol = new TableColumn<>("type");
		typeCol.setCellValueFactory(ed -> ed.getValue().type);
		typeCol.setMinWidth(40);
		typeCol.setMaxWidth(40);
		getColumns().add(typeCol);

		TableColumn<ActivityData, String> desCol = new TableColumn<>("description");
		desCol.setCellValueFactory(ed -> ed.getValue().description);
		desCol.setMinWidth(400);
		getColumns().add(desCol);

		// TableColumn<ActivityData, String> whereCol = new TableColumn<>("where");
		// whereCol.setCellValueFactory(ed -> ed.getValue().where);
		// getColumns().add(whereCol);

		TableColumn<ActivityData, String> whoCol = new TableColumn<>("who");
		whoCol.setCellValueFactory(ed -> ed.getValue().who);
		getColumns().add(whoCol);

		getSortOrder().add(dateCol);

		setRowFactory(this::createTableRow);

		setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

		getCharsActivities().follow(l -> refreshList());

		return this;
	}

	public void refreshList() {
		Platform.runLater(() -> {
			getItems().clear();
			List<ActivityData> l = getCharsActivities().get()
					.stream()
					.filter(a -> a.accepted(settings.getNotifications()))
					.toList();
			getItems().addAll(l);
			sort();
		});
	}

	protected TableRow<ActivityData> createTableRow(TableView<ActivityData> tv) {
		return new TableRow<>() {
			@Override
			public void updateItem(ActivityData item, boolean empty) {
				super.updateItem(item, empty);
				if (item != null && item.time != null) {
					if (item.time.getValue().isBefore(LocalDateTime.now())) {
						setStyle("-fx-background-color: " + item.colorPassed.getValue() + ";");
					} else {
						setStyle("-fx-background-color: " + item.colorNotPassed.getValue() + ";");
					}
				} else {
					setStyle("");
				}
			}
		};
	}

	@Getter(lazy = true)
	private final ListHolder<ActivityData> charsActivities = new ListHolderImpl<>(getAccounts())
	.flatten(this::convertChar);

	protected ListHolder<ActivityData> convertChar(ESIAccount account) {
		return ListHolderImpl
				.of(convertCharSkills(account), convertCharMarket(account)).flatten(l -> l);
	}

	@SuppressWarnings("unchecked")
	protected ListHolder<ActivityData> convertCharSkills(ESIAccount account) {
		ListHolder<R_get_characters_character_id_skillqueue> sq = account.character.skills.getQueue();
		ListHolder<ActivityData> last = sq.map(l -> l.size() > 0 ? l.get(l.size() - 1) : null)
				.map(ls -> ls == null ? null : convertLastSkill(ls, account)).mapList(Arrays::asList);
		ListHolder<ActivityData> skillStart = sq.mapItems(j -> convertSkillChange(j, account));
		ListHolder<ActivityData> remaps = sq.mapItems(j -> convertSkillRemap(j, account))
				.mapList(l -> l.isEmpty() ? Collections.emptyList()
						: l.stream().filter(o -> o != null).limit(1).collect(Collectors.toList()));
		return skillStart.concat(last, remaps).filter(o -> o != null);
	}

	protected ActivityData convertSkillChange(R_get_characters_character_id_skillqueue skill, ESIAccount access) {
		if (skill.finish_date == null) {
			return null;
		}
		return new ActivityData(ESITools.fieldLocalDateTime(skill.finish_date), ActivityData.TYPE.Skill,
				TypeIndex.getType(skill.skill_id).name + " " + skill.finished_level, "", access.name(), 300, 0, skill);
	}

	protected ActivityData convertLastSkill(R_get_characters_character_id_skillqueue skill, ESIAccount access) {
		if (skill == null || skill.finish_date == null) {
			return null;
		}
		return new ActivityData(ESITools.fieldLocalDateTime(skill.finish_date).minusDays(1), ActivityData.TYPE.SQ, "", "",
				access.name(), 320, 3, skill);
	}

	protected ActivityData convertSkillRemap(R_get_characters_character_id_skillqueue skill, ESIAccount access) {
		if (skill == null || skill.start_date == null) {
			return null;
		}
		Skill sk = (Skill) TypeIndex.getType(skill.skill_id);
		int primaryAttId = sk.primaryattribute;
		int secondaryAttId = sk.secondaryattribute;

		if (!access.character.attributes.isAttributeHighest(primaryAttId).get()) {
			return new ActivityData(ESITools.fieldLocalDateTime(skill.start_date).minusDays(1), ActivityData.TYPE.ReM,
					Attributes.of(primaryAttId) + "/" + Attributes.of(secondaryAttId), "", access.name(), 320, 2, skill);
		} else {
			return null;
		}
	}

	protected CollectionHolder<ActivityData, ?> convertCharMarket(ESIAccount account) {
		return account.character.market.getOrders().values()
				// pre cache esi fetch
				.follow(orders -> {
					for (R_get_characters_character_id_orders o : orders) {
						ESIRawPublic.INSTANCE.cache().universe.types(o.type_id);
					}
				})
				// then convert
				.mapItems(o -> convertOrder(o, account)).filter(o -> {
					if (o == null) {
						System.err.println("null market");
						return false;
					} else {
						return true;
					}
				});
	}

	protected ActivityData convertOrder(R_get_characters_character_id_orders order, ESIAccount access) {
		try {
			LocalDateTime expiry = ESITools.fieldLocalDateTime(order.issued).plusDays(order.duration - 7);
			R_get_universe_types_type_id t = ESIRawPublic.INSTANCE.cache().universe.types(order.type_id).get();
			ActivityData ret = new ActivityData(expiry, ActivityData.TYPE.Mk,
					(t == null ? "unknown_" + order.type_id : t.name)
							+ " *" + order.volume_remain + " " + FormatTools.formatPrice(order.price),
					access.universe.locationName(order.location_id)
					, access.name(), 0, 0, order);
			return ret;
		} catch (Exception e) {
			logger.warn("while converting order from " + access.name() + " remain " + order.volume_remain + " type "
					+ order.type_id + " issued is " + order.issued);
			throw e;
		}
	}

}
