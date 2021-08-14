package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class user {
    private Integer id;
    private String accountid;
    private String name;
    private String token;
    private  long gmtcreate;
    private long gmtmodified;

    public user(String accountid, String mame, String token, long gmtcreate, long gmtmodified) {

        this.accountid = accountid;
        this.name = mame;
        this.token = token;
        this.gmtcreate = gmtcreate;
        this.gmtmodified = gmtmodified;
    }

    public user() {
    }
}
