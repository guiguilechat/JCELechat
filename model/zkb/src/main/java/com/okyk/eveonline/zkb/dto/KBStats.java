package com.okyk.eveonline.zkb.dto;

import java.util.Collection;
import java.util.Map;

public class KBStats {

    public Integer allTimeSum;
    public Boolean calcTrophies;
    public Integer dangerRatio;
    public Integer gangRatio;
    public Map<Integer, KBGroup> groups;
    public Long id;
    public Long iskDestroyed;
    public Long iskLost;
    public Map<Integer, KBMothStat> months;
    public Integer nextTopRecalc;
    public Integer pointsDestroyed;
    public Integer pointsLost;
    public Long sequence;
    public Integer shipsDestroyed;
    public Integer shipsLost;
    public Integer soloKills;
    public Integer soloLosses;
    public Collection<KBTopItem> topAllTime;
    public Map<String, Integer> trophies;
    public String type;
    public KBPVPVdata activepvp;
    public KBInfo info;
    public Collection<KBTop> topLists;
    public Collection<Long> topIskKillIDs;

    @Override
    public String toString() {
        return "KBStats{" +
                "dangerRatio=" + dangerRatio +
                ", gangRatio=" + gangRatio +
                ", id=" + id +
                '}';
    }
}
