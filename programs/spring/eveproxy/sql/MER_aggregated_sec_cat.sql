select
	ic.name "category",
--	ig.name "group",
	round(sum(case when sr.universe='Eve' and ss.security_status>=0.45 then mk.isk_destroyed else 0 end)/100000000)/10000 HS_T,
	round(sum(case when sr.universe='Eve' and ss.security_status<0.45 and ss.security_status>0 then mk.isk_destroyed else 0 end)/100000000)/10000 LS_T,
	round(sum(case when sr.universe='Eve' and ss.security_status<0 then mk.isk_destroyed else 0 end)/100000000)/10000 NS_T,
	round(sum(case when sr.universe='WormHole' then mk.isk_destroyed else 0 end)/100000000)/10000 WS_T,
	round(sum(case when sr.universe='Abyssal' then mk.isk_destroyed else 0 end)/100000000)/10000 AS_T,
	round(sum( mk.isk_destroyed)/100000000)/10000 total_T
from
	mer_kill mk
	join sde_space_solarsystem ss on mk.solar_system_id=ss.id
	join sde_space_constellation sc on ss.constellation_id=sc.id
	join sde_space_region sr on sc.region_id=sr.id
	join sde_items_type it on it.id=mk.victim_type_id
	join sde_items_group ig on ig.id=it.group_id
	join sde_items_category ic on ic.id=ig.category_id
where
	mk.kill_date> (select max(kill_date) from mer_kill) - INTERVAL '1' year
group by
	ic.name
--	, ig.name
order by
	ic.name asc
--	, ig.name asc