package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporation.corporationid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporation.corporationid.mining.Extractions;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.corporation.corporationid.mining.Observers;

public class Mining {
    public final EsiOauth2 security;
    public Extractions extractions;
    public Observers observers;

    public Mining(EsiOauth2 security) {
        this.security = security;
        extractions = new Extractions(security);
        observers = new Observers(security);
    }
}
