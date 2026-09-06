package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporation;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporation.corporationid.Mining;

public class Corporationid {
    public final EsiOauth2 security;
    public Mining mining;

    public Corporationid(EsiOauth2 security) {
        this.security = security;
        mining = new Mining(security);
    }
}
