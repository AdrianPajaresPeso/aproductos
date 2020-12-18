package org.jesuitasrioja.helloworld.repository;

import java.util.List;

import org.bson.Document;
import org.jesuitasrioja.helloworld.modelo.Producto;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class ProductosRepositoryMongoDB implements IProductosRepository {
	private MongoCollection<Document> collection;

	public ProductosRepositoryMongoDB() {

		MongoClient mongo = new MongoClient("localhost:27017");
		MongoDatabase db = mongo.getDatabase("productosDB");
		collection = db.getCollection("productos");
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param Id del producto a buscar
	 */
	@Override
	public Producto getById(String id) {
		Producto productoReturn = null;
		Document productoDocument = collection.find(Filters.eq("id", id)).first();

		System.out.println("entra " + productoDocument);
		if (productoDocument != null) {
			productoReturn = document2Producto(productoDocument);
		}
		return productoReturn;
	}

	@Override
	public List<Producto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


	private Producto document2Producto(Document productoDocument) {
		Producto productoReturn;
		Gson gson = new Gson();
		String productoJson = productoDocument.toJson();
		productoReturn = gson.fromJson(productoJson, Producto.class);
		return productoReturn;
	}

	@Override
	public boolean aniadirProducto(Producto producto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarProducto(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
