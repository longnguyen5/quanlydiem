package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.hocky;

public class HocKyDAO extends DBContext {

	public List<hocky> findAll() {
		List<hocky> hockyList = new ArrayList<hocky>();
		String sql = "select hk.maHK as hkMa, hk.tenHK as hkTen  from hocky as hk";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				hocky objHK = new hocky(rs.getString("hkMa"), rs.getString("hkTen"));
				hockyList.add(objHK);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return hockyList;
	}

	public int add(hocky objHK) {
		int result = 0;
		String sql = "INSERT INTO hocky(maHK,tenHK) VALUE(?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, objHK.getMaHK());
			pst.setString(2, objHK.getTenHK());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
