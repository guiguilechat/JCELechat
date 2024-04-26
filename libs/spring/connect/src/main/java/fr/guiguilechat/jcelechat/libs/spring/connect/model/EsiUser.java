package fr.guiguilechat.jcelechat.libs.spring.connect.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "EsiConnectUser")
@Table(name = "esi_connect_user", indexes = {
		@Index(columnList = "app_id,characterId")
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EsiUser {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@ManyToOne
	private EsiApp app;

	private int characterId;

	private String characterName;

	@Column(length = 5000)
	private String refreshToken;

	@ElementCollection(fetch = FetchType.EAGER)
	@Builder.Default
	private Set<String> scopes = new HashSet<>();

	private Instant created;

	private Instant lastSuccess;

	private Instant lastUpdate;

	@Builder.Default
	private boolean canceled = false;

}
