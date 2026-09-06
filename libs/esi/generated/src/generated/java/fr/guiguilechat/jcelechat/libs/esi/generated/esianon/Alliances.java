package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.alliances.Allianceid;

public class Alliances {
    public final EsiAnon security;
    public final Alliances.GET GET = new Alliances.GET();
    public Allianceid allianceid;

    public Alliances(EsiAnon security) {
        this.security = security;
        allianceid = new Allianceid(security);
    }

    public class GET {
    }
}
