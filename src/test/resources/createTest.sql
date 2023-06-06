

SELECT * FROM user;
CREATE TABLE IF NOT EXISTS user (
    userId INTEGER PRIMARY KEY,
    userName TEXT NOT NULL
);

SELECT * FROM normalBook;
CREATE TABLE IF NOT EXISTS normalBook (
    id INTEGER PRIMARY KEY,
    author TEXT NOT NULL,
    year INTEGER,
    language TEXT,
    numberOfHardCopies INTEGER,
    loanPeriod INTEGER,
    bookGenre TEXT,
    bookVersion TEXT
);

SELECT * FROM acousticBook;
CREATE TABLE IF NOT EXISTS acousticBook (
    id INTEGER PRIMARY KEY,
    author TEXT NOT NULL,
    year INTEGER,
    language TEXT,
    freeTrialPeriod INTEGER,
    bookGenre TEXT,
    bookVersion TEXT
);

SELECT * FROM loanRecord;
drop table loanRecord;
PRAGMA foreign_keys = ON;
CREATE TABLE IF NOT EXISTS loanRecord (
    userId INTEGER PRIMARY KEY,
    userName TEXT NOT NULL,
    bookId INTEGER NOT NULL,
    bookGenre TEXT,
    bookVersion TEXT,
    FOREIGN KEY (bookId)
        REFERENCES user(userId)
);
