package fr.guiguilechat.jcelechat.libs.spring.industry.activity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("serial")
@Entity(name = "GameClientBlueprintActivity")
@Table(name = "gameclient_blueprint_activity", indexes = {
		@Index(columnList = "activity_id,activity_name") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class IndustryActivity implements Serializable {

	@Id
	private int activityId;
	private String activityName;
	private String description;

}
