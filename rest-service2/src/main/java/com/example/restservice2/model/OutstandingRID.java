package com.example.restservice2.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutstandingRID {

    private String outstadingRID;
    private String parentOutstadingRID;
    private String error;
}
