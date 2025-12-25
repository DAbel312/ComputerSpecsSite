package de.computerspecs.backend.dto;

import java.util.Date;

public record ArticleCreateDTO(
    String title,
    String content,
    Date date,
    String author,
    Long imageId
) {}
