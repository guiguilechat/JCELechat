package fr.guiguilechat.jcelechat.model.sde.types.subsystem;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.AgilityBonusAdd;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FitsToShipType;
import fr.guiguilechat.jcelechat.model.sde.attributes.HiSlotModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.LowSlotModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MedSlotModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubSystemSlot;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusAmarrPropulsion;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusAmarrPropulsion2;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusCaldariPropulsion;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusCaldariPropulsion2;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusGallentePropulsion;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusGallentePropulsion2;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusMinmatarPropulsion;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusMinmatarPropulsion2;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpBubbleImmuneModifier;
import fr.guiguilechat.jcelechat.model.sde.types.Subsystem;
import org.yaml.snakeyaml.Yaml;

public class PropulsionSubsystem
    extends Subsystem
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double agilitybonusadd;
    /**
     * Maximum range at which the scanner can lock a target.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double maxtargetrange;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(100.0)
    public double signatureradius;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystembonusamarrpropulsion;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystembonusamarrpropulsion2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double subsystembonuscaldaripropulsion;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double subsystembonuscaldaripropulsion2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double subsystembonusgallentepropulsion;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double subsystembonusgallentepropulsion2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystembonusminmatarpropulsion;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystembonusminmatarpropulsion2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warpbubbleimmunemodifier;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {AgilityBonusAdd.INSTANCE, WarpBubbleImmuneModifier.INSTANCE, Mass.INSTANCE, Hp.INSTANCE, MaxTargetRange.INSTANCE, RequiredSkill1Level.INSTANCE, SubSystemSlot.INSTANCE, SubsystemBonusAmarrPropulsion.INSTANCE, HiSlotModifier.INSTANCE, MedSlotModifier.INSTANCE, SubsystemBonusGallentePropulsion.INSTANCE, LowSlotModifier.INSTANCE, Radius.INSTANCE, FitsToShipType.INSTANCE, SubsystemBonusCaldariPropulsion.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, SubsystemBonusAmarrPropulsion2 .INSTANCE, SubsystemBonusCaldariPropulsion2 .INSTANCE, SubsystemBonusMinmatarPropulsion.INSTANCE, SubsystemBonusGallentePropulsion2 .INSTANCE, SubsystemBonusMinmatarPropulsion2 .INSTANCE, RequiredSkill1 .INSTANCE, MetaLevel.INSTANCE })));
    public static final PropulsionSubsystem.MetaGroup METAGROUP = new PropulsionSubsystem.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2690 :
            {
                return agilitybonusadd;
            }
            case  76 :
            {
                return maxtargetrange;
            }
            case  552 :
            {
                return signatureradius;
            }
            case  1435 :
            {
                return subsystembonusamarrpropulsion;
            }
            case  1512 :
            {
                return subsystembonusamarrpropulsion2;
            }
            case  1445 :
            {
                return subsystembonuscaldaripropulsion;
            }
            case  1513 :
            {
                return subsystembonuscaldaripropulsion2;
            }
            case  1440 :
            {
                return subsystembonusgallentepropulsion;
            }
            case  1520 :
            {
                return subsystembonusgallentepropulsion2;
            }
            case  1450 :
            {
                return subsystembonusminmatarpropulsion;
            }
            case  1523 :
            {
                return subsystembonusminmatarpropulsion2;
            }
            case  1539 :
            {
                return warpbubbleimmunemodifier;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<PropulsionSubsystem> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PropulsionSubsystem>
    {
        public static final String RESOURCE_PATH = "SDE/types/subsystem/PropulsionSubsystem.yaml";
        private Map<String, PropulsionSubsystem> cache = (null);

        @Override
        public IMetaCategory<? super PropulsionSubsystem> category() {
            return Subsystem.METACAT;
        }

        @Override
        public int getGroupId() {
            return  957;
        }

        @Override
        public String getName() {
            return "PropulsionSubsystem";
        }

        @Override
        public synchronized Map<String, PropulsionSubsystem> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(PropulsionSubsystem.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, PropulsionSubsystem> types;
        }
    }
}
