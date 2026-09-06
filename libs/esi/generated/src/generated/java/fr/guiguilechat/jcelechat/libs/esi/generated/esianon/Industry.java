package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.industry.Facilities;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.industry.Systems;

public class Industry {
    public final EsiAnon security;
    public Facilities facilities;
    public Systems systems;

    public Industry(EsiAnon security) {
        this.security = security;
        facilities = new Facilities(security);
        systems = new Systems(security);
    }
}
