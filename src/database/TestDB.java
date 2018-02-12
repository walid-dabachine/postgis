package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import geoexplorer.gui.GeoMainFrame;
import geoexplorer.gui.MapPanel;

public class TestDB {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		//Creation de la connexion
		Connection connection = Utils.getConnection();
		String arg = args[0];
		System.out.println(arg);
		PreparedStatement stmt11 =  connection.prepareStatement("SELECT tags->'name' as nom, ST_X(geom) as Longitude, ST_Y(geom) as Latitude FROM nodes WHERE tags->'name' LIKE "+"'"+arg+"'");
		
		ResultSet res = stmt11.executeQuery();
		print_request(res);
		
		MapPanel panel = new MapPanel(50,50,20);
		//panel.addPrimitive();
		GeoMainFrame mainFrame = new GeoMainFrame("main_frame", panel);
		
		Utils.closeConnection();
	}
	
	public static void print_request(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
	    int columnsNumber = rsmd.getColumnCount();
	    while (rs.next()) {
	        for (int i = 1; i <= columnsNumber; i++) {
	            if (i > 1) System.out.print(" | ");
	            System.out.print(rs.getString(i));
	        }
	        System.out.println("");
	    }
	}

}
