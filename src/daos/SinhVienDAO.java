package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Khoa;
import models.Lop;
import models.sinhvien;

public class SinhVienDAO extends DBContext {

	public List<sinhvien> findAll() {

		List<sinhvien> sinhvienList = new ArrayList<sinhvien>();
		String sql = "SELECT sv.maSV as svMa," + " sv.tenSV as svTen," + " sv.diaChi as svDiaChi," + " sv.sdt as svSDT,"
				+ " sv.email as svEmail," + " sv.maLop as svMaLop," + " l.tenLop as lTen," + " l.maKH as lMaKH,"
				+ " k.tenKH as kTen," + " k.lienheKH as kLH" + " FROM sinhvien as sv"
				+ " INNER JOIN lop as l ON sv.maLop = l.maLop" + " INNER JOIN khoa as k ON k.maKH = l.maKH"
				+ " ORDER BY sv.maSV DESC";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sinhvien objSV = new sinhvien(rs.getInt("svMa"), rs.getString("svTen"), rs.getString("svDiaChi"),
						rs.getInt("svSDT"), rs.getString("svEmail"), new Lop(rs.getInt("svMaLop"), rs.getString("lten"),
								new Khoa(rs.getString("lMaKH"), rs.getString("kTen"), rs.getString("kLH"))));
				sinhvienList.add(objSV);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sinhvienList;
	}

	public int add(sinhvien objSV) {

		int result = 0;
		String sql = "INSERT INTO sinhvien(maSV,tenSV,diaChi,sdt,email,maLop)" + " VALUE(?,?,?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setInt(1, objSV.getMaSV());
			pst.setString(2, objSV.getTenSV());
			pst.setString(3, objSV.getDiaChi());
			pst.setInt(4, objSV.getSdt());
			pst.setString(5, objSV.getEmail());
			pst.setInt(6, objSV.getLop().getMaLop());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
