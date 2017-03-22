-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: transporter
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accident_cause`
--

DROP TABLE IF EXISTS `accident_cause`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accident_cause` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cause` varchar(90) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accident_cause`
--

LOCK TABLES `accident_cause` WRITE;
/*!40000 ALTER TABLE `accident_cause` DISABLE KEYS */;
INSERT INTO `accident_cause` VALUES (1,'Carrying Out Work on the Road without Proper Attire or Sufficient Warning Signs'),(2,'Causes Attributed to Road Conditions'),(3,'Causes Attributed to Vehicles'),(4,'Changing Lane without Due Care'),(5,'Crossing Heedless of Traffic'),(6,'Crossing In Front or Behind a Vehicle which Obstructs View'),(7,'Disobeying Traffic Light Signals Resulting in Accidents with Vehicle'),(8,'Driving under the Influence of Alcohol'),(9,'Failing to Give Way to Traffic with Right of Way'),(10,'Failing to Have Proper Control'),(11,'Failing to Keep a Proper Lookout'),(12,'Failing to Use Available Pedestrian Crossing'),(13,'Following Too Close to Vehicle In Front'),(14,'Other Causes'),(15,'Other causes attributed to drivers, riders or pedal cyclists'),(16,'Other Causes of Accidents Attributed to Pedestrians'),(17,'Overtaking without Due Care'),(18,'Playing on The Road or Carpark'),(19,'Turning Vehicle & Failing to Give Way to Pedestrian During Green Man'),(20,'Turning Without Due Care'),(21,'Under the Influence of Alcohol'),(22,'Under the Influence of Drugs/Intoxicated Substance');
/*!40000 ALTER TABLE `accident_cause` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accident_report`
--

DROP TABLE IF EXISTS `accident_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accident_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formattedAddress` varchar(200) NOT NULL,
  `accidentDateTime` datetime NOT NULL,
  `numOfCasualties` int(5) DEFAULT NULL,
  `description` varchar(90) DEFAULT NULL,
  `longitude` double NOT NULL,
  `latitude` double NOT NULL,
  `imageLink` varchar(255) NOT NULL,
  `approvedDateTime` datetime DEFAULT NULL,
  `approvedBy` int(11) DEFAULT NULL,
  `resolvedDateTime` datetime DEFAULT NULL,
  `officialCause` int(11) DEFAULT NULL,
  `resolvedBy` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_accident_report_approvedBy_idx` (`approvedBy`),
  KEY `fk_accident_report_resolvedBy_idx` (`resolvedBy`),
  KEY `fk_accident_report_initialCause_idx` (`description`),
  KEY `fk_accident_report_officialCause_idx` (`officialCause`),
  CONSTRAINT `fk_accident_report_approvedBy` FOREIGN KEY (`approvedBy`) REFERENCES `lta_personnel` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_accident_report_officialCause` FOREIGN KEY (`officialCause`) REFERENCES `accident_cause` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_accident_report_resolvedBy` FOREIGN KEY (`resolvedBy`) REFERENCES `lta_personnel` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accident_report`
--

LOCK TABLES `accident_report` WRITE;
/*!40000 ALTER TABLE `accident_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `accident_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user`
--

DROP TABLE IF EXISTS `auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  `email` varchar(254) NOT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user`
--

LOCK TABLES `auth_user` WRITE;
/*!40000 ALTER TABLE `auth_user` DISABLE KEYS */;
INSERT INTO `auth_user` VALUES (1,'yhchan','password1','yhchan@lta.gov.sg','Yi Hao','Chan'),(2,'junjie','junjie','junjie@lta.gov.sg','Jun Jie','Soh'),(3,'sean','sean','sean@lta.gov.sg','Sean','Tan'),(4,'minhtu','minhtu','minhtu@lta.gov.sg','Minh Tu','Vu'),(5,'shannon','shannon','shannon@lta.gov.sg','Shannon','Neo'),(6,'simon','simon','simon@lta.gov.sg','Simon','Sim'),(7,'stella','stella','stella@lta.gov.sg','Stella','Tan');
/*!40000 ALTER TABLE `auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `camera`
--

DROP TABLE IF EXISTS `camera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `camera` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formattedAddress` varchar(200) NOT NULL,
  `longitude` double NOT NULL,
  `latitude` double NOT NULL,
  `type` int(2) NOT NULL,
  `status` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1065 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camera`
--

LOCK TABLES `camera` WRITE;
/*!40000 ALTER TABLE `camera` DISABLE KEYS */;
INSERT INTO `camera` VALUES (805,'Alexandra Road by Commonwealth Avenue',103.80829,1.29175,1,1),(806,'Clementi Avenue 6 by Clementi Loop',103.764168,1.325548,1,1),(807,'West Coast H\'way by Wholesale Centre',103.776435,1.288351,1,1),(808,'West Coast H\'way by Wholesale Centre',103.7766,1.287846,1,1),(809,'Upp Thomson Road by Jln Todak',103.834573,1.353281,1,1),(810,'Upper Thomson Road by Jln Pelatina',103.835741,1.351255,1,1),(811,'Whitley Road by Mount Pleasant Road',103.82698,1.32447,1,1),(812,'Eunos Link by Airport Road',103.89665,1.33715,1,1),(813,'Eunos Link by Bedok Reservoir Road',103.904501,1.329673,1,1),(814,'Jalan Eunos by Bedok Reservior Road',103.90484,1.3292,1,1),(815,'Marymount Road by Ang Mo Kio Avenue 1',103.84269,1.363983,1,1),(816,'Ang Mo Kio Avenue 1 by Marymount Road',103.843268,1.3642,1,1),(817,'Ang Mo Kio Avenue 1 by CTE',103.857375,1.355232,1,1),(818,'Ang Mo Kio Avenue 1 by CTE',103.856132,1.355874,1,1),(819,'Ang Mo Kio Avenue 3 by Ang Mo Kio Industrial Park 2',103.864237,1.370336,1,1),(820,'Woodlands Avenue 12 by Woodlands Avenue 1',103.798973,1.426989,1,1),(821,'Choa Chu Kang Road by Choa Chu Kang Way',103.75415,1.376435,1,1),(822,'Bukit Batok East Avenue 3 by Toh Tuck Road',103.760246,1.337575,1,1),(823,'Beach Road by Ophir Road',103.859615,1.299865,1,1),(824,'Yio Chu Kang Road by Jalan Kelulut',103.876962,1.382763,1,1),(825,'Ang Mo Kio Avenue 6 by Ang Mo Kio Avenue 5',103.844588,1.377076,1,1),(826,'Mandai Road by BKE',103.770954,1.410087,1,1),(827,'Paya Lebar Road by Ubi Avenue 3',103.889794,1.32981,1,1),(828,'Sembawang Road by Yishun Avenue 5',103.826653,1.429918,1,1),(829,'Airport Road by Ubi Road 2',103.894261,1.335711,1,1),(830,'Boon Lay Dr by Corporation Road',103.715758,1.345688,1,1),(831,'Rivervale Drive by Sengkang East Way',103.90627,1.38878,1,1),(832,'Tampines Avenue 5 by Tampines Street 71',103.935988,1.355945,1,1),(833,'Tiong Bahru by Lower Delta Road',103.825027,1.286487,1,1),(834,'Tampines Avenue 5 by Tampines Street 71',103.936535,1.356035,1,1),(835,'Pasir Ris Drive 12 by (Exit) TPE',103.932205,1.375909,1,1),(836,'Bukit Batok Road by Bt Batok West Avenue 2',103.742275,1.363005,1,1),(837,'Lor Chuan by Serangoon Avenue 2',103.867508,1.355374,1,1),(838,'Ang Mo Kio Avenue 1 by Lor Chuan',103.863992,1.354033,1,1),(839,'Hougang Avenue 3 by Defu Avenue 1',103.890495,1.346211,1,1),(840,'Upper Thomson Road by Ang Mo Kio Avenue 1',103.828405,1.368241,1,1),(841,'Admiralty Road by Marsiling Lane',103.777,1.445056,1,1),(842,'Jurong West Avenue 4 by Jurong West St 64',103.702782,1.345793,1,1),(843,'Canberra Link by Yishun Industrial Park A',103.83074,1.44192,1,1),(844,'Jln Eunos by PIE',103.905538,1.32567,1,1),(845,'Toh Tuck Avenue by Toh Tuck Link',103.758466,1.330527,1,1),(846,'Buangkok Green by Hougang Street 51',103.887718,1.380803,1,1),(847,'Hougang Avenue 3 by Lor Ah Soo',103.890404,1.351984,1,1),(848,'Hougang Avenue 3 by Lor Ah Soo',103.890926,1.352375,1,1),(849,'Toh Tuck Avenue by Toh Tuck Link',103.759193,1.330751,1,1),(850,'Pasir Ris Drive 1 by Pasir Ris Dr 4',103.959473,1.368108,1,1),(851,'Canberra Road by Sembawang Vista',103.822669,1.44765,1,1),(852,'Bukit Panjang Rd by Pending Rd',103.772113,1.377138,1,1),(853,'Pioneer Road North by Jurong West Avenue 5',103.693062,1.342295,1,1),(854,'Brickland Road x Choa Chu Kang Grove',103.746263,1.371083,1,1),(855,'Brickland Road by Choa Chu Kang Avenue 3',103.735374,1.376306,1,1),(856,'Bishan Street 11 by Bishan St 12',103.850052,1.347804,1,1),(857,'Pioneer Road North by Jurong West Avenue 5',103.693369,1.341767,1,1),(858,'Bukit Panjang Road by Gangsa Road',103.766983,1.379793,1,1),(859,'Dunearn Road by Kheam Hock Road',103.819582,1.322901,1,1),(860,'Fernvale Road by Sengkang West Avenue',103.875686,1.39103,1,1),(861,'Punggol East by Punggol Central',103.91656,1.39537,1,1),(862,'Sengkang East Drive by Sengkang East Avenue',103.903325,1.381601,1,1),(863,'Compassvale Drive by Sengkang Central',103.894491,1.388908,1,1),(864,'Sengkang East Way by Sengkang Square',103.89478,1.39306,1,1),(865,'Yio Chu Kang Road by Yio Chu Kang Link',103.874322,1.358214,1,1),(866,'Yishun Avenue 3 by Yishun Ring Road',103.83144,1.423012,1,1),(867,'Woodlands Avenue 5 by Marsiling Rise',103.78295,1.438,1,1),(868,'Yio Chu Kang Road by Ang Mo Kio Avenue 5',103.83444,1.38101,1,1),(869,'Thomson Road by Marymount Road',103.837426,1.33949,1,1),(870,'Still Road by East Coast Road',103.90863,1.30837,1,1),(871,'Telok Kurau Road by East Coast Road',103.912357,1.308875,1,1),(872,'Marine Parade Road by Marine Parade Central',103.90686,1.30371,1,1),(873,'Still Road South by Marine Parade Road',103.91,1.30581,1,1),(874,'New Upp Changi Road by Siglap Road',103.92004,1.32133,1,1),(875,'Tampines Avenue 4 by Tampines Avenue 3',103.93827,1.349,1,1),(876,'Geylang East Central  by Geylang East Avenue 2',103.88899,1.31923,1,1),(877,'Victoria Street by Bras Basah Road',103.852,1.29587,1,1),(878,'Tampines Avenue 7 by Tampines Avenue 4',103.94647,1.35664,1,1),(879,'Marymount Road by Sin Ming Avenue',103.841576,1.360407,1,1),(880,'Yio Chu Kang Road by Yio Chu Kang Link',103.87419,1.35854,1,1),(881,'Adam Road by Dunearn Road',103.81363,1.32403,1,1),(882,'Tuas South Avenue 3 by Tuas South Avenue 6',103.624857,1.303611,1,1),(883,'Mandai Road by (into) BKE',103.772049,1.409779,1,1),(884,'Serangoon Avenue 2 by Lorong Chuan',103.867746,1.355126,1,1),(885,'Mandai Road by Mandai Lake Road',103.7784,1.41092,1,1),(886,'Jurong West Avenue 1 by Jurong West Street 42',103.71705,1.35049,1,1),(887,'Jurong West Avenue 1 by Jurong West Street 52',103.717325,1.350426,1,1),(888,'West Coast Road by Clementi Avenue 2',103.76324,1.30675,1,1),(889,'West Coast Road by Clementi West Street 2',103.76355,1.30624,1,1),(890,'Victoria Street by Rochor Road',103.855775,1.300658,1,1),(891,'Victoria Street by Arab Street',103.857712,1.302803,1,1),(892,'Victoria Street by Ophir Road',103.857095,1.301905,1,1),(893,'Lavender Street by Jln Besar',103.860289,1.313931,1,1),(894,'Ophir Road by Beach Road',103.859358,1.299856,1,1),(895,'Commonwealth Avenue by Holland Avenue',103.793156,1.306667,1,1),(896,'Jurong West Avenue 2 by Corporation Road',103.712482,1.351786,1,1),(897,'Boon Lay Way by Jurong East Central',103.740464,1.337136,1,1),(898,'Holland Road by Sixth Avenue',103.787351,1.317598,1,1),(899,'Upper Aljiunied Road by Cedar Avenue',103.878014,1.335094,1,1),(900,'Yio Chu Kang Road by Munshi Abdullah Avenue',103.831361,1.380109,1,1),(901,'Jurong West Avenue 2 by Jln Boon Lay',103.705395,1.347171,1,1),(902,'Corporation Road by Jurong West Avenue 1',103.7145,1.348683,1,1),(903,'Bukit Batok East Avenue 3 by Bukit Batok St 21',103.756222,1.347343,1,1),(904,'Bukit Panjang Ring Road by Bangkit Road',103.77252,1.37956,1,1),(905,'Upper Changi Road East by Xilin Avenue',103.95517,1.332554,1,1),(906,'Tampines Avenue 1 by Tampines Avenue 5',103.942708,1.34637,1,1),(907,'Tampines Avenue 5 by Tampines Avenue 1',103.94297,1.346032,1,1),(908,'Changi Road by Jalan Eunos',103.90537,1.317811,1,1),(909,'Jln Eunos by Changi Road',103.905248,1.318054,1,1),(910,'Loyang Avenue by Pasir Ris Drive 1',103.964436,1.363627,1,1),(911,'Marymount Road by Shunfu Road',103.83974,1.351031,1,1),(912,'Upp Bukit Timah Road by Old Jurong Road',103.77047,1.34854,1,1),(913,'Lower Delta Road by Jalan Bt Merah',103.823692,1.281559,1,1),(914,'Bedok South Avenue 1 by Bedok South Road',103.932833,1.319524,1,1),(915,'Marymount Road by Thomson Road',103.837726,1.339357,1,1),(916,'Pasir Ris Drive 1 by Pasir Ris St 12',103.959783,1.367894,1,1),(917,'Newton Road by Thomson Road',103.843255,1.319202,1,1),(918,'Woodlands Avenue 3 by Woodlands Avenue 1',103.773163,1.432235,1,1),(919,'Lornie Road by Sime Road',103.818723,1.335954,1,1),(920,'Adam Road by Sime Road',103.818411,1.335612,1,1),(921,'Sengkang East Dr by Sengkang East Avenue',103.90347,1.38208,1,1),(922,'Tampines Road by Hougang Avenue 1',103.888702,1.361,1,1),(923,'Buangkok Green by Hougang St 51',103.887016,1.380544,1,1),(924,'Bukit Panjang Road by Gangsa Road',103.767647,1.379621,1,1),(925,'Ang Mo Kio Avenue 6 by Ang Mo Kio Avenue 8',103.844361,1.381201,1,1),(926,'Marine Parade Road by Still Road South',103.90963,1.305399,1,1),(927,'Lor Ah Soo by Hougang Avenue 1',103.889126,1.352726,1,1),(928,'Tampines Avenue 2 by Tampines Street 31',103.954345,1.352109,1,1),(929,'Bedok North Avenue 1 by Bedok North Street 1',103.927104,1.326887,1,1),(930,'Bukit Panjang Road by Bangkit Road',103.773613,1.376606,1,1),(931,'Ang Mo Kio Avenue 1 by Ang Mo Kio Avenue 10',103.853732,1.358733,1,1),(932,'Toa Payoh Lor 6 by Kim Keat Link',103.854983,1.331751,1,1),(933,'Paterson Hill by Grange Road',103.82992,1.29967,1,1),(934,'Guillemard Road Road by Geylang Lorong 22',103.882759,1.310597,1,1),(935,'Upper East Coast Road by Bedok South Avenue 1',103.933359,1.314386,1,1),(936,'Hougang Avenue 2 by Hougang Avenue 10',103.887146,1.36813,1,1),(937,'Bedok Reservoir Road by Bedok North Avenue 3',103.932718,1.337923,1,1),(938,'Loyang Avenue by TPE',103.96112,1.360277,1,1),(939,'Hougang Avenue 2 by Hougang Avenue 8',103.880196,1.369107,1,1),(940,'Ulu Pandan Road by Pandan Valley',103.776721,1.320995,1,1),(941,'Sengkang East Dr by Sengkang East Way',103.90778,1.38649,1,1),(942,'Jalan Bahar by PIE',103.698076,1.35477,1,1),(943,'Lower Delta Road by Bukit Purmei Avenue',103.823053,1.274871,1,1),(944,'Tiong Bahru Road by Lower Delta Road',103.82564,1.286129,1,1),(945,'Hougang Avenue 3 by Defu Avenue 1',103.890921,1.345813,1,1),(946,'Corporation Road by Boon Lay Avenue',103.712502,1.350192,1,1),(947,'Ang Mo Kio Avenue 6 by Ang Mo Kio Avenue 5',103.844774,1.377585,1,1),(948,'Holland Road by Holland Grove Road',103.784081,1.318542,1,1),(949,'Sengkang East Avenue by Sengkang Central',103.894076,1.386635,1,1),(950,'Bedok North Road by Bedok North Avenue 2',103.93025,1.33065,1,1),(951,'Bedok North Road by Bedok North Avenue 2',103.930841,1.330578,1,1),(952,'Sims Avenue by Geylang Lorong 1',103.872055,1.311425,1,1),(953,'New Upper Changi Road by Bedok South Road',103.92756,1.32355,1,1),(954,'New Upper Changi Road by Chai Chee Road',103.9231,1.32279,1,1),(955,'Havelock Road by Outram Road',103.835163,1.289335,1,1),(956,'Tampines Avenue 10 by (Into) TPE',103.93153,1.37552,1,1),(957,'Jalan Boon Lay by Jalan Ahmad Ibrahim',103.710015,1.322125,1,1),(958,'Tampines Avenue 2 by Tampines Street 22',103.95111,1.34983,1,1),(959,'Tampines Street 41 by Tampines Avenue 7',103.946347,1.356923,1,1),(960,'Yishun Avenue 1 by Sembawang Road',103.823262,1.413141,1,1),(961,'Woodlands Street 32 by Woodlands Avenue 3',103.77936,1.43222,1,1),(962,'Woodlands Avenue 5 by Woodlands Drive 50',103.790792,1.435468,1,1),(963,'Yio Chu Kang Road by Hougang Avenue 9',103.877032,1.374682,1,1),(964,'Yio Chu Kang Road by Thomson Hills Drive',103.83168,1.38004,1,1),(965,'Jalan Boon Lay by Jurong West Avenue 4',103.70517,1.34679,1,1),(966,'Sembawang Drive by Admiralty Drive',103.817693,1.451002,1,1),(967,'Jurong West Street 63 by Jurong West Street 64',103.70436,1.33828,1,1),(968,'Jurong West Avenue 1 by Corporation Road',103.714617,1.348953,1,1),(969,'Tuas West Road by Tuas Avenue 7',103.64298,1.33481,1,1),(970,'Pasir Ris Drive 1 by Pasir Ris Street 11',103.96245,1.36508,1,1),(971,'Punggol Way by Punggol Central',103.897868,1.406639,1,1),(972,'Jurong West Avenue 2 by Jurong West Street 23',103.70931,1.34983,1,1),(973,'Boon Lay Way by Jurong East Street 11',103.747038,1.331228,1,1),(974,'Lim Chu Kang Road by Old Choa Chu Kang Road',103.695268,1.374057,1,1),(975,'Boundary Road by Yio Chu Kang Link',103.873318,1.355733,1,1),(976,'Upper Thomson Road by Marymount Lane',103.83783,1.34799,1,1),(977,'Upper Serangoon Road by Upper Aljunied Road',103.870765,1.339773,1,1),(978,'Lornie Road by Thomson Road',103.83551,1.34173,1,1),(979,'Sembawang Avenue by Sembawang Drive',103.821183,1.443164,1,1),(980,'Paya Lebar Road by Macpherson Road',103.88881,1.33327,1,1),(981,'West Coast Highway by Pandan Crescent',103.7619,1.30263,1,1),(982,'Toh Guan Road by Boon Lay Way',103.74712,1.33176,1,1),(983,'Jurong Town Hall Road by Teban Gardens Road',103.74508,1.32022,1,1),(984,'Upper Changi Road by Simei Avenue',103.952669,1.335107,1,1),(985,'Yishun Avenue 2 by Yishun Avenue 7',103.833068,1.438007,1,1),(986,'Rivervale Drive by Sengkang East Way',103.905872,1.388546,1,1),(987,'Upper Thomson Road by Tagore Road',103.82615,1.38232,1,1),(988,'Ang Mo Kio Avenue 6 by Ang Mo Kio Avenue 9',103.843138,1.38431,1,1),(989,'Sengkang East Road by Sengkang Square',103.893006,1.391505,1,1),(990,'Commonwealth Avenue West by Ghim Moh Road',103.78924,1.3083,1,1),(991,'Tuas South Avenue 3 by Tuas Soth Avenue 1',103.626391,1.307252,1,1),(992,'Choa Chu Kang Way by Choa Chu Kang North 6',103.74269,1.39673,1,1),(993,'Jurong West Street 61 by Jurong West Street 63',103.698714,1.338063,1,1),(994,'Corporation Road by Boon Lay Drive',103.71602,1.345654,1,1),(995,'Lor Chuan by Ang Mo Kio Avenue 1',103.864577000393,1.35396800014471,1,1),(996,'Upper Thomson Road by Marymount Lane',103.837470999953,1.3486580000838,1,1),(997,'Ang Mo Kio Avenue 8 by Ang Mo Kio Avenue 3',103.849785000311,1.36855999965943,1,1),(998,'Woodlands Avenue 7 by Woodlands Drive 72',103.804595999931,1.44274500015019,1,1),(999,'Woodlands Avenue 12 by Woodlands Avenue 1',103.79860700018,1.42663500044248,1,1),(1000,'Ang Mo Kio Avenue 1 by Ang Mo Kio Avenue 6',103.842540000036,1.36445000044357,1,1),(1001,'Sembawang Drive by Sembawang Vista',103.819464999888,1.44673600037853,1,1),(1002,'Tampines Avenue 9 by Tampines Street 44',103.954800000308,1.36121999962832,1,1),(1003,'Tampines Avenue 9 by Tampines Street 45',103.954570000092,1.36159999990212,1,1),(1004,'Jalan Bukit Merah by Lower Delta Road',103.823525000107,1.28220899959679,1,1),(1005,'Tampines Avenue 8 by Tampines Avenue 3',103.930250000049,1.35189999983703,1,1),(1006,'Holland Road  by North Buona Vista Road',103.793089999804,1.31360999967518,1,1),(1007,'Jurong West Street 64  by Jurong West Street 63',103.704472999969,1.33850699971587,1,1),(1008,'Lower Delta Road by Tiong Bahru Road',103.825543000312,1.28640799996864,1,1),(1009,'Woodlands Avenue 7 by Woodlands Street 83',103.79156700026,1.43912899984472,1,1),(1010,'Jurong Town Hall Road by Pandan Gardens',103.745250000391,1.32097000021095,1,1),(1011,'Ang Mo Kio Avenue 3 by Ang Mo Kio Industrial Park 2',103.863685000236,1.37035700028097,1,1),(1012,'Boundary Road by Serangoon Avenue 4',103.871500000411,1.35566000020078,1,1),(1013,'Toa Payoh Lorong 6 by Toa Payoh East',103.856166999638,1.33407000021406,1,1),(1014,'Upper Thomson Road by (Into) SLE',103.818739999796,1.39386000007875,1,1),(1015,'Ang Mo Kio Avenue 8 by Ang Mo Kio Street 31',103.851246999827,1.36617800037069,1,1),(1016,'Buyong Road by Orchard Road',103.843050000159,1.30014999971161,1,1),(1017,'Woodlands Avenue 6 by Woodlands Street 64',103.8034220002,1.43620499980611,1,1),(1018,'Lavender Street by Kallang Road',103.864030000028,1.30891000042184,1,1),(1019,'Farrer Road by Holland Road',103.803740000324,1.31111000005587,1,1),(1020,'Dunearn Road by Adam Road',103.813180000293,1.32395999969025,1,1),(1021,' Bedok North Avenue 1 by Chai Chee Street',103.926829999916,1.32654200021839,1,1),(1022,'International Road by Corporation Road',103.720822000434,1.33170899977093,1,1),(1023,'Mandai Road by Mandai Lake Road',103.77882399978,1.41079400043152,1,1),(1024,'Macpherson Road  by Genting Lane',103.872770000357,1.32906000004178,1,1),(1025,'Toa Payoh Lorong 6 by Kim Keat Link',103.854480000124,1.33177599987043,1,1),(1026,'Holland Avenue by Taman Warna',103.795854000255,1.31048700002531,1,1),(1027,'AYE (exit) by Jurong Town Hall Road',103.745649999764,1.32380600004338,1,1),(1028,'Orchard Boulevard by Tomlinson Road',103.826350000097,1.30342999970043,1,1),(1029,'Sin Ming  Avenue  by Marymount Road',103.841655999958,1.36098400025344,1,1),(1030,'Brickland Road by Choa Chu Kang Avenue 6',103.741140000224,1.37271900033534,1,1),(1031,'Chua Chu Kang Avenue 4 by Chua Chu Kang Avenue 1',103.738131000262,1.3802880001173,1,1),(1032,'Geylang Lorong 1 by Sims Avenue',103.872178999809,1.31145200022409,1,1),(1033,'Hougang Avenue 2 by Hougang Avenue 10',103.886839999733,1.36771999997973,1,1),(1034,'Boundary Road by Serangoon Avenue 2',103.868099999582,1.35533999994056,1,1),(1035,'Tuas South Avenue 3 by Tuas South Street 2',103.627159999621,1.30941000035609,1,1),(1036,'Hougang Avenue 6 by Hougang Avenue 8',103.892691000161,1.37660800033367,1,1),(1037,'Punggol Field by Punggol Walk',103.899620000253,1.40246000038539,1,1),(1038,'Sengkang West Road  by Sengkang West Avenue',103.869289999776,1.39161000038889,1,1),(1039,'Sengkang Central by Sengkang East Avenue',103.893997999596,1.38683799993721,1,1),(1040,'Punggol Drive by Punggol Road',103.907489999851,1.40606000037942,1,1),(1041,'Sengkang East Road by Compassvale Bow',103.890010000275,1.38391999983424,1,1),(1042,'Sengkang East Road by TPE',103.89568299991,1.39928399997362,1,1),(1043,'Sengkang East Way by Sengkang East Drive',103.907344999728,1.38663099974504,1,1),(1044,'Sengkang East Avenue by Rivervale Drive',103.902021000401,1.38158300040089,1,1),(1045,'Holland Road towards Farrer Road',103.810180971797,1.30809473930226,0,1),(1046,'Holland Road towards Grange Road',103.81020792848,1.30806760861228,0,1),(1047,'Yishun Avenue 2 towards Lentor Avenue',103.832285202807,1.41529819611881,0,1),(1048,'Yishun Avenue 2 towards Sembawang Road',103.832312160422,1.41532532704901,0,1),(1049,'Bukit Batok Road towards Chua Chu Kang Road',103.7415209692,1.3619479958435,0,1),(1050,'Bukit Batok Road towards Jurong Town Hall Road',103.741547925191,1.36197512777203,0,1),(1051,'Boon Lay Way towards Commonwealth Avenue West',103.725491310893,1.34473730365346,0,1),(1052,'Boon Lay Way towards Jalan Boon Lay',103.725518267281,1.34474634848035,0,1),(1053,'Ayer Rajah Expressway towards City',103.78167924014,1.29823673972661,0,1),(1054,'Ayer Rajah Expressway towards Jurong',103.781724167708,1.2982186533609,0,1),(1055,'Bukit Timah Expressway towards Pan Island Expressway',103.775486299165,1.38232437354741,0,1),(1056,'Bukit Timah Expressway towards Woodlands',103.775522244554,1.382224894418,0,1),(1057,'Pan Island Expressway towards East Coast Parkway',103.810737825993,1.33525281258995,0,1),(1058,'Pan Island Expressway towards Tuas',103.810773768625,1.3352166383431,0,1),(1059,'Seletar Expressway towards Bukit Timah Expressway',103.80811327302,1.4083073256175,0,1),(1060,'Seletar Expressway towards Central Expressway',103.808149217577,1.40819880234007,0,1),(1061,'Upper Thomson Road towards Lornie Road',103.820352116247,1.38688304216287,0,1),(1062,'Upper Thomson Road towards Sembawang Road',103.820397045181,1.38686495512772,0,1),(1063,'Yishun Avenue 1 towards Lentor Avenue',103.841316003183,1.41389641843669,0,1),(1064,'Loyang Avenue towards Tampines Avenue 7',103.970098793517,1.3692078755048,0,1);
/*!40000 ALTER TABLE `camera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lta_personnel`
--

DROP TABLE IF EXISTS `lta_personnel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lta_personnel` (
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  CONSTRAINT `fk_lta_personnel_id` FOREIGN KEY (`id`) REFERENCES `auth_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lta_personnel`
--

LOCK TABLES `lta_personnel` WRITE;
/*!40000 ALTER TABLE `lta_personnel` DISABLE KEYS */;
INSERT INTO `lta_personnel` VALUES (1),(2),(3),(4),(5),(6),(7);
/*!40000 ALTER TABLE `lta_personnel` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-22 18:20:28
