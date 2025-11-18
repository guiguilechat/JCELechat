package fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.time;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position3D;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.SolarSystem;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.Stargate;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.AStarOrbit;

public sealed interface GatingPoint {

	default Iterable<GatingPoint> reachable(GatingPoint target) {
		if (target.solarSystem().equals(solarSystem())) {
			return List.of(target);
		}
		return solarSystem().stargates().stream()
				.map(sg -> sg.destination().stargate())
				.map(StargatePoint::new)
				.map(p -> (GatingPoint) p)
				.toList();
	}

	SolarSystem solarSystem();

	int id();

	Position3D position();

	String enName();

	public record StargatePoint(Stargate stargate) implements GatingPoint {

		@Override
		public SolarSystem solarSystem() {
			return stargate().solarSystem();
		}

		@Override
		public Iterable<GatingPoint> reachable(GatingPoint target) {
			if (target.solarSystem().equals(solarSystem())) {
				return List.of(target);
			}
			return solarSystem().stargates().stream()
					.filter(sg -> !sg.equals(stargate()))
					.map(sg -> sg.destination().stargate())
					.map(StargatePoint::new)
					.map(p -> (GatingPoint) p)
					.toList();
		}

		@Override
		public int id() {
			return stargate().id();
		}

		@Override
		public Position3D position() {
			return stargate().position();
		}

		@Override
		public String enName() {
			return stargate.enName();
		}

		@Override
		public boolean equals(Object obj) {
			return obj != null
					&& obj.getClass().equals(getClass())
					&& ((GatingPoint) obj).id() == id();
		}

		@Override
		public int hashCode() {
			return id();
		}

		@Override
		public final String toString() {
			return stargate().toString();
		}

	}

	public record OrbitingPoint(AStarOrbit<?> orbiting) implements GatingPoint {

		@Override
		public SolarSystem solarSystem() {
			return orbiting().solarSystem();
		}

		@Override
		public int id() {
			return orbiting().id();
		}

		@Override
		public Position3D position() {
			return orbiting().position();
		}

		@Override
		public String enName() {
			return orbiting.enName();
		}

		@Override
		public boolean equals(Object obj) {
			return obj != null
					&& obj.getClass().equals(getClass())
					&& ((GatingPoint) obj).id() == id();
		}

		@Override
		public int hashCode() {
			return id();
		}

		@Override
		public final String toString() {
			return orbiting().toString();
		}

	}

}