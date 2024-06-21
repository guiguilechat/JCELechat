package fr.guiguilechat.jcelechat.libs.spring.universe.stargate;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.IRemoteResourceRepository;
import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;

public interface StargateRepository extends IRemoteResourceRepository<Stargate, Integer> {

	public List<Stargate> findBySolarSystem(SolarSystem solarSystem);

	/**
	 * @return all couple of stargate where you can warp from the first, jump an
	 *           intermediate stargate, and arrive at the second. Also returns the
	 *           distance in meter of that warp
	 */
	@Query(value = """
		select
			s1,
			s2.destination,
			sqrt(( s1.posX-s2.posX )*( s1.posX-s2.posX )
				+  ( s1.posY-s2.posY )*( s1.posY-s2.posY )
				+  ( s1.posZ-s2.posZ )*( s1.posZ-s2.posZ ) ) dst
		from
			EsiUniverseStargate s1
			join EsiUniverseStargate s2 on s1.solarSystem=s2.solarSystem and s1!=s2
		order by
			s1.id,
			s2.id
		""")
	public List<Object[]> listWarpJump();

	@Query(value = """
		select
			s1,
			s2.destination,
			sqrt(( s1.posX-s2.posX )*( s1.posX-s2.posX )
				+  ( s1.posY-s2.posY )*( s1.posY-s2.posY )
				+  ( s1.posZ-s2.posZ )*( s1.posZ-s2.posZ ) ) dst
		from
			EsiUniverseStargate s1
			join EsiUniverseStargate s2 on s1.solarSystem=s2.solarSystem and s1!=s2
		where
			s1.solarSystem.securityStatus>0.45
		order by
			s1.id,
			s2.id
		""")
	public List<Object[]> listWarpJumpHS();

	@Query(value = """
		select
			s2.destination,
			sqrt(( :x-s2.posX )*( :x-s2.posX )
				+  ( :y-s2.posY )*( :y-s2.posY )
				+  ( :z-s2.posZ )*( :z-s2.posZ ) ) dst
		from
			EsiUniverseStargate s2
		where :sysId=s2.solarSystem.id
		order by
			s2.destination.id
		""")
	public List<Object[]> listWarpJumpFromStation(int sysId, double x, double y, double z);

	@Query(value = """
		select
			s2,
			sqrt(( :x-s2.posX )*( :x-s2.posX )
				+  ( :y-s2.posY )*( :y-s2.posY )
				+  ( :z-s2.posZ )*( :z-s2.posZ ) ) dst
		from
			EsiUniverseStargate s2
		where :sysId=s2.solarSystem.id
		order by
			s2.id
		""")
	public List<Object[]> listWarpJumpToStation(int sysId, double x, double y, double z);

}
