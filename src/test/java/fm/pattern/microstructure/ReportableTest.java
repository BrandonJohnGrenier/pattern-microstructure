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

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.junit.Test;

public class ReportableTest {

    @Test
    public void shouldBeAbleToConstructAReportable() {
        Reportable reportable = new Reportable("code", "message", UnprocessableEntityException.class);
        assertThat(reportable.getCode()).isEqualTo("code");
        assertThat(reportable.getMessage()).isEqualTo("message");
    }

    @Test
    public void shouldBeAbleToConstructAReportableWithAFormattableString() {
        Reportable reportable = new Reportable("code", "message %s", UnprocessableEntityException.class, "content");
        assertThat(reportable.getCode()).isEqualTo("code");
        assertThat(reportable.getMessage()).isEqualTo("message content");
    }
    
    @Test
    public void shouldBeAbleToFormatAMessageWithArguments() {
        String message = Reportable.report("The username %s cannot be greater than %d characters.", "smithers", 5).getMessage();
        assertThat(message).isEqualTo("The username smithers cannot be greater than 5 characters.");
    }

    @Test
    public void shouldBeAbleToLookupAndFormatAMessageWithArguments() {
        String message = Reportable.report("username.length", "smithers", 5).getMessage();
        assertThat(message).isEqualTo("The username smithers cannot be greater than 5 characters.");
    }

    @Test
    public void shouldBeAbleToLookupACodeAndMessageByKey() {
        Reportable reportable = Reportable.report("contact.name.required");
        assertThat(reportable.getMessage()).isEqualTo("A contact name is required.");
        assertThat(reportable.getCode()).isEqualTo("CON-1000");
    }

    @Test
    public void shouldBeAbleToLookupACodeAndMessageByKeyAndSubstituteValues() {
        Reportable reportable = Reportable.report("username.length", "jim", 2);
        assertThat(reportable.getCode()).isEqualTo("LOC-NTFD");
        assertThat(reportable.getMessage()).isEqualTo("The username jim cannot be greater than 2 characters.");
    }

}
