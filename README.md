# beersafe training application 

This is a beersafe app to train your web security skills.
It is built for the  Web Security Fundamentals course on EDX:

https://courses.edx.org/courses/course-v1:KULeuvenX+WEBSECx+3T2017/

The course offers a VirtualBox image with a pre-installed version of the training application and its tools. The instructions listed here are for installing the training applicaiton on your own machine.

## Installation

Start with a fresh Linux installation, then install git.

### Step 1. Git and Cloning

```
sudo apt-get install git
```

Next clone the application:

```
git clone https://github.com/philippederyck/mooc-websecurity-beersafe-3T2017.git beersafe
```

- it will be saved in the `beersafe` folder.

### Step 2. Install Eclipse.

Download Eclipse IDE from here: https://www.eclipse.org/downloads/

Then extract it with `tar xzvf eclipse-inst-*.tar.gz`, and cd into it:
`cd eclipse-installer`.  

Run eclipse installer `./eclipse-inst`. 

Navigate to the installer menu on the top-right and select update to 
*update the installer* itself. After the update, proceed to installing 
eclipse for Java EE developers.

Grab a coffee, it takes forever to install it... 😴

### Step 3. Import the project to Eclipse.

Run eclipse, navigate to the Project Explorer and select import existing Maven
project in there. Navigate to where you cloned the 
beersafe to and enter the beersafe folder in there. Import.

Have I already told you that it takes forever with eclipse?
Wait... 😴

You can go ahead to the step 4 before the import ends.

### Step 4. Install MySQL Server 

With this command:
```
sudo apt-get install mysql-server
```

We will have to create a database, a user for it and 
grant permissions. Then we will have to import the 
sql dump of beersafe with test data in there.

We do it from the console.

```
mysql -u root -p
```
 - enter the password you set when installing mysql server.

```
mysql> CREATE DATABASE beersafe;
Query OK, 1 row affected (0.03 sec)

mysql> CREATE USER 'beersafe'@'localhost' IDENTIFIED BY 'usestrongpasswordhere123&*(';
Query OK, 0 rows affected (0.04 sec)

mysql> GRANT ALL ON beersafe.* TO 'beersafe'@'localhost';
Query OK, 0 rows affected (0.00 sec)
```

Quit the MySQL console with Ctrl+D now.

This is how you input the sql sample data:
```
cd beersafe/beersafe/src/main/resources/
mysql -u beersafe -p beersafe < beersafe.sql
#    ^ this is the user  ^ this is the database name
# now you enter your password  usestrongpasswordhere123&*(
# do not use this password for obvious reasons
Enter Password:
...
```

Control the import:
```
websec@websec:~$ mysql -u beersafe -p beersafe
Enter password: ...
...
Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> SHOW TABLES;
+--------------------+
| Tables_in_beersafe |
+--------------------+
| Beers              |
| Notes              |
| Users              |
+--------------------+
3 rows in set (0.00 sec)

mysql> SELECT * FROM Users;
+----+----------------------------------+------------------------------------------+------------------+
| id | email                            | password                                 | name             |
+----+----------------------------------+------------------------------------------+------------------+
|  1 | info@beersafe.eu                 | ......................................... | Philippe De Ryck |
|  2 | ti-torres@autozone-inc.info      | ......................................... | Tillie Torres    |
...
+----+----------------------------------+------------------------------------------+------------------+
7 rows in set (0.00 sec)
```

You must be almost done now.

Let's go back to Eclipse and connect the app to our new database.

### Step 5. Connect App to the Database.

In the DB.java file (/beersafe/src/main/java/eu/beersafe/mooc/data/access/DB.java)

there is a harcoded url to access the database.

You have to change it to something like this:

```
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BeerSafe?allowMultiQueries=true","beersafe","usestrongpasswordhere123&*(");
```

Here you address again the db name, the use and his password.

### Step 6. Run the app. 

In Eclipse again, click on the Project and select there Run As .. Java Application in the drop-down 
menu.

Pick the BeerSafeApplication class as the starter class.

Visit http://127.0.0.1:8080/ to test the app.





### Step 7. Install additional tools

Follow typical installation instructions to install BeEF and Wireshark on the Linux you use


## Credits

This readme is written by Zaur Molotnikov, github.com/qutorial .
The app author is Philippe de Ryck.

Buy them a beer if you meet them.


