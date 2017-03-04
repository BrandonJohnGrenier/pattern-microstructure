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

package fm.pattern.microstructure;

import org.apache.commons.lang3.StringUtils;

import fm.pattern.commons.util.JSON;

public class Reportable {

    private final String code;
    private final String message;

    public static Reportable report(String keyOrMessage, Object... arguments) {
        return new Reportable(ValidationMessages.getCode(keyOrMessage), resolve(keyOrMessage), arguments);
    }

    public Reportable(String code, String message, Object... arguments) {
        this.code = code;
        this.message = String.format(message, arguments);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private static String resolve(String input) {
        String message = ValidationMessages.getMessage(input);
        return StringUtils.isEmpty(message) ? input : message;
    }

    public String toString() {
        return JSON.stringify(this);
    }

}