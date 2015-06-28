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

INSERT INTO `AccountIcon` VALUES 
(1,'icon1'),
(2,'icon2'),
(3,'icon3'),
(4,'icon4');

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
(2,'admin','admin','admin','aking',2),
(3,'A.King','123456','admin','aking',2),
(4,'NhanNP','nhan','member','member',1),
(6,'QuyenNP','quyen','member','member',1),
(5,'HungHH','hung','member','member',1);


CREATE TABLE FriendShip (
	   friendShipID INT NOT NULL AUTO_INCREMENT
	 , accountID1 INT NOT NULL
	 , accountID2 INT NOT NULL
	 , PRIMARY KEY (friendShipID)
	 , FOREIGN KEY (accountID1) REFERENCES Account(accountID)
	 , FOREIGN KEY (accountID2) REFERENCES Account(accountID)
);

INSERT INTO `friendShip` VALUES 
(1,1,3),
(2,1,5),
(3,1,4),
(4,2,3),
(5,2,4),
(6,2,5),
(7,4,3),
(8,5,3),
(10,4,5),
(11,2,6),
(12,6,3),
(13,6,4),
(9,5,6);


CREATE TABLE Rating (
       ratingID INT NOT NULL AUTO_INCREMENT
	 , name VARCHAR(50) NOT NULL
	 , score DOUBLE NOT NULL
	 , icon VARCHAR(50) NOT NULL
	 , PRIMARY KEY (ratingID)
);

INSERT INTO `rating` VALUES 
(1,'AAA',1424,'icon1'),
(2,'BBB',342,'icon1'),
(3,'ABA',32453,'icon1'),
(5,'OKF',4353,'icon3'),
(6,'MMM',9654,'icon2'),
(7,'NHH',7432,'icon3'),
(8,'PIC',53332,'icon3'),
(9,'HVN',99999,'icon2'),
(10,'VJA',1241,'icon2');

CREATE TABLE Thread (
       threadID INT NOT NULL AUTO_INCREMENT
	 , accountID INT NOT NULL
	 , name TEXT NOT NULl
	 , isLock boolean
	 , lastUpdate datetime
	 , lastUpdateBy INT NOT NULL
	 , PRIMARY KEY (threadID)
	 , FOREIGN KEY (accountID) REFERENCES Account(accountID)
	 , FOREIGN KEY (lastUpdateBy) REFERENCES Account(accountID)
);

INSERT INTO `thread` VALUES 
(1,2,'Promotion of weapons in two days from 11.06.2015',1,'2015-06-12 00:00:00',2),
(2,2,'Event new idea for spaceship',1,'2015-06-12 00:00:00',1),
(3,2,'Help!!! How to kill the last boss',1,'2015-06-12 00:00:00',3),
(4,2,'Upgrade version 1.11.42 form 09.06.2015',0,'2015-06-12 00:00:00',2);


CREATE TABLE Comment (
       commentID INT NOT NULL AUTO_INCREMENT
	 , threadID INT NOT NULl
	 , accountID INT NOT NULL
	 , hide BOOLEAN
	 , content TEXT
	 , lastEdit datetime
	 , lastEditBy INT NOT NULL
	 , PRIMARY KEY (commentID)
	 , FOREIGN KEY (threadID) REFERENCES Thread(threadID)
	 , FOREIGN KEY (lastEditBy) REFERENCES Account(accountID)
	 , FOREIGN KEY (accountID) REFERENCES Account(accountID)
);

INSERT INTO `comment` VALUES 
(1,1,1,1,'122222','2015-06-12 00:00:00',1);