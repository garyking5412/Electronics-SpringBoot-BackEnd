package com.bkap.DTOs;

import lombok.Data;

@Data
public class MessageDTO {
    private int id;
    private String to;
    private String toName;
    private String content;
}
