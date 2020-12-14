package org.jesuitasrioja.helloworld.controles;

import java.util.ArrayList;
import java.util.List;

import org.jesuitasrioja.helloworld.modelo.Producto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {
	
	@GetMapping("/productos")
	public List<Producto> allProducts(){
		List<Producto> listReturn = new ArrayList<Producto>();
		listReturn.add(new Producto("0", "Patata", 99999.9999));
		listReturn.add(new Producto("1", "Macarrones", 99999.9999));
		listReturn.add(new Producto("2", "helados", 99999.9999));
		listReturn.add(new Producto("3", "pescaito frito", 99999.9999));
		listReturn.add(new Producto("4", "no se que mas poner", 99999.9999));
		listReturn.add(new Producto("5", "holo", 99999.9999));
		
		return listReturn;
	}
	
	@GetMapping("producto/{id}")
	public Producto getProducto(@PathVariable String id) {
		return new Producto(id, "patata", 5.2);
	}

	@PostMapping("/producto")
	public String postProducto(@RequestBody Producto newProduct) {
		return "Añadido"+newProduct.toString();
	}
	
	@PutMapping("/producto")
	public String putProducto(@RequestBody Producto editProduct) {
		return null;
	} 
	
	@DeleteMapping("/producto")
	public String deleteProduct(@PathVariable Producto deleteProduct) {
		return null;
	}
//asdasdasd	
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


