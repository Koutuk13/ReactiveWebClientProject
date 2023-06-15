package com.example.restservice1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutstandingRID {
    private String outstadingRID;
    private String parentOutstadingRID;
    private String error;
}
