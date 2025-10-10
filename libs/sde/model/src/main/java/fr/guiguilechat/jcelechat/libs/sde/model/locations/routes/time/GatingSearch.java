package fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.time;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.inspace.Position;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.Stargate;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.generic.AOrbiting;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.AStar;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.time.GatingPoint.OrbitingPoint;
import fr.guiguilechat.jcelechat.libs.sde.model.locations.routes.time.GatingPoint.StargatePoint;
import fr.guiguilechat.jcelechat.model.formula.space.WarpTime;
import lombok.AccessLevel;
import lombok.Getter;

public class GatingSearch extends AStar<GatingPoint> {

	private final double ws_aups;
	private final int align_s;
	private final double subwarpSpeed_mps;
	private final double gateTime_s;
	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	private final double maxGateDistance = Stargate.maxDistance();

	public GatingSearch(GatingPoint start, GatingPoint target, double ws_aups, int align_s, double subwarpSpeed_mps,
			double gateTime_s) {
		super(start, target);
		this.ws_aups = ws_aups;
		this.align_s = align_s;
		this.subwarpSpeed_mps = subwarpSpeed_mps;
		this.gateTime_s = gateTime_s;
	}

	@Override
	protected Iterable<GatingPoint> reachable(GatingPoint from) {
		return from.reachable(target);
	}

	/**
	 * distance(as time, in seconds) between two points depends on whether the later
	 * is a stargate, in which case we need to warp to its other end then jump, or
	 * an orbital, that is
	 * something in the same system (as in, the target)
	 */
	@Override
	protected double distance(GatingPoint start, GatingPoint end) {
		return switch (end) {
		case OrbitingPoint(AOrbiting<?> orbiting): {
			yield warpTime(start.position(), orbiting.position());
		}
		case StargatePoint(Stargate sg): {
			yield warpTime(start.position(), sg.destination().stargate().position()) + gateTime_s;
		}
		};
	}

	@Override
	protected double evalMin(GatingPoint start, GatingPoint end) {
		if (start.solarSystem().equals(end.solarSystem())) {
			return 0.0;
		}
		double distance = start.position().distance(end.position());
		int minNbJumps = (int) Math.ceil(distance / getMaxGateDistance());
//		System.err.println("min " + minNbJumps + "jumps  from " + start + " to " + end + " dist=" + distance
//				+ " maxGateDistance=" + getMaxGateDistance());
		return gateTime_s * minNbJumps;
	}

	protected double warpTime(Position start, Position end) {
		return align_s + WarpTime.ofMeters(start.distance(end), ws_aups, subwarpSpeed_mps);
	}

}
