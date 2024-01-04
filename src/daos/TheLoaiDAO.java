package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.theloai;

public class TheLoaiDAO extends DBContext {

	public List<theloai> findAll() {

		List<theloai> theloaiList = new ArrayList<theloai>();
		String sql = "select * from theloai";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				theloai objTL = new theloai(rs.getString("maTL"), rs.getString("tenTL"));
				theloaiList.add(objTL);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theloaiList;
	}

	public int add(theloai objTL) {

		int result = 0;
		String sql = "INSERT INTO theloai(maTL,tenTL) VALUE(?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, objTL.getMaTL());
			pst.setString(2, objTL.getTenTL());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
