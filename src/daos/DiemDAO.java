package daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.diem;

public class DiemDAO extends DBContext {

	public int nhapdiem(diem objDiem) {
		int result = 0;
		String sql = "INSERT INTO diem(heso1,heso3,heso6,tongDiem,maGV,maSV,maMH,maTC,maTL,maHK,maNH)"
				+ " VALUE(?,?,?,?,?,?,?,?,?,?,?)";
		try {

			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setFloat(1, objDiem.getHeso1());
			pst.setFloat(2, objDiem.getHeso3());
			pst.setFloat(3, objDiem.getHeso6());
			pst.setFloat(4, objDiem.getTongDiem());
			pst.setString(5, objDiem.getGiangvien().getMaGV());
			pst.setInt(6, objDiem.getSinhvien().getMaSV());
			pst.setString(7, objDiem.getMonhoc().getMaMH());
			pst.setString(8, objDiem.getTinchi().getMaTC());
			pst.setString(9, objDiem.getTheloai().getMaTL());
			pst.setString(10, objDiem.getHocky().getMaHK());
			pst.setString(11, objDiem.getNamhoc().getMaNH());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
