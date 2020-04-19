package db.migration;

import org.itstep.msk.app.enums.Role;
import org.itstep.msk.app.service.MyBCryptPasswordEncoder;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;

public class V6__Admin extends AbstractMigration {

    @Override
    protected void migrate(NamedParameterJdbcTemplate template) {
        insertAdmin(template);
        Long adminId = getAdminId(template);
        insertAdminRoles(adminId, template);
    }

    private void insertAdmin(NamedParameterJdbcTemplate template) {
        String query = "INSERT INTO users (username, email, password, active) " +
                "VALUES (:username, :email, :password, :active)";
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("username", "admin")
                .addValue("email", "admin@mail.ru")
                .addValue("password", encodePassword("123"))
                .addValue("active", true);

        template.update(query, parameters);
    }

    private String encodePassword(String password) {
        PasswordEncoder passwordEncoder = new MyBCryptPasswordEncoder();

        return passwordEncoder.encode(password);
    }

    public Long getAdminId(NamedParameterJdbcTemplate template) {
        String query = "SELECT id FROM users WHERE username = :username";
        SqlParameterSource parameters = new MapSqlParameterSource().addValue("username", "admin");

        return template.queryForObject(query, parameters, Long.class);
    }

    private void insertAdminRoles(Long adminId, NamedParameterJdbcTemplate template) {
        String query = "INSERT INTO user_roles (user_id, role) VALUES (:user_id, :role)";

        Role[] roles = {Role.ROLE_WAITER, Role.ROLE_ADMIN};
        for (Role role : roles) {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("user_id", adminId)
                    .addValue("role", role.name());
            template.update(query, parameters);
        }
    }
}
