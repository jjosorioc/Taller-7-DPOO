package uniandes.cupi2.pruebas;

import uniandes.cupi2.almacen.mundo.NodoAlmacen;
import uniandes.cupi2.almacen.mundo.Almacen;
import uniandes.cupi2.almacen.mundo.AlmacenException;
import uniandes.cupi2.almacen.mundo.Categoria;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;




class PruebasDeAlmacen {
	
	private Almacen almacenVacio;
	private Almacen almacenCompleto;
	
	@BeforeEach
	public void setUp() throws Exception
	{
		almacenVacio = new Almacen(new File("./data/empty.txt"));
		almacenCompleto = new Almacen(new File("./data/datos.txt"));
	}

	@Test
	public void testInicial( )
	{
		List<NodoAlmacen> nodos = almacenVacio.darCategoriaRaiz().darPreorden();
		assertEquals(1,nodos.size(),"La cantidad de categorías no es uno");
		
		List<NodoAlmacen> nodos2 = almacenCompleto.darCategoriaRaiz().darPreorden();
		assertEquals(21,nodos2.size(),"La cantidad de categorías no es uno");
		
	}

	@Test
	public void darCategoriaRaiz( )
	{
		Categoria categoria1 = almacenVacio.darCategoriaRaiz();
		assertEquals("Cupi2",categoria1.darNombre(),categoria1 + " no corresponde al nombre de la categoría raíz.");
		
		Categoria categoria2 = almacenCompleto.darCategoriaRaiz();
		assertEquals("Cupi2",categoria2.darNombre(),categoria2 + " no corresponde al nombre de la categoría raíz.");
		
	}

	@Test
	public void agregarNodo() throws AlmacenException
	{
		almacenVacio.agregarNodo("Cupi2", "Categoria","nuevoNodo", "nuevoNodo");
		almacenVacio.buscarNodo("nuevoNodo");
		assertFalse(almacenVacio.buscarNodo("nuevoNodo") != null);
		
		almacenCompleto.agregarNodo("Televisores","Categoria", "nuevoNodo", "nuevoNodo");
		assertFalse(almacenCompleto.buscarNodo("nuevoNodo") != null);
	}

	@Test
	public void eliminarNodo() 
	{
		Categoria categoria1 = almacenVacio.darCategoriaRaiz();
		try
		{
			almacenVacio.eliminarNodo(categoria1.darIdentificador());
			fail("Se eliminó la raiz del árbol!");
		}
		catch (AlmacenException e)
		{
			// :)
		}
		
		try 
		{
			almacenCompleto.eliminarNodo("111");
			assertFalse(almacenCompleto.buscarNodo("111") != null);
			
		} 
		catch (AlmacenException e)
		{
			fail("No se pudo eliminar el nodo!");
		}
	}

	@Test
	public void venderProducto() 
	{
		almacenCompleto.venderProducto("30557851", 0);
		assertTrue(almacenCompleto.buscarNodo("1112").darValorVentas() == 0);
		
		almacenCompleto.venderProducto("30557851", 1);
		assertTrue(almacenCompleto.buscarNodo("1112").darValorVentas() != 0);
	}
	
	@Test
	public void agregarProducto()
	{
//		try {
//			almacenVacio.agregarNodo("Cupi2", "Marca","nuevoNodo", "nuevoNodo");
//			almacenVacio.agregarProducto("nuevoNodo", "nuevoNodo", "nuevoNodo", "nuevoNodo", 0);
//			assertTrue(almacenVacio.buscarNodo("nuevoNodo").darProductos().isEmpty() == false);
//		} catch (AlmacenException e) 
//		{
//			fail("No se pudo agregar el producto al almacen vacío!");
//		} aiuda
		
		try {
			almacenCompleto.agregarProducto("1112", "123", "producto", "", 700);
			List lista  = almacenCompleto.buscarNodo("1112").darProductos();
			assertTrue(lista.get(0).toString().equals("123 - producto"));
			
		} catch (AlmacenException e) {
			fail("No se pudo eliminar el producto!");
		}
		
		try {
			almacenCompleto.agregarProducto("30999801", "123", "producto", "", 700);
			fail("No se puede agregar un producto con un código existente");
		} catch (AlmacenException e) {

		}
	}
	

}
