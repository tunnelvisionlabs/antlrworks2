/*
 *  Copyright (c) 2012 Sam Harwell, Tunnel Vision Laboratories LLC
 *  All rights reserved.
 *
 *  The source code of this document is proprietary work, and is not licensed for
 *  distribution. For information about licensing, contact Sam Harwell at:
 *      sam@tunnelvisionlabs.com
 */
package org.antlr.netbeans.editor.formatting;

import java.util.prefs.Preferences;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.Parameters;

/**
 *
 * @author Sam Harwell
 * @param <E>
 */
public class EnumFormatOption<E extends Enum<E>> extends AbstractFormatOption {

    @NonNull
    private final Class<E> _enumClass;
    @NonNull
    private final E _defaultValue;

    public EnumFormatOption(@NonNull String name, @NonNull Class<E> enumClass, @NonNull E defaultValue) {
        super(name);
        Parameters.notNull("enumClass", enumClass);
        Parameters.notNull("defaultValue", defaultValue);

        this._enumClass = enumClass;
        this._defaultValue = defaultValue;
    }

    @NonNull
    public Class<E> getEnumClass() {
        return _enumClass;
    }

    @NonNull
    public E getDefaultValue() {
        return _defaultValue;
    }

    @NonNull
    public E getValue(Preferences preferences) {
        String value = preferences.get(getName(), getDefaultValueAsString());
        E typedValue;
        try {
            typedValue = Enum.valueOf(getEnumClass(), value);
        } catch (IllegalArgumentException ex) {
            typedValue = getDefaultValue();
        }

        return typedValue;
    }

    @Override
    @NonNull
    public String getDefaultValueAsString() {
        return getDefaultValue().name();
    }

    @Override
    @NonNull
    public String getValueAsString(Preferences preferences) {
        return getValue(preferences).name();
    }

}
