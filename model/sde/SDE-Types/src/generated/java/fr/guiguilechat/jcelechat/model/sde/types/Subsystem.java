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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FitsToShipType;
import fr.guiguilechat.jcelechat.model.sde.attributes.HiSlotModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.LowSlotModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MedSlotModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubSystemSlot;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.types.subsystem.CoreSubsystem;
import fr.guiguilechat.jcelechat.model.sde.types.subsystem.DefensiveSubsystem;
import fr.guiguilechat.jcelechat.model.sde.types.subsystem.OffensiveSubsystem;
import fr.guiguilechat.jcelechat.model.sde.types.subsystem.PropulsionSubsystem;

public abstract class Subsystem
    extends EveType
{
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fitstoshiptype;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int hislotmodifier;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int lowslotmodifier;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double mass;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int medslotmodifier;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double radius;
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
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystemslot;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Mass.INSTANCE, Hp.INSTANCE, RequiredSkill1Level.INSTANCE, SubSystemSlot.INSTANCE, HiSlotModifier.INSTANCE, MedSlotModifier.INSTANCE, LowSlotModifier.INSTANCE, Radius.INSTANCE, FitsToShipType.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, RequiredSkill1 .INSTANCE, MetaLevelOld.INSTANCE })));
    public static final Subsystem.MetaCat METACAT = new Subsystem.MetaCat();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  38 :
            {
                return capacity;
            }
            case  1380 :
            {
                return fitstoshiptype;
            }
            case  1374 :
            {
                return hislotmodifier;
            }
            case  9 :
            {
                return hp;
            }
            case  1376 :
            {
                return lowslotmodifier;
            }
            case  4 :
            {
                return mass;
            }
            case  1375 :
            {
                return medslotmodifier;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  162 :
            {
                return radius;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  1366 :
            {
                return subsystemslot;
            }
            case  422 :
            {
                return techlevel;
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
    public IMetaCategory<Subsystem> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Subsystem>
    {

        @Override
        public int getCategoryId() {
            return  32;
        }

        @Override
        public String getName() {
            return "Subsystem";
        }

        @Override
        public Collection<IMetaGroup<? extends Subsystem>> groups() {
            return Arrays.asList(DefensiveSubsystem.METAGROUP, OffensiveSubsystem.METAGROUP, PropulsionSubsystem.METAGROUP, CoreSubsystem.METAGROUP);
        }
    }
}
