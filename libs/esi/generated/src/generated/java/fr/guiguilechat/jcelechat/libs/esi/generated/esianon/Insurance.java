package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.insurance.Prices;

public class Insurance {
    public final EsiAnon security;
    public Prices prices;

    public Insurance(EsiAnon security) {
        this.security = security;
        prices = new Prices(security);
    }
}
