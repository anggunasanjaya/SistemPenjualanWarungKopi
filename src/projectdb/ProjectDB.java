package projectdb;

import Database.Koneksi;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import View.Login_View;
import View.OwnerView;
import View.UpdateBarang;
import View.UpdateJenis_Barang;
import View.UpdateMenu;
import View.Transaksi_View;

public class ProjectDB {

    public static void main(String[] args) throws SQLException {
        
        new UpdateMenu().show();
    }
    
}
