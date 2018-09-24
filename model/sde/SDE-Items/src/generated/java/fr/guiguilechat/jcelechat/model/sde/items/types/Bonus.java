package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.bonus.AmarrEducation;
import fr.guiguilechat.jcelechat.model.sde.items.types.bonus.BloodlineBonus;
import fr.guiguilechat.jcelechat.model.sde.items.types.bonus.CaldariEducation;
import fr.guiguilechat.jcelechat.model.sde.items.types.bonus.CareerBonus;
import fr.guiguilechat.jcelechat.model.sde.items.types.bonus.GallenteEducation;
import fr.guiguilechat.jcelechat.model.sde.items.types.bonus.MinmatarEducation;
import fr.guiguilechat.jcelechat.model.sde.items.types.bonus.PhobiaHandicap;
import fr.guiguilechat.jcelechat.model.sde.items.types.bonus.PhysicalBenefit;
import fr.guiguilechat.jcelechat.model.sde.items.types.bonus.PhysicalHandicap;

public abstract class Bonus
    extends Item
{
    public final static Bonus.MetaCat METACAT = new Bonus.MetaCat();

    @Override
    public IMetaCategory<Bonus> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Bonus>
    {

        @Override
        public int getCategoryId() {
            return  14;
        }

        @Override
        public String getName() {
            return "Bonus";
        }

        @Override
        public Collection<IMetaGroup<? extends Bonus>> groups() {
            return Arrays.asList(BloodlineBonus.METAGROUP, PhysicalBenefit.METAGROUP, PhysicalHandicap.METAGROUP, PhobiaHandicap.METAGROUP, AmarrEducation.METAGROUP, CaldariEducation.METAGROUP, GallenteEducation.METAGROUP, MinmatarEducation.METAGROUP, CareerBonus.METAGROUP);
        }
    }
}
