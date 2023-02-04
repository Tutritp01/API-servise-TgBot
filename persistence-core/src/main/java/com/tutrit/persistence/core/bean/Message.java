package com.tutrit.persistence.core.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String id;
    private String eventText;
    private LocalDateTime timestamp;
    private LocalDateTime startDate;
    private User author;
    private MessageStatus status;

}
