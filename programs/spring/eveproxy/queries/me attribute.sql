select
	att.name att,
	gp.group_id gpid,
	gp.name gp,
	count(ta.attribute_attribute_id) nbtypes
from
	sde_dogma_attribute att
	left join sde_dogma_typeattribute ta on ta.attribute_attribute_id=att.attribute_id
	left join sde_dogma_type tpe on tpe.type_id=ta.type_type_id
	left join sde_dogma_group gp on tpe.group_group_id=gp.group_id
where
	att.name like '%aterial%'
group by
	att.name,
	gp.group_id,
	gp.name
order by 
	att.name,
	gp.name
;