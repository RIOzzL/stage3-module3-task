CREATE TABLE IF NOT EXISTS news
(
    id               BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title            VARCHAR(30)  NOT NULL,
    content          VARCHAR(255) NOT NULL,
    create_date      TIMESTAMP,
    last_update_date TIMESTAMP,
    author_id        BIGINT       REFERENCES authors (id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS authors
(
    id               BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name             VARCHAR(15) NOT NULL,
    create_date      TIMESTAMP,
    last_update_date TIMESTAMP
);


CREATE TABLE IF NOT EXISTS tags
(
    id   BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS news_tags
(
    news_id BIGINT REFERENCES news (id),
    tag_id  BIGINT REFERENCES tags (id),
    PRIMARY KEY (news_id, tag_id)
);
