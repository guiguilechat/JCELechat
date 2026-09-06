package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.structures.Mercenarydens;

public class Structures {
    public final EsiOauth2 security;
    public Mercenarydens mercenarydens;

    public Structures(EsiOauth2 security) {
        this.security = security;
        mercenarydens = new Mercenarydens(security);
    }
}
