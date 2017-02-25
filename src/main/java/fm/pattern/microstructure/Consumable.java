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

import fm.pattern.commons.util.JSON;

public class Consumable {

    private final String code;
    private final String description;
    private final String property;

    public Consumable(String code, String description, String property) {
        this.code = code;
        this.description = description;
        this.property = property;
    }

    public Consumable(String code, String description) {
        this.code = code;
        this.description = description;
        this.property = null;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getProperty() {
        return property;
    }

    public String toString() {
        return JSON.stringify(this);
    }

}
