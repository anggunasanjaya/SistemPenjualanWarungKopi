package Model;

import java.util.ArrayList;

public class Transaksi {

    private Integer id_transaksi;
    private Integer jumlah_barang;
    private Integer total_harga;
    private Integer jumlah_bayar;
    private Integer kembalian;
    private ArrayList <Detail_Transaksi> arrDetail_Transaksi;

    public Integer getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(Integer id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public Integer getJumlah_barang() {
        return jumlah_barang;
    }

    public void setJumlah_barang(Integer jumlah_barang) {
        this.jumlah_barang = jumlah_barang;
    }

    public Integer getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(Integer total_harga) {
        this.total_harga = total_harga;
    }

    public Integer getJumlah_bayar() {
        return jumlah_bayar;
    }

    public void setJumlah_bayar(Integer jumlah_bayar) {
        this.jumlah_bayar = jumlah_bayar;
    }

    public Integer getKembalian() {
        return kembalian;
    }

    public void setKembalian(Integer kembalian) {
        this.kembalian = kembalian;
    }

    public ArrayList<Detail_Transaksi> getArrDetail_Transaksi() {
        return arrDetail_Transaksi;
    }

    public void setArrDetail_Transaksi(ArrayList<Detail_Transaksi> arrDetail_Transaksi) {
        this.arrDetail_Transaksi = arrDetail_Transaksi;
    }

  
}
