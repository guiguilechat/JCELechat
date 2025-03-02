package fr.guiguilechat.jcelechat.libs.spring.universe.stargate;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.universe.solarsystem.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.IRemoteEntityRepository;

public interface StargateRepository extends IRemoteEntityRepository<Stargate, Integer> {

	List<Stargate> findBySolarSystem(SolarSystem solarSystem);

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
	#{#entityName} s1
	join #{#entityName} s2 on s1.solarSystem=s2.solarSystem and s1!=s2
order by
	s1.id,
	s2.id
""")
	List<Object[]> listWarpJump();

	@Query(value = """
select
	s1,
	s2.destination,
	sqrt(( s1.posX-s2.posX )*( s1.posX-s2.posX )
		+  ( s1.posY-s2.posY )*( s1.posY-s2.posY )
		+  ( s1.posZ-s2.posZ )*( s1.posZ-s2.posZ ) ) dst
from
	#{#entityName} s1
	join #{#entityName} s2 on s1.solarSystem=s2.solarSystem and s1!=s2
where
	s1.solarSystem.securityStatus>0.45
order by
	s1.id,
	s2.id
""")
	List<Object[]> listWarpJumpHS();

	@Query(value = """
select
	s2.destination,
	sqrt(( :x-s2.posX )*( :x-s2.posX )
		+  ( :y-s2.posY )*( :y-s2.posY )
		+  ( :z-s2.posZ )*( :z-s2.posZ ) ) dst
from
	#{#entityName} s2
where :sysId=s2.solarSystem.id
order by
	s2.destination.id
""")
	List<Object[]> listWarpJumpFromStation(int sysId, double x, double y, double z);

	@Query(value = """
select
	s2,
	sqrt(( :x-s2.posX )*( :x-s2.posX )
		+  ( :y-s2.posY )*( :y-s2.posY )
		+  ( :z-s2.posZ )*( :z-s2.posZ ) ) dst
from
	#{#entityName} s2
where :sysId=s2.solarSystem.id
order by
	s2.id
""")
	List<Object[]> listWarpJumpToStation(int sysId, double x, double y, double z);

}
