package fr.guiguilechat.jcelechat.programs.notiftoons.activities;

import java.time.LocalDateTime;

import fr.guiguilechat.jcelechat.programs.notiftoons.settings.NotifToonsSettings;
import fr.guiguilechat.jcelechat.programs.notiftoons.settings.NotifToonsSettings.Notification;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import lombok.RequiredArgsConstructor;

/**
 * activity that happen at a given time. Since some activities must be known
 * before they happen, the time may be changed from the actual date of
 * happening. Correspondingly, an event like a war may have several activities :
 * one in advance to warn about it, and one for the exact moment it starts.
 */
public class ActivityData {

	/**
	 * type of the activity. Needed to filter and present.
	 *
	 */
	@RequiredArgsConstructor
	public enum TYPE {
		Mk("market order expiration") {
			@Override
			public boolean isActive(Notification settings) {
				return settings.isShowMarketOrders();
			}

			@Override
			public void active(Notification settings, boolean activation) {
				settings.setShowMarketOrders(activation);
			}

		},
		ReM("attributes remaping") {
			@Override
			public boolean isActive(Notification settings) {
				return settings.isShowRemaps();
			}

			@Override
			public void active(Notification settings, boolean activation) {
				settings.setShowRemaps(activation);
			}
		},
		Skill("skill completed") {
			@Override
			public boolean isActive(Notification settings) {
				return settings.isShowSkillsChange();
			}

			@Override
			public void active(Notification settings, boolean activation) {
				settings.setShowSkillsChange(activation);
			}
		},
		SQ("skill queue ended") {
			@Override
			public boolean isActive(Notification settings) {
				return settings.isShowSkillsQueueEnd();
			}

			@Override
			public void active(Notification settings, boolean activation) {
				settings.setShowSkillsQueueEnd(activation);
			}
		}
		;

		public final String longName;

		/**
		 * check if that type is active in a settings
		 *
		 * @param settings
		 *          the notification part of the settings
		 * @return true iff this type is written as active in the settings
		 */
		public abstract boolean isActive(NotifToonsSettings.Notification settings);

		/**
		 * update the active state of this type in a settings
		 *
		 * @param settings
		 *          the settings to change
		 * @param activation
		 *          the new activation state of this type in the settings
		 */
		public abstract void active(NotifToonsSettings.Notification settings, boolean activation);
	}

	/**
	 *
	 * @param hue
	 *          base hue to indicate the type of event
	 * @param warninglevel
	 *          from 1 to 5, the importance of the event.
	 * @param passed
	 *          true if the event is in the past
	 * @return
	 */
	public static String colorEvent(int hue, int warninglevel, boolean passed) {
		Color ret = Color.hsb(hue, 0.25 + 0.15 * warninglevel, 1.0);
		return rgbValue(passed ? ret.saturate() : ret.desaturate());
	}

	public static String rgbValue(Color color) {
		int r = (int) Math.round(color.getRed() * 255.0);
		int g = (int) Math.round(color.getGreen() * 255.0);
		int b = (int) Math.round(color.getBlue() * 255.0);
		return String.format("#%02x%02x%02x", r, g, b);
	}

	public Property<LocalDateTime> time = new SimpleObjectProperty<>();
	public Property<TYPE> type = new SimpleObjectProperty<>(null);
	public Property<String> description = new SimpleObjectProperty<>("");
	public Property<String> where = new SimpleObjectProperty<>("");
	public Property<String> who = new SimpleObjectProperty<>("");
	public Property<String> colorPassed = new SimpleObjectProperty<>("grey");
	public Property<String> colorNotPassed = new SimpleObjectProperty<>("white");
	public Object origin = null;

	public ActivityData(LocalDateTime time, TYPE type, String description, String where, String who,
			String colorPassed, String colorNotPassed, Object origin) {
		this.time.setValue(time);
		this.type.setValue(type);
		this.description.setValue(description);
		this.where.setValue(where);
		this.who.setValue(who);
		if (colorPassed != null) {
			this.colorPassed.setValue(colorPassed);
		}
		if (colorNotPassed != null) {
			this.colorNotPassed.setValue(colorNotPassed);
		}
		this.origin = origin;
	}

	public ActivityData(LocalDateTime time, TYPE type, String description, String where, String who, int hue,
			int warninglevel, Object origin) {
		this(time, type, description, where, who, colorEvent(hue, warninglevel, true),
				colorEvent(hue, warninglevel, false), origin);
	}

	@Override
	public int hashCode() {
		return +type.hashCode() + description.hashCode() + where.hashCode() + who.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != ActivityData.class) {
			return false;
		}
		ActivityData o = (ActivityData) obj;
		return type.getValue().equals(o.type.getValue()) && description.equals(o.description) && where.equals(o.where)
				&& who.equals(o.who);
	}

	@Override
	public String toString() {
		return "" + type.getValue() + ":" + description;
	}

	public boolean accepted(NotifToonsSettings.Notification settings) {
		return type.getValue().isActive(settings);
	}

}
