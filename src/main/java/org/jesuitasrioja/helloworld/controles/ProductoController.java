package org.jesuitasrioja.helloworld.controles;

import java.util.List;

import org.jesuitasrioja.helloworld.modelo.Producto;
import org.jesuitasrioja.helloworld.repository.IProductosRepository;
import org.jesuitasrioja.helloworld.repository.ProductosRepository;
import org.jesuitasrioja.helloworld.repository.ProductosRepositoryMongoDB;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class ProductoController {

	private IProductosRepository pr = new ProductosRepositoryMongoDB();

	@GetMapping("/productos")
	public ResponseEntity<List<Producto>> allProducts() {
		ResponseEntity<List<Producto>> re = ResponseEntity.ok(pr.getAll());
		return re;
	}

	@GetMapping("/producto/{id}")
	public ResponseEntity<Producto> getProducto(@PathVariable String id) {
		Producto prtn = pr.getById(id);
		if (prtn != null) {
//			return ResponseEntity.ok(prtn);
			return ResponseEntity.status(HttpStatus.OK).body(prtn);
		} else {
//			return ResponseEntity.notFound().build();

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(prtn);
		}

	}

	@GetMapping("/producto")
	public Producto getProducto2(@RequestParam String id) {
		return pr.getById(id);
	}

	@PostMapping("/producto")
	public ResponseEntity<Object> postProducto(@RequestBody Producto nuevoProducto) {
		Boolean isAdded = pr.aniadirProducto(nuevoProducto);
		if(isAdded) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.status(400).build();	
		}
	}

	@PutMapping("/producto")
	public String putProducto(@RequestBody Producto editadoProducto) {
		return null;
	}

	@DeleteMapping("/producto/{id}")
	public ResponseEntity<Object> deleteProducto(@PathVariable String id) {
		Boolean isDeleted = pr.eliminarProducto(id);
		if(isDeleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.status(400).build();	
		}
	}

}
//@GetMapping("/saludar/{nombre}")
//public String getSaludo(@PathVariable String nombre) {
//	return "Hello "+nombre;
//}

//@GetMapping("/saludar")
//public String getSaludo() {
//	return "Hello ";
//}
//
//@PostMapping("/saludar")
//public String postSaludo() {
//	return "Dejame en paz POST";
//}
//
//@DeleteMapping("/saludar")
//public String deleteSaludo() {
//	return "Dejame en paz DELETE";
//}
//@PutMapping("/saludar")
//public String putSaludo() {
//	return "Dejame en paz PUT";
//}
