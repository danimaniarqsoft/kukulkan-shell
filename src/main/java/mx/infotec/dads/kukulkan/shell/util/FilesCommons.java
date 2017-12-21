package mx.infotec.dads.kukulkan.shell.util;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.CompletionProposal;

import mx.infotec.dads.kukulkan.shell.domain.ShellCompletionProposal;

/**
 * FilesCommon operations
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class FilesCommons {

    private FilesCommons() {
    }

    public static List<CompletionProposal> filterFiles(Path currentPath) {
        List<CompletionProposal> completionProposal = new ArrayList<>();
        try (DirectoryStream<Path> directories = Files.newDirectoryStream(currentPath);) {
            for (Path path : directories) {
                if (!path.toFile().isDirectory()) {
                    completionProposal.add(new ShellCompletionProposal(path.getFileName().toString(), "file"));
                } else {
                    completionProposal.add(new ShellCompletionProposal(path.getFileName().toString(), "directory"));
                }
            }
        } catch (IOException e) {
        }
        return completionProposal;
    }

    public static List<AttributedString> showFiles(Path currentPath) {
        List<AttributedString> fileList = new ArrayList<>();
        fileList.add(new AttributedString(""));
        try (DirectoryStream<Path> directories = Files.newDirectoryStream(currentPath);) {
            for (Path path : directories) {
                if (!path.toFile().isDirectory()) {
                    fileList.add(
                            new AttributedStringBuilder()
                                    .append("f ", AttributedStyle.BOLD.foreground(AttributedStyle.WHITE))
                                    .append(path.getFileName().toString(),
                                            AttributedStyle.BOLD.foreground(AttributedStyle.WHITE))
                                    .toAttributedString());
                } else {
                    fileList.add(
                            new AttributedStringBuilder()
                                    .append("d ", AttributedStyle.BOLD.foreground(AttributedStyle.WHITE))
                                    .append(path.getFileName().toString(),
                                            AttributedStyle.BOLD.foreground(AttributedStyle.BLUE))
                                    .toAttributedString());
                }
            }
        } catch (IOException e) {
        }
        fileList.add(new AttributedString(""));
        return fileList;
    }

    public static List<CompletionProposal> filterDirs(Path currentPath) {
        List<CompletionProposal> completionProposal = new ArrayList<>();
        try (DirectoryStream<Path> directories = Files.newDirectoryStream(currentPath);) {
            for (Path path : directories) {
                if (path.toFile().isDirectory()) {
                    completionProposal.add(new ShellCompletionProposal(path.getFileName().toString()));
                }
            }
        } catch (IOException e) {
        }
        return completionProposal;
    }

    public static boolean hasGitFiles(Path currentPath) {
        try (DirectoryStream<Path> directories = Files.newDirectoryStream(currentPath)) {
            for (Path path : directories) {
                if (path.toFile().isDirectory() && path.toFile().getName().equals(".git")) {
                    return true;
                }
            }
        } catch (IOException e) {
        }
        return false;
    }
}
