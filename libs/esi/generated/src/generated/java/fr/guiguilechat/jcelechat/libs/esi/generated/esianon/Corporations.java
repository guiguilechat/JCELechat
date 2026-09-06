package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.corporations.Corporationid;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.corporations.Npccorps;

public class Corporations {
    public final EsiAnon security;
    public Npccorps npccorps;
    public Corporationid corporationid;

    public Corporations(EsiAnon security) {
        this.security = security;
        npccorps = new Npccorps(security);
        corporationid = new Corporationid(security);
    }
}
