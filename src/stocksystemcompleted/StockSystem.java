package stocksystemcompleted;

import domein.IRemoteStockService;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockSystem {

//controller
    private IRemoteStockService stockService;
    private Scanner choose = new Scanner(System.in);

    public static void main(String[] args) {
        new StockSystem().run();
    }
    
    private IRemoteStockService getRemoteService(){
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            return (IRemoteStockService)registry.lookup("stockservice");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void run() {
        //---------------------------------
        stockService = getRemoteService();
                
        System.out.println("RemoteStockSystem client running ...");
        //---------------------------------

        String action = askAction();
        String mes;
        while (!action.equalsIgnoreCase("end")) {
            //---------------------------------
            try {
                
                mes = stockService.executeAction(action.split(" "));
                System.out.print(mes);
                action = askAction();
            } //-----------------------------------------
            catch (RemoteException ex) {
                ex.printStackTrace();
            }
            //----------------------------------------
        }
        choose.close();
    }

    private String askAction() {
        System.out.println("");
        System.out.println("-------------Usage:StockSystem    TaskName Arguments. Enter end to quit------------");
        System.out.println("Commands:");
        System.out.println("   createProduct productname quantity");
        System.out.println("   updateQuantity productname quantity");
        System.out.println("   shipProduct productname");
        System.out.println("   showStock");
        System.out.println("Enter command :");
        return choose.nextLine();
    }

}
