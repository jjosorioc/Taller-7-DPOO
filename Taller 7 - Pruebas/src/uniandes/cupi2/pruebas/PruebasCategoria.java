package uniandes.cupi2.pruebas;

import org.junit.jupiter.api.Assertions.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;



import uniandes.cupi2.almacen.mundo.AlmacenException;
import uniandes.cupi2.almacen.mundo.Categoria;
import uniandes.cupi2.almacen.mundo.NodoAlmacen;
import uniandes.cupi2.almacen.mundo.Producto;

class PruebasCategoria {
	

	private Categoria categoria;

	
	@BeforeEach
	public void setup() throws AlmacenException, IOException
	{
		
		BufferedReader in = new BufferedReader( new FileReader( "./data/datos.txt" ) );
        categoria = new Categoria( in.readLine( ), in );
		//categoria.agregarNodo(categoria.darIdentificador(),nuevaCategoria);
	}
	
	@Test
	public void testCrearCategoria () {
		
		Categoria nuevaCategoria = new Categoria("Categoria1","Categoria1");
		assertNotNull(nuevaCategoria.darIdentificador());
		assertNotNull(nuevaCategoria.darNombre());
	}
	
	
	@ParameterizedTest
	@ValueSource(strings = {"./data/datos.txt","./data/datoserroneos.txt"})
	public void testCargarCategorias(String archivo) throws AlmacenException, IOException {
		
		if (archivo.equals("./data/datos.txt"))
		{		
			BufferedReader in = new BufferedReader( new FileReader( "./data/datos.txt" ) );
			categoria = new Categoria( in.readLine( ), in );
		
			assertNotNull(categoria);
		}
		
		else
		{

		    Exception exception = assertThrows(AlmacenException.class, () -> {
		    	BufferedReader in = new BufferedReader( new FileReader( "./data/datoserroneos.txt" ) );
		        categoria = new Categoria( in.readLine( ), in );
		    });
		}
		
		
	}
	
	@ParameterizedTest
	@ValueSource(strings= {"Categoria","Marca"})
	public void testAgregarNodo1(String tipo) throws AlmacenException {
		
		categoria.agregarNodo("1",tipo,"Nuevo","Nuevo");
		assertEquals(categoria.darNodos().size(),3);
		
	}

	@ParameterizedTest
	@ValueSource(strings= {"Categoria1","1","11"})
	public void testAgregarNodo(String nodo) throws AlmacenException {
		
		Categoria nuevaCategoria = new Categoria("Categoria1","Categoria1");
		
		if (nodo.equals("Categoria1"))
		{
			categoria.agregarNodo("1",nuevaCategoria);
			assertEquals(categoria.darNodos().size(),3);

		}
		else if (nodo.equals("1"))
		{
			Exception exception = assertThrows(AlmacenException.class, () -> {
				categoria.agregarNodo(nodo,categoria);
		    });
		}
		else
		{
			categoria.agregarNodo(nodo, nuevaCategoria);
			Categoria estaCategoria = (Categoria) categoria.darNodos().get(0);
			assertEquals(estaCategoria.darNodos().size(),3);
		}
		
		}
	
	@ParameterizedTest
	@ValueSource(strings= {"1","11"})
	public void testBuscarNodo(String identificador) {
		
		NodoAlmacen nodo = categoria.buscarNodo(identificador);
		
		if (identificador.equals("1"))
		{
			assertEquals(nodo,categoria);
		}
		else
		{
			assertNotEquals(nodo,categoria);
		}
		
	}
	
	@Test
	public void testDarValorVentas() {
		
		double valor = categoria.darValorVentas();
		assertNotNull(valor);
		
	}
	
	@Test
	public void testEliminarNodo() {
		
		NodoAlmacen NodoEliminado = categoria.darNodos().get(1);
		categoria.eliminarNodo(NodoEliminado.darIdentificador());
		assertFalse(categoria.darNodos().contains(NodoEliminado));
		
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"0","7704791121"})
	public void testBuscarProducto(String codigo) {
		
		Producto producto = categoria.buscarProducto(codigo);
		
		if (codigo.equals("0"))
		{
			assertNull(producto);
		}
		else
		{
			assertNotNull(producto);
		}
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"0","11"})
	public void testBuscarPadre(String identificador) {
		
		Categoria padre = categoria.buscarPadre(identificador);
		
		if (identificador.equals("11"))
		{
			assertEquals(categoria,padre);
		}
		else
		{
			assertNull(padre);
		}
		
	}
	
	@Test
	public void testDarProductos() {
		
		NodoAlmacen nodo = categoria.darNodos().get(0);
		categoria.darProductos(nodo.darProductos());
		
	}
	
	@Test
	public void testDarMarcas() {
		
		assertNotNull(categoria.darMarcas());
	}
	
	@Test
	public void testDarPreorden() {
		
		assertNotEquals(0,categoria.darPreorden().size());
	}
	
	@Test
	public void testDarPosorden() {
		
		assertNotEquals(0,categoria.darPosorden().size());
		
	}
}
