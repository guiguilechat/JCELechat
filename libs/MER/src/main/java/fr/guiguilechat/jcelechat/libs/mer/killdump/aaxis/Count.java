package fr.guiguilechat.jcelechat.libs.mer.killdump.aaxis;

import java.util.Collection;
import java.util.function.ToDoubleBiFunction;

import fr.guiguilechat.jcelechat.libs.mer.killdump.KDEntry;

public class Count {

	public static final ToDoubleBiFunction<Collection<KDEntry>, Collection<KDEntry>> TOTAL = new ToDoubleBiFunction<>() {

		@Override
		public double applyAsDouble(Collection<KDEntry> aggregated, Collection<KDEntry> global) {
			return global.size();
		}

		@Override
		public String toString() {
			return "total";
		}
	};

	public static final ToDoubleBiFunction<Collection<KDEntry>, Collection<KDEntry>> AGG = new ToDoubleBiFunction<>() {

		@Override
		public double applyAsDouble(Collection<KDEntry> aggregated, Collection<KDEntry> global) {
			return aggregated.size();
		}

		@Override
		public String toString() {
			return "qtty";
		}
	};

	public static final ToDoubleBiFunction<Collection<KDEntry>, Collection<KDEntry>> PCT = new ToDoubleBiFunction<>() {

		@Override
		public double applyAsDouble(Collection<KDEntry> aggregated, Collection<KDEntry> global) {
			return 100.0 * aggregated.size() / global.size();
		}

		@Override
		public String toString() {
			return "pct";
		}
	};

}
