package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.Characterid;

public class Characters {
    public final EsiOauth2 security;
    public Characterid characterid;

    public Characters(EsiOauth2 security) {
        this.security = security;
        characterid = new Characterid(security);
    }
}
