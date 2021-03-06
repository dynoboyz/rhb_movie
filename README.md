# Movie CRUD with spring boot
CRUD movie via Spring Boot
- Users can create a Movie - the movie should have a title, a category and a star rating (from 0.5 to 5) 
- Users can retrieve a list of movies
- Users can retrieve a single movie by ID
- Users can update a movie
- Users can delete a movie 

# step for mysql
create new database eg: rhb
run movie.sql
modify application.properties to follow correct mysql connection

# step for spring boot
using eclipse/IntelliJ IDEA, go to run configuration, add maven from template then add 'spring-boot:run' to command line then click run

# step for JUnit test
after run spring boot, go to terminal run 'mvn test'

# api detail
- POST http://localhost:8080/api/create
{
    "title": "Movie 1",
    "category": "Action",
    "rating": 5.5
}
- GET http://localhost:8080/api/
- GET http://localhost:8080/api/movie/{id}
- POST http://localhost:8080/api/update
{
    "id": "10",
    "title": "Movie Update",
    "category": "Action",
    "rating": 0.7
}
- POST http://localhost:8080/api/delete/{id}

# api response
- status 200 = OK (Get)
- status 201 = CREATED (Create new)
- status 500 = ERROR (Min/Max rating, Movie not found)
- status 405 = ERROR (Different method)
