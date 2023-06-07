package com.mjc.school.repository.util;

import com.mjc.school.repository.model.entity.Author;
import com.mjc.school.repository.model.entity.News;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataSource {

    //private static DataSource dataSource;

    private final static String NEWS = "news";
    private final static String CONTENT = "content";
    private final static String AUTHORS = "authors";
    private final List<News> newsList;
    private final List<Author> authorList;

    private Long newsId;
    private Long authorId;

    public DataSource() {
        newsList = new ArrayList<>();
        authorList = new ArrayList<>();
        initAuthors();
        initNews();
    }


//    public static DataSource getInstance() {
//        if (dataSource == null) {
//            dataSource = new DataSource();
//            return dataSource;
//        }
//        return dataSource;
//    }

    private void initNews() {
        List<String> newsTitles = FileResourcesUtils.getDataFromResourceFiles(NEWS);
        List<String> newsContents = FileResourcesUtils.getDataFromResourceFiles(CONTENT);
        for (int id = 1; id <= 20; id++) {
            newsList.add(new News((long) id, newsTitles.get(id - 1), newsContents.get(id - 1),
                    LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                    LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), authorList.get(id - 1).getId()));
        }
        newsId = (long) newsList.size();
    }

    private void initAuthors() {
        List<String> newsAuthors = FileResourcesUtils.getDataFromResourceFiles(AUTHORS);
        for (int id = 1; id <= 20; id++) {
            authorList.add(new Author((long) id, newsAuthors.get(id - 1).trim(),
                    LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                    LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)));
        }
        authorId = (long) authorList.size();
    }

    public Long increaseNewsId() {
        return ++newsId;
    }

    public Long increaseAuthorId() {
        return ++authorId;
    }

    public List<News> getNewsList() {
        return this.newsList;
    }

    public List<Author> getAuthorList() {
        return this.authorList;
    }

    public Long getNewsId() {
        return this.newsId;
    }

    public Long getAuthorId() {
        return this.authorId;
    }
}
