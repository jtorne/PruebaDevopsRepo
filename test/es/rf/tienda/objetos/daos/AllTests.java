package es.rf.tienda.objetos.daos;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CategoriaDAOTest.class, ProductoDAOTest.class, UsuarioDAOTest.class, CategoriaDAOHTest.class,UsuarioDAOHTest.class})
public class AllTests {

}
