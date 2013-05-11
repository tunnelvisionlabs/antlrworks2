/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.semantics;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.annotations.common.NullAllowed;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 * @param <T>
 */
public class ObjectDecorator<T> {
    private final Map<T, Map<ObjectProperty<?>, Object>> properties;

    public ObjectDecorator() {
        properties = new HashMap<>();
    }

    public ObjectDecorator(@NonNull Map<T, Map<ObjectProperty<?>, Object>> properties) {
        this.properties = properties;
    }

    @NonNull
    public Map<? extends T, ? extends Map<? extends ObjectProperty<?>, ? extends Object>> getProperties() {
        return properties;
    }

    public void clear() {
        properties.clear();
    }

    @CheckForNull
    public <V> V getProperty(@NonNull T tree, @NonNull ObjectProperty<? extends V> property) {
        Parameters.notNull("tree", tree);
        Parameters.notNull("property", property);

        Map<ObjectProperty<?>, Object> nodeProperties = properties.get(tree);
        if (nodeProperties == null) {
            return property.getDefaultValue();
        }

        @SuppressWarnings("unchecked")
        V result = (V)nodeProperties.get(property);
        if (result == null) {
            return property.getDefaultValue();
        }

        return result;
    }

    @CheckForNull
    public <V> V putProperty(@NonNull T tree, @NonNull ObjectProperty<V> property, @NullAllowed V value) {
        Parameters.notNull("tree", tree);
        Parameters.notNull("property", property);

        Map<ObjectProperty<?>, Object> nodeProperties = properties.get(tree);
        if (nodeProperties == null) {
            nodeProperties = new HashMap<>();
            properties.put(tree, nodeProperties);
        }

        @SuppressWarnings("unchecked")
        V previous = (V)nodeProperties.put(property, value);
        if (previous == null) {
            return property.getDefaultValue();
        }

        return previous;
    }

    @NonNull
    public Map<? extends ObjectProperty<?>, ? extends Object> getProperties(@NonNull T tree) {
        Parameters.notNull("tree", tree);

        Map<ObjectProperty<?>, Object> nodeProperties = this.properties.get(tree);
        if (nodeProperties == null) {
            return Collections.emptyMap();
        }

        return nodeProperties;
    }

    public void putProperties(@NonNull T tree, @NonNull Map<? extends ObjectProperty<?>, ? extends Object> properties) {
        Parameters.notNull("tree", tree);
        Parameters.notNull("properties", properties);

        Map<ObjectProperty<?>, Object> nodeProperties = this.properties.get(tree);
        if (nodeProperties == null) {
            nodeProperties = new HashMap<>(properties);
            this.properties.put(tree, nodeProperties);
        } else {
            nodeProperties.putAll(properties);
        }
    }

    @CheckForNull
    public Map<? extends ObjectProperty<?>, ? extends Object> removeProperties(@NonNull T tree) {
        Parameters.notNull("tree", tree);

        return properties.remove(tree);
    }

}
