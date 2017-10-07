/*
 * Copyright 2017 Hammock and its contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ws.ament.hammock.health;

import java.util.Map;

public class ObjectHealthCheckModel implements HealthCheckModel{
    private final String outcome;
    private final Map<String, HealthResultModel> checks;

    public ObjectHealthCheckModel(String outcome, Map<String, HealthResultModel> checks) {
        this.outcome = outcome;
        this.checks = checks;
    }

    @Override
    public String getOutcome() {
        return outcome;
    }

    public Map<String, HealthResultModel> getChecks() {
        return checks;
    }

    @Override
    public String toString() {
        return "ObjectHealthCheckModel{" + "outcome='" + outcome + '\'' +
                ", checks=" + checks +
                '}';
    }
}
