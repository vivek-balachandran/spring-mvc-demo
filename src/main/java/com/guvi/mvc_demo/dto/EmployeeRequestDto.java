package com.guvi.mvc_demo.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EmployeeRequestDto {
    private String name;
    private String department;
}
