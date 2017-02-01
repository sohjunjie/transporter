This transporter project uses spring mvc web framework

## Overview

transporter - Accident Management System

### Version Information

Java

	jdk1.8.0_51
	
Spring framework

	4.3.2
	
## Quick Start

### Setup database
- Install MySQL
- Create a 'transporter' database schema
- In MySQL Workbench > Management > Data Import/Restore, import the latest dump backup into 'transporter' schema
- In \src\main\webapp\WEB-INF\jdbc.properties, setup your username, password, port as following
		
		jdbc.driverClassName=com.mysql.jdbc.Driver
		jdbc.dialect=org.hibernate.dialect.MySQLDialect
		jdbc.databaseurl=jdbc:mysql://localhost:{port}/transporter
		jdbc.username={username}
		jdbc.password={password}


- Right-click jdbc.properties then Team > Advance > Assume unchanged
