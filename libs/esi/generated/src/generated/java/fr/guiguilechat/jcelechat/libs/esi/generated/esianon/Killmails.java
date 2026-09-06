package fr.guiguilechat.jcelechat.libs.esi.generated.esianon;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiAnon;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.killmails.Killmailid;

public class Killmails {
    public final EsiAnon security;
    public Killmailid killmailid;

    public Killmails(EsiAnon security) {
        this.security = security;
        killmailid = new Killmailid(security);
    }
}
