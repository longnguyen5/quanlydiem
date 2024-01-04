package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.giangvien;

public class GiangVienDAO extends DBContext {

	public List<giangvien> findAll() {

		List<giangvien> giangvienList = new ArrayList<giangvien>();
		String sql = "select gv.maGV as gvMa," + " gv.tenGV as gvTen," + " gv.diaChi as gvDC," + " gv.sdt as gvSDT,"
				+ " gv.email as gvEmail" + " from giangvien as gv";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				giangvien objGV = new giangvien(rs.getString("gvMa"), rs.getString("gvTen"), rs.getString("gvDC"),
						rs.getInt("gvSDT"), rs.getString("gvEmail"));
				giangvienList.add(objGV);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return giangvienList;
	}

	public int add(giangvien objGV) {
		int result = 0;
		String sql = "INSERT INTO giangvien(maGV,tenGV,diaChi,sdt,email) VALUE(?,?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, objGV.getMaGV());
			pst.setString(2, objGV.getTenGV());
			pst.setString(3, objGV.getDiaChi());
			pst.setInt(4, objGV.getSdt());
			pst.setString(5, objGV.getEmail());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
