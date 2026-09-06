package fr.guiguilechat.jcelechat.libs.esi.generated.esianon.killmails;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.killmails.killmailid.Killmailhash;

public class Killmailid {
    public final EsiAnon security;
    public Killmailhash killmailhash;

    public Killmailid(EsiAnon security) {
        this.security = security;
        killmailhash = new Killmailhash(security);
    }
}
