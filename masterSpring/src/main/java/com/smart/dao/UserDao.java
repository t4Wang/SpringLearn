package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    private final static String GET_USER_BY_N_A_P =
            "select user_id, user_name, credits from t_user " +
                    "where user_name=? and " +
                    "password=?";
    private final static String UPDATE_LOGIN_INFO = "update t_user set " +
            "last_visit=?, last_ip=?, credits=? WHERE user_id=?";

    private JdbcTemplate jdbcTemplate;

    public User getByNameAndPassword(User loginInfo) {
        final User user = new User();
        jdbcTemplate.query(GET_USER_BY_N_A_P, new Object[] {loginInfo.getUserName(), loginInfo.getPassword()},
            new RowCallbackHandler() {
                public void processRow(ResultSet rs) throws SQLException {
                    user.setUserId(rs.getInt("user_id"));
                    user.setUserName(rs.getString("user_name"));
                    user.setCredits(rs.getInt("credits"));
                }
            });
        return user;
    }

    /**
     * 更新用户积分，最后登录ip和时间
     * @param loginInfo
     */
    public void updateLoginInfo(User loginInfo) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO,
            new Object[]{
                    loginInfo.getLastVisit(), loginInfo.getLastIp(), loginInfo.getCredits(), loginInfo.getUserId()});
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
