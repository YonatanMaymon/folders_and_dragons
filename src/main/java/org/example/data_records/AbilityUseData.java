package org.example.data_records;

import java.util.Map;

public record AbilityUseData(String name, Map<String, Integer> damageMap) {
}
