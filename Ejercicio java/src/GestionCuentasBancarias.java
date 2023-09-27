import java.util.ArrayList;
import java.util.Scanner;

class Cliente {
    // Clase que representa a un cliente del banco

    private String nombre;
    private String apellido;
    private int numeroCliente;

    public Cliente(String nombre, String apellido, int numeroCliente) {
        // Constructor para crear un cliente

        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroCliente = numeroCliente;
    }

    // Getters y setters para los atributos del cliente

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(int numeroCliente) {
        this.numeroCliente = numeroCliente;
    }
}

class CuentaBancaria {
    // Clase base para representar una cuenta bancaria


    protected Cliente cliente;
    protected double saldo;

    public CuentaBancaria(Cliente cliente, double saldoInicial) {
        // Constructor para crear una cuenta bancaria


        this.cliente = cliente;
        this.saldo = saldoInicial;
    }

    public void depositar(double cantidad) {
        // Método para realizar un depósito en la cuenta

        saldo += cantidad;
    }

    public boolean retirar(double cantidad) {
        // Método para realizar un retiro de la cuenta

        if (cantidad <= saldo) {
            saldo -= cantidad;
            return true;
        } else {
            System.out.println("Saldo insuficiente.");
            return false;
        }
    }

    public double getSaldo() {
        // Getter para obtener el saldo de la cuenta

        return saldo;
    }

    public Cliente getCliente() {
        // Getter para obtener el cliente asociado a la cuenta

        return cliente;
    }
}

class CuentaCorriente extends CuentaBancaria {
    // Clase para representar una cuenta corriente, que es una cuenta bancaria

    public CuentaCorriente(Cliente cliente, double saldoInicial) {
        super(cliente, saldoInicial);
    }
}

class CuentaAhorro extends CuentaBancaria {
    // Clase para representar una cuenta de ahorro, que es una cuenta bancaria

    private double tasaDeInteres;
    private double saldoInicial;

    public CuentaAhorro(Cliente cliente, double saldoInicial, double tasaDeInteres) {
        // Constructor para crear una cuenta de ahorro

        super(cliente, saldoInicial);
        this.tasaDeInteres = tasaDeInteres;
        this.saldoInicial = saldoInicial; // Almacenar el saldo inicial
    }

    public void calcularIntereses() {
        // Método para calcular los intereses de la cuenta de ahorro

        double intereses = getSaldo() * tasaDeInteres / 100;
        depositar(intereses);
    }

    public double getSaldoInicial() {
        // Getter para obtener el saldo inicial de la cuenta de ahorro

        return saldoInicial;
    }
}

public class GestionCuentasBancarias {
    // Clase principal que gestiona las cuentas bancarias

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<CuentaCorriente> cuentasCorriente = new ArrayList<>();
        ArrayList<CuentaAhorro> cuentasAhorro = new ArrayList<>();

        while (true) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Agregar nuevo cliente");
            System.out.println("2. Crear cuenta corriente");
            System.out.println("3. Crear cuenta de ahorro");
            System.out.println("4. Realizar operación (Depósito / Retiro)");
            System.out.println("5. Calcular intereses (Cuenta de ahorro)");
            System.out.println("6. Ver cuentas del cliente");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    // Caso 1: Agregar nuevo cliente

                    // Solicitar información del nuevo cliente
                    System.out.print("Nombre del cliente: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Apellido del cliente: ");
                    String apellido = scanner.nextLine();
                    System.out.print("Número de cliente: ");
                    int numeroCliente = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea

                    // Crear un nuevo objeto Cliente y agregarlo a la lista de clientes
                    Cliente nuevoCliente = new Cliente(nombre, apellido, numeroCliente);
                    clientes.add(nuevoCliente);

                    // Informar que el cliente ha sido agregado con éxito
                    System.out.println("Cliente agregado con éxito.");
                    break;

                case 2:
                    // Caso 2: Crear cuenta corriente

                    // Verificar si hay clientes registrados
                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados. Por favor, agregue un cliente primero.");
                    } else {
                        // Solicitar el número de cliente para la cuenta corriente
                        System.out.print("Número de cliente para la cuenta corriente: ");
                        int numeroClienteCorriente = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de línea

                        // Buscar el cliente correspondiente en la lista de clientes
                        Cliente clienteCorriente = buscarClientePorNumero(clientes, numeroClienteCorriente);

                        if (clienteCorriente != null) {
                            // Solicitar el saldo inicial de la cuenta corriente
                            System.out.print("Saldo inicial de la cuenta corriente: ");
                            double saldoInicialCorriente = scanner.nextDouble();
                            scanner.nextLine(); // Consumir el salto de línea

                            // Crear una nueva cuenta corriente y agregarla a la lista de cuentas corrientes
                            CuentaCorriente nuevaCuentaCorriente = new CuentaCorriente(clienteCorriente, saldoInicialCorriente);
                            cuentasCorriente.add(nuevaCuentaCorriente);

                            // Informar que la cuenta corriente ha sido creada con éxito
                            System.out.println("Cuenta corriente creada con éxito.");
                        } else {
                            // Informar que el cliente no ha sido encontrado
                            System.out.println("Cliente no encontrado.");
                        }
                    }
                    break;

                case 3:
                    // Caso 3: Crear cuenta de ahorro

                    // Verificar si hay clientes registrados
                    if (clientes.isEmpty()) {
                        System.out.println("No hay clientes registrados. Por favor, agregue un cliente primero.");
                    } else {
                        // Solicitar el número de cliente para la cuenta de ahorro
                        System.out.print("Número de cliente para la cuenta de ahorro: ");
                        int numeroClienteAhorro = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de línea

                        // Buscar el cliente correspondiente en la lista de clientes
                        Cliente clienteAhorro = buscarClientePorNumero(clientes, numeroClienteAhorro);

                        if (clienteAhorro != null) {
                            // Solicitar el saldo inicial de la cuenta de ahorro
                            System.out.print("Saldo inicial de la cuenta de ahorro: ");
                            double saldoInicialAhorro = scanner.nextDouble();
                            scanner.nextLine(); // Consumir el salto de línea

                            // Solicitar la tasa de interés para la cuenta de ahorro
                            System.out.print("Tasa de interés (%) para la cuenta de ahorro: ");
                            double tasaDeInteres = scanner.nextDouble();
                            scanner.nextLine(); // Consumir el salto de línea

                            // Crear una nueva cuenta de ahorro y agregarla a la lista de cuentas de ahorro
                            CuentaAhorro nuevaCuentaAhorro = new CuentaAhorro(clienteAhorro, saldoInicialAhorro, tasaDeInteres);
                            cuentasAhorro.add(nuevaCuentaAhorro);

                            // Informar que la cuenta de ahorro ha sido creada con éxito
                            System.out.println("Cuenta de ahorro creada con éxito.");
                        } else {
                            // Informar que el cliente no ha sido encontrado
                            System.out.println("Cliente no encontrado.");
                        }
                    }
                    break;

                case 4:
                    // Caso 4: Realizar operación (Depósito / Retiro)
                    break; // Será explicado en detalle a continuación

                case 5:
                    // Caso 5: Calcular intereses (Cuenta de ahorro)
                    break; // Será explicado en detalle a continuación

                case 6:
                    // Caso 6: Ver cuentas del cliente
                    break; // Será explicado en detalle a continuación

                case 7:
                    // Caso 7: Salir del programa

                    // Informar que el programa está saliendo
                    System.out.println("Saliendo del programa.");

                    // Cerrar el scanner y salir del programa
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    // Caso por defecto: Opción no válida

                    // Informar que la opción seleccionada no es válida
                    System.out.println("Opción no válida.");
                    break;
            }

        }
    }

    private static Cliente buscarClientePorNumero(ArrayList<Cliente> clientes, int numeroCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getNumeroCliente() == numeroCliente) {
                return cliente;
            }
        }
        return null;
    }

    private static void realizarOperacion(CuentaBancaria cuenta, Scanner scanner) {
        System.out.println("Seleccione una operación:");
        System.out.println("1. Depósito");
        System.out.println("2. Retiro");
        System.out.print("Opción: ");
        int opcionOperacion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        switch (opcionOperacion) {
            case 1:
                System.out.print("Monto a depositar: ");
                double montoDeposito = scanner.nextDouble();
                scanner.nextLine(); // Consumir el salto de línea
                cuenta.depositar(montoDeposito);
                System.out.println("Depósito realizado con éxito.");
                break;

            case 2:
                System.out.print("Monto a retirar: ");
                double montoRetiro = scanner.nextDouble();
                scanner.nextLine(); // Consumir el salto de línea

                if (cuenta.retirar(montoRetiro)) {
                    System.out.println("Retiro realizado con éxito.");
                }
                break;

            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
}

