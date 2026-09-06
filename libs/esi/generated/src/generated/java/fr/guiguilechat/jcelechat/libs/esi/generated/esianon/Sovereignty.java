package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.sovereignty.Campaigns;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.sovereignty.Systems;

public class Sovereignty {
    public final EsiAnon security;
    public Campaigns campaigns;
    public Systems systems;

    public Sovereignty(EsiAnon security) {
        this.security = security;
        campaigns = new Campaigns(security);
        systems = new Systems(security);
    }
}
