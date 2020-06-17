package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

import java.util.List;

/**
 * employeesテーブルを操作するリポジトリ(Dao)
 * 
 * @author konomitaimu
 *
 */
@Repository
public class EmployeeRepository {

	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 従業員一覧情報を入社日順で取得する(従業員が存在しない場合はサイズ0件の従業員一覧を返す)。
	 * 
	 * @return 従業員一覧情報(入社日順)
	 */
	public List<Employee> findAll() {
		String sql = "SELECT * FROM employees ORDER BY hire_date";

		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);

		return employeeList;
	}

	/**
	 * 主キーから従業員情報を取得する(従業員が存在しない場合はSpringが自動的に例外を発生します)
	 * 
	 * @param id
	 * @return 取得された従業員情報
	 */
	public Employee load(Integer id) {
		String sql = "SELECT * FROM employees WHERE id=:id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);

		return employee;
	}

	/**
	 * 従業員情報を変更する(今回は従業員の扶養人数だけを更新できるようなSQLを発行する)
	 * 
	 * @param employee
	 */
	public void update(Employee employee) {
		String sql = "UPDATE employees SET dependents_count=:dependents_count WHERE id=:id";

		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

		template.update(sql, param);
	}
}
