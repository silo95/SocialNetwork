DROP DATABASE IF EXISTS SocialNetwork; 
CREATE DATABASE SocialNetwork;
use SocialNetwork;

CREATE TABLE IF NOT EXISTS User (
	IdUser INT PRIMARY KEY AUTO_INCREMENT,
    Username CHAR(50) UNIQUE NOT NULL,
    Password CHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS Post (
	IdPost INT PRIMARY KEY AUTO_INCREMENT,
    strPost CHAR(50) NOT NULL,
    User INT NOT NULL,
    Date TIMESTAMP NOT NULL,
  	FOREIGN KEY(User) REFERENCES User(IdUser)
		ON DELETE cascade
        ON UPDATE cascade
);

CREATE TABLE IF NOT EXISTS Comment (
	IdComment INT PRIMARY KEY AUTO_INCREMENT,
    strComment CHAR(50) NOT NULL,
    User INT NOT NULL,
    Post INT NOT NULL,
    Date TIMESTAMP NOT NULL,
  	FOREIGN KEY(User) REFERENCES User(IdUser)
		ON DELETE cascade
        ON UPDATE cascade,
    FOREIGN KEY(Post) REFERENCES Post(IdPost)
		ON DELETE cascade
        ON UPDATE cascade
);
