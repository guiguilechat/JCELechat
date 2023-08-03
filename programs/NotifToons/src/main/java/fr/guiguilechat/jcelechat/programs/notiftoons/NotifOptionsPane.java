package fr.guiguilechat.jcelechat.programs.notiftoons;

import fr.guiguilechat.jcelechat.programs.notiftoons.ActivityData.TYPE;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class NotifOptionsPane extends HBox{

	private final NotifToonsSettings settings;

	private final Runnable onChange;

	public NotifOptionsPane build() {
		setSpacing(20);
		for (TYPE t : ActivityData.TYPE.values()) {
			CheckBox chk = new CheckBox(t.name());
			chk.setAllowIndeterminate(false);
			chk.setSelected(t.isActivate(settings.getNotifications()));
			chk.selectedProperty().addListener((o, ov, nv) -> {
				t.activate(settings.getNotifications(), nv);
				onChange.run();
				settings.storeLater();
			});
			getChildren().add(chk);
		}

		return this;
	}

}
