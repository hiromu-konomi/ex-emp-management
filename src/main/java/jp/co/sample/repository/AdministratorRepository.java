
package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * administratorsテーブルを操作するリポジトリ(Dao)
 * 
 * @author konomitaimu
 *
 */
@Repository
public class AdministratorRepository {

	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 管理者情報を挿入する
	 * 
	 * @param administrator
	 */
	public void insert(Administrator administrator) {
		String sql = "INSERT INTO administrators (name, mail_address, password) VALUES (:name, :mailAddress, :password)";

		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

		template.update(sql, param);
	}

	/**
	 * メールアドレスとパスワードから管理者情報を取得する(存在しない場合は nullを返す)
	 * 
	 * @param mailAddress
	 * @param password
	 * @return 取得した管理者情報
	 */
	public Administrator findByMailAddressPassword(String mailAddress, String password) {
		String sql = "SELECT * FROM administrators WHERE mail_address=:mailAddress AND password=:password ORDER BY id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("mail_address", mailAddress)
				.addValue("password", password);

		Administrator administrator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);

		return administrator;
	}
}
