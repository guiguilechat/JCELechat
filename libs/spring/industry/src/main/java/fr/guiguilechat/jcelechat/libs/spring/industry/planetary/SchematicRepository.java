package fr.guiguilechat.jcelechat.libs.spring.industry.planetary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SchematicRepository extends JpaRepository<Schematic, Integer> {

	@Query("""
select
	distinct (s)
from
	SdePlanetarySchematic s
	left join s.materials mat
	left join mat.type mtype
	left join mtype.group mgroup
	left join mgroup.category mcat
	left join s.products prod
	left join prod.type ptype
	left join ptype.group pgroup
	left join pgroup.category pcat
""")
	List<Schematic> fetchAll();

}
