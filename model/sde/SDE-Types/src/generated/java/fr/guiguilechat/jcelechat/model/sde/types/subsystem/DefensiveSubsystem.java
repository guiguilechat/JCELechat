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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHPBonusAdd;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CargoCapacityAdd;
import fr.guiguilechat.jcelechat.model.sde.attributes.CloakingCpuNeedBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.CovertCloakCPUPenalty;
import fr.guiguilechat.jcelechat.model.sde.attributes.CovertOpsAndReconOpsCloakModuleDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.FitsToShipType;
import fr.guiguilechat.jcelechat.model.sde.attributes.HiSlotModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsBlackOpsJumpConduitPassenger;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsBlackOpsJumpPortalPassenger;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpHarmonicsModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.LowSlotModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MedSlotModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureHPBonusAdd;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubSystemSlot;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusAmarrDefensive;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusAmarrDefensive2;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusAmarrDefensive3;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusCaldariDefensive;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusCaldariDefensive2;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusCaldariDefensive3;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusGallenteDefensive;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusGallenteDefensive2;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusGallenteDefensive3;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusMassAddition;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusMinmatarDefensive;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusMinmatarDefensive2;
import fr.guiguilechat.jcelechat.model.sde.attributes.SubsystemBonusMinmatarDefensive3;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.VirusStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.types.Subsystem;
import org.yaml.snakeyaml.Yaml;

public class DefensiveSubsystem
    extends Subsystem
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int armorhpbonusadd;
    /**
     * Capacitor capacity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorcapacity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int cargocapacityadd;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cloakingcpuneedbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int covertcloakcpupenalty;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(30000)
    public int covertopsandreconopscloakmoduledelay;
    /**
     * Set this attribute on a ship to declare that the ship is an eligible passenger to be carried through a BlackOps Jump Conduit
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int isblackopsjumpconduitpassenger;
    /**
     * Set this attribute on a ship to declare that the ship is an eligible passenger to travel through a BlackOps Jump Portal
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int isblackopsjumpportalpassenger;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int jumpharmonicsmodifier;
    /**
     * Amount of maximum shield HP on the item.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldcapacity;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(100.0)
    public double signatureradius;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structurehpbonusadd;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double subsystembonusamarrdefensive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystembonusamarrdefensive2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double subsystembonusamarrdefensive3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double subsystembonuscaldaridefensive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystembonuscaldaridefensive2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double subsystembonuscaldaridefensive3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double subsystembonusgallentedefensive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystembonusgallentedefensive2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double subsystembonusgallentedefensive3;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystembonusmassaddition;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double subsystembonusminmatardefensive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int subsystembonusminmatardefensive2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double subsystembonusminmatardefensive3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int virusstrengthbonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {StructureHPBonusAdd.INSTANCE, SubsystemBonusMassAddition.INSTANCE, CargoCapacityAdd.INSTANCE, JumpHarmonicsModifier.INSTANCE, ShieldCapacity.INSTANCE, ArmorHPBonusAdd.INSTANCE, CloakingCpuNeedBonus.INSTANCE, Hp.INSTANCE, CovertOpsAndReconOpsCloakModuleDelay.INSTANCE, CovertCloakCPUPenalty.INSTANCE, RequiredSkill1Level.INSTANCE, SubSystemSlot.INSTANCE, SubsystemBonusAmarrDefensive.INSTANCE, SubsystemBonusGallenteDefensive.INSTANCE, HiSlotModifier.INSTANCE, MedSlotModifier.INSTANCE, LowSlotModifier.INSTANCE, CapacitorCapacity.INSTANCE, Radius.INSTANCE, SubsystemBonusAmarrDefensive2 .INSTANCE, SubsystemBonusCaldariDefensive.INSTANCE, FitsToShipType.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, SubsystemBonusMinmatarDefensive.INSTANCE, SubsystemBonusCaldariDefensive2 .INSTANCE, SubsystemBonusGallenteDefensive2 .INSTANCE, SubsystemBonusMinmatarDefensive2 .INSTANCE, RequiredSkill1 .INSTANCE, IsBlackOpsJumpPortalPassenger.INSTANCE, SubsystemBonusAmarrDefensive3 .INSTANCE, MetaLevelOld.INSTANCE, IsBlackOpsJumpConduitPassenger.INSTANCE, SubsystemBonusCaldariDefensive3 .INSTANCE, SubsystemBonusGallenteDefensive3 .INSTANCE, VirusStrengthBonus.INSTANCE, SubsystemBonusMinmatarDefensive3 .INSTANCE })));
    public static final DefensiveSubsystem.MetaGroup METAGROUP = new DefensiveSubsystem.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1159 :
            {
                return armorhpbonusadd;
            }
            case  482 :
            {
                return capacitorcapacity;
            }
            case  2689 :
            {
                return cargocapacityadd;
            }
            case  649 :
            {
                return cloakingcpuneedbonus;
            }
            case  1871 :
            {
                return covertcloakcpupenalty;
            }
            case  1034 :
            {
                return covertopsandreconopscloakmoduledelay;
            }
            case  3322 :
            {
                return isblackopsjumpconduitpassenger;
            }
            case  3320 :
            {
                return isblackopsjumpportalpassenger;
            }
            case  1541 :
            {
                return jumpharmonicsmodifier;
            }
            case  263 :
            {
                return shieldcapacity;
            }
            case  552 :
            {
                return signatureradius;
            }
            case  2688 :
            {
                return structurehpbonusadd;
            }
            case  1433 :
            {
                return subsystembonusamarrdefensive;
            }
            case  1507 :
            {
                return subsystembonusamarrdefensive2;
            }
            case  2680 :
            {
                return subsystembonusamarrdefensive3;
            }
            case  1443 :
            {
                return subsystembonuscaldaridefensive;
            }
            case  1516 :
            {
                return subsystembonuscaldaridefensive2;
            }
            case  2682 :
            {
                return subsystembonuscaldaridefensive3;
            }
            case  1438 :
            {
                return subsystembonusgallentedefensive;
            }
            case  1517 :
            {
                return subsystembonusgallentedefensive2;
            }
            case  2684 :
            {
                return subsystembonusgallentedefensive3;
            }
            case  3328 :
            {
                return subsystembonusmassaddition;
            }
            case  1448 :
            {
                return subsystembonusminmatardefensive;
            }
            case  1526 :
            {
                return subsystembonusminmatardefensive2;
            }
            case  2686 :
            {
                return subsystembonusminmatardefensive3;
            }
            case  1918 :
            {
                return virusstrengthbonus;
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
    public IMetaGroup<DefensiveSubsystem> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DefensiveSubsystem>
    {
        public static final String RESOURCE_PATH = "SDE/types/subsystem/DefensiveSubsystem.yaml";
        private Map<String, DefensiveSubsystem> cache = (null);

        @Override
        public IMetaCategory<? super DefensiveSubsystem> category() {
            return Subsystem.METACAT;
        }

        @Override
        public int getGroupId() {
            return  954;
        }

        @Override
        public String getName() {
            return "DefensiveSubsystem";
        }

        @Override
        public synchronized Map<String, DefensiveSubsystem> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(DefensiveSubsystem.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DefensiveSubsystem> types;
        }
    }
}
