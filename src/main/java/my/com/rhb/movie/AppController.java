package my.com.rhb.movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AppController {

	@Autowired
	private MovieService service;

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> newMovie(@RequestBody Movie movie) {
		service.save(movie);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Movie>> listMovies(Model model) {
		return new ResponseEntity<List<Movie>>(service.listAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/movie/{id}", produces = "application/json")
	public ResponseEntity<Movie> getMovie(@PathVariable(name = "id") int id) {
		return new ResponseEntity<Movie>(service.get(id), HttpStatus.OK);
	}

	@PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Void> updateMovie(@RequestBody Movie movie) {
		service.save(movie);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}", produces = "application/json")
	public ResponseEntity<Void> deleteMovie(@PathVariable(name = "id") int id) {
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
