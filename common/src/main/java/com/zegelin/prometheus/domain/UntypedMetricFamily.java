package com.zegelin.prometheus.domain;

import java.util.Objects;
import java.util.stream.Stream;

public class UntypedMetricFamily extends MetricFamily<UntypedMetricFamily.Untyped> {
    public UntypedMetricFamily(final String name, final String help, final Stream<Untyped> metrics) {
        super(name, help, metrics);
    }

    @Override
    public <R> R accept(final MetricFamilyVisitor<R> visitor) {
        return visitor.visit(this);
    }

    public static class Untyped extends NumericMetric {
        public final String name;

        public Untyped(final Labels labels, final String name, final Float value) {
            super(labels, value);

            this.name = name;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof Untyped)) return false;
            if (!super.equals(o)) return false;

            final Untyped untyped = (Untyped) o;
            return Objects.equals(name, untyped.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), name);
        }
    }
}
