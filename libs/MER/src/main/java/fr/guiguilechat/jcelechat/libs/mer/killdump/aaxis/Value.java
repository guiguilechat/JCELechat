package fr.guiguilechat.jcelechat.libs.mer.killdump.aaxis;

import java.util.Collection;
import java.util.function.ToDoubleBiFunction;

import fr.guiguilechat.jcelechat.libs.mer.killdump.KDParser.KDEntry;

public class Value {

	public static final ToDoubleBiFunction<Collection<KDEntry>, Collection<KDEntry>> AVG = new ToDoubleBiFunction<>() {

		@Override
		public double applyAsDouble(Collection<KDEntry> aggregated, Collection<KDEntry> global) {
			return aggregated.stream().mapToDouble(kde -> kde.iskLost()).average().orElse(0.0) / 1000000;
		}

		@Override
		public String toString() {
			return "avgValue";
		}
	};

	public static final ToDoubleBiFunction<Collection<KDEntry>, Collection<KDEntry>> MAX = new ToDoubleBiFunction<>() {

		@Override
		public double applyAsDouble(Collection<KDEntry> aggregated, Collection<KDEntry> global) {
			return aggregated.stream().mapToDouble(kde -> kde.iskLost()).max().orElse(0.0) / 1000000;
		}

		@Override
		public String toString() {
			return "maxValue";
		}
	};

	public static final ToDoubleBiFunction<Collection<KDEntry>, Collection<KDEntry>> MIN = new ToDoubleBiFunction<>() {

		@Override
		public double applyAsDouble(Collection<KDEntry> aggregated, Collection<KDEntry> global) {
			return aggregated.stream().mapToDouble(kde -> kde.iskLost()).min().orElse(0.0) / 1000000;
		}

		@Override
		public String toString() {
			return "minValue";
		}
	};

	public static final ToDoubleBiFunction<Collection<KDEntry>, Collection<KDEntry>> PCT = new ToDoubleBiFunction<>() {

		@Override
		public double applyAsDouble(Collection<KDEntry> aggregated, Collection<KDEntry> global) {
			return 100.0 * aggregated.stream().mapToDouble(kde -> kde.iskLost()).sum()
					/ global.stream().mapToDouble(kde -> kde.iskLost()).sum();
		}

		@Override
		public String toString() {
			return "pctValue";
		}
	};

	public static final ToDoubleBiFunction<Collection<KDEntry>, Collection<KDEntry>> SUM = new ToDoubleBiFunction<>() {

		@Override
		public double applyAsDouble(Collection<KDEntry> aggregated, Collection<KDEntry> global) {
			return aggregated.stream().mapToDouble(kde -> kde.iskLost()).sum() / 1000000;
		}

		@Override
		public String toString() {
			return "sumValue";
		}
	};

}
