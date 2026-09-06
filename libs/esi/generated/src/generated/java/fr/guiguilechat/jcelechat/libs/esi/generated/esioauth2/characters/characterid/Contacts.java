package fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid;

import fr.guiguilechat.jcelechat.libs.esi.generated.EsiOauth2;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.characters.characterid.contacts.Labels;

public class Contacts {
    public final EsiOauth2 security;
    public final Contacts.GET GET = new Contacts.GET();
    public final Contacts.PUT PUT = new Contacts.PUT();
    public final Contacts.POST POST = new Contacts.POST();
    public final Contacts.DELETE DELETE = new Contacts.DELETE();
    public Labels labels;

    public Contacts(EsiOauth2 security) {
        this.security = security;
        labels = new Labels(security);
    }

    public class DELETE {
    }

    public class GET {
    }

    public class POST {
    }

    public class PUT {
    }
}
