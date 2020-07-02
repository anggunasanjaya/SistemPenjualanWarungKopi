package Model;

public class Barang {
    
    private Integer id_barang;
    private Jenis_Barang jenis_barang;
    private String nama_barang;
    private Integer harga_barang;
    private Integer stock_barang;

    public Integer getId_barang() {
        return id_barang;
    }

    public void setId_barang(Integer id_barang) {
        this.id_barang = id_barang;
    }

    public Jenis_Barang getJenis_barang() {
        return jenis_barang;
    }

    public void setJenis_barang(Jenis_Barang jenis_barang) {
        this.jenis_barang = jenis_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public Integer getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(Integer harga_barang) {
        this.harga_barang = harga_barang;
    }

    public Integer getStock_barang() {
        return stock_barang;
    }

    public void setStock_barang(Integer stock_barang) {
        this.stock_barang = stock_barang;
    }
}
