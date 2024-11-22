package org.pharmacy;

import java.sql.SQLException;
import java.util.List;

public interface MedicineDAO {

        void addMedicine(int id, String name, double price, int quantity, String expiry_date);

        List<Medicine> getAllMedicines();

        void updateMedicine(int id, String name, double price, int quantity, String expiry_date);

        void deleteMedicine(int id);

        void refreshTable();

        void updateMedicineQuantity(int id, int quantity);

        List<Medicine> searchMedicineByName(String name) throws SQLException;
        List<Medicine> getExpiredMedicine();
        List<Medicine> checkQuantity(String name);

    }

