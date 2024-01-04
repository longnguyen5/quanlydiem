package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Khoa;

public class KhoaDAO extends DBContext {

	public List<Khoa> findAll() {

		List<Khoa> khoaList = new ArrayList<Khoa>();
		String sql = "SELECT k.maKH as kMa, k.tenKH as kTen, lienheKH as kLH FROM khoa as k";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Khoa objK = new Khoa(rs.getString("kMa"), rs.getString("kTen"), rs.getString("kLH"));
				khoaList.add(objK);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return khoaList;
	}

	public int add(Khoa objK) {

		int result = 0;
		String sql = "INSERT INTO khoa(maKH,tenKH,lienheKH) VALUE(?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, objK.getMaKH());
			pst.setString(2, objK.getTenKH());
			pst.setString(3, objK.getLienheKH());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
