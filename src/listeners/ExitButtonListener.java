package listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;


public class ExitButtonListener extends MouseAdapter {

    String nombre;
    int gold;
    Boolean victory;
    public ExitButtonListener(String nombre, int gold, boolean victory) {
        this.nombre = nombre;
        this.gold = gold;
        this.victory = victory;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        String db_url = "jdbc:mysql://localhost:3306/juego";
        String user = "root";
        String passwd = "mysql";
        String insertQy = "insert into partidas(nombre, monedas, victoria) values (?,?,?)";

        try {
            Connection connection = DriverManager.getConnection(db_url,user,passwd);
            PreparedStatement preparedStatement = connection.prepareStatement(insertQy);
            preparedStatement.setString(1,this.nombre);
            preparedStatement.setInt(2, gold);
            preparedStatement.setBoolean(3,victory);
            int addRows = preparedStatement.executeUpdate();
            if (addRows > 0){
                System.out.println("Todo ha ido bien");
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Error en la conexión con la base de datos"); 
        }

        System.exit(0);
    }
}
