package es.rf.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.rf.tienda.dominio.Producto;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.objetos.daos.ProductoDAOH;
import es.rf.tienda.util.SentenciasSQL;

/**
 * Servlet implementation class ProductosController
 */
@WebServlet("/Producto")
public class ProductosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductosController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orden= request.getParameter("orden");
		if(orden==null || orden.equals("listado")){
			HttpSession se= request.getSession();
			ProductoDAOH dao = new ProductoDAOH();
			List<Producto> lista = null;
			try {
				lista=dao.leerTodos();
				if(lista!=null){
					se.setAttribute("listado", lista);
				}
			} catch (DAOException e) {
				e.printStackTrace();
			}
			response.sendRedirect("listaProductos.jsp");
		}
		else if(orden!=null && orden.equals("crear")){
			ProductoDAOH dao = new ProductoDAOH();
			Producto p= new Producto();
			HttpSession se= request.getSession();
			String ID= request.getParameter("idProd").toString();
			String descripcion= request.getParameter("descripcionProd").toString();
			String descripcionLarga= request.getParameter("descripcionLargaProd").toString();
			String precio= request.getParameter("precioProd");
			String uniVenProd= request.getParameter("uniVenProd").toString();
			String paisProd= request.getParameter("paisProd").toString();
			String idCatProd= request.getParameter("idCatProd").toString();
			String stockProd= request.getParameter("stockProd").toString();
			String usoRecProd= request.getParameter("usoRecProd").toString();
			String stkResProd= request.getParameter("stkResProd").toString();
			String stkAltProd= request.getParameter("stkAltProd").toString();
			String stkBajoProd= request.getParameter("stkBajoProd").toString();
			String estadoProd= request.getParameter("estadoProd").toString();
			if(ID!=null && !(ID.equals("")) && descripcion!=null && !(descripcion.equals(""))
						&& precio!=null && !(precio.equals("")) && uniVenProd!=null && !(uniVenProd.equals(""))
						&& paisProd!=null && !(paisProd.equals("")) && idCatProd!=null && !(idCatProd.equals(""))){
				if(estadoProd==null || (estadoProd.equals("")) || estadoProd.equals("A") || estadoProd.equals("B")){
					try {
							p.setId_producto(ID);
							p.setPro_descripcion(descripcion);
							p.setPro_precio(Double.parseDouble(precio));
							p.setPro_uniVenta(uniVenProd);
							p.setId_pais(Integer.parseInt(paisProd));
							p.setId_categoria(Integer.parseInt(idCatProd));
							if(descripcionLarga!=null && !(descripcionLarga.equals(""))){
								p.setPro_desLarga(descripcionLarga);
							}
							if(descripcionLarga!=null && !(descripcionLarga.equals(""))){
								p.setPro_desLarga(descripcionLarga);
							}
							if(stockProd!=null && !(stockProd.equals(""))){
								p.setPro_stock(Integer.parseInt(stockProd));
							}
							if(usoRecProd!=null && !(usoRecProd.equals(""))){
								p.setPro_usoRecomendado(usoRecProd);
							}
							if(stkResProd!=null && !(stkResProd.equals(""))){
								p.setPro_stkReservado(Integer.parseInt(stkResProd));
							}
							if(stkAltProd!=null && !(stkAltProd.equals(""))){
								p.setPro_nStkAlto(Integer.parseInt(stkAltProd));
							}
							if(stkBajoProd!=null && !(stkBajoProd.equals(""))){
								p.setPro_nStkBajo(Integer.parseInt(stkBajoProd));
							}
							if(estadoProd!=null && !(estadoProd.equals(""))){
								p.setPro_stat(estadoProd.charAt(0));
							}
						
						try {
							dao.insertarRegistro(p);
							request.getRequestDispatcher("Producto?orden=listado").forward(request, response);
							
						} catch (DAOException | DomainException e) {
							se.setAttribute("ErrorCreateProd", e.getMessage());
							request.getRequestDispatcher("nuevoProducto.jsp").forward(request, response);
						}
					} catch (DomainException e) {
						se.setAttribute("ErrorCreateProd", e.getMessage());
						request.getRequestDispatcher("nuevoProducto.jsp").forward(request, response);
					}
				}
				else{
					se.setAttribute("ErrorCreateProd","El estado no es correcto");
					request.getRequestDispatcher("nuevoProducto.jsp").forward(request, response);
				}
			}
			else{
				se.setAttribute("ErrorCreateProd", "Faltan campos obligatorios");
				request.getRequestDispatcher("nuevoProducto.jsp").forward(request, response);
			}
		}
		else if(orden!=null && orden.equals("update")){
			if(request.getParameter("id_Prod")!=null){
				String id_Prod= request.getParameter("id_Prod");
				ProductoDAOH dao = new ProductoDAOH();
				Producto p= new Producto();
				HttpSession se= request.getSession();
				String ID= request.getParameter("id_Prod");
				String descripcion= request.getParameter("descripcionProd").toString();
				String descripcionLarga= request.getParameter("descripcionLargaProd").toString();
				String precio= request.getParameter("precioProd");
				String uniVenProd= request.getParameter("uniVenProd").toString();
				String paisProd= request.getParameter("paisProd").toString();
				String idCatProd= request.getParameter("idCatProd").toString();
				String stockProd= request.getParameter("stockProd").toString();
				String usoRecProd= request.getParameter("usoRecProd").toString();
				String stkResProd= request.getParameter("stkResProd").toString();
				String stkAltProd= request.getParameter("stkAltProd").toString();
				String stkBajoProd= request.getParameter("stkBajoProd").toString();
				if(descripcion!=null && !(descripcion.equals(""))
							&& precio!=null && !(precio.equals("")) && uniVenProd!=null && !(uniVenProd.equals(""))
							&& paisProd!=null && !(paisProd.equals("")) && idCatProd!=null && !(idCatProd.equals(""))){
			
						try {
								p.setId_producto(id_Prod);
								p.setPro_descripcion(descripcion);
								p.setPro_precio(Double.parseDouble(precio));
								p.setPro_uniVenta(uniVenProd);
								p.setId_pais(Integer.parseInt(paisProd));
								p.setId_categoria(Integer.parseInt(idCatProd));
								if(descripcionLarga!=null && !(descripcionLarga.equals(""))){
									p.setPro_desLarga(descripcionLarga);
								}
								if(descripcionLarga!=null && !(descripcionLarga.equals(""))){
									p.setPro_desLarga(descripcionLarga);
								}
								if(stockProd!=null && !(stockProd.equals(""))){
									p.setPro_stock(Integer.parseInt(stockProd));
								}
								if(usoRecProd!=null && !(usoRecProd.equals(""))){
									p.setPro_usoRecomendado(usoRecProd);
								}
								if(stkResProd!=null && !(stkResProd.equals(""))){
									p.setPro_stkReservado(Integer.parseInt(stkResProd));
								}
								if(stkAltProd!=null && !(stkAltProd.equals(""))){
									p.setPro_nStkAlto(Integer.parseInt(stkAltProd));
								}
								if(stkBajoProd!=null && !(stkBajoProd.equals(""))){
									p.setPro_nStkBajo(Integer.parseInt(stkBajoProd));
								}
							
							try {
								dao.actualizarRegistro(p);
								request.getRequestDispatcher("Producto?orden=listado").forward(request, response);
								
							} catch (DAOException e) {
								se.setAttribute("ErrorUpdateCat", e.getMessage());
								request.getRequestDispatcher("selectProd.jsp").forward(request, response);
							}
						} catch (DomainException e) {
							se.setAttribute("ErrorUpdateCat", e.getMessage());
							request.getRequestDispatcher("selectProd.jsp").forward(request, response);
						}
				}
				else{
					se.setAttribute("ErrorUpdateCat", "Faltan campos obligatorios");
					request.getRequestDispatcher("selectProd.jsp").forward(request, response);
				}
			}
			else{
				request.getRequestDispatcher("Producto?orden=listado").forward(request, response);
			}
		}
		else if(orden!=null && orden.equals("leerProd")){
			if(request.getParameter("id_Prod")!=null){
				String id_Prod= request.getParameter("id_Prod");
				ProductoDAOH dao = new ProductoDAOH();
				Producto p= new Producto();
				try {
					p.setId_producto(id_Prod);
					Producto pLeida= dao.leerRegistro(p);
					HttpSession se= request.getSession();
					se.setAttribute("Producto", pLeida);
					request.getRequestDispatcher("selectProd.jsp").forward(request, response);
					
				} catch (DomainException | DAOException e) {
					request.getRequestDispatcher("Producto?orden=listado").forward(request, response);
				}
			}
			else{
				request.getRequestDispatcher("Producto?orden=listado").forward(request, response);
			}
		}
		else if(orden!=null && orden.equals("leerProdElim")){
			if(request.getParameter("id_Prod")!=null){
				String id_Prod= request.getParameter("id_Prod");
				ProductoDAOH dao = new ProductoDAOH();
				Producto p= new Producto();
				try {
					p.setId_producto(id_Prod);
					Producto pLeido= dao.leerRegistro(p);
					HttpSession se= request.getSession();
					se.setAttribute("Producto", pLeido);
					request.getRequestDispatcher("eliminarProducto.jsp").forward(request, response);
					
				} catch (DomainException | DAOException e) {
					request.getRequestDispatcher("Producto?orden=listado").forward(request, response);
				}
			}
			else{
				request.getRequestDispatcher("Producto?orden=listado").forward(request, response);
			}
		}
		else if(orden!=null && orden.equals("eliminar")){
			if(request.getParameter("id_Prod")!=null){
				String id_Prod= request.getParameter("id_Prod");
				ProductoDAOH dao = new ProductoDAOH();
				Producto p= new Producto();
				try {
					p.setId_producto(id_Prod);
					dao.borrarRegistro(p);
					request.getRequestDispatcher("Producto?orden=listado").forward(request, response);
					
				} catch (DomainException | DAOException e) {
					request.getRequestDispatcher("Producto?orden=listado").forward(request, response);
				}
			}
			else{
				request.getRequestDispatcher("Producto?orden=listado").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
