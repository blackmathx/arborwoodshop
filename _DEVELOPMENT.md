
Setup Development Environment

1. Clone the repository.
2. Add application.properties with a single line. spring.profiles.active=dev
3. Add application-dev.properties. Add address, username, and password for the dev database.

    spring.datasource.url=jdbc:mysql://192.168.0.X:3306/arborwoodshop-dev
    spring.datasource.username=user
    spring.datasource.password=pass
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    server.shutdown=graceful
    spring.lifecycle.timeout-per-shutdown-phase=20s
    
    ##### log path
    logging.file.name=/home/ec2-user/logs/application.log
    
    ##### set the log file max size before rotating
    logging.file.max-size=1MB
    
    logging.level.root=info
    logging.level.org.springframework.web=info
    logging.level.org.springframework.jdbc=info
    logging.level.com.arborwoodshop=info

4. To set up a database, the schema.sql and data.sql can be run to first create the schema then add data. Spring Data
   JDBC doesn't offer schema generation so these files must be put in an sql editor.
5. The project contains src/main/frontend with Tailwind CSS installed. A new source clone will require "npm install". 
   Then in development, the project.json contains a line for --watch mode: npm run watch, and changes will be reflected 
   during development.

