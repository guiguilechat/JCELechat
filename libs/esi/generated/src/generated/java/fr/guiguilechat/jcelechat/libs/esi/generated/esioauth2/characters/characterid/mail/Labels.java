package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.mail;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.mail.labels.Labelid;

public class Labels {
    public final EsiOauth2 security;
    public final Labels.GET GET = new Labels.GET();
    public final Labels.POST POST = new Labels.POST();
    public Labelid labelid;

    public Labels(EsiOauth2 security) {
        this.security = security;
        labelid = new Labelid(security);
    }

    public class GET {
    }

    public class POST {
    }
}
