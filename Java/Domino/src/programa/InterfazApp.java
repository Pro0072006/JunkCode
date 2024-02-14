/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programa;

import java.util.Scanner;

/**
 *
 * @author Santi
 */
public class InterfazApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int esquinaA, esquinaB;
        Domino obj1, obj2;
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el valor de una esquina de la ficha 1: ");
        esquinaA = sc.nextInt();
        System.out.print("Ingrese el valor de la otra esquina de la ficha 1: ");
        esquinaB = sc.nextInt();
        obj1 = new Domino(esquinaA, esquinaB);

        System.out.print("\nIngrese el valor de una esquina de la ficha 2: ");
        esquinaA = sc.nextInt();
        System.out.print("Ingrese el valor de la otra esquina de la ficha 2: ");
        esquinaB = sc.nextInt();
        obj2 = new Domino(esquinaA, esquinaB);

        System.out.println("\nFicha 1: " + obj1.getEsquinaA() + " | " + obj1.getEsquinaB());
        System.out.println("Ficha 2: " + obj2.getEsquinaA() + " | " + obj2.getEsquinaB());

        if (obj1.Emparejar(obj2))
            System.out.println("\nLas fichas se pueden emparejar");
        else
            System.out.println("\nLas fichas no se pueden emparejar");

        while (true) {
            System.out.println("\nIngrese una de las siguientes opciones: "
                    + "\n1. Cambiar el valor de las esquinas de la ficha 1"
                    + "\n2. Cambiar el valor de las esquinas de la ficha 2"
                    + "\n3. Verificar si las fichas se pueden emparejar"
                    + "\n4. Mostrar fichas"
                    + "\n5. Salir");

            switch (sc.nextInt()) {
                case 1:
                    System.out.print("Ingrese el valor de una esquina de la ficha 1: ");
                    esquinaA = sc.nextInt();

                    while (!obj1.setEsquinaA(esquinaA)) {
                        System.out.print("El valor ingresado no es valido, intente de nuevo: ");
                        esquinaA = sc.nextInt();
                    }

                    System.out.print("\nIngrese el valor de la otra esquina de la ficha 1: ");
                    esquinaB = sc.nextInt();

                    while (!obj1.setEsquinaB(esquinaB)) {
                        System.out.print("El valor ingresado no es valido, intente de nuevo: ");
                        esquinaB = sc.nextInt();
                    }

                    System.out.println("\nLas esquinas de la ficha 1 han sido cambiadas exitosamente");

                    break;
                case 2:
                    System.out.print("Ingrese el valor de una esquina de la ficha 2: ");
                    esquinaA = sc.nextInt();

                    while (!obj2.setEsquinaA(esquinaA)) {
                        System.out.print("El valor ingresado no es valido, intente de nuevo: ");
                        esquinaA = sc.nextInt();
                    }

                    System.out.print("Ingrese el valor de la otra esquina de la ficha 2: ");
                    esquinaB = sc.nextInt();

                    while (!obj2.setEsquinaB(esquinaB)) {
                        System.out.print("El valor ingresado no es valido, intente de nuevo: ");
                        esquinaB = sc.nextInt();
                    }

                    System.out.println("\nLas esquinas de la ficha 2 han sido cambiadas exitosamente");

                    break;
                case 3:
                    if (obj1.Emparejar(obj2))
                        System.out.println("\nLas fichas se pueden emparejar");
                    else
                        System.out.println("\nLas fichas no se pueden emparejar");
                    break;
                case 4:
                    System.out.println("\nFicha 1: " + obj1.getEsquinaA() + " | " + obj1.getEsquinaB());
                    System.out.println("Ficha 2: " + obj2.getEsquinaA() + " | " + obj2.getEsquinaB());
                    break;
                case 5:
                    System.exit(0);
                    break;

            }
        }
    }

}
