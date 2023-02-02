package com.tutrit.persistence.core.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promo {
    private String id;
    private int discount;
    private String namePromo;
    private LocalDateTime timeStamp;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private User author;
    private PromoStatus status;

}
