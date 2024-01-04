package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Khoa;
import models.Lop;

public class LopDAO extends DBContext {

	public List<Lop> findAll() {

		List<Lop> lopList = new ArrayList<Lop>();
		String sql = "SELECT" + " l.maLop as lMa," + " l.tenLop as lTen," + " l.maKH as lMaKH," + " k.tenKH as kTen,"
				+ " k.lienheKH as kLH" + " FROM lop as l" + " INNER JOIN khoa as k ON k.maKH = l.maKH";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Lop objLop = new Lop(rs.getInt("lMa"), rs.getString("lTen"),
						new Khoa(rs.getString("lMaKH"), rs.getString("kTen"), rs.getString("kLH")));
				lopList.add(objLop);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lopList;
	}

	public int add(Lop objL) {
		int result = 0;
		String sql = "INSERT INTO lop(tenLop,maKH) VALUE(?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, objL.getTenLop());
			pst.setString(2, objL.getKhoa().getMaKH());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
