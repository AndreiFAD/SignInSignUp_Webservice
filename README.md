
# SignIn SignUp Webservice
SOAP webservice with mysql db, example for ios app, SignIn and SignUp.


MySqlDB create Table:
CREATE TABLE employs <br><br>
( createdate date NOT NULL, <br>
  username varchar(100) NOT NULL, <br>
  password varchar(1000) NOT NULL, <br>
  mailaddress varchar(1000) NOT NULL, <br>
  userlevel int(11) NOT NULL, <br>
  validationflag int(11) DEFAULT NULL ) <br>
ENGINE=InnoDB DEFAULT CHARSET=latin1;
