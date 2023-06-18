package com.mjc.school.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewsParamsDto {

    String authorName;
    String title;
    String content;
    Set<Long> tagIds;
    List<String> tagNames;
}
