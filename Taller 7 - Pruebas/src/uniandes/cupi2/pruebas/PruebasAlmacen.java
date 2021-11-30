package uniandes.cupi2.pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.cupi2.almacen.mundo.Almacen;
import uniandes.cupi2.almacen.mundo.AlmacenException;
import uniandes.cupi2.almacen.mundo.Categoria;

class PruebasAlmacen
{
	public Almacen llenoAlmacen;
	
	public Almacen vacioAlmacen;
	
	public Categoria raiz;
	
	public PruebasAlmacen() throws AlmacenException
	{
	this.llenoAlmacen = new Almacen(new File("./data/datos.txt"));
	this.vacioAlmacen = new Almacen(new File("./data/empty.txt"));
	
	this.raiz = llenoAlmacen.darCategoriaRaiz();
	System.out.println(raiz); // Cupi2
	}

	@Test
	void test()
	{
		assertEquals("Cupi2", raiz.darNombre());
	}

}
