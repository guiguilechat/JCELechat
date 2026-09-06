package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.paragonhub.skinr;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.paragonhub.skinr.alliances.Allianceid;

public class Alliances {
    public final EsiOauth2 security;
    public Allianceid allianceid;

    public Alliances(EsiOauth2 security) {
        this.security = security;
        allianceid = new Allianceid(security);
    }
}
