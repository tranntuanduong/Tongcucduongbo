package com.jun.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.jun.dao.GenericDAO;
import com.jun.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

    private static final Logger log = Logger.getLogger(AbstractDAO.class);

    private synchronized Connection getConnection() {
        try {
            Class.forName(resourceBundle.getString("driverName"));
            String url = resourceBundle.getString("url");
            String user = resourceBundle.getString("user");
            String password = resourceBundle.getString("password");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            log.info(e.getMessage());
            return null;
        }
    }

    @Override
    public Long save(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        try {
            Long id = null;
            connection = getConnection();
            connection.setAutoCommit(false);
            ptmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < parameters.length; i++) {
                int index = i + 1;
                ptmt.setObject(index, parameters[i]);
            }
            ptmt.executeUpdate();
            resultSet = ptmt.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            connection.commit();
            return id;
        } catch (SQLException e) {
            log.info(e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    log.info(e1.getMessage());
                }
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ptmt != null) {
                    ptmt.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e2) {
                log.info(e2.getMessage());
            }
        }
        return null;
    }

    @SuppressWarnings("hiding")
    @Override
    public <T> List<T> findByProperty(String sql, RowMapper<T> rowMapper, Object param) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(sql);
            if (param != null) {
                ptmt.setObject(1, param);
            }
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                results.add(rowMapper.mapRow(resultSet));
            }
            return results;
        } catch (SQLException e) {
            log.info(e.getMessage());
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ptmt != null) {
                    ptmt.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                log.info(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public void saveList(String sql) {
        Connection conn = null;
        PreparedStatement ptmt = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            ptmt = conn.prepareStatement(sql);

            ptmt.execute();
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<String> findAllString(String sql) {
        List<String> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(sql);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                results.add(resultSet.getString("license_plate"));
            }
            return results;
        } catch (SQLException e) {
            log.info(e.getMessage());
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ptmt != null) {
                    ptmt.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                log.info(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public HashMap<String, Integer> findAllNameAndId(String sql) {
        HashMap<String, Integer> results = new HashMap<String, Integer>();
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(sql);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                results.put(resultSet.getString("name"), resultSet.getInt("id"));
            }
            return results;
        } catch (SQLException e) {
            log.info(e.getMessage());
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ptmt != null) {
                    ptmt.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                log.info(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public HashMap<Integer, String> findAllIdAndName(String sql) {
        HashMap<Integer, String> results = new HashMap<>();
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(sql);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                results.put(resultSet.getInt("id"), resultSet.getString("name"));
            }
            return results;
        } catch (SQLException e) {
            log.info(e.getMessage());
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ptmt != null) {
                    ptmt.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                log.info(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public HashMap<String, String> findAllNameAndId(
            String sql, HashMap<String, String> companyRepeat, int locationId) {
        HashMap<String, String> results = new HashMap<>();
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(sql);
            ptmt.setObject(1, locationId);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                String companyName = resultSet.getString("name");
                if (!companyRepeat.containsKey(resultSet.getString("name"))) {
                    results.put(companyName, resultSet.getInt("id") + "");
                } else {
                    //neu 1 tinh co 3 cong ti cung ten -> day vaof truong hop unknow id = a,b,c..
                    String idArr = companyRepeat.get(companyName);
                    results.put(companyName, idArr);
                }
            }
            return results;
        } catch (SQLException e) {
            log.info(e.getMessage());
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ptmt != null) {
                    ptmt.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                log.info(e.getMessage());
                return null;
            }
        }
    }

    public HashMap<String, String> findAllStringByProperty(String sql, Object param) {
        HashMap<String, String> results = new HashMap<>();
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(sql);
            ptmt.setObject(1, param);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                results.put(resultSet.getString("name"), resultSet.getString("id") + "");
            }
            return results;
        } catch (SQLException e) {
            log.info(e.getMessage());
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ptmt != null) {
                    ptmt.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                log.info(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public HashMap<String, String> findAllNameAndTaxNumber(String sql) {
        HashMap<String, String> results = new HashMap<>();
        Connection connection = null;
        PreparedStatement ptmt = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            ptmt = connection.prepareStatement(sql);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                //pha vo quy tac ti code cho de
                String key = resultSet.getString("name") + "-LocationId" + resultSet.getInt("location_id");
                String value = results.get(key);
                String taxtNumber1 = resultSet.getString("taxt_number") == null ? "" : resultSet.getString("taxt_number");
                String taxtNumber2 = value == null ? "" : value;
                if (!taxtNumber2.contains(taxtNumber1)) {
                    if (!taxtNumber2.equals("")) {
                        taxtNumber2 = "," + taxtNumber2;
                    }
                    taxtNumber2 = taxtNumber1 + taxtNumber2;
                }
                results.put(key, taxtNumber2);
            }
            return results;
        } catch (SQLException e) {
            log.info(e.getMessage());
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (ptmt != null) {
                    ptmt.close();
                }

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                log.info(e.getMessage());
                return null;
            }
        }
    }
}
