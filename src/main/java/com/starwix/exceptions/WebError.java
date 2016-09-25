package com.starwix.exceptions;

import java.util.Map;

/**
 * Created by starwix on 25.9.16.
 */
public interface WebError {
    String getError();
    Map<String, Object> getArgs();
}
