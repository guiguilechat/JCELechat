package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.structures;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.structures.mercenarydens.Mercenarydenid;

public class Mercenarydens {
    public final EsiOauth2 security;
    public final Mercenarydens.GET GET = new Mercenarydens.GET();
    public Mercenarydenid mercenarydenid;

    public Mercenarydens(EsiOauth2 security) {
        this.security = security;
        mercenarydenid = new Mercenarydenid(security);
    }

    public class GET {
    }
}
