package Controller;

import Database.Koneksi;
import Model.Barang;
import Model.Detail_Transaksi;
import Model.Jenis_Barang;
import Model.Transaksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    Koneksi koneksi;
    ArrayList<Barang> arrBarang;
    ArrayList<Transaksi> arrTransaksi;
    ArrayList<Jenis_Barang> arrJenisBarang;

    public Controller() {
        this.koneksi = new Koneksi();
        this.arrBarang = new ArrayList<>();
        this.arrTransaksi = new ArrayList<>();
        this.arrJenisBarang = new ArrayList<>();
    }

    public ArrayList<Barang> getDataBarang() throws SQLException {
        this.arrBarang.clear();

        ResultSet rs = this.koneksi.getData("SELECT * FROM BARANG JOIN JENISBARANG ON BARANG.ID_JENISBARANG = JENISBARANG.ID_JENISBARANG");
        while (rs.next()) {
            Jenis_Barang jenis_barang = new Jenis_Barang();
            jenis_barang.setId_jenis_barang(rs.getInt("ID_JENISBARANG"));
            jenis_barang.setNama_jenis_barang(rs.getString("NAMA_JENISBARANG"));

            Barang b = new Barang();
            b.setId_barang(rs.getInt("ID_BARANG"));
            b.setNama_barang(rs.getString("NAMA_BARANG"));
            b.setHarga_barang(rs.getInt("HARGA_BARANG"));
            b.setStock_barang(rs.getInt("STOCK_BARANG"));

            this.arrBarang.add(b);

        }
        return this.arrBarang;
    }

    public Barang getDataBarang(int idBarang) throws SQLException {

        ResultSet rs = this.koneksi.getData("SELECT * FROM BARANG JOIN JENISBARANG ON BARANG.ID_JENISBARANG = JENISBARANG.ID_JENISBARANG WHERE BARANG.ID_BARANG=" + idBarang);
        rs.next();
        Jenis_Barang jenis_barang = new Jenis_Barang();
        jenis_barang.setId_jenis_barang(rs.getInt("ID_JENISBARANG"));
        jenis_barang.setNama_jenis_barang(rs.getString("NAMA_JENISBARANG"));

        Barang b = new Barang();
        b.setId_barang(rs.getInt("ID_BARANG"));
        b.setNama_barang(rs.getString("NAMA_BARANG"));
        b.setHarga_barang(rs.getInt("HARGA_BARANG"));
        b.setStock_barang(rs.getInt("STOCK_BARANG"));

        return b;
    }

    public ArrayList<Jenis_Barang> getDataJenisBarang() throws SQLException {
        arrJenisBarang.clear();
        ResultSet rs = koneksi.getData("SELECT * FROM JENISBARANG");

        while (rs.next()) {

            Jenis_Barang jenisbarang = new Jenis_Barang();
            jenisbarang.setId_jenis_barang(rs.getInt("ID_JENISBARANG"));
            jenisbarang.setNama_jenis_barang(rs.getString("NAMA_JENISBARANG"));
            jenisbarang.setIdjenisbarang(String.valueOf(rs.getInt("ID_JENISBARANG")));

            this.arrJenisBarang.add(jenisbarang);
        }
        return this.arrJenisBarang;
    }

    public ArrayList<Transaksi> getDataTransaksi() throws SQLException {
        this.arrTransaksi.clear();

        ResultSet rs = this.koneksi.getData("SELECT * FROM TRANSAKSI");
        while (rs.next()) {
            Transaksi t = new Transaksi();
            t.setId_transaksi(rs.getInt("ID_TRANSAKSI"));
            t.setJumlah_barang(rs.getInt("JUMLAH_BARANG"));
            t.setTotal_harga(rs.getInt("TOTAL_HARGA"));
            t.setJumlah_bayar(rs.getInt("JUMLAH_BAYAR"));
            t.setKembalian(rs.getInt("KEMBALIAN"));

            ResultSet rsDetail_Transaksi = koneksi.getData("SELECT * FROM DETAIL_TRANSAKSI JOIN BARANG ON DETAIL_TRANSAKSI.ID_BARANG = BARANG.ID_BARANG JOIN JENISBARANG ON BARANG.ID_JENISBARANG = JENISBARANG.ID_JENISBARANG WHERE DETAIL_TRANSAKSI.ID_TRANSAKSI = " + rs.getString("ID_TRANSAKSI"));
            ArrayList<Detail_Transaksi> dp = new ArrayList<>();
            while (rsDetail_Transaksi.next()) {
                Jenis_Barang jenis = new Jenis_Barang();
                jenis.setId_jenis_barang(rsDetail_Transaksi.getInt("ID_JENISBARANG"));
                jenis.setNama_jenis_barang(rsDetail_Transaksi.getString("NAMA_JENISBARANG"));

                Barang B = new Barang();
                B.setId_barang(rsDetail_Transaksi.getInt("ID_BARANG"));
                B.setJenis_barang(jenis);
                B.setNama_barang(rsDetail_Transaksi.getString("NAMA_BARANG"));
                B.setHarga_barang(rsDetail_Transaksi.getInt("HARGA_BARANG"));
                B.setStock_barang(rsDetail_Transaksi.getInt("STOCK_BARANG"));

                Detail_Transaksi detail_transaksi = new Detail_Transaksi();
                detail_transaksi.setBarang(B);
                detail_transaksi.setJumlah_barang(rsDetail_Transaksi.getInt("JUMLAH_BARANG"));

                dp.add(detail_transaksi);

            }
            t.setArrDetail_Transaksi(dp);
            this.arrTransaksi.add(t);
        }
        return this.arrTransaksi;
    }

    public void insertTransaksi(Transaksi transaksi) {
        try {
            this.koneksi.ManipulasiData("INSERT INTO TRANSAKSI VALUES (ID_TRANSAKSI.NEXTVAL," + transaksi.getJumlah_barang() + "," + transaksi.getTotal_harga() + "," + transaksi.getJumlah_bayar() + "," + transaksi.getKembalian() + ")");
            ResultSet rs = this.koneksi.getData("SELECT ID_TRANSAKSI.CURRVAL FROM DUAL");

            rs.next();
            int Id_Transaksi = rs.getInt("CURRVAL");
            for (Detail_Transaksi p : transaksi.getArrDetail_Transaksi()) {
                this.koneksi.ManipulasiData("INSERT INTO DETAIL_TRANSAKSI VALUES (" + Id_Transaksi + "," + p.getBarang().getId_barang() + "," + p.getJumlah_barang() + ")");
                this.koneksi.ManipulasiData("UPDATE BARANG SET STOCK_BARANG = STOCK_BARANG - " + p.getJumlah_barang() + "WHERE ID_BARANG = " + p.getBarang().getId_barang());
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void insertJenisBarang(Jenis_Barang jenisbarang) {
        try {
            koneksi.ManipulasiData("INSERT INTO JENIS_BARANG VALUES (ID_JENIS_BARANG.NEXTVAL,'" + jenisbarang.getNama_jenis_barang() + "')");
            ResultSet rs = this.koneksi.getData("SELECT ID_JENISBARANG.CURRVAL FROM DUAL");
            System.err.println(rs);

            rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteJenisBarang(Jenis_Barang jenisbarang) {
        koneksi.ManipulasiData("DELETE FROM JENIS_BARANG WHERE ID_JENISBARANG = " + jenisbarang.getId_jenisbarang());
    }

    public void insertBarang(Barang barang) {
        try {
            System.out.println("INSERT INTO BARANG VALUES (ID_BARANG.NEXTVAL,'" + barang.getNama_barang() + "'," + barang.getStock_barang() + "," + barang.getHarga_barang() + "," + barang.getJenis_barang().getIdjenisbarang() + ")");
            koneksi.ManipulasiData("INSERT INTO BARANG VALUES ('7','" + barang.getNama_barang() + "'," + barang.getStock_barang() + "," + barang.getHarga_barang() + "," + barang.getJenis_barang().getIdjenisbarang() + ")");

        } catch (Exception ex) {
            System.out.println("ERROR = " + ex.getMessage());
        }
    }

    public void updateBarang(Barang barang, int Stock, int Harga) {
        koneksi.ManipulasiData("UPDATE BARANG SET STOCK_BARANG = " + Stock + ", HARGA_BARANG = " + Harga + "WHERE ID_BARANG =" + barang.getId_barang());

    }

    public void deleteBarang(Barang barang) {
        koneksi.ManipulasiData("DELETE FROM BARANG WHERE ID_BARANG = " + barang.getId_barang());
    }

}
