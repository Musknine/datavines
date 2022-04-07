/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.datavines.metric.expected;

import io.datavines.metric.api.ExpectedValue;

import java.util.Map;

public class SrcTableTotalRows implements ExpectedValue {

    private StringBuilder sql =
            new StringBuilder("select count(*) as expected_value from ${src_table}");

    @Override
    public String getName() {
        return "expected_value";
    }

    @Override
    public String getType() {
        return "src_table_total_rows";
    }

    @Override
    public String getExecuteSql() {
        return sql.toString();
    }

    @Override
    public String getOutputTable() {
        return "total_count";
    }

    @Override
    public boolean isNeedDefaultDatasource() {
        return false;
    }

    @Override
    public void prepare(Map<String, String> config) {
        if (config.containsKey("src_filter")) {
            if (sql.toString().contains("where")) {
                sql.append(" and ").append(config.get("src_filter"));
            } else {
                sql.append(" where ").append(config.get("src_filter"));
            }
        }
    }
}
