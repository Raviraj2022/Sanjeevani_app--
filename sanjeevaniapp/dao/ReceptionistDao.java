
package sanjeevaniapp.dao;

//import dbUtil.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sanjeevaniapp.dbutil.DBConnection;
import sanjeevaniapp.pojo.ReceptionistPojo;


public class ReceptionistDao {
    public static void updateName(String  currName,String newName)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Update receptionists set receptionist_name=? where receptionist_name=?");
        ps.setString(1, newName);
        ps.setString(2, currName);
        ps.executeUpdate();
    }
    public static String getNewRecId()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select max(receptionist_id) from receptionists");
        rs.next();
        int recId=101;
        String id=rs.getString(1);
         if(id!=null)
         {
             String num=id.substring(3);
             recId=Integer.parseInt(num);
             
         }
         return "REC"+recId;
    } 
    public static boolean addReceptionist(ReceptionistPojo rec) throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Insert into receptionists values(?,?,?)");
        ps.setString(1,rec.getReceptionistId());
        ps.setString(2,rec.getReceptionistName());
        ps.setString(3,rec.getGender());
        return ps.executeUpdate()==1;
    }
    public static List<ReceptionistPojo> getAllReceptionistDetails()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select * from receptionists order by receptionist_id");
        List<ReceptionistPojo >recList=new ArrayList<>();
        while(rs.next())
        {
           ReceptionistPojo recep=new ReceptionistPojo();
           recep.setReceptionistId(rs.getString(1));
           recep.setReceptionistName(rs.getString(2));
           recep.setGender(rs.getString(3));
           recList.add(recep);
        }
        return recList;
    }
}
