-- MySQL dump 10.13  Distrib 5.5.50, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: beers_dev
-- ------------------------------------------------------
-- Server version	5.5.50-0ubuntu0.14.04.1

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
-- Table structure for table `Beers`
--

DROP TABLE IF EXISTS `Beers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Beers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `image` text,
  `alcohol` float DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `category` text,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Beers`
--

LOCK TABLES `Beers` WRITE;
/*!40000 ALTER TABLE `Beers` DISABLE KEYS */;
INSERT INTO `Beers` VALUES (1,'Duvel','duvel.png',8.5,'Blond','Strong Blond','A Duvel is still seen as the reference among strong golden ales. Its bouquet is lively and tickles the nose with an element of citrus which even tends towards grapefruit thanks to the use of only the highest-quality hop varieties. This is also reflected in the flavour, which is beautifully balanced with a hint of spiciness. Thanks to its high CO2 content, this beer has a wonderful roundness in the mouth. A Duvel is both the perfect thirst quencher and the ideal aperitif.'),(2,'Chouffe Houblon','chouffe_houblon.png',9,'Blond','IPA','he Houblon Chouffe is a crossover beer – a Belgian interpretation of the India Pale Ale (IPA), a beer style originating in England. The Houblon Chouffe starts off with a discernible citrus touch, with grapefruit prominently there, before blending into its herbal, green hoppy character. Unsurprisingly this is a determinedly bitter beer, supported by a generous alcohol content and a pleasant, drying finish. A potent and characterful beer, both for lovers of this style, as well as for real connoisseurs.'),(3,'Chimay Blue','chimay_blue.png',9,'Dark Brown','Dark Trappist','The blue Chimay has the full power and complexity of a genuine Trappist beer, and as such just begs a prime spot at the dining table. It\'s not exactly a secret that beer and cheese make an excellent pairing; this dark Chimay provides the proof. This beer belongs to the very select club of those that love to be aged – for five years or even longer. And if you manage to wait that long, your patience will be rewarded with profound aromas of aged port, thanks to a beautifully beneficial madeirisation process.'),(4,'Chimay Triple','chimay_triple.png',8,'Blond','Triple Trappist','This triple really is a drinkable proof of the complex art of brewing. It has a mainly dry character, enhanced by a floral fruitiness that brings to mind memories of a fine dry white wine. In the glass, this triple has a golden colour verging on amber. Its initial taste is a sweet one. Then you will discover a triple that\'s beautifully balanced between herbs and fruit, but one which, above all, preserves a mild and pleasantly bitter aftertaste.'),(5,'Cornet','cornet.png',8.5,'Blond','Strong Blond','Cornet will amaze you with its very subtle, wilful but refined taste of wood and a silky, mouth feel. Taste the successful balance between the fruitiness of the yeast and the vanilla sweetness coming from the wood. The long finish provides a touch of warmth accompanied by a mild bitterness.'),(6,'Liefmans Fruitesse','liefmans_fruitesse.png',3.8,'Red','Fruit Beer','Fruitesse is no classic \'kriek\'. Rather, it is a blend of beer with natural fruit juices. It is a fruit beer that iss just as a fruit beer ought to be. Fresh, and with a vivid red colour, it grabs your attention straight away, and doesn\'t let go. The same can be said for its nose. Once the aroma from the glass reaches invades your nostrils, there\'s no escaping it – an overwhelming fruit aroma, dosed with a shot of almond. The taste of the beer is satisfyingly sweet, without being syrupy, and does full justice to those red fruit aromas. Even the aftertaste does not let you get away from its luscious, fresh fruitiness . It\'s the kind of beer that could also serve as an ideal ingredient for creative cocktails.'),(7,'Lindemans Apple','lindemans_apple.png',3.5,'Blond','Fruit Lambic','Beer of spontaneous fermentation. Lindemans Apple is made from one-year-old oak-aged lambic and apple juice. The tasting experience, which is quite exceptional, offers a perfect balance between the sweetness of red apples, the freshness of green apples and the sourness of traditional lambic beers'),(8,'Lindemans Cassis','lindemans_cassis.png',3.5,'Purple Red','Fruit Lambic','Beer of spontaneous fermentation. Product of exceptional flavour and complexity, Lindemans Cassis is made from one-year-old oak-aged lambic and pure black currant juice. Purplish red with an ample creamy pink foam head. A delicious aperitif with the fresh sweet-and-sour taste of bright black currants and lambic.'),(9,'Lindemans Framboise','lindemans_framboise.png',2.5,'Deep Red','Fruit Lambic','To produce this fruit beer, 30% pure raspberry juice is blended with one-year-old lambic, hence the beautiful dark pink colour. Blending the lambic and fruit juice results in a significant reduction of the alcohol content. Framboise makes for an excellent aperitif but also lends itself to culinary use, as an ingredient for sauces for example.'),(10,'Lindemans Pecheresse','lindemans_pecheresse.png',2.5,'Blonde','Fruit Lambic','Beer of spontaneous fermentation. Pecheresse is made from one-year-old oak-aged lambic and pure peach juice. Golden blond, slightly cloudy colour. It offers a bright and full-bodied flavour as well as a nice balance between the sweet taste of the peach and the sourness of the lambic. A perfect aperitif.');
/*!40000 ALTER TABLE `Beers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Notes`
--

DROP TABLE IF EXISTS `Notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Notes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `title` text,
  `content` text,
  `public` tinyint(1) DEFAULT NULL,
  `userid` int(11) NOT NULL,
  `beerid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `beerid` (`beerid`),
  CONSTRAINT `Notes_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `Users` (`id`),
  CONSTRAINT `Notes_ibfk_2` FOREIGN KEY (`beerid`) REFERENCES `Beers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Notes`
--

LOCK TABLES `Notes` WRITE;
/*!40000 ALTER TABLE `Notes` DISABLE KEYS */;
INSERT INTO `Notes` VALUES (1,'2017-02-12 12:24:55','First Spring Beer','Really fruity, really nice. The suggestion to enjoy it on the rocks was awesome',1,1,6),(2,'2017-03-01 23:00:00','Second Spring Beer','I had my first beer this spring, and man it was good. Really fresh, bitter. The best!',0,1,1),(3,'2017-03-14 12:24:55','Duvel FTW','Blond is good, amirite?',0,1,1),(4,'2017-03-15 12:24:55','Duvel makes you forget','Wow, I had a couple of these in one evening. Talk about a bad idea. I don\'t even remember how I got home, but it was fun!',1,2,1);
/*!40000 ALTER TABLE `Notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` text,
  `password` text,
  `name` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO Users VALUES (1, "info@beersafe.eu", "9bc34549d565d9505b287de0cd20ac77be1d3f2c", "Philippe De Ryck");
INSERT INTO Users VALUES (2, "ti-torres@autozone-inc.info", "211a492a3371d6d290933b1d4f7162a9b29463f9", "Tillie Torres");
INSERT INTO Users VALUES (3, "gab.neece@progressenergyinc.info", "6C616F7C2D2FDE9018A09F06EAEFCFC7582BC7BA", "Gabor Neece");
INSERT INTO Users VALUES (4, "joschinner@example.com", "9F2FEB0F1EF425B292F2F94BC8482494DF430413", "Jovany Schinner");
INSERT INTO Users VALUES (5, "gla_con@arvinmeritor.info", "5C17FA03E6D5FC247565E1CD8FFA70E1BFE5B8D9", "Gladwin Cone");
INSERT INTO Users VALUES (6, "delsi.manrique@arvinmeritor.info", "1999E4893F732BA38B948DBE8D34ED48CD54F058", "Delsin Manriquez");
INSERT INTO Users VALUES (7, "dotty.pilk@egl-inc.info", "6C616F7C2D2FDE9018A09F06EAEFCFC7582BC7BA", "Dotty Pilkington");
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-02 10:02:14
