-- list all the fitting stats of all published ships

select
	tp.id type_id,
	tp.name type_name,
	grp.id group_id,
	grp.name group_name,
	coalesce(max(ta.value) filter( where att.id=48), max(att.default_value) filter( where att.id=48)) cpu,
	coalesce(max(ta.value) filter( where att.id=11), max(att.default_value) filter( where att.id=11)) powergrid,
	coalesce(max(ta.value) filter( where att.id=1132), max(att.default_value) filter( where att.id=1132)) calibration,
	coalesce(max(ta.value) filter( where att.id=14), max(att.default_value) filter( where att.id=14)) high_slots,
	coalesce(max(ta.value) filter( where att.id=102), max(att.default_value) filter( where att.id=102)) turret_slots,
	coalesce(max(ta.value) filter( where att.id=101), max(att.default_value) filter( where att.id=101)) launcher_slots,
	coalesce(max(ta.value) filter( where att.id=13), max(att.default_value) filter( where att.id=13)) medium_slots,
	coalesce(max(ta.value) filter( where att.id=12), max(att.default_value) filter( where att.id=12)) low_slots,
	coalesce(max(ta.value) filter( where att.id=1154), max(att.default_value) filter( where att.id=1154)) rig_slots
from
	esi_items_type tp
	join esi_items_group grp on grp.id=tp.group_id
	cross join esi_items_attribute att
	left join esi_items_typeattribute ta on ta.type_id = tp.id and ta.attribute_id=att.id
where
	grp.category_id=6 -- ship category
	and tp.published
group by tp.id, tp.name, grp.id, grp.name

-- list drake attributes

select
	tp."name" type_name,
	tp.id type_id,
	att."name" att_name,
	att.id att_id,
	ta."value" "val", 
	att.default_value "default"
from
	esi_items_type tp
	join esi_items_typeattribute ta on ta.type_id = tp.id
	join esi_items_attribute att on ta.attribute_id = att.id
where
	tp."name" = 'Drake'
;