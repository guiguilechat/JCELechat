package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.militarycampaigns;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.militarycampaigns.objectives.Objectiveid;

public class Objectives {
    public final EsiOauth2 security;
    public final Objectives.GET GET = new Objectives.GET();
    public Objectiveid objectiveid;

    public Objectives(EsiOauth2 security) {
        this.security = security;
        objectiveid = new Objectiveid(security);
    }

    public class GET {
    }
}
