CREATE DATABASE IF NOT EXISTS dbauth;
CREATE DATABASE IF NOT EXISTS dbbook;
CREATE DATABASE IF NOT EXISTS dbloan;

CREATE USER 'admin'@'db' IDENTIFIED BY 'admin123!';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%';
FLUSH PRIVILEGES ;

USE dbauth;

create table if not exists oauth_client_details (
  client_id varchar(255) not null,
  client_secret varchar(255) not null,
  web_server_redirect_uri varchar(2048) default null,
  scope varchar(255) default null,
  access_token_validity int(11) default null,
  refresh_token_validity int(11) default null,
  resource_ids varchar(1024) default null,
  authorized_grant_types varchar(1024) default null,
  authorities varchar(1024) default null,
  additional_information varchar(4096) default null,
  autoapprove varchar(255) default null,
  primary key (client_id)
) engine=innodb ;

create table if not exists account (
  id int(11) not null auto_increment,
  username varchar(100) not null,
  password varchar(1024) not null,
  email varchar(1024) not null,
  firstName varchar(100) not null,
  lastName varchar(100) not null,
  phoneNumber varchar(10) not null,
  enabled tinyint(4) not null,
  account_Non_Expired tinyint(4) not null,
  credentials_Non_Expired tinyint(4) not null,
  account_Non_Locked tinyint(4) not null,
  roles varchar(1024) not null,
  primary key (id),
  unique key username (username)
) engine=innodb ;


-- token store
create table if not exists oauth_client_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table if not exists oauth_access_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(256)
);

create table if not exists oauth_refresh_token (
  token_id VARCHAR(256),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);

create table if not exists oauth_code (
  code VARCHAR(256), authentication LONG VARBINARY
);

create table if not exists oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);

INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, autoapprove, additional_information)
VALUES
('client', '{noop}secret', 'http://localhost:8000/login', 'READ,WRITE', '3600', '10000', 'library-book,library-loan', 'authorization_code,password,refresh_token,implicit', 'true', '{}'),
('batch', '{noop}secret', 'http://localhost:9005/login', 'READ', '3600', '10000', 'library-loan', 'client_credentials', 'true', '{}');


INSERT INTO account
(id, username, firstName, lastName, password, email, phoneNumber, roles, enabled, account_Non_Expired, credentials_Non_Expired, account_Non_Locked)
VALUES
(1, 'admin','Jean', 'Lastname', '{bcrypt}$2y$12$dspd2mG2WbRCo0CI2e92vOe1JxnOv.V4Pvp2e2drjN.kofrkkaJnG', 'admin@aaa.com', '0111111111', 'ADMIN,USER', '1', '1', '1', '1' ),
(2, 'user', 'Paul', 'Lastname', '{bcrypt}$2y$12$dspd2mG2WbRCo0CI2e92vOe1JxnOv.V4Pvp2e2drjN.kofrkkaJnG', 'openclassroomsbiblio@gmail.com', '0222222222', 'USER', '1', '1', '1', '1' ),
(3, 'user2', 'Pierre', 'Lastname', '{bcrypt}$2y$12$dspd2mG2WbRCo0CI2e92vOe1JxnOv.V4Pvp2e2drjN.kofrkkaJnG', 'user2@aaa.com', '0333333333', 'USER', '1', '1', '1', '1' ),
(4, 'user3', 'Jacques', 'Lastname', '{bcrypt}$2y$12$dspd2mG2WbRCo0CI2e92vOe1JxnOv.V4Pvp2e2drjN.kofrkkaJnG', 'user3@aaa.com', '0333333333', 'USER', '1', '1', '1', '1' ),
(5, 'user4', 'Alphonse', 'Lastname', '{bcrypt}$2y$12$dspd2mG2WbRCo0CI2e92vOe1JxnOv.V4Pvp2e2drjN.kofrkkaJnG', 'user4@aaa.com', '0333333333', 'USER', '1', '1', '1', '1' );





USE dbbook;

create table if not exists book
(
    id int          not null
        primary key,
    author       varchar(255) null,
    editor       varchar(255) null,
    genre        varchar(255) null,
    language     varchar(255) null,
    release_date varchar(255) null,
    summary      varchar(255) null,
    title        varchar(255) null
);

create table if not exists copy
(
    id   int not null
        primary key,
    book int not null
);

create table if not exists hibernate_sequence
(
    next_val bigint null
);

INSERT INTO book
(id, title, author, genre, language, editor, summary, release_date )
VALUES
(1, 'La jeune fille et la nuit','Guillaume Musso', 'thriller', 'français', 'Calmann-Lévy', 'Un campus prestigieux figé sous la neige. Trois amis liés par un secret tragique. Une jeune fille emportée par la nuit.', '24/04/2018' ),
(2, 'Sérotonine','Michel Houellebecq', 'fiction', 'français', 'Flammarion', 'Le narrateur de Sérotonine approuverait sans réserve. Son récit traverse une France qui piétine ses traditions, banalise ses villes, détruit ses campagnes au bord de la révolte. ', '04/01/2019'),
(3, 'Tous les hommes n’habitent pas le monde de la même façon', 'Jean-Paul Dubois', 'roman', 'français', 'éditions de lOlivier', 'Le prix Goncourt 2019 retrace la vie de Paul Hansen, super intendant à L’Excelsior, une résidence où il déploie ses talents de concierge, de gardien et de réparateur des âmes.', '14/08/2019'),
(4, 'Changer leau des fleurs','Valérie Perrin', 'roman', 'français', 'Albin Michel' , 'Violette Toussaint est garde-cimetière dans une petite ville de Bourgogne. Les gens de passage et les habitués viennent se réchauffer dans sa loge où rires et larmes se mélangent au café quelle leur offre. Son quotidien est rythmé par leurs confidences.', '28/02/2018'),
(5, 'Livre5','auteur', 'essai', 'français', 'editeur', 'resume du livre', '01/01/2020' ),
(6, 'Livre6','auteur', 'polar', 'français', 'editeur', 'resume du livre', '01/01/2020' ),
(7, 'Livre7','auteur', 'science-fiction', 'français', 'editeur', 'resume du livre', '01/01/2020' ),
(8, 'Livre8','auteur', 'fantastique', 'français', 'editeur', 'resume du livre', '01/01/2020' ),
(9, 'Livre9','auteur', 'conte', 'français', 'editeur', 'resume du livre', '01/01/2020' ),
(10, 'Livre10','auteur', 'roman', 'français', 'editeur', 'resume du livre', '01/01/2020' );

INSERT INTO copy
(id, book)
VALUES
(1,1),
(2,1),
(3,2),
(4,2),
(5,3),
(6,4),
(7,5),
(8,6),
(9,7),
(10,8);

USE dbloan;

create table if not exists hibernate_sequence
(
    next_val bigint null
);

create table if not exists loan
(
    id         int         not null
        primary key,
    copy       int         not null,
    end_date   datetime(6) not null,
    renewed    bit         not null,
    returned   bit         not null,
    start_date datetime(6) not null,
    user       int         not null
);

INSERT INTO loan
(id, copy, user, start_date, end_date, returned, renewed)
VALUES
(2, 1, 2, '2020-07-18', '2020-08-15', true, false),
(3, 2, 2, '2020-08-13', '2020-09-10', false, false),
(4, 3, 2, '2020-09-01', '2020-09-29', false, false),
(5, 4, 2, '2020-09-01', '2020-09-29', false, false);
