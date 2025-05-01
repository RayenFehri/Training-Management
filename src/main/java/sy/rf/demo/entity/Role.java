package sy.rf.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
  @JsonProperty("SIMPLE") SIMPLE,
    @JsonProperty("ADMIN") ADMIN,
    @JsonProperty("MANAGER") MANAGER
}
