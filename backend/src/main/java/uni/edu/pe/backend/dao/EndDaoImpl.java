package uni.edu.pe.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uni.edu.pe.backend.dto.Maquinaria;
import uni.edu.pe.backend.dto.TopOp;
import uni.edu.pe.backend.dto.ReporteOperador;

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
    public List<ReporteOperador> obtenerTurnosOperador() {
        List<ReporteOperador> list = new ArrayList<>();
        String sql = " SELECT * \n" +
                "FROM operador op \n" +
                "INNER JOIN turnos_operacion top\n" +
                "ON op.id_operador = top.id_operador\n" +
                "INNER JOIN maquinaria maq\n" +
                "ON maq.id_maquinaria = top.id_maquinaria;";
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
    public Maquinaria insertarMaquinaria(Maquinaria maquinaria) {
        try{
            obtenerConexion();
            String sql = "INSERT INTO maquinaria(id_maquinaria, codigo, marca, modelo, descripcion, id_planta) VALUES(?, ?, ?, ?, ?, ?);";
            PreparedStatement st = conexion.prepareStatement(sql);
            st.setInt(1, maquinaria.getId_maquinaria());
            st.setString(2, maquinaria.getCodigo());
            st.setString(3, maquinaria.getMarca());
            st.setString(4, maquinaria.getModelo());
            st.setString(5, maquinaria.getDescripcion());
            st.setInt(6, maquinaria.getId_planta());

            st.executeUpdate();
            st.close();
            cerrarConexion(null, st);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return maquinaria;
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

    private ReporteOperador extractReport(ResultSet resultado) throws SQLException {
        ReporteOperador report = new ReporteOperador(
                resultado.getInt("id_operador"),
                resultado.getString("dni"),
                resultado.getString("nombre"),
                resultado.getInt("telefono")
        );
        return report;
    }
}
