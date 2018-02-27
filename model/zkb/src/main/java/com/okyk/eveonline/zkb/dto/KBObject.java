package com.okyk.eveonline.zkb.dto;

import java.util.Collection;
import java.util.Date;

public class KBObject {

    public String killmail_id;
    public Date killmail_time;
    public Victim victim;
    public Collection<Attacker> attackers;
    public Integer solar_system_id;
    public ZKBInfo zkb;

}
