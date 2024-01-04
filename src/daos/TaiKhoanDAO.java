package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.roles;
import models.taikhoan;

public class TaiKhoanDAO extends DBContext {

	public List<taikhoan> findAll() {

		List<taikhoan> taiKhoanList = new ArrayList<taikhoan>();
		String sql = "SELECT" + " tk.id as tkID," + " tk.username as tkUser," + " tk.password as tkPass,"
				+ " tk.email as tkEmail," + " tk.role as tkRole," + " r.role as rRole" + " FROM taikhoan as tk"
				+ " INNER JOIN roles as r ON tk.role = r.id";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				taikhoan objTK = new taikhoan(rs.getInt("tkID"), rs.getString("tkUser"), rs.getString("tkPass"),
						rs.getString("tkEmail"), new roles(rs.getInt("tkRole"), rs.getString("rRole")));
				taiKhoanList.add(objTK);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return taiKhoanList;
	}

	public int add(taikhoan objTK) {

		int result = 0;
		String sql = "INSERT INTO taikhoan(username,password,email,role) VALUE(?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, objTK.getUsername());
			pst.setString(2, objTK.getPassword());
			pst.setString(3, objTK.getEmail());
			pst.setInt(4, objTK.getRole().getId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public taikhoan findUsernameANDPass(String username, String pass) {

		String sql = "SELECT" + " tk.id as tkID," + " tk.username as tkUser," + " tk.password as tkPass,"
				+ " tk.email as tkEmail," + " tk.role as tkRole," + " r.role as rRole" + " FROM taikhoan tk"
				+ " INNER JOIN roles as r ON r.id = tk.role" + " WHERE username = ? AND password = ?";
		taikhoan objUser = null;
		try {
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, username);
			pst.setString(2, pass);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				objUser = new taikhoan(rs.getInt("tkID"), rs.getString("tkUser"), rs.getString("tkPass"),
						rs.getString("tkEmail"), new roles(rs.getInt("tkRole"), rs.getString("rRole")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objUser;
	}

	public taikhoan viewAll(String username) {

		String sql = "SELECT" + " tk.id as tkID," + " tk.username as tkUser," + " tk.password as tkPass,"
				+ " tk.email as tkEmail," + " tk.role as tkRole," + " r.role as rRole" + " FROM taikhoan as tk"
				+ " INNER JOIN roles as r ON r.id = tk.role" + " WHERE username = ?";
		taikhoan objUser = null;
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				objUser = new taikhoan(rs.getInt("tkID"), rs.getString("tkUser"), rs.getString("tkPass"),
						rs.getString("tkEmail"), new roles(rs.getInt("tkRole"), rs.getString("rRole")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return objUser;
	}

}
