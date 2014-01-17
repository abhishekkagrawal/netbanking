/*
SQLyog Community v11.27 (32 bit)
MySQL - 5.6.12-log : Database - netbanking
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `accountId` int(5) NOT NULL AUTO_INCREMENT,
  `accountType` varchar(20) NOT NULL,
  `minBalance` double NOT NULL,
  `dateOfCreation` datetime DEFAULT NULL,
  `dateOfModification` datetime DEFAULT NULL,
  PRIMARY KEY (`accountId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `account` */

insert  into `account`(`accountId`,`accountType`,`minBalance`,`dateOfCreation`,`dateOfModification`) values (1,'Saving',1000,'2013-10-02 10:44:17','2013-10-02 10:43:52'),(2,'Current',5000,'2013-10-02 10:44:17','2013-10-02 10:43:52');

/*Table structure for table `bankdetail` */

DROP TABLE IF EXISTS `bankdetail`;

CREATE TABLE `bankdetail` (
  `bankId` int(11) NOT NULL AUTO_INCREMENT,
  `bankName` varchar(50) NOT NULL,
  PRIMARY KEY (`bankId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `bankdetail` */

insert  into `bankdetail`(`bankId`,`bankName`) values (1,'State Bank Of India'),(2,'Punjab National Bank'),(3,'ICICI'),(4,'HDFC Bank');

/*Table structure for table `beneficiarydetail` */

DROP TABLE IF EXISTS `beneficiarydetail`;

CREATE TABLE `beneficiarydetail` (
  `beneficiaryId` int(10) NOT NULL AUTO_INCREMENT,
  `accountNumber` int(10) NOT NULL,
  `beneficiaryName` varchar(30) NOT NULL,
  `beneficiaryAccountNumber` int(10) NOT NULL,
  `bankName` varchar(30) NOT NULL,
  `IFSCCode` varchar(20) NOT NULL,
  `accountType` varchar(30) NOT NULL,
  `dateOfCreation` datetime DEFAULT NULL,
  `dateOfModification` datetime DEFAULT NULL,
  `transactionType` varchar(10) DEFAULT NULL,
  `URNNumber` int(6) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`beneficiaryId`),
  UNIQUE KEY `beneficiaryUniqueKey` (`accountNumber`,`beneficiaryAccountNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `beneficiarydetail` */

insert  into `beneficiarydetail`(`beneficiaryId`,`accountNumber`,`beneficiaryName`,`beneficiaryAccountNumber`,`bankName`,`IFSCCode`,`accountType`,`dateOfCreation`,`dateOfModification`,`transactionType`,`URNNumber`,`status`) values (18,4,'Test User',1,'State Bank Of India','SBI020501','SAVING','2014-01-17 13:33:05','2014-01-17 13:33:05','NEFT',612722,'Active');

/*Table structure for table `billtypes` */

DROP TABLE IF EXISTS `billtypes`;

CREATE TABLE `billtypes` (
  `billerId` int(10) NOT NULL AUTO_INCREMENT,
  `billerName` varchar(255) NOT NULL,
  `stateId` int(10) DEFAULT NULL,
  `dateOfCreation` datetime DEFAULT NULL,
  `dateOfModification` datetime DEFAULT NULL,
  PRIMARY KEY (`billerId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `billtypes` */

insert  into `billtypes`(`billerId`,`billerName`,`stateId`,`dateOfCreation`,`dateOfModification`) values (1,'BSNL CellOne',NULL,'2013-11-05 00:00:00','2013-11-05 00:00:00'),(2,'Electricity Board',NULL,'2013-11-05 00:00:00','2013-11-05 00:00:00'),(3,'SBI Life Insurece',NULL,'2013-11-05 00:00:00','2013-11-05 00:00:00');

/*Table structure for table `bsnlcellonebill` */

DROP TABLE IF EXISTS `bsnlcellonebill`;

CREATE TABLE `bsnlcellonebill` (
  `bsnlCelloneId` int(10) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(50) DEFAULT NULL,
  `mobileNumber` decimal(10,0) DEFAULT NULL,
  `stateId` int(10) DEFAULT NULL,
  `accountNumber` int(10) DEFAULT NULL,
  `dateOfCreation` datetime DEFAULT NULL,
  `dateOfModification` datetime DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `amount` decimal(5,2) DEFAULT '0.00',
  `paidStatus` tinyint(1) DEFAULT '0',
  `lastPaidDate` date DEFAULT NULL,
  PRIMARY KEY (`bsnlCelloneId`),
  UNIQUE KEY `bsnluniquekey` (`accountNumber`,`mobileNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `bsnlcellonebill` */

insert  into `bsnlcellonebill`(`bsnlCelloneId`,`nickName`,`mobileNumber`,`stateId`,`accountNumber`,`dateOfCreation`,`dateOfModification`,`status`,`amount`,`paidStatus`,`lastPaidDate`) values (9,'Abhi','9969561769',18,1,'2014-01-17 13:43:56','2014-01-17 13:43:56','Active','0.00',0,'2013-12-17');

/*Table structure for table `clientdetail` */

DROP TABLE IF EXISTS `clientdetail`;

CREATE TABLE `clientdetail` (
  `accountNumber` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `address` varchar(255) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `number` decimal(10,0) NOT NULL,
  `accountId` int(5) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `emailId` varchar(50) NOT NULL,
  `panCardNumber` varchar(20) NOT NULL,
  `availBalance` double NOT NULL,
  `dateOfCreation` datetime DEFAULT NULL,
  `dateOfModification` datetime DEFAULT NULL,
  `noOfCardIssue` int(2) DEFAULT '2',
  PRIMARY KEY (`accountNumber`),
  KEY `FK_clientdetail` (`accountId`),
  CONSTRAINT `FK_clientdetail` FOREIGN KEY (`accountId`) REFERENCES `account` (`accountId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `clientdetail` */

insert  into `clientdetail`(`accountNumber`,`name`,`address`,`gender`,`number`,`accountId`,`dateOfBirth`,`emailId`,`panCardNumber`,`availBalance`,`dateOfCreation`,`dateOfModification`,`noOfCardIssue`) values (1,'Jay Prakash','Mumbai','Male','9175641027',1,'1988-01-07','abhishek.agrawal@webaccess.co.in','AVFPP0243G',42633,'2013-08-13 14:40:45','2013-08-13 14:40:45',0),(2,'Jay','Bettiah','Male','9594670616',2,'1988-01-07','abhishek.agrawal@webaccess.co.in','AVFPP0243G',50000,'2013-09-13 14:40:45','2013-09-13 14:40:45',1),(3,'Anup Shende','Nagpur','Male','8149173488',1,'1990-02-14','abhishek.agrawal@webaccess.co.in','ASDPA1254P',40000,'2013-10-13 14:40:45','2013-10-13 14:40:45',2),(4,'Abhishek Agrawal','Mumbai','Male','9969561769',1,'1987-09-24','abhishek.agrawal@webaccess.co.in','AJVPA7432A',50000,'2014-01-15 00:00:00','2014-01-05 00:00:00',2);

/*Table structure for table `creditcarddetail` */

DROP TABLE IF EXISTS `creditcarddetail`;

CREATE TABLE `creditcarddetail` (
  `cardId` int(10) NOT NULL AUTO_INCREMENT,
  `creditCardNumber` decimal(16,0) DEFAULT NULL,
  `nameOnCard` varchar(20) DEFAULT NULL,
  `creditCardId` int(5) DEFAULT NULL,
  `unbilledAmount` double(10,2) DEFAULT '0.00',
  `dateOfCreation` datetime DEFAULT NULL,
  `dateOfModification` datetime DEFAULT NULL,
  `accountNumber` int(10) DEFAULT NULL,
  `lastPaid` date DEFAULT NULL,
  PRIMARY KEY (`cardId`),
  KEY `FK_creditcarddetail` (`accountNumber`),
  CONSTRAINT `FK_creditcarddetail` FOREIGN KEY (`accountNumber`) REFERENCES `clientdetail` (`accountNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `creditcarddetail` */

insert  into `creditcarddetail`(`cardId`,`creditCardNumber`,`nameOnCard`,`creditCardId`,`unbilledAmount`,`dateOfCreation`,`dateOfModification`,`accountNumber`,`lastPaid`) values (11,'5852062228449105','Abhishek Agrawa',2,0.00,'2014-01-17 13:49:57','2014-01-17 13:49:57',1,'2014-01-17');

/*Table structure for table `creditcardtype` */

DROP TABLE IF EXISTS `creditcardtype`;

CREATE TABLE `creditcardtype` (
  `creditCardId` int(5) NOT NULL AUTO_INCREMENT,
  `cardTypeName` varchar(20) DEFAULT NULL,
  `maxLimit` double(10,2) DEFAULT NULL,
  `dateOfCreation` datetime DEFAULT NULL,
  `dateOfModification` datetime DEFAULT NULL,
  PRIMARY KEY (`creditCardId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `creditcardtype` */

insert  into `creditcardtype`(`creditCardId`,`cardTypeName`,`maxLimit`,`dateOfCreation`,`dateOfModification`) values (1,'Platinum',100000.00,'2013-11-11 15:18:55','2013-11-11 15:18:55'),(2,'Gold',50000.00,'2013-11-11 15:18:55','2013-11-11 15:18:55'),(3,'Silver',25000.00,'2013-11-11 15:18:55','2013-11-11 15:18:55');

/*Table structure for table `electricitybill` */

DROP TABLE IF EXISTS `electricitybill`;

CREATE TABLE `electricitybill` (
  `electricityBillID` int(10) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(50) DEFAULT NULL,
  `billingUnit` int(10) DEFAULT NULL,
  `processingCycle` int(50) DEFAULT NULL,
  `consumerNumber` int(10) DEFAULT NULL,
  `stateId` int(10) DEFAULT NULL,
  `accountNumber` int(10) DEFAULT NULL,
  `dateOfCreation` datetime DEFAULT NULL,
  `dateOfModification` datetime DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `amount` decimal(5,2) DEFAULT '0.00',
  `paidStatus` tinyint(1) DEFAULT '0',
  `lastPaidDate` date DEFAULT NULL,
  PRIMARY KEY (`electricityBillID`),
  UNIQUE KEY `electricityuniquekey` (`accountNumber`,`consumerNumber`),
  KEY `FK_electricitybill` (`stateId`),
  CONSTRAINT `FK_electricitybill` FOREIGN KEY (`stateId`) REFERENCES `states` (`stateId`),
  CONSTRAINT `FK_electricitybillAccountNumber` FOREIGN KEY (`accountNumber`) REFERENCES `clientdetail` (`accountNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `electricitybill` */

insert  into `electricitybill`(`electricityBillID`,`nickName`,`billingUnit`,`processingCycle`,`consumerNumber`,`stateId`,`accountNumber`,`dateOfCreation`,`dateOfModification`,`status`,`amount`,`paidStatus`,`lastPaidDate`) values (3,'Abhi',100,50,4,14,1,'2014-01-17 13:47:44','2014-01-17 13:47:44','Active','0.00',0,'2013-12-17'),(4,'Abhi',100,50,1111,18,3,'2014-01-17 14:40:55','2014-01-17 14:40:55','Active','0.00',0,'2013-12-17');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `loginId` int(10) NOT NULL AUTO_INCREMENT,
  `accountNumber` int(10) NOT NULL,
  `userId` varchar(20) NOT NULL,
  `loginPassword` varchar(255) NOT NULL,
  `oldLoginPassword` varchar(255) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  `loginType` varchar(50) NOT NULL DEFAULT 'View',
  `transactionPassword` varchar(255) DEFAULT NULL,
  `optNumber` int(8) DEFAULT '0',
  `dateOfCreation` datetime DEFAULT NULL,
  `dateOfModification` datetime DEFAULT NULL,
  `lastLogin` datetime DEFAULT NULL,
  `oldTransactionPassword` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`loginId`),
  KEY `FK_login` (`accountNumber`),
  CONSTRAINT `FK_login` FOREIGN KEY (`accountNumber`) REFERENCES `clientdetail` (`accountNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`loginId`,`accountNumber`,`userId`,`loginPassword`,`oldLoginPassword`,`active`,`loginType`,`transactionPassword`,`optNumber`,`dateOfCreation`,`dateOfModification`,`lastLogin`,`oldTransactionPassword`) values (5,4,'UDL885531','f00cb32e1f6a9df51332d8a8fc3ca473',NULL,1,'View','',0,NULL,NULL,'2014-01-17 13:40:30',NULL),(6,1,'NMA417129','a234aa8343b9e6d88d59bdd37af77478',NULL,1,'View And transaction','2b722c1b6e4fbd6992038252d971bd25',0,NULL,NULL,'2014-01-17 13:52:08',NULL),(7,3,'HXF853464','293c3f4085a7601a0e926762f821191f',NULL,1,'View And transaction','75516a3acd7d157e59b664aa02b3680e',0,NULL,NULL,'2014-01-17 13:54:23',NULL);

/*Table structure for table `sbilifeinsurence` */

DROP TABLE IF EXISTS `sbilifeinsurence`;

CREATE TABLE `sbilifeinsurence` (
  `sbiLifeInsurenceId` int(10) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(50) DEFAULT NULL,
  `policyNumber` int(10) DEFAULT NULL,
  `dobOfPolicyHolder` date DEFAULT NULL,
  `stateId` int(10) DEFAULT NULL,
  `accountNumber` int(10) DEFAULT NULL,
  `dateOfCreation` datetime DEFAULT NULL,
  `dateOfModification` datetime DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `amount` decimal(5,2) DEFAULT '0.00',
  `paidStatus` tinyint(1) DEFAULT '0',
  `lastPaidDate` date DEFAULT NULL,
  PRIMARY KEY (`sbiLifeInsurenceId`),
  UNIQUE KEY `sbiuniquekey` (`accountNumber`,`policyNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `sbilifeinsurence` */

/*Table structure for table `states` */

DROP TABLE IF EXISTS `states`;

CREATE TABLE `states` (
  `stateId` int(5) NOT NULL AUTO_INCREMENT,
  `stateName` varchar(50) NOT NULL,
  `dateOfCreation` datetime DEFAULT NULL,
  `dateOfModification` datetime DEFAULT NULL,
  PRIMARY KEY (`stateId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

/*Data for the table `states` */

insert  into `states`(`stateId`,`stateName`,`dateOfCreation`,`dateOfModification`) values (1,'Jammu & Kashmir',NULL,NULL),(2,'Punjab',NULL,NULL),(3,'Uttaranchal',NULL,NULL),(4,'Delhi',NULL,NULL),(5,'Uttar Pradesh',NULL,NULL),(6,'Sikkim',NULL,NULL),(7,'Nagaland',NULL,NULL),(8,'Mizoram',NULL,NULL),(9,'Meghalaya',NULL,NULL),(10,'West Bengal',NULL,NULL),(11,'Orissa',NULL,NULL),(12,'Madhya Pradesh',NULL,NULL),(13,'Daman & Diu',NULL,NULL),(14,'Maharashtra',NULL,NULL),(15,'Karnataka',NULL,NULL),(16,'Lakshadweep',NULL,NULL),(17,'Tamil Nadu',NULL,NULL),(18,'Andaman & Nicobar Islands',NULL,NULL),(19,'Bihar',NULL,NULL);

/*Table structure for table `transactiondetail` */

DROP TABLE IF EXISTS `transactiondetail`;

CREATE TABLE `transactiondetail` (
  `transactionId` int(10) NOT NULL AUTO_INCREMENT,
  `accountNumber` int(10) NOT NULL,
  `transactionType` varchar(20) NOT NULL,
  `transactionDate` date NOT NULL,
  `transactionAmount` double NOT NULL,
  `dateOfCreation` datetime DEFAULT NULL,
  `dateOfModification` datetime DEFAULT NULL,
  `particulars` varchar(100) DEFAULT NULL,
  `transferAccountNumber` int(10) DEFAULT NULL,
  `chequeNumber` int(6) DEFAULT NULL,
  `cardNumber` int(10) DEFAULT NULL,
  PRIMARY KEY (`transactionId`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;

/*Data for the table `transactiondetail` */

insert  into `transactiondetail`(`transactionId`,`accountNumber`,`transactionType`,`transactionDate`,`transactionAmount`,`dateOfCreation`,`dateOfModification`,`particulars`,`transferAccountNumber`,`chequeNumber`,`cardNumber`) values (54,1,'DR','2014-01-17',7367,'2014-01-17 13:50:55','2014-01-17 13:50:55','cardbillpay/Test',NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
