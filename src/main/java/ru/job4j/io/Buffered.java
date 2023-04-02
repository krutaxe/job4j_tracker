package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Buffered {

    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("data/777");
        Files.createDirectories(dir);

        Path file = Path.of("data/777/test.txt");
        BasicFileAttributeView attributeView = Files.getFileAttributeView(file,
                BasicFileAttributeView.class);
        BasicFileAttributes attributes = attributeView.readAttributes();

        System.out.println("Это обычный файл? " + attributes.isRegularFile());
        System.out.println("Это директория? " + attributes.isDirectory());
        System.out.println("Это символическая ссылка? " + attributes.isSymbolicLink());
        System.out.println("Это не файл, директория или символическая ссылка? "
                + attributes.isOther());
        System.out.println("Дата создания файла: " + attributes.creationTime());
        System.out.println("Размер файла: " + attributes.size());
        System.out.println("Время последнего доступа: " + attributes.lastAccessTime());
        System.out.println("Время последнего изменения: " + attributes.lastModifiedTime());
    }
}
