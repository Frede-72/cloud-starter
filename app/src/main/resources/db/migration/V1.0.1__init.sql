CREATE TABLE books
(
    id    BIGINT       NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    isbn  CHAR(13)     NOT NULL,
    CONSTRAINT pk_books PRIMARY KEY (id),
    CONSTRAINT uc_books_isbn UNIQUE (isbn)
);