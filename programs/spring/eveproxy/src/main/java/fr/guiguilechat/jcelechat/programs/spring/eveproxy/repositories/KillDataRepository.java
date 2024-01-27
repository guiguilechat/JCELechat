package fr.guiguilechat.jcelechat.programs.spring.eveproxy.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.programs.spring.eveproxy.model.KillData;

public interface KillDataRepository extends JpaRepository<KillData, Long> {
// much faster (4×) than a
// left join EveProxyKillData kd on kd.kill=mk
// where kd is null
	@Query("""
select
	mk,
	ss,
	vt,
	ss.constellation,
	ss.constellation.region,
	vt.group,
	vt.group.category
from
	MerKill mk
	join SdeUniverseSolarSystem ss on mk.solarSystemId=ss.solarSystemId
	join SdeDogmaType vt on mk.destroyedShipTypeId=vt.typeId
where
	mk.id >:lastid
	and not exists(select 1 from EveProxyKillData kd where kd.kill=mk)
order by mk.id asc
""")
	public List<Object[]> findMissing(Pageable pageable, long lastid);

	public Optional<KillData> findByKillId(long killId);

}
