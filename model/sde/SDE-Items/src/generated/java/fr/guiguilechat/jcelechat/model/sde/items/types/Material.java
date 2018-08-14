package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.AbyssalMaterials;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.AncientSalvage;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.BiochemicalMaterial;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.Composite;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.FuelBlock;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.HybridPolymers;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.IceProduct;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.IntermediateMaterials;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.Mineral;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.MoonMaterials;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.NamedComponents;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.RogueDroneComponents;
import fr.guiguilechat.jcelechat.model.sde.items.types.material.SalvagedMaterials;

public abstract class Material
    extends Item
{
    public final static Material.MetaCat METACAT = new Material.MetaCat();

    @Override
    public IMetaCategory<Material> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Material>
    {

        @Override
        public int getCategoryId() {
            return  4;
        }

        @Override
        public String getName() {
            return "Material";
        }

        @Override
        public Collection<IMetaGroup<? extends Material>> groups() {
            return Arrays.asList(Mineral.METAGROUP, IceProduct.METAGROUP, MoonMaterials.METAGROUP, IntermediateMaterials.METAGROUP, Composite.METAGROUP, BiochemicalMaterial.METAGROUP, SalvagedMaterials.METAGROUP, RogueDroneComponents.METAGROUP, AncientSalvage.METAGROUP, HybridPolymers.METAGROUP, FuelBlock.METAGROUP, NamedComponents.METAGROUP, AbyssalMaterials.METAGROUP);
        }
    }
}
