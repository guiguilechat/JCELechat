package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.alliances;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.alliances.allianceid.Corporations;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.alliances.allianceid.Icons;

public class Allianceid {
    public final EsiAnon security;
    public final Allianceid.GET GET = new Allianceid.GET();
    public Corporations corporations;
    public Icons icons;

    public Allianceid(EsiAnon security) {
        this.security = security;
        corporations = new Corporations(security);
        icons = new Icons(security);
    }

    public class GET {
    }
}
