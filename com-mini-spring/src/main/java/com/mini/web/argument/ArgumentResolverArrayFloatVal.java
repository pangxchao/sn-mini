package com.mini.web.argument;

import com.mini.web.interceptor.ActionInvocation;

import javax.annotation.Nonnull;
import javax.inject.Named;
import javax.inject.Singleton;

import static com.mini.util.TypeUtil.castToFloatVal;

@Named
@Singleton
public final class ArgumentResolverArrayFloatVal extends ArgumentResolverArray {

    protected Object parse(@Nonnull String[] values, @Nonnull Class<?> type, @Nonnull ActionInvocation invocation) {
        float[] result = new float[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = castToFloatVal(values[i]);
        }
        return result;
    }
}