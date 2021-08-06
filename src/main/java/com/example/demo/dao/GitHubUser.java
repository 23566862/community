package com.example.demo.dao;

import lombok.Data;

@Data
public class GitHubUser {
    private String login;
    private long id;
    private String node_id;
}
