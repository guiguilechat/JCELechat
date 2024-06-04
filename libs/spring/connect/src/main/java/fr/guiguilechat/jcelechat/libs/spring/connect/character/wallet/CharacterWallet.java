package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet;

import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResource;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "EsiConnectCharacterWallet")
@Table(name = "esi_connect_characterwallet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CharacterWallet extends ARemoteFetchedResource<Integer, Double> {

	private double wallet = 0.0;

	@Override
	public void update(Double data) {
		wallet = data;
	}

}
