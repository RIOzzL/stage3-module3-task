package com.mjc.school.repository.model.params;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Scope("prototype")
public class NewsParams {

    private String authorName;
    private String title;
    private String content;
    private List<String> tagNames;
    private Set<Long> tagIds;
}
