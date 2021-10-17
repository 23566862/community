package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/*通知类*/
public class notification {
    private Integer id;
    private  Integer notifier;
    private  Integer receiver;
    private  Integer outerid;
    private  int type;
    private  Long gmt_create;
    private  int status;
}
