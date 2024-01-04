package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.namhoc;

public class NamHocDAO extends DBContext {

	public List<namhoc> findAll() {

		List<namhoc> namhocList = new ArrayList<namhoc>();
		String sql = "select nh.maNh as nhMa, nh.tenNh as nhTen  from namhoc as nh";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				namhoc objNH = new namhoc(rs.getString("nhMa"), rs.getString("nhTen"));
				namhocList.add(objNH);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return namhocList;
	}

	public int add(namhoc objNH) {

		int result = 0;
		String sql = "INSERT INTO namhoc(maNH,tenNH) VALUE(?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, objNH.getMaNH());
			pst.setString(2, objNH.getTenNH());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
