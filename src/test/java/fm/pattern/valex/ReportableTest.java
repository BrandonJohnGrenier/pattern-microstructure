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

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import fm.pattern.valex.config.ValexConfiguration;
import fm.pattern.valex.config.YamlConfigurationFile;

public class ReportableTest {

    @Before
    public void before() {
        ValexConfiguration.use(new YamlConfigurationFile());
    }

    @Test
    public void shouldBeAbleToConstructAReportable() {
        Reportable reportable = new Reportable("bad.request");
        assertThat(reportable.getCode()).isEqualTo("REQ-1000");
        assertThat(reportable.getMessage()).isEqualTo("Bad Request");
        assertThat(reportable.getException()).isEqualTo(BadRequestException.class);
    }

    @Test
    public void shouldBeAbleToLookupAndFormatAMessageWithArguments() {
        String message = new Reportable("username.length", "smithers", 5).getMessage();
        assertThat(message).isEqualTo("The username smithers cannot be greater than 5 characters.");
    }

    @Test
    public void shouldBeAbleToConstructAReportableWithAFormattableString() {
        Reportable reportable = Reportable.report("bad.request", "explicit message");
        assertThat(reportable.getCode()).isEqualTo("REQ-1000");
        assertThat(reportable.getMessage()).isEqualTo("explicit message");
    }

}
