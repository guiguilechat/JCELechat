select
	ss.name,
	max(sqrt(
		power(sg1.posx-sg2.posx, 2)
		+ power(sg1.posy-sg2.posy, 2)
		+ power(sg1.posz-sg2.posz, 2)
	))/ 149597870691 diameter_au
from
	sde_space_stargate sg1
	join sde_space_stargate sg2 on sg1.solar_system_id=sg2.solar_system_id and sg1.id<sg2.id
	join sde_space_solarsystem ss on sg1.solar_system_id = ss.id
group by
	ss.name
order by	
	max(sqrt(
		power(sg1.posx-sg2.posx, 2)
		+ power(sg1.posy-sg2.posy, 2)
		+ power(sg1.posz-sg2.posz, 2)
	)) desc