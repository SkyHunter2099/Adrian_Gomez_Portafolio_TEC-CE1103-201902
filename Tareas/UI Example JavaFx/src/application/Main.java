package application;
	

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	public static final String SEPARATOR=";";
	public static final String QUOTE="\"";

    public static void main(String[] args) {    	
        launch(args);
    }

    @SuppressWarnings("unchecked")
	@Override
    public void start(Stage primaryStage) {
        @SuppressWarnings("rawtypes")
		TableView tableView = new TableView();
        TableColumn<String, Person> column1 = new TableColumn<>("First Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<String, Person> column2 = new TableColumn<>("Last Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        
        BufferedReader br = null;
		try {
	         br =new BufferedReader(new FileReader("files/Libro1_ColumnaExtra.csv"));
	         String line = br.readLine();
	         try {
	        	 String [] Archivo = line.split(SEPARATOR);
 	        	 @SuppressWarnings("unused")
				String ArchivoVacio = Archivo[0];
 	         }catch(Exception g){
 	        	System.err.println("ERROR el archivo esta vacio! "+g.getMessage());
 	        	JOptionPane.showMessageDialog(null, "El archivo esta vacio!", "ERROR", JOptionPane.ERROR_MESSAGE); 
 	         }
	         while (null!=line){
	 	         String [] fields = line.split(SEPARATOR);
	 	         System.out.println(Arrays.toString(fields));
	 	            
	 	         fields = removeTrailingQuotes(fields);
	 	         String Nombre = fields[0];
	 	         String Segundo = fields[1];
	 	         try {
	 	        	 int ExcesoColumnas = fields.length;
	 	        	 System.out.println(ExcesoColumnas);
	 	        	 if (ExcesoColumnas==2) {
	 	        		 System.out.println("");
	 	        	 }
	 	        	 else {
	 	        		 System.out.println(fields[ExcesoColumnas]);	 	        
	 	        	 }
	 	         }catch(ArrayIndexOutOfBoundsException f) {
	 	        	 System.err.println("ERROR el archivo tiene mas columnas de las permitidas asi que no se mostrarán los datos fuera de lo permitido!");
	 	        	 JOptionPane.showMessageDialog(null, "El archivo tiene mas columnas de las permitidas asi que no se mostrarán los datos fuera de lo permitido!", "ERROR", JOptionPane.ERROR_MESSAGE); 
	 	         }
	 	         tableView.getItems().add(new Person(Nombre, Segundo));
	 	         System.out.println(Arrays.toString(fields));
	 	         line = br.readLine();   
	         }
	         
	      } catch (Exception e) {
	         System.err.println("Error! "+e.getMessage());
	         
	      } finally {
	         if (null!=br){
	            try {
	               br.close();
	            } catch (IOException e) {
	               System.err.println("Error closing file !! "+e.getMessage());
	            }
	         }
	      }
        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private static String[] removeTrailingQuotes(String[] fields) {
	      String result[] = new String[fields.length];
	      for (int i=0;i<result.length;i++){
	         result[i] = fields[i].replaceAll("^"+QUOTE,"").replaceAll(QUOTE+"$","");
	      }
	      return result;
	   }
}
