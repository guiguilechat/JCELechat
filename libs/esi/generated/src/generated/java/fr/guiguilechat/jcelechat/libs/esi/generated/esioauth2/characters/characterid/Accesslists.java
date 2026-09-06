package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.accesslists.Accesslistid;

public class Accesslists {
    public final EsiOauth2 security;
    public final Accesslists.GET GET = new Accesslists.GET();
    public Accesslistid accesslistid;

    public Accesslists(EsiOauth2 security) {
        this.security = security;
        accesslistid = new Accesslistid(security);
    }

    public class GET {
    }
}
