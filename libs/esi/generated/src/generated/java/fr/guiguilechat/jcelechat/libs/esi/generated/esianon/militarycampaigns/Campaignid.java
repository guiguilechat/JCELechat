package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.militarycampaigns;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.militarycampaigns.campaignid.Objectives;

public class Campaignid {
    public final EsiAnon security;
    public final Campaignid.GET GET = new Campaignid.GET();
    public Objectives objectives;

    public Campaignid(EsiAnon security) {
        this.security = security;
        objectives = new Objectives(security);
    }

    public class GET {
    }
}
