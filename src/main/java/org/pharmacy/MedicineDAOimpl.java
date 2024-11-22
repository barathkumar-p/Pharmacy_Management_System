package org.pharmacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAOimpl implements MedicineDAO{
    public void addMedicine(int id,String name, double price,int quantity,String expiry_date){
        String sql ="INSERT INTO medicines(id,name,price,quantity,expiry_date)  VALUES(?,?,?,?,?)";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setDouble(3,price);
            ps.setInt(4,quantity);
            ps.setDate(5,java.sql.Date.valueOf(expiry_date));
            ps.executeUpdate();
            System.out.println("Medicine Added Succesfully");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        String sql = "SELECT * FROM medicines";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Medicine med = new Medicine(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getDate("expiry_date").toLocalDate()
                );
                medicines.add(med);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return medicines;
    }

    public void updateMedicine(int id,String name, double price,int quantity,String expiry_date){

        String sql="UPDATE medicines SET name=?,price=?,quantity=?,expiry_date=? WHERE id=?";

        try(Connection connection=DBConnection.getConnection();
            PreparedStatement ps=connection.prepareStatement(sql)){

            ps.setString(1,name);
            ps.setDouble(2,price);
            ps.setInt(3,quantity);
            ps.setDate(4,java.sql.Date.valueOf(expiry_date));
            ps.setInt(5,id);

            ps.executeUpdate();
            System.out.println("medicines updated succesfully");

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deleteMedicine(int id){
        String sql="DELETE FROM medicines WHERE id=?";
        try (Connection connection=DBConnection.getConnection();
        PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Medicines deleted successfully");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void refreshTable(){
        String sql="DELETE FROM medicines";
        try (Connection connection=DBConnection.getConnection();
             PreparedStatement ps=connection.prepareStatement(sql)){
          //  ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("all Medicines deleted successfully");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void updateMedicineQuantity(int id,int quantity){
        String sql="UPDATE medicines SET quantity=? WHERE id=?";
        try(Connection connection =DBConnection.getConnection();
        PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setInt(1,id);
            ps.setInt(2,quantity);
            ps.executeUpdate();
            System.out.println("Medicine Quantity updated Successfully!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Medicine> searchMedicineByName(String name) throws SQLException {
        List <Medicine> medicine=new ArrayList<>();
        String sql="SELECT * FROM medicines WHERE name ILIKE ?";
        try(Connection connection=DBConnection.getConnection();
        PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Medicine med = new Medicine(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("quantity"),
                            rs.getDate("expiry_date").toLocalDate()
                    );
                    medicine.add(med);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return medicine;
    }

    public List<Medicine> getExpiredMedicine(){
        List<Medicine> medicine=new ArrayList<>();
        String sql="SELECT * FROM medicines WHERE expiry_date < CURRENT_DATE";
        try(Connection connection=DBConnection.getConnection();
        PreparedStatement ps=connection.prepareStatement(sql)){
            try(ResultSet rs=ps.executeQuery()){
                while(rs.next()){
                    Medicine med=new Medicine(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("quantity"),
                            rs.getDate("expiry_date").toLocalDate()
                    );
                    medicine.add(med);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return medicine;
    }

    public List<Medicine> checkQuantity(String name){
        List<Medicine>medicine=new ArrayList<>();
        String sql="SELECT id,name,quantity FROM medicines WHERE name ILIKE ?";
        try(Connection connection=DBConnection.getConnection();
            PreparedStatement ps=connection.prepareStatement(sql)){
            ps.setString(1,"%"+name+"%");
            try(ResultSet rs=ps.executeQuery()){
                while(rs.next()){
                    Medicine med=new Medicine(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("quantity")
                    );
                    medicine.add(med);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return medicine;
    }




}
