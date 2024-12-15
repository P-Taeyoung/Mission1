package missionJava;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WifiService {
    public void setWifiInfo (WifiInfoData wifiInfo) {
        String url = "jdbc:mariadb://localhost:3306/mission1";
        String dbUserId = "testuser1";
        String dbPassword = "zerobase";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            String sql =  " insert ignore into wifi_info_data(mgrNo, wrdofc, mainNm, adres1, adres2, instlFloor, instlTy, instlMby, svcSe, cmcwr, cnstcYear, inoutDoor, remars3, lnt, lat, dttm) " +
                    " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?) ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, wifiInfo.getMgrNo());
            preparedStatement.setString(2, wifiInfo.getWrdofc());
            preparedStatement.setString(3, wifiInfo.getMainNm());
            preparedStatement.setString(4, wifiInfo.getAdres1());
            preparedStatement.setString(5, wifiInfo.getAdres2());
            preparedStatement.setString(6, wifiInfo.getInstlFloor());
            preparedStatement.setString(7, wifiInfo.getInstlTy());
            preparedStatement.setString(8, wifiInfo.getInstlMby());
            preparedStatement.setString(9, wifiInfo.getSvcSe());
            preparedStatement.setString(10, wifiInfo.getCmcwr());
            preparedStatement.setString(11, wifiInfo.getCnstcYear());
            preparedStatement.setString(12, wifiInfo.getInoutDoor());
            preparedStatement.setString(13, wifiInfo.getRemars3());
            preparedStatement.setDouble(14, wifiInfo.getLnt());
            preparedStatement.setDouble(15, wifiInfo.getLat());
            preparedStatement.setString(16, wifiInfo.getDttm());

            int affected = preparedStatement.executeUpdate();
            // 반영된 행의 개수를 리턴하기 때문에 int자료형으로 값을 받음

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void setMyLocation (double myLat, double myLnt) {
        String url = "jdbc:mariadb://localhost:3306/mission1";
        String dbUserId = "testuser1";
        String dbPassword = "zerobase";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            String sql =  " insert into history_search(`X_Cd`, `Y_Cd`, search_datetime) " +
                    " values (?, ?, NOW()) " ;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, myLat);
            preparedStatement.setDouble(2, myLnt);

            int affected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeHistory (int id) {
        String url = "jdbc:mariadb://localhost:3306/mission1";
        String dbUserId = "testuser1";
        String dbPassword = "zerobase";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            String sql =  " delete from history_search " +
                    " where ID = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, id);


            int affected = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<MyLocation> historyMyLocation() {
        String url = "jdbc:mariadb://localhost:3306/mission1";
        String dbUserId = "testuser1";
        String dbPassword = "zerobase";
        List<MyLocation> myLocations = new ArrayList<>();

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            String sql =  " select *" +
                    " from history_search ";

            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();


            while (rs.next()) {
                MyLocation myLocation = new MyLocation();
                int id = rs.getInt("id");
                double lat = rs.getDouble("X_Cd");
                double lnt = rs.getDouble("Y_Cd");
                Date date = rs.getDate("search_datetime");
                myLocation.setId(id);
                myLocation.setMyLat(lat);
                myLocation.setMyLnt(lnt);
                myLocation.setDate(date);
                myLocations.add(myLocation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return myLocations;
    }

    public List<WifiNearMe> nearWifiInfo () {
        String url = "jdbc:mariadb://localhost:3306/mission1";
        String dbUserId = "testuser1";
        String dbPassword = "zerobase";
        List<WifiNearMe> dataList = new ArrayList<>();

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            String sql =  " SELECT FORMAT(ST_Distance_Sphere( " +
                    "           point(hs.Y_Cd, hs.X_Cd), " +
                    "           point(wi.lnt, wi.lat) " +
                    "       ) / 1000, 4) AS distance_km, " +
                    "        wi.*  " +
                    " FROM wifi_info_data wi " +
                    " JOIN ( " +
                    "    SELECT * " +
                    "    FROM history_search " +
                    "    ORDER BY search_datetime DESC " +
                    "    LIMIT 1  " +
                    " ) hs ON 1=1  " +
                    " ORDER BY distance_km " +
                    " LIMIT 20 " ;

            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();


            while (rs.next()) {
                 WifiNearMe wifiNearMe = new WifiNearMe();
                 wifiNearMe.setKm(rs.getString("distance_km"));
                 wifiNearMe.setMgrNo(rs.getString("mgrNo"));
                 wifiNearMe.setWrdofc(rs.getString("wrdofc"));
                 wifiNearMe.setMainNm(rs.getString("mainNm"));
                 wifiNearMe.setAdres1(rs.getString("adres1"));
                 wifiNearMe.setAdres2(rs.getString("adres2"));
                 wifiNearMe.setInstlFloor(rs.getString("instlFloor"));
                 wifiNearMe.setInstlTy(rs.getString("instlTy"));
                 wifiNearMe.setInstlMby(rs.getString("instlMby"));
                 wifiNearMe.setSvcSe(rs.getString("svcSe"));
                 wifiNearMe.setCmcwr(rs.getString("cmcwr"));
                 wifiNearMe.setCnstcYear(rs.getString("cnstcYear"));
                 wifiNearMe.setInoutDoor(rs.getString("inoutDoor"));
                 wifiNearMe.setRemars3(rs.getString("remars3"));
                 wifiNearMe.setLnt(rs.getDouble("lnt"));
                 wifiNearMe.setLat(rs.getDouble("lat"));
                 wifiNearMe.setDttm(rs.getString("dttm"));
                 dataList.add(wifiNearMe);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return dataList;
    }

    public int countData() {
        int count = 0;

        String url = "jdbc:mariadb://localhost:3306/mission1";
        String dbUserId = "testuser1";
        String dbPassword = "zerobase";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            String sql =  " select count(*) as count " +
                    " from wifi_info_data ";

            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();


            while (rs.next()) {
                count = Integer.parseInt(rs.getString("count")) ;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return count;
    }


}
