package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "EsiConnectCharacterWallet")
@Table(name = "esi_connect_characterwallet", indexes = {
    @Index(columnList = "fetch_active,expires")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CharacterWallet extends RemoteEntity<Integer, Double> {

	private double wallet = 0.0;

	@Override
	public void update(Double data) {
		wallet = data;
	}

}
