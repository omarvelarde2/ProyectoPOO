package uni.edu.pe.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uni.edu.pe.backend.dto.Objeto;
import uni.edu.pe.backend.dto.Pedido;
import uni.edu.pe.backend.dto.ObjUs;
import uni.edu.pe.backend.dto.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EndDaoImpl implements EndDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Connection conexion;

    // Se define un método para iniciar la conexión con la base de datos por medio de un try catch
    private void obtenerConexion(){
        try {
            this.conexion = jdbcTemplate.getDataSource().getConnection();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
    // Se define otro método para cerrar la conexión con la base de datos por medio de otro try catch
    private void cerrarConexion(ResultSet resultado, Statement sentencia){
        try {
            if(resultado != null) resultado.close();
            if(sentencia != null) sentencia.close();
            this.conexion.commit();
            this.conexion.close();
            this.conexion = null;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    // Se sobreescriben los métodos de dao y se implementan por medio de sentencias sql de tipo DML

    // Haciendo uso de los métodos obtener y cerrar conexión además de un extractReport() definido al final
    // Se obtiene los pedidos en una lista con un SELECT gracias a un while(.next), ejecutando el Statement
    @Override
    public List<Pedido> obtenerPedidos() {
        List<Pedido> list = new ArrayList<>();
        String sql = " SELECT * \n" +
                "FROM usuario us \n" +
                "INNER JOIN pedido ped \n" +
                "ON us.id_usuario = ped.id_usuario \n" +
                "INNER JOIN objeto obj \n" +
                "ON obj.id_objeto = ped.id_objeto;";
        try {
            obtenerConexion();
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            while (resultado.next()){
                list.add(extractReport(resultado));
            }
            cerrarConexion(resultado,sentencia);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    // Del mismo modo se registra un nuevo pedido con un INSERT, usando en este caso un PreparedStatement para previnir
    // los conocidos ataques de inyección sql, de este modo se obtienen todos los parámetros de la entrada pedido
    @Override
    public Pedido registrarPedido(Pedido pedido) {
        try{
            obtenerConexion();
            String sql = "INSERT INTO pedido(id_pedido, id_objeto, id_usuario, fecha) \n" +
                    "VALUES(?, ?, ?, ?);";
            PreparedStatement st = conexion.prepareStatement(sql);
            st.setInt(1, pedido.getId_pedido());
            st.setInt(2, pedido.getId_objeto());
            st.setInt(3, pedido.getId_usuario());
            st.setString(4, pedido.getFecha());

            st.executeUpdate();
            st.close();
            cerrarConexion(null, st);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return pedido;
    }

    // Del mismo modo que el anterior para registrar un nuevo objeto
    @Override
    public Objeto insertarObjeto(Objeto objeto) {
        try{
            obtenerConexion();
            String sql = "INSERT INTO objeto(id_objeto, id_vendedor, nombre, descripcion, precio) \n" +
                    "VALUES(?, ?, ?, ?, ?);";
            PreparedStatement st = conexion.prepareStatement(sql);
            st.setInt(1, objeto.getId_objeto());
            st.setInt(2, objeto.getId_vendedor());
            st.setString(3, objeto.getNombre());
            st.setString(4, objeto.getDescripcion());
            st.setDouble(5, objeto.getPrecio());

            st.executeUpdate();
            st.close();
            cerrarConexion(null, st);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return objeto;
    }

    // Nuevamente lo mismo para el registro de usuario
    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        try{
            obtenerConexion();
            String sql = "INSERT INTO usuario(id_usuario, nombre, tarjeta, saldo, telefono, direccion) \n" +
                    "VALUES(?, ?, ?, ?, ?, ?);";
            PreparedStatement st = conexion.prepareStatement(sql);
            st.setInt(1, usuario.getId_usuario());
            st.setString(2, usuario.getNombre());
            st.setString(3, usuario.getTarjeta());
            st.setDouble(4, usuario.getSaldo());
            st.setString(5, usuario.getTelefono());
            st.setString(6, usuario.getDireccion());

            st.executeUpdate();
            st.close();
            cerrarConexion(null, st);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return usuario;
    }

    // Similar a los anteriores, se actualiza el precio de un objeto con un UPDATE haciendo coincidir los id
    @Override
    public ObjUs actualizarPrecioObjeto(ObjUs objus) {
        try {
            obtenerConexion();
            String sql = "UPDATE objeto AS obj \n" +
                    "INNER JOIN usuario AS us \n" +
                    "ON obj.id_vendedor = us.id_usuario \n" +
                    "SET obj.precio = ? \n" +
                    "WHERE obj.nombre LIKE ?;";
            PreparedStatement st = conexion.prepareStatement(sql);
            st.setDouble(1, objus.getPrecio());
            st.setString(2, objus.getNombre());
            st.executeUpdate();
            st.close();
            cerrarConexion(null, st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objus;
    }

    // Análogamente, se elimina un pedido con un DELETE haciendo coincidir los id
    @Override
    public String eliminarPedido(int id_objeto) {
        try {
            obtenerConexion();
            String sql = "DELETE FROM pedido \n" +
                    "WHERE id_objeto = ? \n" +
                    "AND id_usuario = ( \n" +
                    "SELECT id_usuario \n" +
                    "FROM usuario);";
            PreparedStatement st = conexion.prepareStatement(sql);
            st.setInt(1, id_objeto);
            st.executeUpdate();
            st.close();
            cerrarConexion(null, st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Eliminado";
    }

    // Del mismo modo se elimina un objeto, haciendo la eliminación de algún posible pedido primero
    @Override
    public String eliminarObjeto(int id_objeto) {
        try {
            obtenerConexion();
            eliminarPedido(id_objeto);
            String sql = "DELETE FROM objeto \n" +
                    "WHERE id_objeto = ? \n" +
                    "AND id_vendedor = ( \n" +
                    "SELECT id_ usuario \n" +
                    "FROM usuario);";
            PreparedStatement st = conexion.prepareStatement(sql);
            st.setInt(1, id_objeto);
            st.executeUpdate();
            st.close();
            cerrarConexion(null, st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Eliminado";
    }

    // Método para extraer un único reporte de pedido
    private Pedido extractReport(ResultSet resultado) throws SQLException {
        Pedido report = new Pedido(
                resultado.getInt("id_pedido"),
                resultado.getInt("id_objeto"),
                resultado.getInt("id_usuario"),
                resultado.getString("fecha")
        );
        return report;
    }
}
