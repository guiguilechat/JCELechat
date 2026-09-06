package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.markets;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.markets.groups.Marketgroupid;

public class Groups {
    public final EsiAnon security;
    public final Groups.GET GET = new Groups.GET();
    public Marketgroupid marketgroupid;

    public Groups(EsiAnon security) {
        this.security = security;
        marketgroupid = new Marketgroupid(security);
    }

    public class GET {
    }
}
