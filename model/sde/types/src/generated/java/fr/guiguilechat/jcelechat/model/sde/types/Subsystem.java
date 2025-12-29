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
import fr.guiguilechat.jcelechat.model.sde.attributes.*;
import fr.guiguilechat.jcelechat.model.sde.types.subsystem.CoreSubsystem;
import fr.guiguilechat.jcelechat.model.sde.types.subsystem.DefensiveSubsystem;
import fr.guiguilechat.jcelechat.model.sde.types.subsystem.OffensiveSubsystem;
import fr.guiguilechat.jcelechat.model.sde.types.subsystem.PropulsionSubsystem;

public abstract class Subsystem
    extends EveType
{
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Hp.INSTANCE, RequiredSkill1Level.INSTANCE, SubSystemSlot.INSTANCE, HiSlotModifier.INSTANCE, MedSlotModifier.INSTANCE, LowSlotModifier.INSTANCE, FitsToShipType.INSTANCE, TechLevel.INSTANCE, RequiredSkill1 .INSTANCE, MetaLevelOld.INSTANCE })));
    public static final Subsystem.MetaCat METACAT = new Subsystem.MetaCat();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
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
            case  1375 :
            {
                return medslotmodifier;
            }
            case  633 :
            {
                return metalevelold;
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
