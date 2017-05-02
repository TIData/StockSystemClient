/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author bram
 */
public interface IRemoteStockService extends Remote{
    String executeAction(String[] args) throws RemoteException;
}
