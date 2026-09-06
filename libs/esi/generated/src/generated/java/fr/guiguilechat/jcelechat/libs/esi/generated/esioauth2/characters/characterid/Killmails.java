package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.killmails.Recent;

public class Killmails {
    public final EsiOauth2 security;
    public Recent recent;

    public Killmails(EsiOauth2 security) {
        this.security = security;
        recent = new Recent(security);
    }
}
