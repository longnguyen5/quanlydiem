package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.tinchi;

public class TinChiDAO extends DBContext {

	public List<tinchi> findAll() {

		List<tinchi> tinchiList = new ArrayList<tinchi>();
		String sql = "select * from tinchi";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tinchi objTC = new tinchi(rs.getString("maTC"), rs.getInt("soTC"));
				tinchiList.add(objTC);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tinchiList;
	}

	public int add(tinchi objTC) {

		int result = 0;
		String sql = "INSERT INTO tinchi(maTC,soTC) VALUE(?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, objTC.getMaTC());
			pst.setInt(2, objTC.getSoTC());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
