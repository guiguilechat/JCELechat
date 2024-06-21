package fr.guiguilechat.jcelechat.libs.spring.connect.character.informations;

import java.util.HashSet;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.ARemoteResource;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_roles;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_roles_roles;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_roles_roles_at_base;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_roles_roles_at_hq;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_roles_roles_at_other;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiConnectCharacterRoles")
@Table(name = "esi_connect_characterroles", indexes = {
    @Index(columnList = "fetch_active,expires")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharacterRoles extends ARemoteResource<Integer, R_get_characters_character_id_roles> {

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<get_characters_character_id_roles_roles> roles = new HashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<get_characters_character_id_roles_roles_at_base> rolesAtBase = new HashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<get_characters_character_id_roles_roles_at_hq> rolesAtHq = new HashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<get_characters_character_id_roles_roles_at_other> rolesAtOther = new HashSet<>();

	@Override
	public void update(R_get_characters_character_id_roles roles) {
		setRoles(Set.of(roles.roles));
		setRolesAtBase(Set.of(roles.roles_at_base));
		setRolesAtHq(Set.of(roles.roles_at_hq));
		setRolesAtOther(Set.of(roles.roles_at_other));
	}
}
