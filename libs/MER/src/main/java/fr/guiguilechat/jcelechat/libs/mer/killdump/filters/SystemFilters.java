package fr.guiguilechat.jcelechat.libs.mer.killdump.filters;

import java.util.function.Predicate;

import fr.guiguilechat.jcelechat.libs.mer.killdump.KDParser.KDEntry;

public class SystemFilters {

	public static final Predicate<KDEntry> AS = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return kde.getSolarSystem() != null && kde.getSolarSystem().isAbyssal;
		}

		@Override
		public String toString() {
			return "AS";
		}
	};

	public static final Predicate<KDEntry> AS_TYPED = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return kde.getSolarSystem() != null && kde.getSolarSystem().isAbyssal && kde.getType() != null;
		}

		@Override
		public String toString() {
			return "AST";
		}
	};

	public static final Predicate<KDEntry> HS = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return kde.getSolarSystem() != null && kde.getSolarSystem().isKS && kde.getSolarSystem().isHS();
		}

		@Override
		public String toString() {
			return "HS";
		}
	};

	public static final Predicate<KDEntry> HS_TYPED = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return kde.getSolarSystem() != null && kde.getSolarSystem().isKS && kde.getSolarSystem().isHS()
					&& kde.getType() != null;
		}

		@Override
		public String toString() {
			return "HST";
		}
	};

	public static final Predicate<KDEntry> LS = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return kde.getSolarSystem() != null && kde.getSolarSystem().isKS && kde.getSolarSystem().isLS();
		}

		@Override
		public String toString() {
			return "LS";
		}
	};
	public static final Predicate<KDEntry> LS_TYPED = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return kde.getSolarSystem() != null && kde.getSolarSystem().isKS && kde.getSolarSystem().isLS()
					&& kde.getType() != null;
		}

		@Override
		public String toString() {
			return "LST";
		}
	};

	public static final Predicate<KDEntry> NS = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return kde.getSolarSystem() != null && kde.getSolarSystem().isKS && kde.getSolarSystem().isNS();
		}

		@Override
		public String toString() {
			return "NS";
		}
	};

	public static final Predicate<KDEntry> NS_TYPED = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return kde.getSolarSystem() != null && kde.getSolarSystem().isKS && kde.getSolarSystem().isNS()
					&& kde.getType() != null;
		}

		@Override
		public String toString() {
			return "NST";
		}
	};

	public static final Predicate<KDEntry> WS = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return kde.getSolarSystem() != null && kde.getSolarSystem().isWormhole;
		}

		@Override
		public String toString() {
			return "WS";
		}
	};

	public static final Predicate<KDEntry> WS_TYPED = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return kde.getSolarSystem() != null && kde.getSolarSystem().isWormhole && kde.getType() != null;
		}

		@Override
		public String toString() {
			return "WST";
		}
	};
}
