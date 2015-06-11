DROP DATABASE IF EXISTS Forum;
CREATE DATABASE Forum;
USE Forum;

DROP TABLE IF EXISTS Account;
DROP TABLE IF EXISTS Rating;
DROP TABLE IF EXISTS Thread;
DROP TABLE IF EXISTS AccountIcon;
DROP TABLE IF EXISTS FriendShip;
DROP TABLE IF EXISTS Comment;

CREATE TABLE AccountIcon (
	   accIconID INT NOT NULL AUTO_INCREMENT
	 , icon VARCHAR(50) NOT NULL
	 , PRIMARY KEY (accIconID)
);

INSERT INTO `accounticon` VALUES 
(1,'icon1'),
(2,'icon2'),
(3,'icon3');

CREATE TABLE Account (
       accountID INT NOT NULL AUTO_INCREMENT
	 , username VARCHAR(50) NOT null
	 , passwords VARCHAR(50) NOT NULL
	 , role VARCHAR(32) NOT NULL
	 , avatarImg VARCHAR(50) NOT null
	 , accIconID INT NOT NULL
	 , PRIMARY KEY (accountID)
	 , FOREIGN KEY (accIconID) REFERENCES AccountIcon(accIconID)
);

INSERT INTO `account` VALUES 
(1,'member','member','member','member',1),
(2,'admin','admin','admin','member',1);

CREATE TABLE FriendShip (
	   friendShipID INT NOT NULL AUTO_INCREMENT
	 , accountID INT NOT NULL
	 , friends TEXT
	 , PRIMARY KEY (friendShipID)
	 , FOREIGN KEY (accountID) REFERENCES Account(accountID)
);

CREATE TABLE Rating (
       ratingID INT NOT NULL AUTO_INCREMENT
	 , name VARCHAR(50) NOT NULl
	 , score DOUBLE NOT NULL
	 , PRIMARY KEY (ratingID)
);

INSERT INTO `rating` VALUES 
(1,'AAA',1424),
(2,'BBB',342),
(3,'ABA',32453),
(5,'OKF',4353),
(6,'MMM',9654),
(7,'NHH',7432),
(8,'PIC',53332),
(9,'HVN',99999),
(10,'VJA',1241);

CREATE TABLE Thread (
       threadID INT NOT NULL AUTO_INCREMENT
	 , accountID INT NOT NULL
	 , name TEXT NOT NULl
	 , isLock boolean
	 , PRIMARY KEY (threadID)
	 , FOREIGN KEY (accountID) REFERENCES Account(accountID)
);

INSERT INTO `thread` VALUES 
(1,2,'Promotion of weapons in two days from 11.06.2015',1),
(2,2,'Upgrade version 1.11.42 form 09.06.2015',0);


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