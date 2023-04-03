package com.zhuwang.dao;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String gender;
    private String opinion;
    private String[] skills;
    private String ip;
    private String cnames;
    private String fileName;
    private String nfileName;
    private String fileUrl;
}
