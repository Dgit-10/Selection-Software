import java.sql.*;  
import java.util.*;
import java.io.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font; 
public class Q2_DA2 extends Application{ 
public static void main(String args[]){  
launch(args);
}  
   public void start(Stage s) throws Exception{
      ChoiceBox cb=new ChoiceBox();
      cb.getItems().addAll("Chennai","Vellore","AP");
      ChoiceBox cb1=new ChoiceBox();
      cb1.getItems().addAll("CS","IT","Electric");
      GridPane gp=new GridPane();
      Label l=new Label("Campus");
      Label l1=new Label("Programme");
      //cb.setPromptText("Campus");
      //cb1.setPromptText("Programme");
      gp.add(l,0,0);
      gp.add(cb,1,0);
      gp.add(l1,0,1);
      gp.add(cb1,1,1);
      gp.setVgap(10);
      gp.setHgap(10);
      Button b1=new Button("Choose Course");
      gp.add(b1,2,1);
      Label l2=new Label();
      //l2.setTextFill(Color.web("red"));
      gp.add(l2,2,2);
      Button b2=new Button("Register");
      gp.add(b2,3,3);
      b1.setOnAction((event)->{
         try{
            String a=(String)cb.getValue();
            String b=(String)cb1.getValue();
            //System.out.println(a+" "+b);
            Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb","root","Devansh");
            PreparedStatement st=con.prepareStatement("Select Seats from VITCourseAvailability where Campus=(?) and Programme =(?)");
            st.setString(1,a);
            st.setString(2,b);
            ResultSet rs=st.executeQuery();
            while (rs.next())
            {
               String a12=rs.getString("Seats");
               //String a21=rs.getString("TS");
               String a123=" Seats Avaialble: "  + a12;
               l2.setText(a123);
               if(Integer.parseInt(a12)<=0)
               {
                  b2.setDisable(true);
               }
            }
         }
         catch(Exception e)
         {
            System.out.println(e);
         }
      });
      
      b2.setOnAction((event)->{
         Label l3=new Label("Name: ");
         TextField t1=new TextField();
         Label l5=new Label("Last name: ");
         TextField t3=new TextField();
         Label l4=new Label("Phone: ");
         TextField t2=new TextField();
         Label l6=new Label("Email: ");
         TextField t4=new TextField();
         gp.add(l3,4,3);
         gp.add(t1,5,3);
         gp.add(l5,4,4);
         gp.add(t3,5,4);
         gp.add(l4,4,5);
         gp.add(t2,5,5);
         gp.add(l6,4,6);
         gp.add(t4,5,6);

         Button b3=new Button("Enroll");
         gp.add(b3,4,7);
         b3.setOnAction((event1)->{
         try{
            String a=(String)cb.getValue();
            String b=(String)cb1.getValue();
            Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb","root","Devansh");
            PreparedStatement st=con.prepareStatement("Select Seats from VITCourseAvailability where Campus=(?) and Programme =(?)");
            st.setString(1,a);
            st.setString(2,b);
            ResultSet rs=st.executeQuery();
            int sea=0;
            while (rs.next())
            {
               String a12=rs.getString("Seats");
              // String a21=rs.getString("TS");
               sea=Integer.parseInt(a12);
               //sea1=Integer.parseInt(a21);
            }
            System.out.println("Done!!");
            PreparedStatement ps=con.prepareStatement("UPDATE VITCourseAvailability set Seats=(?) where Campus=(?) and Programme=(?)");
            sea-=1;
            String aa=String.valueOf(sea);
            ps.setString(1,aa);
            ps.setString(2,a);
            ps.setString(3,b);
            System.out.println("Done1!!");
            //Name, Last NAme, Phone, Email, Campus Name and Programe
            PreparedStatement ps1=con.prepareStatement("Insert into VITEnrollement2021 values(?,?,?,?,?,?)");
            ps1.setString(1,t1.getText());
            ps1.setString(2,t3.getText());
            ps1.setString(3,t2.getText());
            ps1.setString(4,t4.getText());
            ps1.setString(5,a);
            ps1.setString(6,b);
            int c=ps1.executeUpdate();
            if (c>0)
            {
               System.out.println("Submitted");
               Label l10=new Label("Registeration Success!");
               l10.setFont(new Font("Times New Roman",24));
               gp.add(l10,5,7);
            }
            int c1=ps.executeUpdate();
            if (c1>0)
            {
               System.out.println("Yes");

            }
         }
         catch(Exception e){System.out.println(e);}
         });
      });
      Scene sc=new Scene(gp,700,500);
      s.setScene(sc);
      s.show();
   }
}  

