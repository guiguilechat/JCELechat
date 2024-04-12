SELECT
 ss.space,
 tp.name,
min(planet.radius) min_radius,
avg(planet.radius) avg_radius,
max(planet.radius) max_radius
FROM
 SDE_UNIVERSE_PLANET planet
 join sde_universe_solarsystem ss on planet.SOLAR_SYSTEM_SOLAR_SYSTEM_ID = ss.solar_system_id
 join sde_dogma_type tp on planet.type_type_id=tp.type_id
group by
 ss.space,
 tp.name
order by
 avg(planet.radius)
