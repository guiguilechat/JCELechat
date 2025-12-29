package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.types.implant.*;

public abstract class Implant
    extends EveType
{
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1level;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {RequiredSkill1Level.INSTANCE, RequiredSkill1 .INSTANCE })));
    public static final Implant.MetaCat METACAT = new Implant.MetaCat();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
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
