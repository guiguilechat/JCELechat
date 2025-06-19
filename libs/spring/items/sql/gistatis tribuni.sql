select
	att."name" att_name,
	att.id att_id,
	ta."value" "val", 
	att.default_value "default"
from
	esi_items_type tp
	join esi_items_typeattribute ta on ta.type_id = tp.id
	join esi_items_attribute att on ta.attribute_id = att.id
where
	tp.id =24005
--	and lower(att.name) like '%missile%'
order by
	att.id asc
;