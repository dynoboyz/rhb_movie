package my.com.rhb.movie;

import java.util.List;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

public class TestMovie {

    private String endpoint = "http://localhost:8080/api/";
    private RestTemplate template = new RestTemplate();

    @Test
    public void testCreate() {
        Movie movie = new Movie("Movie 1", "Action", (float) 4.5);
        ResponseEntity<String> response = template.postForEntity(endpoint + "/create", movie, String.class);

        assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));

        movie = new Movie("Movie 2", "Action", (float) 3.5);
        response = template.postForEntity(endpoint + "/create", movie, String.class);

        assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));
    }

    @Test
    public void testRating() {
        Movie movie = new Movie("testRating min", "Action", (float) 0.4);
        try {
            template.postForEntity(endpoint + "/create", movie, String.class);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }

        movie = new Movie("testRating max", "Action", (float) 5.1);
        try {
            template.postForEntity(endpoint + "/create", movie, String.class);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetAll() {
        ResponseEntity<List<Movie>> response = template.exchange(endpoint,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Movie>>() {});

        List<Movie> movies = response.getBody();
        assertTrue(movies.size() >= 2);
    }

    @Test
    public void testGetOne() {
        ResponseEntity<List<Movie>> responseList = template.exchange(endpoint,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Movie>>() {});
        List<Movie> movies = responseList.getBody();
        System.out.println(movies.get(0).getId());

        Movie movie = template.getForObject(endpoint + "/movie/" + movies.get(0).getId(), Movie.class);
        assertNotNull(movie);
        assertTrue(movie.getId() == movies.get(0).getId());
    }

    @Test
    public void testUpdate() {
        ResponseEntity<List<Movie>> responseList = template.exchange(endpoint,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Movie>>() {});
        List<Movie> movies = responseList.getBody();

        Movie movie = new Movie((long) movies.get(0).getId(),"testUpdate 1", "Action", (float) 5.0);
        HttpEntity<Movie> requestUpdate = new HttpEntity<>(movie);
        ResponseEntity response = template.exchange(endpoint + "/update", HttpMethod.PUT, requestUpdate, Void.class);
        assertNotNull(response);
        assertTrue(response.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void testDelete() {
        ResponseEntity<List<Movie>> responseList = template.exchange(endpoint,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Movie>>() {});
        List<Movie> movies = responseList.getBody();

        ResponseEntity response = template.exchange(endpoint + "/delete/" +  + movies.get(0).getId(), HttpMethod.DELETE, null,ResponseEntity.class);
        assertNotNull(response);
        assertTrue(response.getStatusCode() == HttpStatus.OK);
    }
}