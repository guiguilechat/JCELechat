package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.militarycampaigns.Campaignid;

public class Militarycampaigns {
    public final EsiAnon security;
    public final Militarycampaigns.GET GET = new Militarycampaigns.GET();
    public Campaignid campaignid;

    public Militarycampaigns(EsiAnon security) {
        this.security = security;
        campaignid = new Campaignid(security);
    }

    public class GET {
    }
}
