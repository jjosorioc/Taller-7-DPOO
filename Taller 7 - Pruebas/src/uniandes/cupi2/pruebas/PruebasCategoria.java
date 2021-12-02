package uniandes.cupi2.pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.cupi2.almacen.mundo.AlmacenException;
import uniandes.cupi2.almacen.mundo.Categoria;
import uniandes.cupi2.almacen.mundo.NodoAlmacen;

class PruebasCategoria {
	
	private Categoria categoria;
	public Categoria nuevaCategoria;
	
	@BeforeEach
	public void setup() throws AlmacenException
	{
		categoria = new Categoria ("Categoria", "Categoria");
		nuevaCategoria = new Categoria ("Categoria1","Categoria1");
		categoria.agregarNodo(categoria.darIdentificador(),nuevaCategoria);
	}

	@Test
	public void testAgregarNodo() {
		
		assertEquals(categoria.darNodos().size(),1);
		
		}
	
	@Test
	public void testBuscarNodo() {
		
		NodoAlmacen buscada = categoria.buscarNodo(nuevaCategoria.darIdentificador());
		assertEquals(buscada,nuevaCategoria);
		
	}
	
	@Test
	public void testDarValorVentas() {
		
		fail("Not yet implemented");
	}
	
	@Test
	public void testEliminarNodo() {
		
		categoria.eliminarNodo(nuevaCategoria.darIdentificador());
		assertFalse(categoria.darNodos().contains(nuevaCategoria));
		
	}
	
	@Test
	public void testBuscarProducto() {
		
		fail("Not yet implemented");
	}
	
	@Test
	public void testBuscarPadre() {
		
		Categoria padre = categoria.buscarPadre(nuevaCategoria.darIdentificador());
		assertEquals(categoria,padre);
		
	}
	
	@Test
	public void testDarProductos() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testDarMarcas() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testDarPreorden() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testDarPosorden() {
		fail("Not yet implemented");
	}
}
