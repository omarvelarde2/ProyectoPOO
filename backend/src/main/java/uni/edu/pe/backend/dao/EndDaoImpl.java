package uni.edu.pe.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uni.edu.pe.backend.dto.Objeto;
import uni.edu.pe.backend.dto.Pedido;
import uni.edu.pe.backend.dto.TopOp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EndDaoImpl implements EndDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Connection conexion;

    private void obtenerConexion(){
        try {
            this.conexion = jdbcTemplate.getDataSource().getConnection();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
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


    @Override
    public List<Pedido> obtenerPedidos() {
        List<Pedido> list = new ArrayList<>();
        String sql = " SELECT * \n" +
                "FROM usuario us \n" +
                "INNER JOIN pedido ped\n" +
                "ON us.id_usuario = ped.id_usuario\n" +
                "INNER JOIN objeto obj\n" +
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

    @Override
    public Pedido registrarPedido(Pedido pedido) {
        try{
            obtenerConexion();
            String sql = "INSERT INTO pedido(id_pedido, id_objeto, id_usuario, fecha) VALUES(?, ?, ?, ?);";
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

    @Override
    public Objeto insertarObjeto(Objeto objeto) {
        try{
            obtenerConexion();
            String sql = "INSERT INTO objeto(id_objeto, nombre, descripcion, precio) VALUES(?, ?, ?, ?);";
            PreparedStatement st = conexion.prepareStatement(sql);
            st.setInt(1, objeto.getId_objeto());
            st.setString(2, objeto.getNombre());
            st.setString(3, objeto.getDescripcion());
            st.setInt(4, objeto.getPrecio());

            st.executeUpdate();
            st.close();
            cerrarConexion(null, st);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return objeto;
    }


    @Override
    public TopOp actualizarEstadoTurno(TopOp TopOp) {
        try {
            obtenerConexion();
            String sql = "UPDATE turnos_operacion AS top " +
                    "INNER JOIN operador AS op" +
                    "ON op.id_operador = top.id_operador" +
                    "SET top.estado = ?" +
                    "WHERE op.dni LIKE ?;";
            PreparedStatement st = conexion.prepareStatement(sql);
            st.setString(1, TopOp.getEstado());
            st.setString(2, "4416231_");
            st.executeUpdate();
            st.close();
            cerrarConexion(null, st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return TopOp;
    }

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
