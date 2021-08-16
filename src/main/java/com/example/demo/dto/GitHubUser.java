package com.example.demo.dto;

import lombok.Data;

@Data
public class GitHubUser {
    private String login;
    private long id;
    private String node_id;
    private String avatar_url;
}
