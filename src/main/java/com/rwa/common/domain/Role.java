package com.rwa.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.rwa.common.domain.Permission.*;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN(Arrays.asList(USER_W, DOC_W, COMPLAINT_W, EVENT_W, SURVEY_W, FINANCE_W)),
    PRESIDENT(Arrays.asList(USER_W, DOC_W, COMPLAINT_W, EVENT_W, SURVEY_W, FINANCE_R)),
    SECRETARY(Arrays.asList(USER_W, DOC_W, COMPLAINT_W, EVENT_W, SURVEY_W, FINANCE_R)),
    TREASURER(Arrays.asList(USER_R, DOC_R, COMPLAINT_R, EVENT_R, SURVEY_R, FINANCE_W)),
    MEMBER(Arrays.asList(USER_R, DOC_R, COMPLAINT_R, EVENT_R, SURVEY_R, FINANCE_R)),
    BASIC(Collections.EMPTY_LIST);

    List<Permission> permissions;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
