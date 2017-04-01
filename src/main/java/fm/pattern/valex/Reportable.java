/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fm.pattern.valex;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import fm.pattern.commons.util.JSON;
import fm.pattern.valex.config.ValexConfiguration;

@SuppressWarnings("unchecked")
public class Reportable {

    private final String code;
    private final String message;
    private final Map<String, String> properties = new HashMap<>();

    private final Class<? extends ReportableException> exception;

    public Reportable(String key, Object... arguments) {
        this(key, getMessage(key), arguments);
    }

    private Reportable(String key, String message, Object... arguments) {
        this.code = getCode(key);
        this.message = String.format(message, arguments);
        this.exception = getException(key);
    }

    public static Reportable report(String key, String message) {
        return new Reportable(key, message);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Class<? extends ReportableException> getException() {
        return exception;
    }

    private static String getCode(String key) {
        String code = ValexConfiguration.getCode(key);
        return StringUtils.isBlank(code) ? key : code;
    }

    private static String getMessage(String key) {
        String message = ValexConfiguration.getMessage(key);
        return StringUtils.isBlank(message) ? key : message;
    }

    private static Class<? extends ReportableException> getException(String key) {
        String className = ValexConfiguration.getException(key);
        if (StringUtils.isBlank(className)) {
            return null;
        }

        try {
            return (Class<? extends ReportableException>) Class.forName(className);
        }
        catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String toString() {
        return JSON.stringify(this);
    }

}
