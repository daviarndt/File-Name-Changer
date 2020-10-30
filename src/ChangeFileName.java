import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Scanner;

public class ChangeFileName {

    public static void main(String[] args) throws Exception {
        System.out.println("FILE NAME CHANGER \n");
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o caminho do diretório de origem: ");
        String caminhoOrigem = sc.nextLine();
        File folderSource = new File(caminhoOrigem);

        if (!folderSource.exists()) {
            throw new Exception("O diretório de origem não existe.");
        }

        File[] listOfFiles = folderSource.listFiles();
        int qtdFiles = listOfFiles.length;

        System.out.println("Digite o caminho do diretório de destino dos arquivos: ");
        String caminhoDestino = sc.nextLine();
        File folderTarget = new File(caminhoDestino);

        boolean directoryCreated;
        if (!folderTarget.exists()) {
            directoryCreated = folderTarget.mkdir();
            if (!directoryCreated)
                throw new Exception("Não foi possível criar o diretório");
        }

        System.out.println("Digite o texto a ser removido dos arquivos: ");
        String pieceToRemove = sc.nextLine();

        String fileName;
        String newFilename;

        System.out.println("Removendo o texto " + pieceToRemove + " dos arquivos...");

        for (int i=0; i<qtdFiles; i++) {

            if (listOfFiles[i].isFile()) {
                File currentFile = listOfFiles[i];

                try (FileOutputStream fileOutputStream = new FileOutputStream(currentFile.getAbsolutePath());
                     FileChannel channel = fileOutputStream.getChannel();
                     FileLock lock = channel.lock()) {
                } catch (Exception e) {
                    e.printStackTrace();
                }

                fileName = listOfFiles[i].getName();

                if (fileName.contains(pieceToRemove)) {
                    newFilename = fileName.replace(pieceToRemove, "");
                    File completeFile = new File(folderTarget + "\\" + newFilename);
                    try {
                        currentFile.renameTo(completeFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("O arquivo " + fileName + " não contém o texto fornecido para remover.");
                }
            }
        }
    }
}
