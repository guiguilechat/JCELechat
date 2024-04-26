package fr.guiguilechat.jcelechat.libs.spring.connect.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "EsiConnectApp")
@Table(name = "esi_connect_app", indexes = { @Index(columnList = "appId") })
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EsiApp {

	@Id
	private long id;

	private String appId;

	private String appBase64;

}
