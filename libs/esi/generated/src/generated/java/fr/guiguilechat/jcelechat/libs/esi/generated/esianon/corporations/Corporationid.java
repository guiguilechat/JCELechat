package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.corporations;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.corporations.corporationid.Alliancehistory;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.corporations.corporationid.Icons;

public class Corporationid {
    public final EsiAnon security;
    public final Corporationid.GET GET = new Corporationid.GET();
    public Alliancehistory alliancehistory;
    public Icons icons;

    public Corporationid(EsiAnon security) {
        this.security = security;
        alliancehistory = new Alliancehistory(security);
        icons = new Icons(security);
    }

    public class GET {
    }
}
