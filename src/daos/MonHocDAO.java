package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.monhoc;
import models.theloai;
import models.tinchi;

public class MonHocDAO extends DBContext {

	public List<monhoc> findAll() {
		List<monhoc> monhocList = new ArrayList<monhoc>();
		String sql = "SELECT m.maMH as mMH,m.tenMH as mtMH,m.maTC as mTC,m.maTL as mTL," + " soTC,tenTL FROM monhoc m"
				+ " INNER JOIN tinchi t ON m.maTC = t.maTC" + " INNER JOIN theloai tl ON m.maTL = tl.maTL";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				monhoc objMH = new monhoc(rs.getString("mMH"), rs.getString("mtMH"),
						new tinchi(rs.getString("mTC"), rs.getInt("soTC")),
						new theloai(rs.getString("mTL"), rs.getString("tenTL")));
				monhocList.add(objMH);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return monhocList;
	}

	public int add(monhoc objMH) {

		int result = 0;
		String sql = "INSERT INTO monhoc(maMH,tenMH,maTC,maTL) VALUE(?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, objMH.getMaMH());
			pst.setString(2, objMH.getTenMH());
			pst.setString(3, objMH.getTinchi().getMaTC());
			pst.setString(4, objMH.getTheloai().getMaTL());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
