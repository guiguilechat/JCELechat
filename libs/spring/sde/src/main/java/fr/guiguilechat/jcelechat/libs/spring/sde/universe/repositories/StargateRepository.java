package fr.guiguilechat.jcelechat.libs.spring.sde.universe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.Stargate;

public interface StargateRepository extends JpaRepository<Stargate, Integer> {

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
			sqrt(( s1.position_x-s2.position_x )*( s1.position_x-s2.position_x )
				+  ( s1.position_y-s2.position_y )*( s1.position_y-s2.position_y )
				+  ( s1.position_z-s2.position_z )*( s1.position_z-s2.position_z ) ) dst
		from
			SdeUniverseStargate s1
			join SdeUniverseStargate s2 on s1.solarSystem=s2.solarSystem and s1!=s2
		order by
			s1.stargateId,
			s2.stargateId
		""")
	public List<Object[]> listWarpJump();

	@Query(value = """
		select
			s1,
			s2.destination,
			sqrt(( s1.position_x-s2.position_x )*( s1.position_x-s2.position_x )
				+  ( s1.position_y-s2.position_y )*( s1.position_y-s2.position_y )
				+  ( s1.position_z-s2.position_z )*( s1.position_z-s2.position_z ) ) dst
		from
			SdeUniverseStargate s1
			join SdeUniverseStargate s2 on s1.solarSystem=s2.solarSystem and s1!=s2
		where
			s1.solarSystem.security>0.45
		order by
			s1.stargateId,
			s2.stargateId
		""")
	public List<Object[]> listWarpJumpHS();

	@Query(value = """
		select
			s2.destination,
			sqrt(( :x-s2.position_x )*( :x-s2.position_x )
				+  ( :y-s2.position_y )*( :y-s2.position_y )
				+  ( :z-s2.position_z )*( :z-s2.position_z ) ) dst
		from
			SdeUniverseStargate s2
		where :sysId=s2.solarSystem.solarSystemId
		order by
			s2.destination.stargateId
		""")
	public List<Object[]> listWarpJumpFromStation(int sysId, double x, double y, double z);

	@Query(value = """
		select
			s2,
			sqrt(( :x-s2.position_x )*( :x-s2.position_x )
				+  ( :y-s2.position_y )*( :y-s2.position_y )
				+  ( :z-s2.position_z )*( :z-s2.position_z ) ) dst
		from
			SdeUniverseStargate s2
		where :sysId=s2.solarSystem.solarSystemId
		order by
			s2.stargateId
		""")
	public List<Object[]> listWarpJumpToStation(int sysId, double x, double y, double z);

}
