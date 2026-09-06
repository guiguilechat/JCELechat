package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.alliances;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.alliances.allianceid.Contacts;

public class Allianceid {
    public final EsiOauth2 security;
    public Contacts contacts;

    public Allianceid(EsiOauth2 security) {
        this.security = security;
        contacts = new Contacts(security);
    }
}
