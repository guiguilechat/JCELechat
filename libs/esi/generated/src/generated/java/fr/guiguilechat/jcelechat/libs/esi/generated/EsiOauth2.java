package fr.guiguilechat.jcelechat.libs.esi.generated;

import fr.guiguilechat.jcelechat.libs.esi.commons.ISecurity;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.Alliances;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.Characters;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.Corporation;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.Corporations;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.Fleets;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.Markets;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.Paragonhub;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.Ui;
import fr.guiguilechat.jcelechat.libs.esi.generated.esioauth2.Universe;

public class EsiOauth2
    implements ISecurity
{
    public Alliances alliances;
    public Characters characters;
    public Corporation corporation;
    public Corporations corporations;
    public Fleets fleets;
    public Markets markets;
    public Paragonhub paragonhub;
    public Ui ui;
    public Universe universe;

    public EsiOauth2() {
        alliances = new Alliances(this);
        characters = new Characters(this);
        corporation = new Corporation(this);
        corporations = new Corporations(this);
        fleets = new Fleets(this);
        markets = new Markets(this);
        paragonhub = new Paragonhub(this);
        ui = new Ui(this);
        universe = new Universe(this);
    }
}
