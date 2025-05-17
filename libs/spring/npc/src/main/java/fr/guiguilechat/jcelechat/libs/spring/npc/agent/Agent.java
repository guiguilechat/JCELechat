package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "SdeNpcAgent")
@Table(name = "sde_npc_agent", indexes = {
// @Index(columnList = "activity_id,type_id")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Agent {

	@Id
	private Integer id;

	@Enumerated(EnumType.STRING)
	private AgentDivision agentDivision;
	@Enumerated(EnumType.STRING)
	private AgentType agentType;
	private int corporationId;
	private int divisionId;
	private boolean isLocator;
	private int level;
	private int locationId;
	private String name;

	public enum AgentType {
		Basic(2),
		Tutorial(3),
		RnD(4),
		Concord(5),
		Storyline(6),
		StorylineMission(7),
		Event(8),
		FactionalWarfare(9),
		EpicArc(10),
		Aura(11),
		Career(12),
		Paragon(13);

		public final int typeId;

		AgentType(int typeId) {
			this.typeId = typeId;
		}

		@Getter(lazy = true)
		private static final Map<Integer, AgentType> byTypeId = Stream.of(values())
				.collect(Collectors.toMap(t -> t.typeId, t -> t));

		public static AgentType of(int typeId) {
			return getByTypeId().get(typeId);
		}

	}

	public enum AgentDivision {
		RnD(18),
		Distribution(22),
		Mining(23),
		Security(24),
		BusinessCareerPath(25),
		ExplorationCareerPath(26),
		IndustryCareerPath(27),
		MilitaryCareerPath(28),
		AdvancedMilitaryPath(29),
		Interbus(37);

		public final int divisionId;

		AgentDivision(int divisionId) {
			this.divisionId = divisionId;
		}

		@Getter(lazy = true)
		private static final Map<Integer, AgentDivision> byDivisionId = Stream.of(values())
				.collect(Collectors.toMap(t -> t.divisionId, t -> t));

		public static AgentDivision of(int divisionId) {
			return getByDivisionId().get(divisionId);
		}
	}

}
