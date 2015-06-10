DROP DATABASE IF EXISTS Forum;
CREATE DATABASE Forum;
USE Forum;

DROP TABLE IF EXISTS Account;
DROP TABLE IF EXISTS Rating;
DROP TABLE IF EXISTS Thread;
DROP TABLE IF EXISTS Comment;

CREATE TABLE Account (
       accountID INT NOT NULL AUTO_INCREMENT
	 , username VARCHAR(50) NOT NULl
	 , password VARCHAR(50) NOT NULL
	 , role VARCHAR(32) NOT NULL
	 , PRIMARY KEY (accountID)
);

CREATE TABLE Rating (
       ratingID INT NOT NULL AUTO_INCREMENT
	 , name VARCHAR(50) NOT NULl
	 , score DOUBLE NOT NULL
	 , PRIMARY KEY (ratingID)
);

CREATE TABLE Thread (
       threadID INT NOT NULL AUTO_INCREMENT
	 , name VARCHAR(50) NOT NULl
	 , isLock boolean
	 , PRIMARY KEY (threadID)
);

CREATE TABLE Comment (
       commentID INT NOT NULL AUTO_INCREMENT
	 , threadID INT NOT NULl
	 , accountID INT NOT NULL
	 , hide BOOLEAN
	 , content TEXT
	 , PRIMARY KEY (commentID)
	 , FOREIGN KEY (threadID) REFERENCES Thread(threadID)
	 , FOREIGN KEY (accountID) REFERENCES Account(accountID)
);