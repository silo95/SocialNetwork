DROP DATABASE IF EXISTS SocialNetwork; 
CREATE DATABASE SocialNetwork;
use SocialNetwork;

CREATE TABLE IF NOT EXISTS Person (
    idPerson INT PRIMARY KEY AUTO_INCREMENT,
    username CHAR(50) UNIQUE NOT NULL,
    password CHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS Post (
    idPost INT PRIMARY KEY AUTO_INCREMENT,
    strPost CHAR(50) NOT NULL,
    person INT NOT NULL,
    postDate TIMESTAMP NOT NULL,
  	FOREIGN KEY(Person) REFERENCES Person(idPerson)
		ON DELETE cascade
        ON UPDATE cascade
);

CREATE TABLE IF NOT EXISTS Comment (
    idComment INT PRIMARY KEY AUTO_INCREMENT,
    strComment CHAR(50) NOT NULL,
    person INT NOT NULL,
    post INT NOT NULL,
    commentDate TIMESTAMP NOT NULL,
  	FOREIGN KEY(person) REFERENCES Person(idPerson)
		ON DELETE cascade
        ON UPDATE cascade,
    FOREIGN KEY(post) REFERENCES Post(idPost)
		ON DELETE cascade
        ON UPDATE cascade
);

