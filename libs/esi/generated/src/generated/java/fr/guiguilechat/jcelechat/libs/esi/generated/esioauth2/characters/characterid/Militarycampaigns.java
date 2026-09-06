package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.militarycampaigns.Objectives;

public class Militarycampaigns {
    public final EsiOauth2 security;
    public Objectives objectives;

    public Militarycampaigns(EsiOauth2 security) {
        this.security = security;
        objectives = new Objectives(security);
    }
}
