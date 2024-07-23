
Project 

1. The schema.sql and data.sql can be run to first create the database schema, then add data. Spring Data JDBC doesn't 
   offer schema generation. 
2. Any changes to the data entities needs to be reflected in the schema.sql and data.sql files. Databases need updated
   manually with any changes at release time.
3. The project contains src/main/frontend with Tailwind CSS. A new source clone will require npm install. Then
   in development, the project.json contains a line for --watch mode: npm run watch, and changes will be 
   reflected during development.
4. Tailwind https://tailwindcss.com/
5. The release document has the steps to release to AWS EC2. This is in the project folder on the Black Math data drive.

