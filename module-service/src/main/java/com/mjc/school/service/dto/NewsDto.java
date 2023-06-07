package com.mjc.school.service.dto;

import com.mjc.school.service.validator.restriction.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NewsDto {
    private Long id;
    @Size(min = 5, max = 30)
    private String title;
    @Size(min = 5, max = 255)
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime lastUpdatedDate;

    private Long authorId;
}
