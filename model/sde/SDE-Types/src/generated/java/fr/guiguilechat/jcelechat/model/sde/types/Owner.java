package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.owner.Alliance;
import fr.guiguilechat.jcelechat.model.sde.types.owner.Character;
import fr.guiguilechat.jcelechat.model.sde.types.owner.Corporation;
import fr.guiguilechat.jcelechat.model.sde.types.owner.Faction;

public abstract class Owner
    extends EveType
{
    public static final Owner.MetaCat METACAT = new Owner.MetaCat();

    @Override
    public IMetaCategory<Owner> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Owner>
    {

        @Override
        public int getCategoryId() {
            return  1;
        }

        @Override
        public String getName() {
            return "Owner";
        }

        @Override
        public Collection<IMetaGroup<? extends Owner>> groups() {
            return Arrays.asList(Character.METAGROUP, Corporation.METAGROUP, Faction.METAGROUP, Alliance.METAGROUP);
        }
    }
}
