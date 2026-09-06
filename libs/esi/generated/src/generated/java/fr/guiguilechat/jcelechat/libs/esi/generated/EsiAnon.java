package fr.guiguilechat.jcelechat.libs.esi.generated;

import fr.guiguilechat.jcelechat.libs.esi.commons.ISecurity;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Alliances;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Characters;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Contracts;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Corporations;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Cosmetics;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Dogma;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Freelancejobs;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Fw;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Incursions;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Industry;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Insurance;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Killmails;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Loyalty;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Markets;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Meta;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Militarycampaigns;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Paragonhub;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Route;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Skyhooks;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Sovereignty;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Status;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Universe;
import fr.guiguilechat.jcelechat.libs.esi.generated.esianon.Wars;

public class EsiAnon
    implements ISecurity
{
    public Alliances alliances;
    public Characters characters;
    public Contracts contracts;
    public Corporations corporations;
    public Cosmetics cosmetics;
    public Dogma dogma;
    public Freelancejobs freelancejobs;
    public Fw fw;
    public Incursions incursions;
    public Industry industry;
    public Insurance insurance;
    public Killmails killmails;
    public Loyalty loyalty;
    public Markets markets;
    public Meta meta;
    public Militarycampaigns militarycampaigns;
    public Paragonhub paragonhub;
    public Route route;
    public Skyhooks skyhooks;
    public Sovereignty sovereignty;
    public Status status;
    public Universe universe;
    public Wars wars;

    public EsiAnon() {
        alliances = new Alliances(this);
        characters = new Characters(this);
        contracts = new Contracts(this);
        corporations = new Corporations(this);
        cosmetics = new Cosmetics(this);
        dogma = new Dogma(this);
        freelancejobs = new Freelancejobs(this);
        fw = new Fw(this);
        incursions = new Incursions(this);
        industry = new Industry(this);
        insurance = new Insurance(this);
        killmails = new Killmails(this);
        loyalty = new Loyalty(this);
        markets = new Markets(this);
        meta = new Meta(this);
        militarycampaigns = new Militarycampaigns(this);
        paragonhub = new Paragonhub(this);
        route = new Route(this);
        skyhooks = new Skyhooks(this);
        sovereignty = new Sovereignty(this);
        status = new Status(this);
        universe = new Universe(this);
        wars = new Wars(this);
    }
}
