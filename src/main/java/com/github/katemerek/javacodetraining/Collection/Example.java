package com.github.katemerek.javacodetraining.Collection;

import lombok.Data;

@Data
public class Example implements Filter<Object> {

    @Override
    public Object apply(Object o) {
        return o.hashCode();
    }
}
