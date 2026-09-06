package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.universe.stations.Stationid;

public class Stations {
    public final EsiAnon security;
    public Stationid stationid;

    public Stations(EsiAnon security) {
        this.security = security;
        stationid = new Stationid(security);
    }
}
