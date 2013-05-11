/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.semantics;

import org.netbeans.api.annotations.common.CheckForNull;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 * @param <T>
 */
public class ObjectProperty<T> {

    private final String name;
    private final T defaultValue;

    public ObjectProperty(@NonNull String name) {
        this(name, null);
    }

    public ObjectProperty(@NonNull String name, T defaultValue) {
        Parameters.notNull("name", name);
        this.name = name;
        this.defaultValue = defaultValue;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @CheckForNull
    public T getDefaultValue() {
        return defaultValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ObjectProperty<?>)) {
            return false;
        }

        ObjectProperty<?> other = (ObjectProperty<?>)obj;
        return this.getName().equals(other.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        if (defaultValue != null) {
            return name + "(" + defaultValue + ")";
        }

        return name;
    }

}
