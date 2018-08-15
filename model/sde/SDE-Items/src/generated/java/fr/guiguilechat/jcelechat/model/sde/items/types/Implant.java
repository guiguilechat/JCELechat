package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.Booster;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.CyberArmor;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.CyberBiology;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.CyberDrones;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.CyberElectronicSystems;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.CyberEngineering;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.CyberGunnery;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.CyberLeadership;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.CyberLearning;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.CyberMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.CyberNavigation;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.CyberProduction;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.CyberResourceProcessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.CyberScanning;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.CyberScience;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.CyberShields;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.CyberTargeting;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.CyberXSpecials;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.Cyberimplant;
import fr.guiguilechat.jcelechat.model.sde.items.types.implant.SpecialEditionImplant;

public abstract class Implant
    extends Item
{
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    public final static Implant.MetaCat METACAT = new Implant.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaCategory<Implant> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Implant>
    {

        @Override
        public int getCategoryId() {
            return  20;
        }

        @Override
        public String getName() {
            return "Implant";
        }

        @Override
        public Collection<IMetaGroup<? extends Implant>> groups() {
            return Arrays.asList(Cyberimplant.METAGROUP, Booster.METAGROUP, CyberArmor.METAGROUP, CyberDrones.METAGROUP, CyberElectronicSystems.METAGROUP, CyberEngineering.METAGROUP, CyberGunnery.METAGROUP, CyberProduction.METAGROUP, CyberLeadership.METAGROUP, CyberLearning.METAGROUP, CyberMissile.METAGROUP, CyberNavigation.METAGROUP, CyberScience.METAGROUP, CyberShields.METAGROUP, CyberXSpecials.METAGROUP, CyberTargeting.METAGROUP, CyberResourceProcessing.METAGROUP, CyberScanning.METAGROUP, CyberBiology.METAGROUP, SpecialEditionImplant.METAGROUP);
        }
    }
}