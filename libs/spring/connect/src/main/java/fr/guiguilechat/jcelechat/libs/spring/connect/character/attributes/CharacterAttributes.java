package fr.guiguilechat.jcelechat.libs.spring.connect.character.attributes;

import java.time.Instant;

import fr.guiguilechat.jcelechat.jcesi.ESIDateTools;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.RemoteEntity;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_attributes;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "EsiConnectCharacterAttributes")
@Table(name = "esi_connect_characterattributes", indexes = {
		@Index(columnList = "fetch_active,fetch_priority,expires")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CharacterAttributes extends RemoteEntity<Integer, R_get_characters_character_id_attributes> {

	private Instant accruedRemapCooldown;

	private int bonusRemaps;

	private int charisma;

	private int intelligence;

	private Instant lastRemap;

	private int memory;

	private int perception;

	private int willpower;

	@Override
	public void update(R_get_characters_character_id_attributes data) {
		accruedRemapCooldown = ESIDateTools.fieldInstant(data.accrued_remap_cooldown_date);
		bonusRemaps = data.bonus_remaps;
		charisma = data.charisma;
		intelligence = data.intelligence;
		lastRemap = ESIDateTools.fieldInstant(data.last_remap_date);
		memory = data.memory;
		perception = data.perception;
		willpower = data.willpower;
	}

}
