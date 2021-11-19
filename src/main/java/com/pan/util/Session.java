package com.pan.util;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 标识当前会话
 */
@Data
@NoArgsConstructor
public class Session {

    private String uId;
    private String username;

    public Session(String uId, String username) {
        this.uId = uId;
        this.username = username;
    }

    @Override
    public String toString() {
        return uId + ":" + username;
    }

}
