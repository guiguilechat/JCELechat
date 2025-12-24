with 
accel_gain as(
select
	t.id type_id,
	t.name type_name,
	timestamp '1970-02-12 00:00:00+00' at TIME ZONE 'UTC' + interval '1 day'*expires_att.value expires,
	int_att.value "int_gain",
	(dur_att.value/60000)::int dur_min,
	(int_att.value*1.5*dur_att.value/60000)::bigint sp_gain
from
	sde_items_type t
	left join sde_items_typeattribute expires_att on t.id=expires_att.type_id and expires_att.attribute_id=2422
	join sde_items_typeattribute int_att on t.id=int_att.type_id and int_att.attribute_id=176
	join sde_items_typeattribute dur_att on t.id=dur_att.type_id and dur_att.attribute_id=330
where
	market_group_id=2487
	and published
)

select
	ag.type_id,
	ag.type_name,
	ag.sp_gain,
	min(ml.price)/1000000 price_m,
	(ag.sp_gain*1000000/min(ml.price))::int "sp_p_misk"
from
	accel_gain ag
	join esi_trade_market_line ml
		on ml.type_id=ag.type_id
		and not ml.is_buy_order
		and ml.location_id=60003760
where
	ag.expires is null or ag.expires>now()
group by
	ag.type_id,
	ag.type_name,
	ag.sp_gain,
	ml.location_id
order by
	ag.sp_gain/min(ml.price) desc