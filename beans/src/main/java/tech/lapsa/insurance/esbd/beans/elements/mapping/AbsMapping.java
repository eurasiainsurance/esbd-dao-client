package com.lapsa.insurance.esbd.services.impl.elements.mapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbsMapping<T, E> {

    private final Map<T, E> mapping = new HashMap<>();
    private final Set<T> exceptions = new HashSet<>(0);

    protected final void addMap(E entity, T id) {
	if (mapping.containsKey(id))
	    throw new RuntimeException(String.format("Already has mapping for ID = '%s'", id));
	mapping.put(id, entity);
    }

    protected final void addException(T id) {
	if (exceptions.contains(id))
	    throw new RuntimeException(String.format("Already has exception for ID = '%s'", id));
	exceptions.add(id);
    }

    public final E forId(T id) {
	return mapping.get(id);
    }

    public final boolean isException(T id) {
	return exceptions.contains(id);
    }

    public final Set<T> getAllIds() {
	return mapping.keySet();
    }
}
