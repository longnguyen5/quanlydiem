package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.roles;

public class RolesDAO extends DBContext {

	public List<roles> findAll() {
		List<roles> rolesList = new ArrayList<roles>();
		String sql = "select r.id as rid, r.role as rRole  from roles as r";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				roles objR = new roles(rs.getInt("rid"), rs.getString("rRole"));
				rolesList.add(objR);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rolesList;
	}

	public int add(roles objR) {

		int result = 0;
		String sql = "INSERT INTO roles(role) VALUE(?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);

			pst.setString(1, objR.getRole());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
