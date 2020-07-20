
/* Veri tabanı Oluşturma*/
CREATE DATABASE Reservation_System DEFAULT CHARACTER SET utf8 ;

/* kullanıcılar tablosu oluşturma */
CREATE TABLE users (
  user_id INTEGER NOT NULL AUTO_INCREMENT,
  name VARCHAR(15),
  last_name VARCHAR(15),
  email VARCHAR(50),
  Tel VARCHAR(50),
  password VARCHAR(20),
  type VARCHAR(20),
  PRIMARY KEY(user_id),
  INDEX(email)
) ENGINE=InnoDB CHARSET=utf8;

/* SEFERLER tablosu oluşturma */
CREATE TABLE journeys(
  journy_id INTEGER NOT NULL AUTO_INCREMENT,
  date date,
  time time,
  Bus_No VARCHAR(20),
  departure VARCHAR(20),
  destination VARCHAR(20),
  price double,

  PRIMARY KEY(journy_id)
)ENGINE=InnoDB CHARSET=utf8;




/* ***************** 24.05.2020 GUNCELLEME ******************************* */

/*ilk once eski otobus tablolari silin..*/

DROP TABLE bus_1;
DROP TABLE bus_2;

/* SIMDI YENI TABLOLRI OLUSTURUN */

/* 1. Otobüs tablosu oluşturma */
CREATE TABLE A35(
  journy_id INTEGER,
  seats INTEGER,
  free BOOLEAN NOT NULL DEFAULT true,

  CONSTRAINT bus1
   FOREIGN KEY (journy_id)
   REFERENCES journeys (journy_id)
   ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB CHARSET=utf8;

/* 2. Otobüs tablosu oluşturma */
CREATE TABLE B90(
  journy_id INTEGER,
  seats INTEGER,
  free BOOLEAN NOT NULL DEFAULT true,

  CONSTRAINT bus2
   FOREIGN KEY (journy_id)
   REFERENCES journeys (journy_id)
   ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB CHARSET=utf8;


/* JOURNEYS TABLUDAKI SUTUNLARININ TYPE GUNCELLEMELERI */

/* NOTE: journeys tablosu sectikten sonra asagidki kodu clistirin */

ALTER TABLE journeys MODIFY date date;
ALTER TABLE journeys MODIFY time time;
ALTER TABLE journeys MODIFY price double;


/* GUNCELLEME 26.05.2020 */

/* KULLANICINI DURUMU EKLEME */

ALTER TABLE users 
ADD COLUMN type VARCHAR(20) AFTER password;