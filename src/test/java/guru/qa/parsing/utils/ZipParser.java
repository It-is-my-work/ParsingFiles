package guru.qa.parsing.utils;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ZipParser {

    ClassLoader classLoader = getClass().getClassLoader();
    String pathToZipFile = "zip/test_data.zip";

    public void checkingZipContents(String fileName, int size, String content) throws Exception {
        boolean presenceOfFile = false;

        try (InputStream is = classLoader.getResourceAsStream(pathToZipFile);
             ZipInputStream zis = new ZipInputStream(Objects.requireNonNull(is))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals(fileName)) {
                    presenceOfFile = true;
                    if (fileName.contains(".pdf")) {
                        checkPdfContent(zis, size, content);
                    }
                    if (fileName.contains(".csv")) {
                        checkCsvContent(zis, size, content);
                    }
                    if (fileName.contains(".xls")) {
                        checkXmlContent(zis, size, content);
                    }
                    break;
                }
            }
            assertThat(presenceOfFile).as("Файл %s не найден", fileName).isTrue();
        }
    }

    public void checkPdfContent(ZipInputStream zis, int size, String content) throws Exception {
        PDF pdf = new PDF(zis);
        assertThat(pdf.numberOfPages).isEqualTo(size);
        assertThat(pdf.text).contains(content);
    }

    public void checkCsvContent(InputStream is, int size, String content) throws Exception {
        try (CSVReader reader = new CSVReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            List<String[]> contents = reader.readAll();
            assertThat(contents.get(1))
                    .hasSize(size)
                    .contains(content);
        }
    }

    public void checkXmlContent(InputStream is, int size, String content) throws Exception {
        XLS xls = new XLS(is);
        assertThat(xls.excel
                .getSheetAt(0)
                .getRow(1)
                .getCell(6)
                .getStringCellValue()).contains(content);
        assertThat(xls.excel).hasSize(size);
    }

}

