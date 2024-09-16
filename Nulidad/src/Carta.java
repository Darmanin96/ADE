import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Carta {


        public static void EliminarArchivos(String rutaDirectorio) {
            try {
                File directorio = new File(rutaDirectorio);
                if (!directorio.exists() || !directorio.isDirectory()) {
                    System.out.println("El directorio no existe o no es válido.");
                    return;
                }

                File[] archivos = directorio.listFiles();
                if (archivos != null) {
                    for (File archivo : archivos) {
                        if (archivo.isDirectory()) {
                            EliminarArchivos(archivo.getAbsolutePath());
                        }
                        if (!archivo.delete()) {
                        } else {
                        }
                    }
                }

                if (!directorio.delete()) {
                } else {
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }



    public static void imprimirCarta(String nombre) {
        String archivo = "C:\\Users\\Dani-PC\\Downloads\\Nulidad.txt";
        String carpetas = "C:\\Users\\Dani-PC\\Desktop\\Carpeta\\";

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] usuario = linea.split("\\t+");
                if (usuario.length > 1 && usuario[1].equals(nombre)) {
                    if (!linea.trim().isEmpty()) {
                        String Carpeta = carpetas + usuario[4];
                        File carpeta = new File(Carpeta);
                        if (!carpeta.exists()) {
                            if (carpeta.mkdirs()) {
                                System.out.println("Se ha creado la carpeta: " + Carpeta);
                                String subarchivo = "C:\\Users\\Dani-PC\\Desktop\\Carpeta\\" + usuario[4];
                                CrearCarta(subarchivo, usuario,nombre);
                            } else {
                                System.out.println("Error al crear la carpeta: " + Carpeta);
                            }
                        } else {
                            System.out.println("La carpeta ya existe: " + Carpeta);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void CrearCarta(String subarchivo, String[] usuario, String nombre) {
        try {
            File subarchivo2 = new File(subarchivo + File.separator + usuario[5] + ".txt");
            if (subarchivo2.exists()) {
                System.out.println("El archivo ya existe");
                return;
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(subarchivo2));
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.GERMANY);
            symbols.setGroupingSeparator('.');
            symbols.setDecimalSeparator(',');
            DecimalFormat formato = new DecimalFormat("#,##0.00", symbols);
            if (usuario.length > 8 && !usuario[8].isEmpty()) {
                Set<String> empresa = new HashSet<>();
                empresa.add("Empresa001");
                empresa.add("Empresa002");
                empresa.add("Empresa003");
                empresa.add("Empresa004");
                empresa.add("Empresa005");
                empresa.add("Empresa006");
                empresa.add("Empresa007");
                empresa.add("Empresa008");
                empresa.add("Empresa009");
                empresa.add("Empresa010");
                if (empresa.contains(usuario[1])) {
                    bw.write("Estimado/a: " + usuario[8] + "\n\n");
                    bw.write("Por medio de la presente, le informamos que hemos procedido a la creación de la nulidad " +
                            usuario[5]);
                    bw.write(" Esta acción corresponde al proceso de compensación económica por los servicios prestados por " +
                            "su compañía: " + usuario[1]);
                    bw.write("cuyo monto asciende a: ");
                    double Total = Proveedores.Pagos(nombre);
                    bw.write(formato.format(Total) + "€");
                    bw.write(" A continuación, encontrará el detalle de los pagos correspondientes:\n\n");
                    bw.write("ID de compra: ");
                    bw.write(usuario[4]);
                    bw.write("\n\nLista de pagos: \n\n");
                    bw.write(Proveedores.PagosMostrar(nombre) + "\n\n");
                    bw.write("Es imperativo que nos confirme la recepción de este documento y la aceptación de los términos contenidos en él. Para ello, solicitamos " +
                            "que se comunique con nosotros a través de nuestro correo electrónico darmanindaniel@gmail.com " +
                            "a la mayor brevedad posible.");
                    bw.write("Agradecemos de antemano su colaboración y comprensión.\n" +
                            "\n" +
                            "Atentamente,\n" +
                            "\n" +
                            "Daniel Darmanin Casariego\n" +
                            "Ies Canarias");
                } else {
                    bw.write("¡Hola ");
                    bw.write("! Te escribo para informarte que hemos preparado la nulidad ");
                    bw.write(usuario[5]);
                    bw.write(" para calcular el pago de los servicios de tu empresa, ");
                    bw.write(usuario[1]);
                    bw.write(". El monto total a pagar es de: ");
                    double Total = Proveedores.Pagos(nombre);
                    bw.write(formato.format(Total) + "€");
                    bw.write(". A continuación, te presento el desglose de los pagos:\n\n");
                    bw.write("ID de compra: ");
                    bw.write(usuario[4]);
                    bw.write("\n\nLista de pagos: \n\n");
                    bw.write(Proveedores.PagosMostrar(nombre) + "\n\n");
                    bw.write("Por favor, revisa los detalles y confirma que todo está correcto.");
                    bw.write(" Si todo está bien, envíame un correo a darmanindaniel@gmail.com");
                    bw.write(" para confirmar que has revisado la información y todo está en orden.\n\n¡" +
                            "Gracias y saludos cordiales!\n\nDaniel Darmanin Casariego ");
                }
                bw.close();
                System.out.println("Archivo creado");
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
    }
}
