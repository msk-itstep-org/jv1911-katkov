package db.migration;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class U6__Admin extends AbstractMigration {
    @Override
    protected void migrate(NamedParameterJdbcTemplate template) {
        Long userId = getAdminId(template);
        deleteAdminRoles(userId, template);
        deleteAdmin(userId, template);
    }

    public Long getAdminId(NamedParameterJdbcTemplate template) {
        String query = "SELECT id FROM users WHERE username = :username";
        SqlParameterSource parameters = new MapSqlParameterSource().addValue("username", "admin");

        return template.queryForObject(query, parameters, Long.class);
    }

    public void deleteAdminRoles(Long userId, NamedParameterJdbcTemplate template) {
        String query = "DELETE FROM user_roles WHERE user_id = :user_id";
        SqlParameterSource parameters = new MapSqlParameterSource().addValue("user_id", userId);

        template.update(query, parameters);
    }

    public void deleteAdmin(Long userId, NamedParameterJdbcTemplate template) {
        String query = "DELETE FROM users WHERE user_id = ?";
        SqlParameterSource parameters = new MapSqlParameterSource().addValue("user_id", userId);

        template.update(query, parameters);
    }
}
