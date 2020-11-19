/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 *
 * @author Reynell
 */
public class NEDException extends Exception{
    private String entidad, PK;
    private int code;

    public NEDException(int code, String PK) {
        this.PK = PK;
        this.code = code;
        switch ((code/100)*100) {
            case 100:
                entidad = "Cliente";
                break;
            case 200:
                entidad = "MateriaPrima";
                break;
            case 300:
                entidad = "Producto";
                break;
            case 400:
                entidad = "Proveedor";
                break;
            case 500:
                entidad = "Receta";
                break;
            case 600:
                entidad = "Recibo";
                break;
            case 700:
                entidad = "Vendedor";
            default:
                break;
        }
    }
    
    


    




    @Override
    public String toString() {
        return code+":"+entidad+":"+PK; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized Throwable initCause(Throwable thrwbl) {
        return super.initCause(thrwbl); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLocalizedMessage() {
        return super.getLocalizedMessage(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMessage() {
        return "La entidad en la base de datos " + entidad + " no cuenta con un registro con llave primaria " + PK;
    }
    
}
