package fr.guiguilechat.jcelechat.programs.notiftoons;

import java.time.LocalDateTime;

import fr.guiguilechat.jcelechat.programs.notiftoons.NotifToonsSettings.Notification;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class ActivityData {

	public enum TYPE {
		Mk {
			@Override
			public boolean isActivate(Notification settings) {
				return settings.isShowMarketOrders();
			}

			@Override
			public void activate(Notification settings, boolean activation) {
				settings.setShowMarketOrders(activation);
			}

		},
		ReM {
			@Override
			public boolean isActivate(Notification settings) {
				return settings.isShowRemaps();
			}

			@Override
			public void activate(Notification settings, boolean activation) {
				settings.setShowRemaps(activation);
			}
		},
		Skill {
			@Override
			public boolean isActivate(Notification settings) {
				return settings.isShowSkillsChange();
			}

			@Override
			public void activate(Notification settings, boolean activation) {
				settings.setShowSkillsChange(activation);
			}
		},
		SQ {
			@Override
			public boolean isActivate(Notification settings) {
				return settings.isShowSkillsQueueEnd();
			}

			@Override
			public void activate(Notification settings, boolean activation) {
				settings.setShowSkillsQueueEnd(activation);
			}
		}
		;

		public boolean isActivate(NotifToonsSettings.Notification settings) {
			return true;
		}

		public void activate(NotifToonsSettings.Notification settings, boolean activation) {

		}

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
		return type.getValue().isActivate(settings);
	}

}
