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

package fm.pattern.validation;

import org.apache.commons.lang3.StringUtils;

import fm.pattern.commons.util.JSON;
import fm.pattern.validation.repository.ValidationRepositoryFactory;

@SuppressWarnings("unchecked")
public class Reportable {

    private final String code;
    private final String message;
    private final Class<? extends ReportableException> exception;

    public static Reportable report(String keyOrMessage, Object... arguments) {
        return new Reportable(getCode(keyOrMessage), getMessage(keyOrMessage), getException(keyOrMessage), arguments);
    }

    static Reportable report_interpolated(String key, String message, Object... arguments) {
        return new Reportable(getCode(key), message, getException(key), arguments);
    }

    public Reportable(String code, String message, Class<? extends ReportableException> exception, Object... arguments) {
        this.code = code;
        this.message = String.format(message, arguments);
        this.exception = exception;
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
        String code = ValidationRepositoryFactory.getRepository().getCode(key);
        return StringUtils.isEmpty(code) ? key : code;
    }

    private static String getMessage(String input) {
        String message = ValidationRepositoryFactory.getRepository().getMessage(input);
        return StringUtils.isEmpty(message) ? input : message;
    }

    private static Class<? extends ReportableException> getException(String key) {
        String className = ValidationRepositoryFactory.getRepository().getException(key);
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
