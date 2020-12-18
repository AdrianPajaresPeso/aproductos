package org.jesuitasrioja.helloworld.controllers;

import java.util.List;

import org.jesuitasrioja.helloworld.modelo.Producto;
import org.jesuitasrioja.helloworld.repository.IProductosRepository;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class ProductoController {

	private IProductosRepository pr = new ProductosRepositoryMongoDB();

	@GetMapping("/productos")
	public ResponseEntity<List<Producto>> allProducts() {
		ResponseEntity<List<Producto>> re = ResponseEntity.ok(pr.getAll());
		return re;
	}

	@ApiOperation(value = "Obtener un producto basandonos en su identificador", notes = "Estas son notas sobre este metodo")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Se ha encontrado el producto"),
			@ApiResponse(code = 404, message = "No se ha encontrado el producto") })
	@GetMapping("/producto/{id}")
	public ResponseEntity<Producto> getProducto(
			@ApiParam(example = "123456789", name = "id", type = "String", value = "Id del producto que quiero obtener") @PathVariable String id) {

		Producto prtn = pr.getById(id);
		if (prtn != null) {
//			return ResponseEntity.ok(prtn);
			return ResponseEntity.status(HttpStatus.OK).body(prtn);
		} else {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(prtn);
		}

	}

	@PostMapping("/producto")
	public ResponseEntity<Object> postProducto(@RequestBody Producto nuevoProducto) {
		Boolean isAdded = pr.aniadirProducto(nuevoProducto);
		if (isAdded) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
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
		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(400).build();
		}
	}

}