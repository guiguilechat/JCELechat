package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.militarycampaigns.campaignid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.militarycampaigns.campaignid.objectives.Objectiveid;

public class Objectives {
    public final EsiAnon security;
    public final Objectives.GET GET = new Objectives.GET();
    public Objectiveid objectiveid;

    public Objectives(EsiAnon security) {
        this.security = security;
        objectiveid = new Objectiveid(security);
    }

    public class GET {
    }
}
