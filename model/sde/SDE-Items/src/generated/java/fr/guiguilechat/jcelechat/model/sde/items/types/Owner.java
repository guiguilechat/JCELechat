package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.owner.Character;

public abstract class Owner
    extends Item
{
    public final static Owner.MetaCat METACAT = new Owner.MetaCat();

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
            return Arrays.asList(Character.METAGROUP);
        }
    }
}
