package com.mjc.school.service.dto;


import com.mjc.school.service.aop.validator.restriction.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class TagDto {

    private Long id;
    @Size(min = 3, max = 15)
    private String name;
}
