package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.mail.Labels;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.mail.Lists;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.mail.Mailid;

public class Mail {
    public final EsiOauth2 security;
    public final Mail.GET GET = new Mail.GET();
    public final Mail.POST POST = new Mail.POST();
    public Labels labels;
    public Lists lists;
    public Mailid mailid;

    public Mail(EsiOauth2 security) {
        this.security = security;
        labels = new Labels(security);
        lists = new Lists(security);
        mailid = new Mailid(security);
    }

    public class GET {
    }

    public class POST {
    }
}
