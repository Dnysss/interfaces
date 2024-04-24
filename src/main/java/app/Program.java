/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import entities.Contract;
import entities.Installment;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import services.ContractService;
import services.PaypalService;

/**
 *
 * @author dnys
 */
public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        System.out.println("Entre com os dados do contrato:");
        System.out.print("Number: ");
        int number = sc.nextInt();
        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.next(), dtf);
        System.out.print("Valor do contrato: ");
        double totalValue = sc.nextDouble();
        
        Contract contract = new Contract(number, date, totalValue);
        
        System.out.print("Entre com o numero de parcelas: ");
        int n = sc.nextInt();
        
        ContractService contractService = new ContractService(new PaypalService());
        
        contractService.processContract(contract, n);
        
        System.out.println();
        System.out.println("Parcelas:");
        for (Installment installment : contract.getInstallments()) {
            System.out.println(installment);
        }
        
        sc.close();
    }
}
