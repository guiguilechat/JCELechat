package fr.guiguilechat.jcelechat.libs.spring.connect.model;

import fr.guiguilechat.jcelechat.libs.spring.connect.model.templates.ACharData;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity(name = "EsiConnectCharacterWallet")
@Table(name = "esi_connect_characterwallet")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CharacterWallet extends ACharData<Double> {

	@Builder.Default
	private double wallet = 0.0;

	@Override
	public void update(Double data) {
		wallet = data;
	}

}
