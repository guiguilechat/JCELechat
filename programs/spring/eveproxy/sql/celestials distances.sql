with celestials as(
	select
		id,
		solar_system_id ssid,
		posx x,
		posy y,
		posz z
	from
		sde_space_stargate
	union all
	select
		id,
		solar_system_id ssid,
		posx x,
		posy y,
		posz z
	from
		sde_space_planet
)
select
	ss.name,
	rs.name region,
	rs.universe uni,
	ss.security_status,
	max(sqrt(
		power(c1.x-c2.x, 2)
		+ power(c1.y-c2.y, 2)
		+ power(c1.z-c2.z, 2)
	))/ 149597870691 diameter_au
from
	celestials c1
	join celestials c2 on c1.ssid=c2.ssid and c1.id<c2.id
	join sde_space_solarsystem ss on c1.ssid = ss.id
	join sde_space_constellation cs on cs.id = ss.constellation_id
	join sde_space_region rs on rs.id = cs.region_id
group by
	ss.name,
	rs.name,
	rs.universe,
	ss.security_status
order by	
	max(sqrt(
		power(c1.x-c2.x, 2)
		+ power(c1.y-c2.y, 2)
		+ power(c1.z-c2.z, 2)
	)) desc