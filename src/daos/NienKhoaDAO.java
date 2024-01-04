package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.NienKhoa;

public class NienKhoaDAO extends DBContext {

	public List<NienKhoa> findAll() {

		List<NienKhoa> nienKhoaList = new ArrayList<NienKhoa>();
		String sql = "SELECT nk.maNK as nkMa, nk.tenNK as nkTen  FROM nienkhoa as nk";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NienKhoa objNK = new NienKhoa(rs.getString("nkMa"), rs.getString("nkTen"));
				nienKhoaList.add(objNK);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nienKhoaList;
	}

	public int add(NienKhoa objNK) {
		int result = 0;
		String sql = "INSERT INTO nienkhoa(maNK,tenNK) VALUE(?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, objNK.getMaNK());
			pst.setString(2, objNK.getTenNK());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
