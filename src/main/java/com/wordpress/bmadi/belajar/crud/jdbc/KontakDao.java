package com.wordpress.bmadi.belajar.crud.jdbc;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KontakDao {

    private Connection conn;
    private String dbDriver = "com.mysql.jdbc.Driver";
    private String dbUrl = "jdbc:mysql://localhost/belajar";
    private String dbUsername = "root";
    private String dbPassword = "java";

    public void connect() {
        try {
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnet() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Kontak save(Kontak k) {
        try {
            String sql = "INSERT INTO t_kontak(nama, alamat, tanggal_lahir, jenis_kelamin, aktif) VALUES (?, ?, ?, ?, ?)";

            connect();
            PreparedStatement pInsert = conn.prepareStatement(sql);
            pInsert.setString(1, k.getNama());
            pInsert.setString(2, k.getAlamat());
            pInsert.setDate(3, new java.sql.Date(k.getTanggalLahir().getTime()));
            pInsert.setString(4, k.getJenisKelamin().toString());
            pInsert.setBoolean(5, k.getAktif());
            pInsert.executeUpdate();
            disconnet();
        } catch (Exception ex) {
            Logger.getLogger(KontakDao.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public Kontak update(Kontak k) {
        try {
            String sql = "UPDATE t_kontak SET nama=?, alamat=?, tanggal_lahir=?, jenis_kelamin=?, aktif=? WHERE id=?";

            connect();
            PreparedStatement pUpdate = conn.prepareStatement(sql);
            pUpdate.setString(1, k.getNama());
            pUpdate.setString(2, k.getAlamat());
            pUpdate.setDate(3, new java.sql.Date(k.getTanggalLahir().getTime()));
            pUpdate.setString(4, k.getJenisKelamin().toString());
            pUpdate.setBoolean(5, k.getAktif());
            pUpdate.setInt(6, k.getId());
            pUpdate.executeUpdate();

            disconnet();
        } catch (Exception ex) {
            Logger.getLogger(KontakDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Kontak delete(Kontak k) {
        try {
            String sql = "DELETE FROM t_kontak WHERE id=?";

            connect();
            PreparedStatement pDelete = conn.prepareStatement(sql);
            pDelete.setInt(1, k.getId());
            pDelete.executeUpdate();

            disconnet();
        } catch (Exception ex) {
            Logger.getLogger(KontakDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Kontak> cariSemua() {
        List<Kontak> hasil = new ArrayList<Kontak>();
        try {
            String sql = "SELECT * FROM t_kontak ORDER BY nama";

            connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Kontak k = konversiResultSetJadiKontak(rs);
                hasil.add(k);
            }
            disconnet();
        } catch (SQLException ex) {
            Logger.getLogger(KontakDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }

    public List<Kontak> getByNama(String nama) {
        List<Kontak> hasil = new ArrayList<Kontak>();
        try {
            String sql = "SELECT * FROM t_kontak WHERE nama like ? ORDER BY nama";
            
            connect();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nama + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Kontak k = konversiResultSetJadiKontak(rs);
                hasil.add(k);
            }
            disconnet();
        } catch (SQLException ex) {
            Logger.getLogger(KontakDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }

    private Kontak konversiResultSetJadiKontak(ResultSet rs) throws SQLException {
        Kontak k = new Kontak();
        k.setId(rs.getInt("id"));
        k.setNama(rs.getString("nama"));
        k.setAlamat(rs.getString("alamat"));
        k.setTanggalLahir(rs.getDate("tanggal_lahir"));
        k.setJenisKelamin(JenisKelamin.valueOf(rs.getString("jenis_kelamin")));
        k.setAktif(rs.getBoolean("aktif"));
        return k;
    }
}
